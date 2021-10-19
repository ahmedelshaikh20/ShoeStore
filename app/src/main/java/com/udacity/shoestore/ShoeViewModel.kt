package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import kotlin.collections.ArrayList

class ShoeViewModel : ViewModel()  {

//current shoe
  private val _shoe=MutableLiveData<Shoe>()
  val shoe : LiveData<Shoe>
  get() = _shoe

  private val _shoesList =MutableLiveData<MutableList<Shoe>>()
  val shoeList : LiveData<MutableList<Shoe>>
  get() = _shoesList
   val Emails =ArrayList<String>()
   val Passwords =ArrayList<String>()
  init {
    Emails.add("Ahmed")
    Passwords.add("123")
    _shoesList.value=shoeList()
}


  public fun addUser (email:String , Password:String) {
    Emails.add(email)
    Passwords.add(Password)
  }
  fun addShoe(shoe: Shoe){
    _shoe.value = shoe
    _shoesList.value?.add(shoe)
   // _shoesList.value =_shoesList.value
    Log.i("ModelShoe" , _shoesList.value.toString())
  }
  private fun shoeList(): MutableList<Shoe> {
    return mutableListOf(
      Shoe(
        name = "Nike",
        size = "42",
        company = "Nike",
        description = "Black Kyrie 5"
      )
    )

  }




}

