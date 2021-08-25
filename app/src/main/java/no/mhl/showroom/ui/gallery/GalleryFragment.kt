package no.mhl.showroom.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import no.mhl.showroom.R
import no.mhl.showroom.data.provideGalleryData
import no.mhl.showroom.ui.Showroom

private const val KEY_GALLERY_INDEX = "KEY_GALLERY_INDEX"

class GalleryFragment : Fragment() {

    // region View Properties
    private lateinit var showroom: Showroom
    // endregion

    // region View Initialisation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        val startIndex = savedInstanceState?.getInt(KEY_GALLERY_INDEX, 0) ?: 0
        setupView(view, startIndex)

        return view
    }
    // endregion

    // region View Setup
    private fun setupView(view: View, startIndex: Int) {
        showroom = view.findViewById(R.id.showroom)

        setupShowroom(startIndex)
    }

    private fun setupShowroom(startIndex: Int) {
        showroom.attach(activity as AppCompatActivity, provideGalleryData(), startIndex)
        showroom.setNavigationExitEvent { findNavController().popBackStack()  }
    }
    // endregion

    // region On Destroy
    override fun onDestroy() {
        super.onDestroy()
        showroom.restoreWindowPreGallery()
    }
    // endregion

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_GALLERY_INDEX, showroom.currentPosition)
        super.onSaveInstanceState(outState)
    }
}