package com.example.myrecipes.util

import androidx.recyclerview.widget.DiffUtil
// will take two params: old list and new list
// we use T as generic because we use this diff util for recipes adapter and for ingredients adapter
class RecipesDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
    // if represent the same item in new and old
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }
    // checks if two items have the same data - context the same (same ui)2
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}