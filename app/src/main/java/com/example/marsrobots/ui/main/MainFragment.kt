package com.example.marsrobots.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marsrobots.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var adapter: NasaAdapter? = null
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater).apply {

            adapter = NasaAdapter()
            list.layoutManager = GridLayoutManager(this@MainFragment.requireContext(),2)
            list.adapter = adapter?.withLoadStateFooter(LoadingAdapter(adapter!!))

            swiperefresh.setOnRefreshListener {
                adapter?.refresh()
            }
        }

        setUpObservers()

        return binding.root
    }

    @ExperimentalPagingApi
    private fun setUpObservers() {

        viewModel.apply {

            viewModel.itemsLiveData.observe(viewLifecycleOwner) {
                binding.loading.isVisible = false
                binding.swiperefresh.isRefreshing = false
                lifecycleScope.launch {
                    adapter?.submitData(it)
                }
            }
        }
    }

}