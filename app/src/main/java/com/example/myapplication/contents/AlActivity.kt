package com.example.myapplication.contents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAlBinding
import com.example.myapplication.domain.Alproduct
import com.example.myapplication.dto.RetrofitBuilderDto
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlBinding
    var alproduct = Alproduct();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_al)

        // home
        binding.home.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        // dmc
        binding.dmcMetal.setOnClickListener {
            startActivity(Intent(this, DmcActivity::class.java))
        }
        // smc
        binding.smc.setOnClickListener {
            startActivity(Intent(this, SmcActivity::class.java))
        }
        // al담파
        binding.al.setOnClickListener {
            startActivity(Intent(this, AlActivity::class.java))
        }
        // bath
        binding.bath.setOnClickListener {
            startActivity(Intent(this, BathActivity::class.java))
        }
        // span
        binding.span.setOnClickListener {
            startActivity(Intent(this, SpandActivity::class.java))
        }
        // glasswol
        binding.glasswol.setOnClickListener {
            startActivity(Intent(this, GlassActivity::class.java))
        }
        // myton
        binding.mytont.setOnClickListener {
            startActivity(Intent(this, MytonActivity::class.java))
        }
        // eboard
        binding.eboard.setOnClickListener {
            startActivity(Intent(this, EboardActivity::class.java))
        }
        // isopink
        binding.pink.setOnClickListener {
            startActivity(Intent(this, IsopinkActivity::class.java))
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
            val homepage = Intent(Intent.ACTION_VIEW, Uri.parse("https://ssakda.co.kr/"))
            startActivity(homepage)
        }
        product_data(alproduct)

    }

    fun product_data(alproduct: Alproduct) {
        val call = RetrofitBuilderDto.api.getProductResponse(alproduct)
        call.enqueue(object : Callback<List<Alproduct>> {
            override fun onResponse(call: Call<List<Alproduct>>, response: Response<List<Alproduct>>) {
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

            override fun onFailure(call: Call<List<Alproduct>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
}