package com.example.lotofcats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lotofcats.R
import com.example.lotofcats.model.Cat
import com.example.lotofcats.views.MainFragment
import com.example.lotofcats.views.MainFragmentDirections
import kotlinx.android.synthetic.main.card.view.*

class CatAdapter() :
    RecyclerView.Adapter<CatAdapter.CatViewHolder>() {
    inner class CatViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var cat = Cat()

        init {
            itemView.imageView.setOnClickListener {view ->
                val action = MainFragmentDirections.actionMainFragmentToImageFragment()
                view.findNavController().navigate(action)
            }
        }

        fun displayData(newCat: Cat) {
            cat = newCat
            Glide.with(itemView).load(cat.url).into(itemView.imageView)
        }
    }

    private var catList: ArrayList<Cat> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.displayData(catList[position])
    }

    fun setData(newCatList: ArrayList<Cat>) {
        catList = newCatList
    }
}