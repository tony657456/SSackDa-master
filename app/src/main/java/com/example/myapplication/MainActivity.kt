package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.Product
import com.example.myapplication.dto.RetrofitBuilderDto
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// DB 연결 해야한다... spring boot를 사용할건데 어려울 거 같다.
// spring이랑 연결... 해보자구~~
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var product = Product()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kakao SDK 초기화
        KakaoSdk.init(this, "deee36d8ab760c95e888056a58302a5d")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 전화 걸기 탭
        binding.calltab.setOnClickListener {
            val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:16003482"))
            startActivity(call)
        }
        // 카카오톡 상담하기 탭
        binding.kakatab.setOnClickListener {
            val url = TalkApiClient.instance.channelChatUrl("_xgHcfT")
            KakaoCustomTabsClient.openWithDefault(this, url)
        }
        // 카카오톡 채널추가 하기 탭
        binding.talktab.setOnClickListener {
            val homepage = Intent(Intent.ACTION_VIEW, Uri.parse("http://bz150422a.ilogin.biz/"))
            startActivity(homepage)
        }
        product_data(product)
    }

    fun product_data(product: Product) {
        val call = RetrofitBuilderDto.api.getProductResponse(product)
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful()) {
                    Log.d(
                        "RESPONSE: ",
                        (response.body()?.get(0)?.product_name
                            ?: toString()) + ", " + (response.body()
                            ?.get(0)?.unit ?: toString())
                    )
                } else Log.d("RESPONSE", "FAILSE")
                for (product in response.body()!!){
                    product.product_name
                }
                for (count in response.body()!!){
                    
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}
