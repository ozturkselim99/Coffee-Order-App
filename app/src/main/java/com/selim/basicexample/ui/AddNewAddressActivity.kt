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
import com.selim.basicexample.model.CoffeeCategory
import com.selim.basicexample.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_add_new_address.*
import kotlinx.android.synthetic.main.item_address.*

// todo: selim
class AddNewAddressActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore? = null
    private var address: Address? = null

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

        address = intent.getSerializableExtra("ADDRESS") as Address?

        address?.let {
            bindAddress()
        }

        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()

        adresEkle.setOnClickListener {
            if (editText_adresAdi.text.isEmpty()) {
                editText_adresAdi.error = "Adres adı boş geçilemez"
                editText_adresAdi.requestFocus()
                return@setOnClickListener
            }
            if (editText_cadde.text.isEmpty()) {
                editText_cadde.error = "Cadde adı boş geçilemez"
                editText_cadde.requestFocus()
                return@setOnClickListener
            }
            if (editText_il.text.isEmpty()) {
                editText_il.error = "İl adı boş geçilemez"
                editText_il.requestFocus()
                return@setOnClickListener
            }
            if (editText_ilce.text.isEmpty()) {
                editText_ilce.error = "İlçe adı boş geçilemez"
                editText_ilce.requestFocus()
                return@setOnClickListener
            }
            if (editText_mahalle.text.isEmpty()) {
                editText_mahalle.error = "Mahalle adı boş geçilemez"
                editText_mahalle.requestFocus()
                return@setOnClickListener
            }
            if (editText_sokak.text.isEmpty()) {
                editText_sokak.error = "Sokak adı boş geçilemez"
                editText_sokak.requestFocus()
                return@setOnClickListener
            }
            if (editText_ickapi.text.isEmpty()) {
                editText_ickapi.error = "İç kapı numarası boş geçilemez"
                editText_ickapi.requestFocus()
                return@setOnClickListener
            }
            if (editText_diskapi.text.isEmpty()) {
                editText_diskapi.error = "Dış kapı numarası boş geçilemez"
                editText_diskapi.requestFocus()
                return@setOnClickListener
            }

            if (address == null) {
                addNewAddress()
            } else {
                updateAddress()
            }
        }
    }

    private fun updateAddress() {
        auth?.currentUser?.uid?.let { userId ->
            firestore?.collection("user")?.whereEqualTo("userId", userId)
                ?.addSnapshotListener { value, error ->
                    value?.documents?.firstOrNull()?.id?.let { documentId ->
                        firestore?.collection("user/$documentId/address")?.document(address?.id!!)
                            ?.set(address!!)?.addOnCompleteListener { task->
                                    when (task.isSuccessful) {
                                        true -> {
                                            Toast.makeText(
                                                    this,
                                                    "${address?.addressName} Güncellendi..",
                                                    Toast.LENGTH_LONG
                                            ).show()

                                            finish()
                                        }
                                        false -> Toast.makeText(
                                                this,
                                                "${address?.addressName}  Güncellenemedi..",
                                                Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                    }
                }
        }
    }

    private fun bindAddress() {
        editText_adresAdi.setText(address?.addressName)
        editText_il.setText(address?.city)
        editText_ilce.setText(address?.district)
        editText_cadde.setText(address?.avenue)
        editText_mahalle.setText(address?.neighborhood)
        editText_sokak.setText(address?.street)
        editText_ickapi.setText(address?.buildingNumber.toString())
        editText_diskapi.setText(address?.number.toString())
        adresEkle.setText("GUNCELLE")
    }

    private fun addNewAddress() {

        val address = Address().apply {
            addressName = editText_adresAdi.text.toString()
            avenue=editText_cadde.text.toString()
            city = editText_il.text.toString()
            district = editText_ilce.text.toString()
            neighborhood = editText_mahalle.text.toString()
            street = editText_sokak.text.toString()
            number = editText_ickapi.text.toString().toInt()
            buildingNumber = editText_diskapi.text.toString().toInt()
        }

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