package com.example.lotofcats.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lotofcats.R
import com.example.lotofcats.adapter.CatAdapter
import com.example.lotofcats.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private val recyclerAdapter: CatAdapter by lazy { CatAdapter() }
    private var dataSize = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewMain.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = recyclerAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val gridManager = recyclerView.layoutManager as GridLayoutManager
                    if (gridManager.findLastVisibleItemPosition() == dataSize-1
                    ) {
                        loadMoreData()
                    }
                }
            })
        }
        loadMoreData()
    }

    private fun loadMoreData(){
        viewModel.fetchData().observe(viewLifecycleOwner){
            dataSize = it.size
            recyclerAdapter.setData(ArrayList(it))
            recyclerAdapter.notifyDataSetChanged()
            //todo maybe use other notify
        }
    }
}
