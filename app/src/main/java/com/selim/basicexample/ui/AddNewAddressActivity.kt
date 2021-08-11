package com.selim.basicexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.model.Address
import kotlinx.android.synthetic.main.activity_add_new_address.*

// todo: selim
class AddNewAddressActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null

    override fun onResume() {
        super.onResume()
        // todo: login kontrolü eklenecek
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_address)

        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()
        val address = Address()

        adresEkle.setOnClickListener {

            //todo: Textbox kontrolü return eklenmeli

            if (editText_adresAdi.text.isEmpty()) {
                editText_adresAdi.error = "Adres adı boş geçilemez"
                editText_adresAdi.requestFocus()
                return@setOnClickListener
            } else {
                address.addressName = editText_adresAdi.text.toString()
            }

            if (editText_cadde.text.isEmpty()) {
                editText_cadde.error = "Cadde adı boş geçilemez"
            } else {
                address.avenue = editText_cadde.text.toString()
            }

            if (editText_il.text.isEmpty()) {
                editText_il.error = "İl adı boş geçilemez"
            } else {
                address.city = editText_il.text.toString()
            }

            if (editText_ilce.text.isEmpty()) {
                editText_ilce.error = "İlçe adı boş geçilemez"
            } else {
                address.district = editText_ilce.text.toString()
            }

            if (editText_mahalle.text.isEmpty()) {
                editText_mahalle.error = "Mahalle adı boş geçilemez"
            } else {
                address.neighborhood = editText_mahalle.text.toString()
            }

            if (editText_sokak.text.isEmpty()) {
                editText_sokak.error = "Sokak adı boş geçilemez"
            } else {
                address.street = editText_sokak.text.toString()
            }

            if (editText_ickapi.text.isEmpty()) {
                editText_ickapi.error = "İç kapı numarası boş geçilemez"
            } else {
                address.number = editText_ickapi.text.toString().toInt()
            }

            if (editText_diskapi.text.isEmpty()) {
                editText_diskapi.error = "Dış kapı numarası boş geçilemez"
            } else {
                address.builderNumer = editText_diskapi.text.toString().toInt()
            }


            addNewAddress(address)

        }
    }

    private fun addNewAddress(address: Address) {
        auth?.currentUser?.uid?.let { userId ->
            firestore?.collection("user")?.whereEqualTo("userId", userId)
                ?.addSnapshotListener { value, error ->
                    value?.documents?.firstOrNull()?.id?.let { documentId ->
                        firestore?.collection("user/$documentId/address")?.add(address)
                            ?.addOnCompleteListener { task ->
                                when (task.isSuccessful) {
                                    true -> {
                                        Toast.makeText(
                                            this@AddNewAddressActivity,
                                            "${address.addressName} eklendi",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    else -> {
                                        Toast.makeText(
                                            this@AddNewAddressActivity,
                                            "${address.addressName} eklenemedi",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                    }
                }

        }
    }
}