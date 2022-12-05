## SQLite를 이용한 실습
```kotlin
package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${MainActivity.FeedReaderContract.FeedEntry.TABLE_NAME} (" +
            "${MainActivity.FeedReaderContract.FeedEntry.ID} INTEGER PRIMARY KEY," +
            "${MainActivity.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} TEXT)"

private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${MainActivity.FeedReaderContract.FeedEntry.TABLE_NAME}"

class MainActivity : AppCompatActivity() {

    object FeedReaderContract {
        // Table contents are grouped together in an anonymous object.
        object FeedEntry : BaseColumns {
            const val TABLE_NAME = "entry"
            const val COLUMN_NAME_TITLE = "title"
            const val ID = "id"
        }
    }

    class FeedReaderDbHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }

        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(db, oldVersion, newVersion)
        }

        companion object {
            // If you change the database schema, you must increment the database version.
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "FeedReader.db"
        }
    }

    private lateinit var binding: ActivityMainBinding
    private val dbHelper = FeedReaderDbHelper(this)

    // 0으로 초기화를 하면 다시 실행 시켰을 때 기본 키가 0번인 곳에 값이 들어있으면 error가 남
    private var saveNum = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 위와 같이 설명한 saveNum의 오류로 database에 맨 마지막 id 값의 primary 값을 찾아서
        // 데이터를 추가해주는 형식으로 진행한다.
        val saveNumSet = dbHelper.readableDatabase
        val cursor = saveNumSet.rawQuery("SELECT *FROM entry", null)
        if (cursor.moveToLast()) {
            saveNum = Integer.parseInt("" + cursor.getString(0)) + 1
        }

        read()

        Toast.makeText(applicationContext, "안뇽", Toast.LENGTH_LONG).show()

        binding.save.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("INSERT INTO entry VALUES('$saveNum','" + binding.editText1.text + "')")
            saveNum += 1
            read()
        }

        binding.update.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("UPDATE entry SET title= '" + binding.editText2.text + "' WHERE id='" + binding.editText1.text + "'")
            read()
        }

        binding.delete.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("DELETE FROM entry WHERE id='" + binding.editText1.text + "'")
            read()
        }
    }

    private fun read() {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT *FROM entry", null)
        binding.textView.text = ""
        while (cursor.moveToNext()) {
            binding.textView.text = ""+binding.textView.text+"ID : "+cursor.getString(0)+
                    "Title : "+cursor.getString(1)+"\n"
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
```
## RetrofitBulider 
```kotlin
package com.example.myapplication.dto

import com.example.myapplication.api.API
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// Gson은 정형화 된 Json 형식만 허용할 정도로 엄격하다. 그래서
// var gson = GsonBuilder().setLenient().create() 코드를 사용해
// 조금 더 자유로운 parse가 가능하도록 하였다.
object RetrofitBulider {
    var api : API
    var gson = GsonBuilder().setLenient().create()
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.13:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        api = retrofit.create(API::class.java)
    }
}
```
## 서버랑 통신하기 위한 API
```kotlin
package com.example.myapplication.api

import com.example.myapplication.domain.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// 서버 db에 있는 모든 데이터를 가져오려면 [[name, password]]의 List 형식으로 받아와야 하는데
interface API {
    @POST("android")
    fun getLoginResponse(@Body user: User) : Call<List<User>>
}
```
<<<<<<< HEAD
=======
## response 받는 코드
```kotlin
    fun Login(user: User) {
        val call = RetrofitBulider.api.getLoginResponse(user)

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful()) Log.d("RESPONSE: ",
                    (response.body()?.get(0)?.password ?: toString()) + ", " + (response.body()
                        ?.get(0)?.name ?: toString())
                )
                else Log.d("RESPONSE", "FAILSE")

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }
```
>>>>>>> master2
