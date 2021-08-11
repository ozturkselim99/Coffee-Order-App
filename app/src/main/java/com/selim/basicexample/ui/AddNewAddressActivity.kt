package com.selim.basicexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_new_address.*

class AddNewAddressActivity : AppCompatActivity() {


    override fun onResume() {
        super.onResume()
        // todo: login kontrolü eklenecek
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_address)

        adresEkle.setOnClickListener {

           //Textbox kontrolü

            if( editText_adresAdi.text.isEmpty())
            {
                editText_adresAdi.error="Adres adı boş geçilemez"
                editText_adresAdi.requestFocus()
                return@setOnClickListener
            }

            if( editText_cadde.text.isEmpty())
            {
                editText_cadde.error="Cadde adı boş geçilemez"
            }
            if( editText_il.text.isEmpty())
            {
                editText_il.error="İl adı boş geçilemez"
            }
            if( editText_ilce.text.isEmpty())
            {
                editText_ilce.error="İlçe adı boş geçilemez"
            }
            if( editText_mahalle.text.isEmpty())
            {
                editText_mahalle.error="Mahalle adı boş geçilemez"
            }
            if( editText_sokak.text.isEmpty())
            {
                editText_sokak.error="Sokak adı boş geçilemez"
            }
            if( editText_ickapi.text.isEmpty())
            {
                editText_ickapi.error="İç kapı numarası boş geçilemez"
            }
            if( editText_diskapi.text.isEmpty())
            {
                editText_diskapi.error="Dış kapı numarası boş geçilemez"
            }


            addNewAddress()

        }
    }

    private fun addNewAddress() {

    }
}