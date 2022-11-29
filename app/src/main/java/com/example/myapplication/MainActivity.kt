package com.example.myapplication
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient

// DB 연결 해야한다... spring boot를 사용할건데 어려울 거 같다.
// spring이랑 연결... 해보자구~~
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kakao SDK 초기화
        KakaoSdk.init(this, "deee36d8ab760c95e888056a58302a5d")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 전화 걸기 탭
        binding.calltab.setOnClickListener{
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
    }
}
