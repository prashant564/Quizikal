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
import com.example.quizzio.views.ui.CategoryItem
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryItemAdapter(var listener: RecyclerItemClickListener) :
    ListAdapter<CategoryItem, CategoryItemAdapter.CategoryItemViewHolder>(CategoryItemDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }


    class CategoryItemViewHolder private constructor(val view: View):RecyclerView.ViewHolder(view) {
        fun bind(categoryItem: CategoryItem, listener: RecyclerItemClickListener) {

            view.tv_title.text = categoryItem.title
            view.iv_icon.setImageDrawable(ResourceUtils.toDrawable(AppUtils.getDrawableIdFromCategoryType(categoryItem.itemType)))
            val background = view.cv_main.background as GradientDrawable
            background.setColor(ResourceUtils.toColor(categoryItem.itemType.color))

            view.cv_main.tag = categoryItem
            view.cv_main.setOnClickListener {
                listener.onItemClick(it)
            }
        }

        companion object {
            fun from(parent: ViewGroup): CategoryItemViewHolder {
                var view =LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
                return CategoryItemViewHolder(view)
            }
        }
    }
}

class CategoryItemDiff : DiffUtil.ItemCallback<CategoryItem>() {
    override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem) =
        oldItem == newItem

}