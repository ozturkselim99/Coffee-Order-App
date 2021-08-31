package com.selim.basicexample.ui.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.selim.basicexample.model.Coffee
import com.selim.basicexample.model.CoffeeCategory

class HomeViewModel : ViewModel() {

    private var firestore: FirebaseFirestore? = null
    private var auth: FirebaseAuth? = null

    private val _categories = MutableLiveData<ArrayList<CoffeeCategory>>()
    val categories: LiveData<ArrayList<CoffeeCategory>> = _categories

    private val _coffees = MutableLiveData<ArrayList<Coffee>>()
    val coffees: LiveData<ArrayList<Coffee>> = _coffees

    private val _isSuccessfulAddtoBasket = MutableLiveData<Boolean>()
    val isSuccessfulAddtoBasket: LiveData<Boolean> = _isSuccessfulAddtoBasket

    private val _isNeedLogin = MutableLiveData<Boolean>()
    val isNeedLogin: LiveData<Boolean> = _isNeedLogin

    init {
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
    }

    fun getCategories() {
        firestore?.collection("category")?.get()?.addOnSuccessListener { snapshot ->
            val list = arrayListOf<CoffeeCategory>()

            snapshot.documents.forEach { documentSnapshot ->
                documentSnapshot.toObject(CoffeeCategory::class.java)?.let { category ->
                    category.id = documentSnapshot.id
                    list.add(category)
                }
            }
            _categories.value = list
        }
    }


    fun getCoffeesAsCategory(categoryId: String) {
        firestore?.collection("category")?.document(categoryId)?.collection("coffees")
            ?.addSnapshotListener { snapshot, error ->
                val list = ArrayList<Coffee>()
                snapshot?.documents?.forEach { documentSnapshot ->
                    documentSnapshot.toObject(Coffee::class.java)?.let { coffee ->
                        coffee.id = documentSnapshot.id
                        list.add(coffee)
                    }
                }

                _coffees.value = list
            }
    }

    fun addCoffeeToBasket(coffee: Coffee) {
        if (auth?.currentUser?.uid.isNullOrEmpty()) {
            _isNeedLogin.value = true
            return
        }


        firestore?.collection("user")?.whereEqualTo("userId", auth?.currentUser?.uid)?.get()
            ?.addOnSuccessListener { querySnapshot ->
                querySnapshot.documents.firstOrNull().let { document ->
                    document?.id?.let { documentId ->
                        firestore?.collection("user")?.document(documentId)?.collection("basket")
                            ?.add(coffee)?.addOnCompleteListener { task ->
                                _isSuccessfulAddtoBasket.value = task.isSuccessful
                            }
                    }
                }
            }
    }
}


// design pattern: business
//model - view - viewmodel
// mvc, mvp, mvvm, mvi
// data katmanını, view katmanını ve business birbirinden ayırmak.

