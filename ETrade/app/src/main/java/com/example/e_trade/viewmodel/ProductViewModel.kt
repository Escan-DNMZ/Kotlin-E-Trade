package com.example.e_trade.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_trade.adapter.ProductRecyclerAdapter
import com.example.e_trade.model.Products
import com.example.e_trade.service.ProductAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel:ViewModel() {
    private var job: Job? = null
    val productList = MutableLiveData<List<Products>>()
    val basket = MutableLiveData<List<Products>>()
    val totalBasket = MutableLiveData<Int>()



    fun AddedToBasket(products: Products){
        if(basket.value != null){
            val arrayList = ArrayList(basket.value)
            if (arrayList.contains(products)){
                val indexOfFirst = arrayList.indexOfFirst {it == products }
                val relatedProducts = arrayList.get(indexOfFirst)
                relatedProducts.count += 1
                basket.value = arrayList
            }else{
                products.count += 1
                arrayList.add(products)
                basket.value = arrayList
            }


        }else{
            products.count += 1
            val arrayList = arrayListOf(products)
            basket.value = arrayList
        }
        basket.value.let {
            refreshTotalValue(it!!)
        }

    }

    private fun refreshTotalValue(listofProduct : List<Products>){
        var total = 0
        listofProduct.forEach{ products ->
            var price = products.price.toIntOrNull()
            price?.let{
               val count = products.count
                val revenue = count * it
                total += revenue
            }
        }
        totalBasket.value = total
    }

    fun deleteProduct(products: Products){
        if (basket.value != null){
            val arrayList = ArrayList(basket.value)
            arrayList.remove(products)
            basket.value = arrayList
            refreshTotalValue(arrayList)
        }
    }

     fun downloadData() {
         val retrofit = Retrofit.Builder()
             .baseUrl("https://raw.githubusercontent.com/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(ProductAPI::class.java)

         job = viewModelScope.launch(context = Dispatchers.IO) {
             val response = retrofit.getData()

             withContext(Dispatchers.Main) {
                 if (response.isSuccessful) {
                     response.body()?.let {
                         productList.value = it

                     }
                 }
             }
         }
     }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
