package com.example.admincrud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admincrud.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton
            .setOnClickListener {
                val referenceVehicleNumber = binding.referenceVehicleNumber.text.toString()
                val updateOwnerName = binding.updateOwnerName.text.toString()
                val updateVehicleBrand = binding.updateVehicleBrand.text.toString()
                val updateVehicleRTO = binding.updateVehicleRTO.text.toString()

               fun updateData(VehicleNumber: String, OwnerName: String, vehicleBrand: String, vehicleRTO: String) {

                    databaseReference =
                        FirebaseDatabase.getInstance().getReference("Vehicle Information")
                    val vehicleData = mapOf<String, String>(
                        "ownerName" to OwnerName,
                        "vehicleBrand" to vehicleBrand,
                        "vehicleRTO" to vehicleRTO
                    )
                    databaseReference.child(VehicleNumber).updateChildren(vehicleData)
                        .addOnSuccessListener {
                            binding.referenceVehicleNumber.text.clear()
                            binding.updateOwnerName.text.clear()
                            binding.updateVehicleBrand.text.clear()
                            binding.updateVehicleRTO.text.clear()

                            updateData(
                                referenceVehicleNumber,
                                updateOwnerName,
                                updateVehicleBrand,
                                updateVehicleRTO
                            )
                        }

                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

                }
            }
            .also {
                Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
            }
    }
}



