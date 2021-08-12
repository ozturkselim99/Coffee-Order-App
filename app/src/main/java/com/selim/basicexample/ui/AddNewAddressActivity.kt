package com.selim.basicexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.model.Address
import com.selim.basicexample.model.Coffee
import com.selim.basicexample.ui.AddressActivity
import com.selim.basicexample.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_add_new_address.*
import kotlinx.android.synthetic.main.item_address.*

// todo: selim
class AddNewAddressActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null

    override fun onResume() {
        super.onResume()
        auth = Firebase.auth

        if (auth?.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_address)

        if (intent.hasExtra("address"))
        {
            val updateAddress=intent.getSerializableExtra("address") as Address
            editText_adresAdi.setText(updateAddress.addressName)
            editText_il.setText(updateAddress.city)
            editText_ilce.setText(updateAddress.district)
            editText_cadde.setText(updateAddress.avenue)
            editText_mahalle.setText(updateAddress.neighborhood)
            editText_sokak.setText(updateAddress.street)
            editText_ickapi.setText(updateAddress.buildingNumber.toString())
            editText_diskapi.setText(updateAddress.number.toString())
            adresEkle.setText("GUNCELLE")
        }


        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()
        val address = Address()

        adresEkle.setOnClickListener {
            if (editText_adresAdi.text.isEmpty()) {
                editText_adresAdi.error = "Adres adı boş geçilemez"
                editText_adresAdi.requestFocus()
                return@setOnClickListener
            } else {
                address.addressName = editText_adresAdi.text.toString()
            }

            if (editText_cadde.text.isEmpty()) {
                editText_cadde.error = "Cadde adı boş geçilemez"
                editText_cadde.requestFocus()
                return@setOnClickListener
            } else {
                address.avenue = editText_cadde.text.toString()
            }

            if (editText_il.text.isEmpty()) {
                editText_il.error = "İl adı boş geçilemez"
                editText_il.requestFocus()
                return@setOnClickListener
            } else {
                address.city = editText_il.text.toString()
            }

            if (editText_ilce.text.isEmpty()) {
                editText_ilce.error = "İlçe adı boş geçilemez"
                editText_ilce.requestFocus()
                return@setOnClickListener
            } else {
                address.district = editText_ilce.text.toString()
            }

            if (editText_mahalle.text.isEmpty()) {
                editText_mahalle.error = "Mahalle adı boş geçilemez"
                editText_mahalle.requestFocus()
                return@setOnClickListener
            } else {
                address.neighborhood = editText_mahalle.text.toString()
            }

            if (editText_sokak.text.isEmpty()) {
                editText_sokak.error = "Sokak adı boş geçilemez"
                editText_sokak.requestFocus()
                return@setOnClickListener
            } else {
                address.street = editText_sokak.text.toString()
            }

            if (editText_ickapi.text.isEmpty()) {
                editText_ickapi.error = "İç kapı numarası boş geçilemez"
                editText_ickapi.requestFocus()
                return@setOnClickListener
            } else {
                address.number = editText_ickapi.text.toString().toInt()
            }

            if (editText_diskapi.text.isEmpty()) {
                editText_diskapi.error = "Dış kapı numarası boş geçilemez"
                editText_diskapi.requestFocus()
                return@setOnClickListener
            } else {
                address.buildingNumber = editText_diskapi.text.toString().toInt()
            }

            addNewAddress(address)
        }

        btn_back_to_address.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            startActivity(intent)
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

                                        finish()
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