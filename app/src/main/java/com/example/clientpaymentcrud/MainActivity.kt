package com.example.clientpaymentcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.clientpaymentcrud.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchVehicleNumber : String = binding.searchVehicleNumber.text.toString()
            if  (searchVehicleNumber.isNotEmpty()){
                readData(searchVehicleNumber)
            }else{
                Toast.makeText(this,"PLease enter the Vehicle Number number",Toast.LENGTH_SHORT).show()
            }
        }
    }

        private fun readData(vehicleNumber: String) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
            databaseReference.child(vehicleNumber).get().addOnSuccessListener {
                if (it.exists()) {
                    val ownerName = it.child("ownerName").value
                    val vehicleBrand = it.child("vehicleBrand").value
                    val vehicleRTO = it.child("vehicleRTO").value
                    Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()

                    binding.searchVehicleNumber.text.clear()
                    binding.readOwnerName.text = ownerName.toString()
                    binding.readVehicleBrand.text = vehicleBrand.toString()
                    binding.readvehicleRTO.text = vehicleRTO.toString()
                } else {
                    Toast.makeText(this, "vehicle number does not exist", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

    }
