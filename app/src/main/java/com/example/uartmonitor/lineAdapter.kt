package com.example.uartmonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class lineAdapter(val items: List<textLine>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        //set the views to display the items
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                RecyclerView.ViewHolder {
            return ProductViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_layout, parent,
                    false)
            )
        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                      position: Int) {
            when(holder) {
                is ProductViewHolder -> {
                    holder.bind(items[position])
                }
            }
        }
        override fun getItemCount(): Int {
            return items.size
        }

        class ProductViewHolder constructor(
            itemView: View
        ): RecyclerView.ViewHolder(itemView) {
            private val singleLine: TextView =
                itemView.findViewById(R.id.RecyclerTextView)


            fun bind(line: textLine) {
                Glide
                    .with(itemView.context)
                    //.load(product.image_link)
                    //.into(productImage)
                singleLine.text = line.line
            }

        }
    }
