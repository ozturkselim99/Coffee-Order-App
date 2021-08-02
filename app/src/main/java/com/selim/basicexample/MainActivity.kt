package com.selim.basicexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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


    override fun onCreate(savedInstanceState: Bundle?) {
        val personList= arrayListOf<Person>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var nameText:TextView=findViewById(R.id.editTextTextPersonName)
        var surnameTxt:TextView=findViewById(R.id.editTextTextPersonSurname)

        addPerson.setOnClickListener {
            personList.add(Person(nameText.text.toString(),surnameTxt.text.toString()))
            Toast.makeText(this,nameText.text.toString()+" "+surnameTxt.text.toString(),Toast.LENGTH_LONG).show()
        }
       listPerson.setOnClickListener {
           val layoutManager=LinearLayoutManager(this)
           recyclerView.layoutManager=layoutManager
           val adapter=Adapter(personList)
           recyclerView.adapter=adapter
           Toast.makeText(this,"Listelendi",Toast.LENGTH_LONG).show()
       }
    }
}