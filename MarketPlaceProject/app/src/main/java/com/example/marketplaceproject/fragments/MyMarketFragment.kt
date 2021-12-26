package com.example.marketplaceproject.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplaceproject.R
import com.example.marketplaceproject.adapters.MyProductsAdapter
import com.example.marketplaceproject.adapters.ProductsAdapter
import com.example.marketplaceproject.models.Product
import com.example.marketplaceproject.retrofit.accesLayers.ProductAccessLayer
import com.example.marketplaceproject.utils.Constants
import com.example.marketplaceproject.viewmodels.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MyMarketFragment : Fragment(),MyProductsAdapter.OnItemClickListener {

    private lateinit var avatar: ImageView
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var productsRecyclerView : RecyclerView
    private lateinit var productsAdapter: MyProductsAdapter
    private lateinit var searchView: SearchView
    private lateinit var products : MutableList<Product>
    private lateinit var spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        products = mainActivityViewModel.myProducts
        productsAdapter = MyProductsAdapter(products,this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_time_line, container, false)

        avatar = rootView.findViewById(R.id.timeLineAvatarIcon)
        spinner = rootView.findViewById(R.id.dropStatus)

        setItemsForSpinner()
        productsRecyclerView = rootView.findViewById(R.id.timeLineRecyclerView)

        searchView = rootView.findViewById(R.id.timeLineSearchView)

        products = mutableListOf<Product>().apply { addAll( mainActivityViewModel.myProducts)}

        productsRecyclerView.adapter = productsAdapter

        productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        setOnclickListenerForAvatar()

        setOnSearchViewTextChangedListener()

        return rootView
    }

    private fun setOnclickListenerForAvatar(){

        avatar.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainerView, ProfileFragment())
                .commit()
        }
    }

    private fun setOnSearchViewTextChangedListener(){

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String): Boolean {

                products = searchResult(newText)
                productsAdapter.productsList = products
                productsAdapter.notifyDataSetChanged()
                Log.d("len", products.size.toString())
                Log.d("lenM", mainActivityViewModel.products.size.toString())
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

        })
    }

    private fun searchResult(titleP: String) : MutableList<Product>{

        val result = mainActivityViewModel.products.filter {
            it.title.contains(titleP,ignoreCase = true)
        }

        return result.toMutableList()
    }

    @SuppressLint("ResourceType")
    private fun setItemsForSpinner() {

        val list: MutableList<String> = ArrayList()
        list.add("Ascending by name")
        list.add("Descending by name")
        list.add("Ascending by price")
        list.add("Descending by price")

        val adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View?,
                arg2: Int,
                arg3: Long
            ) {

                when (arg2) {
                    0 -> products.sortBy { it.title }
                    1 -> products.sortByDescending { it.title }
                    2 -> products.sortBy { it.pricePerUnit.toFloatOrNull() }
                    3 -> products.sortByDescending { it.pricePerUnit.toFloatOrNull() }
                }

                productsAdapter.productsList = products
                productsAdapter.notifyDataSetChanged()

            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }

    override fun onItemClick(position: Int) {
        val product = products[position]
        val bundle = Bundle()

        bundle.putFloat(Constants.RATING, product.rating)
        bundle.putString(Constants.AMOUNTTYPE, product.amountType)
        bundle.putString(Constants.PRICETYPE, product.priceType)
        bundle.putString(Constants.PRODUCTID, product.productId)
        bundle.putString(Constants.USERNAME, product.ownerName)
        bundle.putBoolean(Constants.ISACTIVE, product.isActive)
        bundle.putString(Constants.PRICEPERUNIT, product.pricePerUnit)
        bundle.putString(Constants.UNITS, product.units)
        bundle.putString(Constants.DESCRIPTION, product.description)
        bundle.putString(Constants.TITLE, product.title)

        val manager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val productDetailsFragment = MyProductDetailsFragment()
        productDetailsFragment.arguments = bundle
        transaction.replace(R.id.mainFragmentContainerView, productDetailsFragment)
        transaction.commit()
    }

}