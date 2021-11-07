package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList

import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.paging.PagingDataAdapter
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList.innerItem.ItemViewHolder
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@InternalCoroutinesApi
@Singleton
class ItemsListAdapter @Inject constructor() : PagingDataAdapter<PresentationData, ItemViewHolder>(DiffCallback()) {

    private lateinit var clickFlowCollector: (item: PresentationData, direction: NavDirections) -> Unit

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent, clickFlowCollector)
    }

    fun setClickCollector(clickCollector: (item: PresentationData, direction: NavDirections) -> Unit) {
        clickFlowCollector = clickCollector
    }
}