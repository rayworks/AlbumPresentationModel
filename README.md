# AlbumPresentationModel

Port the [Presentation Model Example](http://blogs.adobe.com/paulw/archives/2007/10/presentation_pa_3.html#comments) to Android platform

## Updated note
### Implementation Notes

The Presentation Model pattern attempts to extract non-graphical
presentation logic out of view classes and into model classes. The
resulting presentation model classes are abstractions of the user
interface and are decoupled from view components. Each view observes the
corresponding presentation model object, allowing screen updates to
occur in response to changes in the state of the model.

The key classes are as follows:

`MainActivity.kt` - this is the root of the application and is only
responsible for 'bootstrapping' usage such as instantiating the root
view component and the root presentation model object.

`AlbumListViewModel.kt` - this is the presentation model for the root
view. It encapsulates the behaviour and state of the album browser view.
 This component instantiates and collaborates with an instance of the
`AlbumDetailViewModel` class.

`AlbumDetailViewModel.kt` - this is the presentation model for the album
 form view. It encapsulates the behaviour and state of the album form
 view.

### Dependency Injection

The dependency injection technique is used to hook-up the view classes
with their corresponding presentation models. One nice side-effect of
this is the ability to reset the view by injecting a new presentation
model. This only works because presentation state has been extracted
into the model.

### Observer Pattern

The views use the observer pattern to detect and reflect changes in the
model. This is achieved easily with `bindings`.
Validation in a presentation model application the validation logic
belongs in the model class.


### Graphical logic stays in the view

As mentioned in the blog entry, logic that is strongly related to
graphics and rendering does remain in the views. In this example, the
construction of the 'Abandon Changes' alert box is handled by the Main
View. However, the displaying and hiding of this alert box is still
driven by state changes in the model.

### Model encapsulation

I've deviated a little from Martin Fowler's description, by allowing the
view components to bind directly to value objects. In his example,
Fowler copies information from the underlying data model into the
presentation model for display by the view.

### New VO methods

In the Autonomous View and Supervising Presenter patterns, the user's
edited data is stored in the UI controls themselves, prior to clicking
`'Apply'`. In the Presentation Model, I've moved state out of the view and
into the model and can no longer make use of UI controls for storing
edited data. So I need some place to put this data while the user is
making changes and I decided to use another instance of the Album value
object for this. To support this, I rely on the
method : `copy()` which is the built-in feature in `kotlin`.

### About the Demo

The demo application is based on Martin Fowler's [album browser example](http://martinfowler.com/eaaDev/OrganizingPresentations.html#runningExample).
I've added some very basic input validation to the controls, and a
warning pop-up when a user navigates to a new album without saving.

A fake delegate class is used for 'retrieving' the albums. I haven't
attempted to load the remote albums asynchronously, so the delegate is
too simplistic to be referred to as a mock or stub class.

A collection of value objects represents the list of albums.

## TODO
- [ ] Add test cases
- [ ] Improve the refresh mechanism for Album list
- [ ] Support displaying the error msg when editing the specified field
on the fly
