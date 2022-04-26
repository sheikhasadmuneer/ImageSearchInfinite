package com.example.testinglibs.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.testinglibs.R
import com.example.testinglibs.adapters.FlickrLoadStateAdapter
import com.example.testinglibs.adapters.FlickrPhotoAdapter
import com.example.testinglibs.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.contracts.contract


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        val adapter = FlickrPhotoAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = FlickrLoadStateAdapter { adapter.retry() },
                footer = FlickrLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }


            btnSearch.setOnClickListener { // search query..

                if (!TextUtils.isEmpty(edit_query.text.toString())) {
                    viewModel.photos.observe(viewLifecycleOwner) {
                        adapter.submitData(viewLifecycleOwner.lifecycle, it)
                    }
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchPhotos(edit_query.text.toString())
//                }

                }
            }


            adapter.addLoadStateListener { loadState ->
                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                    buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                    textViewError.isVisible = loadState.source.refresh is LoadState.Error

                    // empty view
                    if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1
                    ) {
                        recyclerView.isVisible = false
                        textViewEmpty.isVisible = true
                    } else {
                        textViewEmpty.isVisible = false
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}