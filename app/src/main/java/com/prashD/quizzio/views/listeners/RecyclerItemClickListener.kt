package com.prashD.quizzio.views.listeners

import android.view.View

open class RecyclerItemClickListener(val clickListener:(view: View)->Unit){
    fun onItemClick(view: View) = clickListener(view)
}
