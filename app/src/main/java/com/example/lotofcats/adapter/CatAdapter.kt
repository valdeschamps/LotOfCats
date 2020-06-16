package com.example.lotofcats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.lotofcats.R
import com.example.lotofcats.model.Cat
import com.example.lotofcats.views.MainFragmentDirections
import kotlinx.android.synthetic.main.card.view.*

class CatAdapter : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {
    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun displayData(newCat: Cat) {
            ViewCompat.setTransitionName(itemView.imageView, newCat.url)

            itemView.imageView.setOnClickListener { view ->
                val url = newCat.url
                val extra = FragmentNavigatorExtras(
                    itemView.imageView to url
                )
                val action = MainFragmentDirections.actionMainFragmentToImageFragment(url)
                view.findNavController().navigate(action, extra)
            }

            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(itemView).load(newCat.url).apply(requestOptions).into(itemView.imageView)
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