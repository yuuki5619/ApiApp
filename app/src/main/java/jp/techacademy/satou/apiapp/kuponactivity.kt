package jp.techacademy.satou.apiapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.recycler_favorite.*


class kuponactivity : AppCompatActivity() {
    var onClickAddFavorite: ((Shop) -> Unit)? = null
    var onClickDeleteFavorite: ((Shop) -> Unit)? = null
    private var fragmentCallback : FragmentCallback? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuponactivity)
        val keyurl = webView.loadUrl(intent.getStringExtra(kuponactivity.KEY_URL).toString())
        val keyid = intent.getStringExtra(kuponactivity.KEY_ID)
        val keyname = intent.getStringExtra(kuponactivity.KEY_NAME)
        val keylogourl = intent.getStringExtra(kuponactivity.KEY_LOGURL)
        val keyadress = intent.getStringExtra(KEY_ADRESS)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (keyid?.let { FavoriteShop.findBy(it) } != null){
                AlertDialog.Builder(this)
                    .setTitle(R.string.delete_favorite_dialog_title)
                    .setMessage(R.string.delete_favorite_dialog_message)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                    FavoriteShop.delete(keyid)
                        Toast.makeText(applicationContext,"お気に入りを削除しました。",Toast.LENGTH_SHORT).show()


                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->}
                    .create()
                    .show()
            }else{
                AlertDialog.Builder(this)
                    .setTitle(R.string.add_favorite_dialog_title)
                    .setMessage(R.string.add_favorite_dialog_message)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        FavoriteShop.insert(FavoriteShop().apply {
                            id = keyid!!
                            name = keyname!!
                            imageUrl = keylogourl!!
                            url = keyurl.toString()!!
                            adress = keyadress.toString()!!
                            Toast.makeText(applicationContext,"お気に入りを登録しました",Toast.LENGTH_SHORT).show()


                        })

                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->}
                    .create()
                    .show()
            }

        }


    }


    companion object {
        private const val KEY_URL = "key_url"
        private const val KEY_ID = "key_id"
        private const val KEY_NAME= "key_name"
        private const val KEY_LOGURL = "key_logourl"
        private const val KEY_ADRESS = "key_adress"
        fun start(activity: Activity, url: String, id: String,name: String,logoImage: String,adress: String) {
            activity.startActivity(
                Intent(activity, kuponactivity::class.java).putExtra(KEY_URL, url).putExtra(
                    KEY_ID, id).putExtra(KEY_NAME,name).putExtra(KEY_LOGURL,logoImage).putExtra(KEY_ADRESS,adress)
            )

        }
    }

}