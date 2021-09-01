package com.keremturker.coronavirusapi.ui.fragment.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.keremturker.coronavirusapi.R
import com.keremturker.coronavirusapi.databinding.FragmentListBinding
import com.keremturker.coronavirusapi.repository.entity.response.CountriesResponseItem
import com.keremturker.coronavirusapi.ui.fragment.list.adapter.CountriesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable


const val items = "ITEM"

@AndroidEntryPoint
class FragmentList : Fragment() {
    lateinit var binding: FragmentListBinding
    private val viewModel: ListVM by viewModels()
    private val countriesListAdapter = CountriesListAdapter(::onClickAction)

    override
    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        subscribe()
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.getList(false)
        }

    }

    private fun prepareRecyclerView() {
        binding.rvList.apply {
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = countriesListAdapter
        }
    }

    private fun subscribe() {
        viewModel.onGetCountriesLD.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.rvList.visibility = View.GONE
                binding.txtNotFound.visibility = View.VISIBLE
            } else {
                binding.rvList.visibility = View.VISIBLE
                binding.txtNotFound.visibility = View.GONE
                countriesListAdapter.updateList(it)
            }
        }

        viewModel.onLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }
    }

    private fun onClickAction(item: CountriesResponseItem) {
        val actionId = R.id.nav_action_fragmentDetail_global
        Bundle().apply {
            putSerializable(items, item as Serializable)
            Navigation.findNavController(binding.root).navigate(actionId, this)
        }
    }

}