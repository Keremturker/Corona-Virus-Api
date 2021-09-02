package com.keremturker.coronavirusapi.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keremturker.coronavirusapi.R
import com.keremturker.coronavirusapi.databinding.FragmentDetailBinding
import com.keremturker.coronavirusapi.repository.entity.response.CountriesResponseItem
import com.keremturker.coronavirusapi.ui.activity.MainActivity
import com.keremturker.coronavirusapi.ui.fragment.list.items
import com.keremturker.coronavirusapi.util.emptyItemCheck
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.getSerializable(items) as CountriesResponseItem?
        (activity as MainActivity?)?.setBackButton()

        binding.txtCountryName.text = item?.country.emptyItemCheck(requireContext())
        binding.txtTotalCase.text = item?.totalCases.emptyItemCheck(requireContext())
        binding.txtNewCase.text = item?.newCases.emptyItemCheck(requireContext())
        binding.txtTotalDeaths.text = item?.totalDeaths.emptyItemCheck(requireContext())
        binding.txtNewDeaths.text = item?.newDeaths.emptyItemCheck(requireContext())
        binding.txtTotalRecovered.text = item?.totalRecovered.emptyItemCheck(requireContext())
        binding.txtActiveCases.text = item?.activeCases.emptyItemCheck(requireContext())
    }



}