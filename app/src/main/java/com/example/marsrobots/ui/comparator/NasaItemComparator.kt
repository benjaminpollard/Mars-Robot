package com.example.marsrobots.ui.comparator

import androidx.recyclerview.widget.DiffUtil
import com.example.marsrobots.models.NasaItemModel

object NasaItemComparator : DiffUtil.ItemCallback<NasaItemModel>() {
    override fun areItemsTheSame(oldItem: NasaItemModel, newItem: NasaItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NasaItemModel, newItem: NasaItemModel): Boolean {
        return oldItem.dateCreated == newItem.dateCreated &&
                oldItem.description == newItem.description &&
                oldItem.image == newItem.image
    }
}