package com.example.quizzio.views.adapters

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzio.R
import com.example.quizzio.utils.AppUtils
import com.example.quizzio.utils.ResourceUtils
import com.example.quizzio.views.listeners.RecyclerItemClickListener
import com.example.quizzio.views.ui.TriviaUI
import kotlinx.android.synthetic.main.item_trivia_list.view.*

class TriviaListAdapter(var listener: RecyclerItemClickListener):
    ListAdapter<TriviaUI, TriviaListAdapter.TriviaListItemViewHolder>(TriviaItemDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaListItemViewHolder {
        return TriviaListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TriviaListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.setIsRecyclable(false)
        holder.bind(item, listener)
    }


    class TriviaListItemViewHolder private constructor(val view: View):RecyclerView.ViewHolder(view) {
        fun bind(item:TriviaUI, listener: RecyclerItemClickListener) {
            view.tv_question.text = item.question
            view.tv_category.text = item.category

            val background = view.cv_main.background as GradientDrawable
            background.setColor(ResourceUtils.toColor(AppUtils.getColorIdFromCategoryType(item.category)))
            view.cv_main.tag = item
            view.cv_main.setOnClickListener {
                listener.onItemClick(it)
            }
        }

        companion object {
            fun from(parent: ViewGroup): TriviaListItemViewHolder {
                var view =LayoutInflater.from(parent.context).inflate(R.layout.item_trivia_list,parent,false)
                return TriviaListItemViewHolder(view)
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