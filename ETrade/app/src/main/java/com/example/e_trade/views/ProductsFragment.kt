package com.example.e_trade.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_trade.R
import com.example.e_trade.adapter.ProductRecyclerAdapter
import com.example.e_trade.model.Products
import com.example.e_trade.service.ProductAPI
import com.example.e_trade.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ProductsFragment : Fragment(),ProductRecyclerAdapter.Listener {

     val productViewModel:ProductViewModel by activityViewModels()
    private var productRecyclerAdapter: ProductRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(activity?.baseContext,2)

        productViewModel.downloadData()
        productViewModel.productList.observe(viewLifecycleOwner, Observer {
            productRecyclerAdapter = ProductRecyclerAdapter(it,this)
            recyclerView.adapter = productRecyclerAdapter
        })

    }

    override fun onItemClick(products: Products) {
        productViewModel.AddedToBasket(products)
    }

}