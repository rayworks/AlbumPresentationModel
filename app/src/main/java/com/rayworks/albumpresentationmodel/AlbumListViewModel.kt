package com.rayworks.albumpresentationmodel

import android.databinding.Observable
import android.databinding.ObservableField
import com.rayworks.album.Album
import com.rayworks.album.AlbumDelegate

class AlbumListViewModel {
    var albums: List<Album>
    var albumForm: AlbumDetailViewModel = AlbumDetailViewModel()

    var windowTitle: ObservableField<String> = ObservableField("My Albums")
    var saveWarning: String? = null

    var selectedAlbum: Album? = null
    var previousSelectedAlbum: Album? = null

    init {
        val delegate: AlbumDelegate = AlbumDelegate()
        albums = delegate.getAlbums()

        albums = albums.sortedBy { album: Album -> album.title }.toList()

        albumForm.albumUpdatedNow.addOnPropertyChangedCallback(
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (albumForm.albumUpdatedNow.get()) {
                        windowTitle.set(albumForm.album?.title)
                    }
                }
            }
        )
    }

    fun changeSelectedAlbum(album: Album?) {
        if (album != null) {
            previousSelectedAlbum = selectedAlbum
            selectedAlbum = album

            if (albumForm.changesToSave()) {
                saveWarning = "Do you want to abandon your changes?"
            } else {
                confirmChangeSelectedAlbum()
            }
        } else {
            windowTitle.set("My Albums")
        }
    }

    fun confirmChangeSelectedAlbum() {
        resetSaveWarning()
        albumForm.album = selectedAlbum;
        windowTitle.set(selectedAlbum?.title!!)
    }

    fun cancelChangeSelectedAlbum() {
        selectedAlbum = previousSelectedAlbum
        resetSaveWarning();
    }

    private fun resetSaveWarning() {
        saveWarning = null
    }
}