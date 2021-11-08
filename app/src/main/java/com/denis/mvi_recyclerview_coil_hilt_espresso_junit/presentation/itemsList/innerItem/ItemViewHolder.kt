package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList.innerItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.R
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.databinding.InnerItemBinding
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList.ItemsFragmentDirections
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.utils.clicks
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.utils.throttleFirst
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ItemViewHolder(
    itemView: View,
    private val clickFlowCollector: ((direction: NavDirections) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {

    private var lastTime = 0L

    companion object {
        fun create(
            parent: ViewGroup,
            clickFlowCollector: ((direction: NavDirections) -> Unit)?
        ): ItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.inner_item, parent, false)
            return ItemViewHolder(view, clickFlowCollector)
        }
    }

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    fun bind(item: PresentationData?) = with(itemView) {
        val binding = InnerItemBinding.bind(itemView)
        if (item == null) return@with
        binding.pictureIv.load(item.thumbnailUrl) {
            scale(Scale.FIT)
            placeholder(R.drawable.palette)
            fallback(R.drawable.palette)
            transformations(RoundedCornersTransformation(10f, 10f, 10f, 10f))
        }
        binding.titleTv.text = item.title
        binding.itemNumberTv.text =
            itemView.context.getString(R.string.item_number, item.id.toString())
        binding.albumNumberTv.text =
            itemView.context.getString(R.string.album_number, item.albumId.toString())

        clicks().throttleFirst(1000L).onEach {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= 1000) {
                lastTime = currentTime
                val direction = ItemsFragmentDirections.actionItemsFragmentToDetailsFragment(
                    id = item.id,
                    albumId = item.albumId,
                    title = item.title,
                    url = item.url
                )
                clickFlowCollector?.let { collector -> collector(direction) }
            }
        }.launchIn(MainScope())
    }
}