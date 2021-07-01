package com.example.appworkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appworkapi.api.MyRetroFit
import com.example.appworkapi.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recycle_products: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle_products = findViewById(R.id.recycle_products)
        recycle_products.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData(){
        val call: Call<List<Product>> =
             MyRetroFit.instance?.productApi()?.getProductAPI() as Call<List<Product>>
        call.enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val adapter = ProductAdapter(this@MainActivity, response.body() as List<Product>)
                recycle_products.adapter = adapter
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}