package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySmcBinding
import com.example.myapplication.domain.DmcProduct
import com.example.myapplication.domain.SmcProduct
import com.example.myapplication.dto.RetrofitBuilderDto
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmcActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySmcBinding
    var smcProduct = SmcProduct();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kakao SDK 초기화
        KakaoSdk.init(this, "deee36d8ab760c95e888056a58302a5d")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_smc)

        binding.smc.setOnClickListener {
            startActivity(Intent(this, SmcActivity::class.java))
        }

        binding.dmcMetal.setOnClickListener {
            startActivity(Intent(this, DmcActivity::class.java))
        }

        binding.home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

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

        product_data(smcProduct)
    }

    // request를 하고 response를 받아오는 코드인데 건축자재 데이터를 List 형태로 받아오기 때문에
    // getIdentifier라는 함수를 이용해서 아이디를 받아온 다음 각 표 셀 안에다가 건축자재 데이터를 넣어주었다.
    fun product_data(smcProduct: SmcProduct) {
        val call = RetrofitBuilderDto.api.getProductResponse(smcProduct)
        call.enqueue(object : Callback<List<SmcProduct>> {
            override fun onResponse(call: Call<List<SmcProduct>>, response: Response<List<SmcProduct>>) {
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

            override fun onFailure(call: Call<List<SmcProduct>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}