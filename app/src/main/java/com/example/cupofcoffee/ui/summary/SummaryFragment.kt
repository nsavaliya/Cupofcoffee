package com.example.cupofcoffee.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bee.cupofcoffee.databinding.FragmentSummaryBinding


class SummaryFragment : Fragment() {

    private var _binding : FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initValues()
    }

    private fun initValues() {
        binding.fullNameValueTv.text = arguments?.getString("fullName")
        binding.phoneNumberValueTv.text = arguments?.getString("phoneNumber")
        binding.pickUpTimeValueTv.text = arguments?.getString("pickUpTime")

        var orderValueText = "A ${arguments?.getString("coffeeSize")} ${arguments?.getString("coffeeName")}"
        if (!arguments?.getString("coffeeOptions").isNullOrEmpty()){
            orderValueText = "$orderValueText with${arguments?.getString("coffeeOptions")}"
        }
        binding.orderValueTv.text = orderValueText

    }

}