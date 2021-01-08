package com.prashD.quizzio.views.adapters

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prashD.quizzio.databinding.ItemFavListBinding
import com.prashD.quizzio.utils.AppUtils
import com.prashD.quizzio.utils.ResourceUtils
import com.prashD.quizzio.views.listeners.RecyclerItemClickListener
import com.prashD.quizzio.views.ui.TriviaUI

class FavoriteListAdapter(var listener: RecyclerItemClickListener) :
    ListAdapter<TriviaUI, FavoriteListAdapter.FavoriteListItemViewHolder>(FavoriteItemDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListItemViewHolder {
        return FavoriteListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoriteListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.setIsRecyclable(false)
        holder.bind(item, listener)
    }

    class FavoriteListItemViewHolder(val binding: ItemFavListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TriviaUI, listener: RecyclerItemClickListener) {
            binding.trivia = item
            val background = binding.cvMainFav.background as GradientDrawable
            background.setColor(ResourceUtils.toColor(AppUtils.getColorIdFromCategoryType(item.category)))
            binding.cvMainFav.tag = item
            binding.cvMainFav.setOnClickListener {
                listener.onItemClick(it)
            }
        }

        companion object {
            fun from(parent: ViewGroup): FavoriteListItemViewHolder {
                var view = LayoutInflater.from(parent.context)
                val binding = ItemFavListBinding.inflate(view, parent, false)
                return FavoriteListItemViewHolder(binding)
            }
        }
    }
}

class FavoriteItemDiff : DiffUtil.ItemCallback<TriviaUI>() {
    override fun areItemsTheSame(oldItem: TriviaUI, newItem: TriviaUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TriviaUI, newItem: TriviaUI) =
        oldItem == newItem

}