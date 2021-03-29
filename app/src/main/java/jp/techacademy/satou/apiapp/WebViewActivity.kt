package jp.techacademy.satou.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    open override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuponactivity)
        webView.loadUrl(intent.getStringExtra(KEY_URL).toString())
        intent.getStringExtra(KEY_ID)

    }

    companion object{
        const val KEY_URL = "key_url"
        const val KEY_ID = "key_id"
        fun start(activity: Activity,url: String,id: String){
            activity.startActivity(Intent(activity,WebViewActivity::class.java).putExtra(KEY_URL,url).putExtra(
                KEY_ID,id))

        }
    }
}
