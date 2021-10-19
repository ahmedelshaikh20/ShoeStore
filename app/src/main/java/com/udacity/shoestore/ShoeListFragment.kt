package com.udacity.shoestore


import android.os.Bundle

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.udacity.shoestore.databinding.ActivityShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding


class ShoeListFragment : Fragment() {

    lateinit var model: ShoeViewModel
  lateinit var AddButton :Button
  lateinit var binding: ActivityShoeListBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding  =DataBindingUtil.inflate(inflater,R.layout.activity_shoe_list ,container,false )
    (activity as AppCompatActivity?)!!.supportActionBar!!.show()

    model = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
    bindView()

    model.shoeList.observe(viewLifecycleOwner , Observer {
      val LinearLayout =binding.ShoesLayout

     // LinearLayout.removeAllViews()

      for (Shoe in it){
        val shoeitemBinding: ShoeItemBinding =
          ShoeItemBinding.inflate(LayoutInflater.from(activity))
       shoeitemBinding.shoe =Shoe

        LinearLayout.addView(shoeitemBinding.root)

shoeitemBinding.root.post(Runnable {
  val param = shoeitemBinding.root.layoutParams as ViewGroup.MarginLayoutParams
  param.setMargins(10,10,10,10)
  shoeitemBinding.root.layoutParams = param
})
      }
    }
    )

    return binding.root
  }

  fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
    if (v.layoutParams is MarginLayoutParams) {
      val p = v.layoutParams as MarginLayoutParams
      p.setMargins(l, t, r, b)
      v.requestLayout()
    }
  }

  private fun bindView() {
    AddButton = binding.AddShoeButton
    clickListeners()
  }

  private fun clickListeners() {
    AddButton.setOnClickListener {
      it.findNavController().navigate(R.id.action_shoeListFragment_to_addShoeFragment)
    }

  }

   fun onSupportNavigateUp(): Boolean {
    val navController = requireActivity().findNavController(R.id.myNavHostFragment)
    return when (navController.currentDestination?.id){
      R.id.shoeListFragment -> {

        true
      }else -> true
    }
  }




}
