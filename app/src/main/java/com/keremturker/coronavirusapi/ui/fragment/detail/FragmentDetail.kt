package com.keremturker.coronavirusapi.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.keremturker.coronavirusapi.databinding.FragmentDetailBinding
import com.keremturker.coronavirusapi.repository.entity.response.CountriesResponseItem
import com.keremturker.coronavirusapi.ui.fragment.list.items
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : Fragment() {

    lateinit var binding: FragmentDetailBinding
    val viewModel: DetailVM by viewModels()
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.getSerializable(items) as CountriesResponseItem?

        binding.txtCountryName.text = item?.country

    }
}