package com.rayworks.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.rayworks.albumpresentationmodel.R

class TitleViewHolder : RecyclerView.ViewHolder {
    var textView: TextView;

    constructor(itemView: View) : super(itemView) {
        textView = itemView.findViewById(R.id.text_view)
        textView.isFocusable = true
        textView.isClickable = true
    }

    fun setClickActionListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
        textView.setOnClickListener(listener)
    }
}