package com.udacity.shoestore.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

  lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
      binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome ,container,false )
      binding.NextButton.setOnClickListener {
        it.findNavController().navigate(R.id.action_welcomeFragment_to_instructionFragment)
      }

      return binding.root
    }


}
