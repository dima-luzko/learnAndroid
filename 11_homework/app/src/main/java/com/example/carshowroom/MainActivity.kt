package com.example.carshowroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addCarToRecyclerView()
    }

    private fun addCarToRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.car_recyclerview)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = CarAdapter(carsList()) {

            }
            hasFixedSize()
        }
    }

    private fun carsList() = listOf(
        Car(
            carImage = R.drawable.tesla_car,
            carIconBrand = R.drawable.tesla_icon,
            carBrand = getString(R.string.tesla)
        ),
        Car(
            carImage = R.drawable.audi_a6_car,
            carIconBrand = R.drawable.audi_icon,
            carBrand = getString(R.string.audi)
        ),
        Car(
            carImage = R.drawable.bmw_i3_car,
            carIconBrand = R.drawable.bmw_icon,
            carBrand = getString(R.string.bmw)
        ),
        Car(
            carImage = R.drawable.nissan_micra_car,
            carIconBrand = R.drawable.nissan_icon,
            carBrand = getString(R.string.nissan)
        ),
        Car(
            carImage = R.drawable.porsche_930_car,
            carIconBrand = R.drawable.porche_icon,
            carBrand = getString(R.string.porsche)
        ),
        Car(
            carImage = R.drawable.cherry_tigo_car,
            carIconBrand = R.drawable.chery_icon,
            carBrand = getString(R.string.cherry)
        ),
        Car(
            carImage = R.drawable.audi_car,
            carIconBrand = R.drawable.audi_icon,
            carBrand = getString(R.string.audi)
        )
    )
}