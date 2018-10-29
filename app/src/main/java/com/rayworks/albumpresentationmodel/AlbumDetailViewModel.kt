package com.rayworks.albumpresentationmodel

import android.databinding.ObservableBoolean
import com.rayworks.album.Album

class AlbumDetailViewModel {

    private val ARTIST_ERROR: String = "Please enter an artist";
    private val TITLE_ERROR: String = "Please enter an album title";
    private val COMPOSER_ERROR: String = "Please enter a composer";

    var canEdit: Boolean = false;
    var canEditComposer: Boolean = false;

    var artistError: String = ""
    var titleError: String = ""
    var composerError: String = ""

    var cancellable: ObservableBoolean = ObservableBoolean(false)
    var canCancel: Boolean = false;

    var canApply: Boolean = false;
    var appliable: ObservableBoolean = ObservableBoolean(false);

    private var artistValid: Boolean = false;
    private var titleValid: Boolean = false;
    private var composerValid: Boolean = false;

    private var _albumToUpdate: Album? = null

    // propagate the title change event
    var albumUpdatedNow: ObservableBoolean = ObservableBoolean(false)

    var album: Album? = null
        set(value) {
            artistError = ""
            titleError = ""
            composerError = ""

            if (value != null) {

                field = value.copy()
                _albumToUpdate = value;
                canEdit = true;
                canEditComposer = value.isClassical
                albumUpdated();

                albumUpdatedNow.set(false)
            } else {
                field = null
                _albumToUpdate = null;
                canEdit = false;
                canEditComposer = false;
            }
        }

    fun applyChanges() {
        _albumToUpdate?.apply {
            artist = album?.artist!!
            title = album?.title!!
            composer = album?.composer!!
            isClassical = album?.isClassical!!

            albumUpdated()

            albumUpdatedNow.set(true)
        }
    }

    fun updateArtist(artist: String) {
        album?.artist = artist
        albumUpdated()
        artistError = setErrorString(artistValid, ARTIST_ERROR)
    }

    fun updateTitle(title: String) {
        album?.title = title
        albumUpdated()
        titleError = setErrorString(titleValid, TITLE_ERROR)
    }

    fun updateIsClassical(isClassical: Boolean) {
        album?.isClassical = isClassical
        canEditComposer = album?.isClassical!!
        if (!isClassical) {
            album?.composer = ""
            composerValid = true
            composerError = ""
        }

        albumUpdated()
    }

    fun updateComposer(composer: String) {
        album?.composer = composer
        albumUpdated()
        composerError = setErrorString(composerValid, COMPOSER_ERROR)
    }

    fun cancelChanges() {
        album = _albumToUpdate?.copy()
    }

    fun changesToSave() = canCancel

    private fun albumUpdated() {
        validateAlbum()
    }

    private fun validateAlbum() {
        artistValid = album?.artist != ""
        titleValid = album?.title != ""
        composerValid = (album?.isClassical == false) ||
            (album?.isClassical == true && album?.composer != "");
        canCancel = album != _albumToUpdate
        canApply = canCancel && artistValid && titleValid && composerValid;

        cancellable.set(canCancel)
        appliable.set(canApply)
    }

    private fun setErrorString(valid: Boolean, errorString: String): String =
        if (valid) {
            ""
        } else {
            errorString
        }
}
