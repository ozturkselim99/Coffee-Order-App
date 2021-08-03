package com.selim.basicexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.person_row.*
import kotlinx.android.synthetic.main.person_row.view.*

class MainActivity : AppCompatActivity() {
     class Person
        (
        var personName:String="",
        var personSurname:String=""
    )

    class Coffe
    (
            var  name:String="",
            var  price:String="",
            var imageUrl:String=""
    )



    override fun onCreate(savedInstanceState: Bundle?) {
       /* val personList= arrayListOf<Person>()
            */
        //Kahve Listesi
        val coffeList= arrayListOf<Coffe>(
                Coffe("Iced Coffee","14","https://www.starbucks.com.tr/media/filtre-kahve_tcm95-19055_w1024_n.png"),
                Coffe("Caffè Misto","15","https://www.starbucks.com.tr/media/caffe-misto_tcm95-1988_w1024_n.png"),
                Coffe("Cold Brew Latte","16","https://www.starbucks.com.tr/media/sutlu-cold-brew_tcm95-20069_w1024_n.png"),
                Coffe("Cold Brew","17","https://www.starbucks.com.tr/media/cold-brew_tcm95-20068_w1024_n.png"))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Adapter
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        val adapter=CoffeeAdapter(coffeList)
        recyclerView.adapter=adapter

        //Adapter içindeki total değişkenimizi gözlemliyoruz. Değişkende bir değişiklik olduğunda activity_xml içindeki total_price textini değiştiriyoruz.
        adapter.total.observe(this, Observer {
            total_price.text=it.toString()
        })




        /*
        var nameText:TextView=findViewById(R.id.editTextTextPersonName)
        var surnameTxt:TextView=findViewById(R.id.editTextTextPersonSurname)
        */

        /*addPerson.setOnClickListener {
            personList.add(Person(nameText.text.toString(),surnameTxt.text.toString()))
            Toast.makeText(this,nameText.text.toString()+" "+surnameTxt.text.toString(),Toast.LENGTH_LONG).show()
        }*/
        /*
       listPerson.setOnClickListener {
           val layoutManager=LinearLayoutManager(this)
           recyclerView.layoutManager=layoutManager
           val adapter=Adapter(personList)
           recyclerView.adapter=adapter
           Toast.makeText(this,"Listelendi",Toast.LENGTH_LONG).show()
       }
         */
    }
}