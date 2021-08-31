package com.selim.basicexample.data

import com.selim.basicexample.model.Address
import com.selim.basicexample.model.Coffee
import com.selim.basicexample.model.CoffeeCategory
import com.selim.basicexample.model.User

//Artık kullanılmıyor ama belki test için kullanırız.

object MockData {

    private val userList= arrayListOf(
        User("1","berkhanozen","teknasyon"),
        User("2","selimozturk","teknasyon"),
        User("3","yasincetin","admin"),
    )

    private val coffeeCategoryList= arrayListOf(
            CoffeeCategory("1","Filtre Kahve","https://www.starbucks.com.tr/media/filtre-kahve_tcm95-19055_w1024_n.png"),
            CoffeeCategory("2","Sıcak Çikolata","https://www.starbucks.com.tr/media/classic-hot-chocolate_tcm95-19059_w1024_n.png"),
            CoffeeCategory("3","Soğuk Çaylar","https://www.starbucks.com.tr/media/iced-shaken-peach-green-tea-lemonade_tcm95-64716_w1024_n.png"),
            CoffeeCategory("4","Espresso Bazlı İçecekler","https://www.starbucks.com.tr/media/toffeenut-latte_tcm95-66838_w1024_n.png"),
            CoffeeCategory("5","Türk Kahvesi","https://www.starbucks.com.tr/media/turk-kahvesi_tcm95-19052_w1024_n.png"),
            CoffeeCategory("6","Frappuccino","https://www.starbucks.com.tr/media/gingerbread-frappuccino_tcm95-66836_w1024_n.png"),
    )


    private val coffeeList = arrayListOf(
        Coffee("1","Iced Coffee","14","https://www.starbucks.com.tr/media/buzlu-filtre-kahve_tcm95-2007_w1024_n.png","1"),
        Coffee("2","Caffè Misto","15","https://www.starbucks.com.tr/media/caffe-misto_tcm95-1988_w1024_n.png","1"),
        Coffee("3","Cold Brew Latte","16","https://www.starbucks.com.tr/media/sutlu-cold-brew_tcm95-20069_w1024_n.png","1"),
        Coffee("4","Cold Brew","17","https://www.starbucks.com.tr/media/cold-brew_tcm95-20068_w1024_n.png","1"),
        Coffee("5","Classic Hot Chocolate","17","https://www.starbucks.com.tr/media/classic-hot-chocolate_tcm95-19059_w1024_n.png","2"),
        Coffee("6","White Hot Chocolate","17","https://www.starbucks.com.tr/media/white-hot-chocolate_tcm95-65679_w1024_n.png","2"),
        Coffee("7","Blackberry Green Tea & Lemonade","17","https://www.starbucks.com.tr/media/iced-shaken-blackberry-green-tea-lemonade_tcm95-64721_w1024_n.png","3"),
        Coffee("8","Buzlu Chai Tea Latte","17","https://www.starbucks.com.tr/media/iced-chai-tea-latte_tcm95-64723_w1024_n.png","3"),
        Coffee("9","Buzlu Toffee Nut Latte","17","https://www.starbucks.com.tr/media/iced-toffeenut-latte_tcm95-66839_w1024_n.png","4"),
        Coffee("10","White Chocolate Mocha","17","https://www.starbucks.com.tr/media/White_Chocolate_Mocha_tcm95-64109_w1024_n.png","4"),
        Coffee("11","Caramel Frappuccino","17","https://www.starbucks.com.tr/media/caramel-cream-frappuccino_tcm95-64704_w1024_n.png","6"),
        Coffee("12","White Chocolate Mocha Frappuccino","17","https://www.starbucks.com.tr/media/white-chocolate-mocha-frappuccino_tcm95-66931_w1024_n.png","6"),
    )

    /*
    private val addressList = arrayListOf(
        Address("1", "Ev", "Bursa", "Nilüfer", "Yüzüncüyıl", " ",
            " ", 20, 17),
        Address("2", "İş", "İstanbul", "Maslak", "Valla", "Buraları",
            "Bilmiyorum", 11, 9),
        Address("3", "Selim'in Evi", "İzmir", "Bu", "Adres", "Sende",
            "Selim", 30, 38)
    )
     */

    fun getCoffeeList(): ArrayList<Coffee> {
        return coffeeList
    }

    fun getCoffeeCategories():ArrayList<CoffeeCategory>
    {
        return coffeeCategoryList
    }

    fun getUserList():ArrayList<User>
    {
        return userList
    }
/*
    fun getAddressList():ArrayList<Address>
    {
        return addressList
    }

 */
}