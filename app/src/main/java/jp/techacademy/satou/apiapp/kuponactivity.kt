package jp.techacademy.satou.apiapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.activity_web_view.*


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

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (keyid?.let { FavoriteShop.findBy(it) } != null){
                AlertDialog.Builder(this)
                    .setTitle(R.string.delete_favorite_dialog_title)
                    .setMessage(R.string.delete_favorite_dialog_message)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                    FavoriteShop.delete(keyid)
                        onClickDeleteFavorite = {
                            fragmentCallback?.onDeleteFavorite(keyid)

                        }


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
        fun start(activity: Activity, url: String, id: String,name: String,logoImage: String) {
            activity.startActivity(
                Intent(activity, kuponactivity::class.java).putExtra(KEY_URL, url).putExtra(
                    KEY_ID, id).putExtra(KEY_NAME,name).putExtra(KEY_LOGURL,logoImage)
            )

        }
    }

}