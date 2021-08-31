package com.selim.basicexample.ui.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.basicexample.adapter.CategoryAdapter
import com.selim.basicexample.adapter.CoffeeHomeAdapter
import com.selim.basicexample.databinding.FragmentHomeBinding
import com.selim.basicexample.model.Coffee
import com.selim.basicexample.model.CoffeeCategory
import com.selim.basicexample.ui.LoginActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        homeViewModel.getCategories()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gridLayoutManager =
            GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCategory.layoutManager = gridLayoutManager

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewProduct.layoutManager = layoutManager

        homeViewModel.categories.observe(viewLifecycleOwner, { categories ->
            loadCategories(categories)
        })

        homeViewModel.coffees.observe(viewLifecycleOwner, { coffees ->
            loadCoffees(coffees)
        })

        homeViewModel.isNeedLogin.observe(viewLifecycleOwner, { isNeedLogin ->
            if (isNeedLogin) {
                context?.startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
        })

        homeViewModel.isSuccessfulAddtoBasket.observe(viewLifecycleOwner, { isSuccessful ->
            when (isSuccessful) {
                true -> Toast.makeText(requireContext(), "Sepete eklendi", Toast.LENGTH_LONG).show()
                false -> Toast.makeText(requireContext(), "Sepete eklenemedi", Toast.LENGTH_LONG)
                    .show()
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun loadCategories(list: ArrayList<CoffeeCategory>) {
        val categoryAdapter = CategoryAdapter(list) { categoryId ->
            homeViewModel.getCoffeesAsCategory(categoryId)
        }
        binding.recyclerViewCategory.adapter = categoryAdapter

        list.firstOrNull()?.let { firstCategory ->
            homeViewModel.getCoffeesAsCategory(firstCategory.id)
        }
    }

    private fun loadCoffees(list: ArrayList<Coffee>) {
        val coffeeAdapter = CoffeeHomeAdapter(list) { coffee ->
            homeViewModel.addCoffeeToBasket(coffee)
        }
        binding.recyclerViewProduct.adapter = coffeeAdapter
    }
}