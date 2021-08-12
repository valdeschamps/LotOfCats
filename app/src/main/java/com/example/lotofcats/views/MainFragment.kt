package com.example.lotofcats.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lotofcats.adapter.CatAdapter
import com.example.lotofcats.databinding.MainFragmentBinding
import com.example.lotofcats.viewmodels.MainViewModel

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val recyclerAdapter: CatAdapter by lazy { CatAdapter() }
    private var dataSize = 0
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding.recyclerViewMain.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = recyclerAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val gridManager = recyclerView.layoutManager as GridLayoutManager
                    if (gridManager.findLastVisibleItemPosition() == dataSize - 1
                    ) {
                        loadMoreData()
                    }
                }
            })
            doOnPreDraw {
                startPostponedEnterTransition()
            }
        }

        viewModel.catList.observe(viewLifecycleOwner, Observer  {catList ->
            dataSize = catList.size
            recyclerAdapter.setData(ArrayList(catList))
            recyclerAdapter.notifyItemRangeInserted(
                dataSize - viewModel.catLimit,
                viewModel.catLimit
            )
            isLoading = false
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })

        loadMoreData()
    }

    private fun loadMoreData() {
        if (!isLoading) {
            isLoading = true
            viewModel.fetchData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
