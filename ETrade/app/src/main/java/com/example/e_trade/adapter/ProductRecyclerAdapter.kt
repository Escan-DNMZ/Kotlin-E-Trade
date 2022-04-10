package com.example.e_trade.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_trade.R
import com.example.e_trade.model.Products
import kotlinx.android.synthetic.main.recycler_row.view.*

class ProductRecyclerAdapter(val productList: List<Products>,private val listener:Listener):RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>() {
    class ProductHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.itemView.productName.text =  productList.get(position).name
        holder.itemView.productPrice.text = productList.get(position).price
        Glide.with(holder.itemView.context).load(productList.get(position).url).into(holder.itemView.productImage)
        holder.itemView.addBasketButton.setOnClickListener{
            val aw = AlertDialog.Builder(holder.itemView.context)
            aw.setTitle("Are You Sure ?")
            aw.setMessage("Are you sure, ${productList[position].name} want to add basket ?")
            aw.setPositiveButton("Add"){dialogInterface, i ->
                Toast.makeText(holder.itemView.context, "${productList[position].name} Added basket", Toast.LENGTH_SHORT).show()
                listener.onItemClick(productList.get(position))
            }
            aw.setNegativeButton("Cancel"){ dialogInterface, i ->
                Toast.makeText(holder.itemView.context, "We can't add basket", Toast.LENGTH_SHORT).show()
            }
            aw.create().show()
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    interface Listener{
        fun onItemClick(products: Products)
    }
}