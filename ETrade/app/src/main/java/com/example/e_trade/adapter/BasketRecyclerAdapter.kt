package com.example.e_trade.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_trade.R
import com.example.e_trade.model.Products
import kotlinx.android.synthetic.main.basket_reycler_row.view.*

class BasketRecyclerAdapter(val basketLists : List<Products>):RecyclerView.Adapter<BasketRecyclerAdapter.BasketViewHolder>() {
    class BasketViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_reycler_row,parent,false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.itemView.basketProductName.text = basketLists.get(position).name
        holder.itemView.basketProductPrice.text = "Price: ${basketLists.get(position).price}"
        holder.itemView.basketProductCount.text = "Count: ${basketLists.get(position).count}"
        Glide.with(holder.itemView.context).load(basketLists.get(position).url).into(holder.itemView.imageView)
    }

    override fun getItemCount(): Int {
      return  basketLists.size

    }

}