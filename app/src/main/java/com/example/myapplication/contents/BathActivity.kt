package com.example.myapplication.contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBathBinding
import com.example.myapplication.domain.BathProduct
import com.example.myapplication.dto.RetrofitBuilderDto
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BathActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBathBinding
    var bathProduct = BathProduct();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_bath)

        // home
        binding.root.home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        // dmc
        binding.root.dmc_metal.setOnClickListener {
            startActivity(Intent(this, DmcActivity::class.java))
        }
        // smc
        binding.root.smc.setOnClickListener {
            startActivity(Intent(this, SmcActivity::class.java))
        }
        // al
        binding.root.al.setOnClickListener {
            startActivity(Intent(this, AlActivity::class.java))
        }
        // bath
        binding.root.bath.setOnClickListener {
            startActivity(Intent(this, BathActivity::class.java))
        }
        product_data(bathProduct)
    }

    fun product_data(bathProduct: BathProduct) {
        val call = RetrofitBuilderDto.api.getProductResponse(bathProduct)
        call.enqueue(object : Callback<List<BathProduct>> {
            override fun onResponse(call: Call<List<BathProduct>>, response: Response<List<BathProduct>>) {
                if (response.isSuccessful()) {
                    // 아이디 30개 가져옴
                    var size = response.body()?.size
                    for (i in 1..size!!) {
                        // getIdentifier 사용해서 for문으로 응답받은 값만큼 반복

                        var standard_id = resources.getIdentifier(
                            "product_standard$i",
                            "id",
                            "com.example.myapplication"
                        )

                        var unit_id = resources.getIdentifier(
                            "product_unit$i",
                            "id",
                            "com.example.myapplication"
                        )
                        var price_id = resources.getIdentifier(
                            "product_price$i",
                            "id",
                            "com.example.myapplication"
                        )

                        // 담아온 id 내용으로 id를 찾아서 각 TextView에다가 response값을 넣어줌
                        val standard_name = findViewById<TextView>(standard_id)
                        val unit_name = findViewById<TextView>(unit_id)
                        val price_name = findViewById<TextView>(price_id)

                        standard_name.text = response.body()?.get(i - 1)?.standard
                        unit_name.text = response.body()?.get(i - 1)?.unit
                        price_name.text = response.body()?.get(i - 1)?.price

                        Log.d("name: ", standard_name.text.toString())
                    }
                } else Log.d("RESPONSE", "FAILSE")
            }

            override fun onFailure(call: Call<List<BathProduct>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}