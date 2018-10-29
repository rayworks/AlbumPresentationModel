package com.rayworks.ui

import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.rayworks.album.Album
import com.rayworks.albumpresentationmodel.AlbumDetailViewModel
import com.rayworks.albumpresentationmodel.AlbumListViewModel
import com.rayworks.albumpresentationmodel.R
import kotlinx.android.synthetic.main.activity_main.recycler_view

class MainActivity : AppCompatActivity() {

    val listViewModel = AlbumListViewModel()

    private var lastPosition: Int = -1;

    private lateinit var viewModel: AlbumDetailViewModel

    private inline val detailFragment: DetailFragment
        get() {
            val fragment: DetailFragment =
                supportFragmentManager.findFragmentById(R.id.detail) as DetailFragment
            return fragment
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val albums = listViewModel.albums;
        val toList = albums.map { album: Album -> album.title }.toList()

        val nameAdapter = NameAdapter(toList)
        setupItemListener(nameAdapter)

        recycler_view.adapter = nameAdapter
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.setHasFixedSize(true)

        viewModel = listViewModel.albumForm

        lastPosition = 0
        val albumOne = listViewModel.albums[0]
        listViewModel.changeSelectedAlbum(albumOne)

        detailFragment.bind(viewModel)

        listViewModel.windowTitle.addOnPropertyChangedCallback(
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    this@MainActivity.title = listViewModel.windowTitle.get()

                    // TODO: Get rid of the full update
                    var len = nameAdapter.items.size
                    nameAdapter.items = albums.map { album: Album -> album.title }.toList()
                    nameAdapter.notifyItemRangeChanged(0, len - 1)
                }
            }
        )
    }

    private fun setupItemListener(nameAdapter: NameAdapter) {
        nameAdapter.itemSelectionChangeListener = object : MyListener() {

            override fun onItemSelected(pos: Int) {

                val album = listViewModel.albums[pos]
                listViewModel.changeSelectedAlbum(album)

                if (lastPosition != pos && lastPosition >= 0) {

                    if (viewModel.canApply || viewModel.canCancel) {
                        AlertDialog.Builder(this@MainActivity)
                            .setMessage(getString(R.string.change_confirmed_msg))
                            .setTitle(getString(R.string.confirm_action))
                            .setPositiveButton(
                                getString(R.string.yes)
                            ) { dialog, which ->
                                run {
                                    confirmAndUpdate()
                                }
                            }
                            .setNegativeButton(
                                getString(R.string.no)
                            ) { dialog, which ->
                                kotlin.run {
                                    listViewModel.cancelChangeSelectedAlbum();
                                    detailFragment.bind(viewModel)
                                }
                            }
                            .create().show()
                    } else {
                        confirmAndUpdate()
                    }
                } else {
                    confirmAndUpdate()
                }
            }
        }
    }

    private fun confirmAndUpdate() {
        listViewModel.confirmChangeSelectedAlbum()
        detailFragment.bind(viewModel)
    }

    open class MyListener() : ItemSelectionChangeListener {
        override fun onItemSelected(pos: Int) {
        }
    }
}
