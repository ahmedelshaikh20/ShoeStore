package com.udacity.shoestore

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ActivityLoginBinding


class LoginFragment : Fragment() {

lateinit var binding: ActivityLoginBinding
lateinit var model :ShoeViewModel
  lateinit var sharedPreferences: SharedPreferences
  lateinit var Editor: SharedPreferences.Editor
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {


    super.onCreate(savedInstanceState)
    (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    model = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

    binding = DataBindingUtil.inflate(inflater, R.layout.activity_login , container,false)
setUpSharedPreference()
    clickListeners()
    return binding.root
  }

  private fun setUpSharedPreference() {
    sharedPreferences = requireActivity()?.getPreferences(Context.MODE_PRIVATE) ?: return
    Editor = sharedPreferences.edit()
  }

  private fun clickListeners() {
    binding.LoginButton.setOnClickListener {
      if(binding.EmailText.text!!.isNotEmpty() && binding.PasswordText.text!!.isNotEmpty()){
        Toast.makeText(activity , "One of the Fields are missing",Toast.LENGTH_SHORT).show()
        it.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)

      }
    }

    binding.SignUp.setOnClickListener {
      if(binding.EmailText.text!!.isNotEmpty() && binding.PasswordText.text!!.isNotEmpty()){
        Toast.makeText(activity , "One of the Fields are missing",Toast.LENGTH_SHORT).show()
        it.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)

      }

    }
  }

  private fun checkIfAuthorized(email:String , password:String):Boolean {

     model = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)



    if(model.Emails !=null && model.Passwords!=null){
    return model.Emails.contains(email) && model.Passwords.contains(password)}
    else
      return false
  }
  private fun checkIfAlreadyUser(email: String):Boolean{
    return model.Emails.contains(email)

  }



}
