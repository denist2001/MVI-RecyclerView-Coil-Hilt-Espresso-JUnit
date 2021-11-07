package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.R
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class ItemsFragment : Fragment(R.layout.items_fragment) {

    @Inject
    lateinit var itemsListAdapter: ItemsListAdapter

    private lateinit var binding: ItemsFragmentBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val viewModel by viewModels<ItemsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemsFragmentBinding.bind(view)
        setupRecyclerView()
        if (savedInstanceState == null) {
            startObserving()
        }
        viewModel.presentationData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                itemsListAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        itemsListAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        linearLayoutManager = LinearLayoutManager(context)
        with(binding.itemsListRv) {
            adapter = itemsListAdapter
            layoutManager = linearLayoutManager
        }
        itemsListAdapter.setClickCollector { _, direction ->
            findNavController().navigate(direction)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            itemsListAdapter.loadStateFlow.collectLatest { loadState ->
                binding.progressPb.visibility = when (loadState.refresh) {
                    is LoadState.Loading -> View.VISIBLE
                    is LoadState.NotLoading -> View.INVISIBLE
                    is LoadState.Error -> View.INVISIBLE
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            itemsListAdapter.loadStateFlow.collectLatest { loadState ->
                val errorMessage = (loadState.refresh as? LoadState.Error)?.error?.message
                if (!errorMessage.isNullOrBlank()) {
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun startObserving() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.startLoading()
        }
    }
}