package jp.techacademy.satou.apiapp

interface FragmentCallback {

    //Itemを押したときの追加処理
    fun onClickItem(url: String,id: String,name: String,imageUrl: String,adress: String)
    // お気に入り追加時の処理
    fun onAddFavorite(shop: Shop)
    // お気に入り削除時の処理
    fun onDeleteFavorite(id: String)
}