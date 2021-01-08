package com.prashD.quizzio.views.adapters

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prashD.quizzio.databinding.ItemTriviaListBinding
import com.prashD.quizzio.utils.AppUtils
import com.prashD.quizzio.utils.ResourceUtils
import com.prashD.quizzio.views.listeners.RecyclerItemClickListener
import com.prashD.quizzio.views.ui.TriviaUI

class TriviaListAdapter(var listener: RecyclerItemClickListener) :
    ListAdapter<TriviaUI, TriviaListAdapter.TriviaListItemViewHolder>(TriviaItemDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaListItemViewHolder {
        return TriviaListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TriviaListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.setIsRecyclable(false)
        holder.bind(item, listener)
    }


    class TriviaListItemViewHolder(val binding: ItemTriviaListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TriviaUI, listener: RecyclerItemClickListener) {
            binding.trivia = item
            val background = binding.cvMain.background as GradientDrawable
            background.setColor(ResourceUtils.toColor(AppUtils.getColorIdFromCategoryType(item.category)))
            binding.cvMain.tag = item
            binding.cvMain.setOnClickListener {
                listener.onItemClick(it)
            }
        }

        companion object {
            fun from(parent: ViewGroup): TriviaListItemViewHolder {
                var view = LayoutInflater.from(parent.context)
                val binding = ItemTriviaListBinding.inflate(view, parent, false)
                return TriviaListItemViewHolder(binding)
            }
        }
    }
}

class TriviaItemDiff : DiffUtil.ItemCallback<TriviaUI>() {
    override fun areItemsTheSame(oldItem: TriviaUI, newItem: TriviaUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TriviaUI, newItem: TriviaUI) =
        oldItem == newItem

}