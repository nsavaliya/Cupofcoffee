package com.example.cupofcoffee.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.bee.cupofcoffee.R
import com.bee.cupofcoffee.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val coffeeType: MutableLiveData<String> = MutableLiveData("")
    private val coffeeSize: MutableLiveData<String> = MutableLiveData("")
    private var coffeeOptions: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()

        initObservers()
    }

    private fun initObservers() {

        coffeeType.observe(viewLifecycleOwner) {
            if (it != "") {
                binding.sizeGroup.visibility = View.VISIBLE
            }
        }

        coffeeSize.observe(viewLifecycleOwner) {
            if (it != "") {
                binding.optionsGroup.visibility = View.VISIBLE
                binding.continueBtn.background = ContextCompat.getDrawable(requireActivity(),R.drawable.enable_btn_bg)
            }
        }
    }

    private fun initClickListeners() {

        binding.coffeeOption1.setOnClickListener {
            coffeeType.value = binding.coffeeOption1.text.toString()
            binding.coffeeOption1.background = ContextCompat.getDrawable(requireActivity(), R.drawable.enable_btn_bg)
            binding.coffeeOption2.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption3.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption4.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)

            binding.coffeeOption1.setTextColor(ContextCompat.getColor(requireActivity(),R.color.black))
            binding.coffeeOption2.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption3.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption4.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
        }

        binding.coffeeOption2.setOnClickListener {
            coffeeType.value = binding.coffeeOption2.text.toString()
            binding.coffeeOption1.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption2.background = ContextCompat.getDrawable(requireActivity(), R.drawable.enable_btn_bg)
            binding.coffeeOption3.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption4.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)


            binding.coffeeOption1.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption2.setTextColor(ContextCompat.getColor(requireActivity(),R.color.black))
            binding.coffeeOption3.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption4.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
        }

        binding.coffeeOption3.setOnClickListener {
            coffeeType.value = binding.coffeeOption3.text.toString()
            binding.coffeeOption1.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption2.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption3.background = ContextCompat.getDrawable(requireActivity(), R.drawable.enable_btn_bg)
            binding.coffeeOption4.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)


            binding.coffeeOption1.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption2.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption3.setTextColor(ContextCompat.getColor(requireActivity(),R.color.black))
            binding.coffeeOption4.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
        }

        binding.coffeeOption4.setOnClickListener {
            coffeeType.value = binding.coffeeOption4.text.toString()
            binding.coffeeOption1.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption2.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption3.background = ContextCompat.getDrawable(requireActivity(), R.drawable.disable_btn_bg)
            binding.coffeeOption4.background = ContextCompat.getDrawable(requireActivity(), R.drawable.enable_btn_bg)

            binding.coffeeOption1.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption2.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption3.setTextColor(ContextCompat.getColor(requireActivity(),R.color.brown3))
            binding.coffeeOption4.setTextColor(ContextCompat.getColor(requireActivity(),R.color.black))
        }

        binding.smallSizeRb.setOnClickListener {
            coffeeSize.value = binding.smallSizeRb.text.toString()
        }

        binding.mediumSizeRb.setOnClickListener {
            coffeeSize.value = binding.mediumSizeRb.text.toString()
        }

        binding.largeSizeRb.setOnClickListener {
            coffeeSize.value = binding.largeSizeRb.text.toString()
        }

        binding.twoShotsCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                coffeeOptions.value?.add(binding.twoShotsCb.text.toString())
            }
            else{
                coffeeOptions.value?.remove(binding.twoShotsCb.text.toString())
            }
        }

        binding.sugarCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                coffeeOptions.value?.add(binding.sugarCb.text.toString())
            }
            else{
                coffeeOptions.value?.remove(binding.sugarCb.text.toString())
            }
        }

        binding.creamCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                coffeeOptions.value?.add(binding.creamCb.text.toString())
            }
            else{
                coffeeOptions.value?.remove(binding.creamCb.text.toString())
            }
        }

        binding.wholeMilkCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                coffeeOptions.value?.add(binding.wholeMilkCb.text.toString())
            }
            else{
                coffeeOptions.value?.remove(binding.wholeMilkCb.text.toString())
            }
        }

        binding.twoPercentMilkCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                coffeeOptions.value?.add(binding.twoPercentMilkCb.text.toString())
            }
            else{
                coffeeOptions.value?.remove(binding.twoPercentMilkCb.text.toString())
            }
        }

        binding.nonFatMilkCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                coffeeOptions.value?.add(binding.nonFatMilkCb.text.toString())
            }
            else{
                coffeeOptions.value?.remove(binding.nonFatMilkCb.text.toString())
            }
        }

        binding.almondMilkCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                coffeeOptions.value?.add(binding.almondMilkCb.text.toString())
            }
            else{
                coffeeOptions.value?.remove(binding.almondMilkCb.text.toString())
            }
        }

        binding.continueBtn.setOnClickListener {
            if (coffeeSize.value != ""){

                var coffeeOptionsString = ""
                coffeeOptions.value?.forEach {
                    coffeeOptionsString =  "$coffeeOptionsString, $it"
                }


                val bundle = Bundle()
                bundle.putString("coffeeName", coffeeType.value)
                bundle.putString("coffeeSize", coffeeSize.value)
                bundle.putString("coffeeOptions", coffeeOptionsString.trim())
                findNavController().navigate(R.id.payFragment, bundle)
            }
        }
    }

}