package com.selim.basicexample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.selim.basicexample.model.Address
import com.selim.basicexample.ui.LoginActivity

class AddNewAddressActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore? = null
    private var address: Address? = null
    private val etAddressName: EditText by lazy { findViewById(R.id.editText_adresAdi) }
    private val etAvenue: EditText by lazy { findViewById(R.id.editText_cadde) }
    private val etCity: EditText by lazy { findViewById(R.id.editText_il) }
    private val etState: EditText by lazy { findViewById(R.id.editText_ilce) }
    private val etNeighborhood: EditText by lazy { findViewById(R.id.editText_mahalle) }
    private val etStreet: EditText by lazy { findViewById(R.id.editText_sokak) }
    private val etInNumber: EditText by lazy { findViewById(R.id.editText_ickapi) }
    private val etOutNumber: EditText by lazy { findViewById(R.id.editText_diskapi) }
    private val btnAddAddress: Button by lazy { findViewById(R.id.adresEkle) }

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

        btnAddAddress.setOnClickListener {
            if (etAddressName.text.isEmpty()) {
                etAddressName.error = "Adres adı boş geçilemez"
                etAddressName.requestFocus()
                return@setOnClickListener
            }
            if (etAvenue.text.isEmpty()) {
                etAvenue.error = "Cadde adı boş geçilemez"
                etAvenue.requestFocus()
                return@setOnClickListener
            }
            if (etCity.text.isEmpty()) {
                etCity.error = "İl adı boş geçilemez"
                etCity.requestFocus()
                return@setOnClickListener
            }
            if (etState.text.isEmpty()) {
                etState.error = "İlçe adı boş geçilemez"
                etState.requestFocus()
                return@setOnClickListener
            }
            if (etNeighborhood.text.isEmpty()) {
                etNeighborhood.error = "Mahalle adı boş geçilemez"
                etNeighborhood.requestFocus()
                return@setOnClickListener
            }
            if (etStreet.text.isEmpty()) {
                etStreet.error = "Sokak adı boş geçilemez"
                etStreet.requestFocus()
                return@setOnClickListener
            }
            if (etInNumber.text.isEmpty()) {
                etInNumber.error = "İç kapı numarası boş geçilemez"
                etInNumber.requestFocus()
                return@setOnClickListener
            }
            if (etOutNumber.text.isEmpty()) {
                etOutNumber.error = "Dış kapı numarası boş geçilemez"
                etOutNumber.requestFocus()
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
        address?.apply {
            addressName = etAddressName.text.toString()
            avenue=etAvenue.text.toString()
            city = etCity.text.toString()
            state = etState.text.toString()
            neighborhood = etNeighborhood.text.toString()
            street = etStreet.text.toString()
            number = etInNumber.text.toString().toInt()
            buildingNumber = etOutNumber.text.toString().toInt()
        }

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
        etAddressName.setText(address?.addressName)
        etCity.setText(address?.city)
        etState.setText(address?.state)
        etAvenue.setText(address?.avenue)
        etNeighborhood.setText(address?.neighborhood)
        etStreet.setText(address?.street)
        etOutNumber.setText(address?.buildingNumber.toString())
        etInNumber.setText(address?.number.toString())
        btnAddAddress.setText("GUNCELLE")
    }

    private fun addNewAddress() {

        val newAddress = Address().apply {
            addressName = etAddressName.text.toString()
            avenue=etAvenue.text.toString()
            city = etCity.text.toString()
            state = etState.text.toString()
            neighborhood = etNeighborhood.text.toString()
            street = etStreet.text.toString()
            number = etInNumber.text.toString().toInt()
            buildingNumber = etOutNumber.text.toString().toInt()
        }

        auth?.currentUser?.uid?.let { userId ->
            firestore?.collection("user")?.whereEqualTo("userId", userId)
                ?.addSnapshotListener { value, error ->
                    value?.documents?.firstOrNull()?.id?.let { documentId ->
                        firestore?.collection("user/$documentId/address")?.add(newAddress)
                            ?.addOnCompleteListener { task ->
                                when (task.isSuccessful) {
                                    true -> {
                                        Toast.makeText(
                                            this@AddNewAddressActivity,
                                            "${newAddress.addressName} eklendi",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        finish()
                                    }
                                    else -> {
                                        Toast.makeText(
                                            this@AddNewAddressActivity,
                                            "${newAddress.addressName} eklenemedi",
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