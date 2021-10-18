package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.udacity.shoestore.databinding.ActivityShoeListBinding
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_add_shoe.*
import java.text.NumberFormat
import java.text.ParsePosition


class AddShoeFragment : Fragment() {
  lateinit var binding: FragmentAddShoeBinding
lateinit var model :ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      super.onCreate(savedInstanceState)

      // Inflate the layout for this fragment
      (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

      model = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
      binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_add_shoe ,container,false )
    clickListeners()
      return binding.root
    }

  private fun clickListeners() {
    binding.AddButton.setOnClickListener {
      val ShoeName = binding.NameText.text
      val ShoeSize = binding.SizeText.text
      val ShoeDescription = binding.DescriptionText.text


      if(isNumeric(ShoeSize.toString()) &&  ShoeName!!.isNotEmpty() && ShoeDescription!!.isNotEmpty()){
        val shoe = Shoe(ShoeName.toString() , ShoeSize.toString(),"",ShoeDescription.toString() )
        it.findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)

        model.addShoe(shoe)
      }
      else
      {
        Toast.makeText(activity,"Something Went Wrong try to add again" , Toast.LENGTH_SHORT).show()
      }

    }
    binding.CancelButton.setOnClickListener {
      it.findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)

    }
  }


  fun isNumeric(str: String): Boolean {
    val pos = ParsePosition(0)
    NumberFormat.getInstance().parse(str, pos)
    return str.length == pos.index
  }
}
