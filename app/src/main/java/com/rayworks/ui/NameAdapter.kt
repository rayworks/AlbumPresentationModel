package com.rayworks.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rayworks.albumpresentationmodel.R

class NameAdapter(var items: List<String>) : RecyclerView.Adapter<TitleViewHolder>() {
    var itemSelectionChangeListener: ItemSelectionChangeListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TitleViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_text_item, viewGroup, false)
        return TitleViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: TitleViewHolder, position: Int) {
        viewHolder.textView.text = items[position]
        viewHolder.setClickActionListener(View.OnClickListener { v: View? ->
            run {
                viewHolder.textView.text = items[position]

                itemSelectionChangeListener?.let {
                    it.onItemSelected(position)
                }
            }
        })
    }
}