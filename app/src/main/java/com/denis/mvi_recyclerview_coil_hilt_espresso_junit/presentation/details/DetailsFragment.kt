package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.R
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.details_fragment) {
    private var itemId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemId = requireArguments().getInt("id")
        val albumId = requireArguments().getInt("albumId")
        val url = requireArguments().getString("url")
        val title = requireArguments().getString("title")
        lifecycleScope.launchWhenStarted {
            DetailsFragmentBinding.bind(view).apply {
                pictureIv.load(url) {
                    scale(Scale.FILL)
                    if (view.rootView.measuredWidth > 0) size(
                        view.rootView.measuredWidth,
                        view.rootView.measuredWidth
                    )
                    placeholder(R.drawable.palette)
                    fallback(R.drawable.palette)
                    transformations(RoundedCornersTransformation(20f, 20f, 20f, 20f))
                }
                titleTv.text = title
                itemNumberTv.text =
                    view.context.getString(R.string.item_number, itemId.toString())
                albumNumberTv.text =
                    view.context.getString(R.string.album_number, albumId.toString())
            }
        }
    }
}