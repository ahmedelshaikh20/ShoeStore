package com.udacity.shoestore.onBoarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding


class InstructionFragment : Fragment() {
  lateinit var binding: FragmentInstructionBinding

  lateinit var sharedPreferences: SharedPreferences
  lateinit var Editor: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      setUpSharedPreference()
      (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

      binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_instruction ,container,false )
binding.DoneButton.setOnClickListener {
  it.findNavController().navigate(R.id.action_instructionFragment_to_shoeListFragment)
  Editor.putBoolean("IsBoardedSuccessfully" , true)
  Editor.apply()
}
      // Inflate the layout for this fragment
        return binding.root
    }

  private fun setUpSharedPreference() {
    sharedPreferences = requireActivity()?.getPreferences(Context.MODE_PRIVATE) ?: return
    Editor = sharedPreferences.edit()

  }


}
