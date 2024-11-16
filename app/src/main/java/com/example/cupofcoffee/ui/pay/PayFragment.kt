package com.example.cupofcoffee.ui.pay

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.bee.cupofcoffee.R
import com.bee.cupofcoffee.databinding.FragmentPayBinding



class PayFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!

    var cardTypes = arrayOf("Visa", "MasterCard")

    private val fullNameText: MutableLiveData<String> = MutableLiveData("")
    private val phoneNumberText: MutableLiveData<String> = MutableLiveData("")
    private val cardTypeText: MutableLiveData<String> = MutableLiveData("")
    private val cardNumberText: MutableLiveData<String> = MutableLiveData("")
    private val cardExpiryMonthText: MutableLiveData<String> = MutableLiveData("")
    private val cardExpiryYearText: MutableLiveData<String> = MutableLiveData("")
    private val cardCvcText: MutableLiveData<String> = MutableLiveData("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCardTypeSpinner()
        initClickListeners()
        initListeners()
        initObservers()
    }

    private fun initListeners() {

        binding.fullNameEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fullNameText.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.phoneNumberEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                phoneNumberText.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.cardNumberEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cardNumberText.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.cardExpiryMonthEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cardExpiryMonthText.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.cardExpiryYearEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cardExpiryYearText.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.cvcEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cardCvcText.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun initObservers() {
        fullNameText.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty() && phoneNumberText.value?.length!! >= 10) {
                Log.i("fullNameText", "initObservers: Called1")
                binding.cardTypeGroup.visibility = View.VISIBLE
            } else {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
                binding.cardTypeGroup.visibility = View.GONE
                Log.i("fullNameText", "initObservers: Called2")
            }
        }

        phoneNumberText.observe(viewLifecycleOwner) {
            if (fullNameText.value?.isEmpty() == false && it.length >= 10) {
                binding.cardTypeGroup.visibility = View.VISIBLE
            } else {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
                binding.cardTypeGroup.visibility = View.GONE
            }
        }

        cardTypeText.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && binding.cardTypeGroup.visibility ==  View.VISIBLE) {
                binding.cardNumberGroup.visibility = View.VISIBLE
            } else {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
                binding.cardNumberGroup.visibility = View.GONE
            }
        }

        cardNumberText.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && it.length == 16) {
                binding.cardExpiryGroup.visibility = View.VISIBLE
            } else {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
//                binding.cardExpiryGroup.visibility = View.GONE
            }
        }

        cardExpiryMonthText.observe(viewLifecycleOwner) {
            if (it.length == 2 && cardExpiryYearText.value?.length == 4 && cardCvcText.value?.length == 3) {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.enable_btn_bg)
            } else {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            }
        }

        cardExpiryYearText.observe(viewLifecycleOwner) {
            if (cardExpiryMonthText.value?.length == 2 && cardExpiryYearText.value?.length == 4 && cardCvcText.value?.length == 3) {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.enable_btn_bg)
            } else {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            }
        }

        cardCvcText.observe(viewLifecycleOwner) {
            if (cardExpiryMonthText.value?.length == 2 && cardExpiryYearText.value?.length == 4 && cardCvcText.value?.length == 3) {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.enable_btn_bg)
            } else {
                binding.placeOrderBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            }
        }
    }

    private fun initClickListeners() {
        binding.placeOrderBtn.setOnClickListener {
            if (cardExpiryMonthText.value?.length == 2 && cardExpiryYearText.value?.length == 4 && cardCvcText.value?.length == 3) {
                val bundle = Bundle()

                Log.i("coffeeOptionsString", "initClickListeners: ${arguments?.getString("coffeeOptions")}")

                bundle.putString("coffeeName", arguments?.getString("coffeeName"))
                bundle.putString("coffeeSize", arguments?.getString("coffeeSize"))
                bundle.putString("coffeeOptions", arguments?.getString("coffeeOptions"))
                bundle.putString("fullName", fullNameText.value)
                bundle.putString("phoneNumber", phoneNumberText.value)
                bundle.putString("pickUpTime", "${binding.timePicker.hour}:${binding.timePicker.minute}")
                findNavController().navigate(R.id.summaryFragment, bundle)
            }
        }
    }


    private fun initCardTypeSpinner() {
        //Setting listener in card type spinner
        binding.spinner.onItemSelectedListener = this
        //Creating the ArrayAdapter instance having the card types list
        val cardTypeAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(requireActivity(), android.R.layout.simple_spinner_item, cardTypes)
        cardTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.adapter = cardTypeAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        cardTypeText.value = cardTypes[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}