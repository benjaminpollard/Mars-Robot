package com.example.marsrobots.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsrobots.databinding.ItemCardBinding
import com.example.marsrobots.models.NasaItemModel
import com.example.marsrobots.ui.comparator.NasaItemComparator

class NasaAdapter :
    PagingDataAdapter<NasaItemModel, NasaAdapter.NasaViewHolder>(NasaItemComparator), IRefreshAdaptor {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NasaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
        val binding = holder.binding
        getItem(position)?.apply {
            Glide.with(binding.cardImage.context).load(image).into(binding.cardImage)
            binding.dateCreated.text = dateCreated
            binding.description.text = description
        }
    }

    class NasaViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

}