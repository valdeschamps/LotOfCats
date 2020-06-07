package com.example.lotofcats.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
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
        }

        viewModel.string.observe(viewLifecycleOwner) {
            Log.d("test", "result = $it")
            recyclerAdapter.setData(ArrayList(it))
            recyclerAdapter.notifyDataSetChanged()
        }
    }
}
