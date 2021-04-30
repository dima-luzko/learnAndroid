package com.example.carshowroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carshowroom.R
import com.example.carshowroom.data.Car

class CarAdapter(private val carList: List<Car>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val carImage: ImageView = view.findViewById(R.id.car_image)
        val carIconBrand: ImageView = view.findViewById(R.id.car_icon_brand)
        val carBrand: TextView = view.findViewById(R.id.car_brand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car: Car = carList[position]
        with(holder) {
            carImage.setImageResource(car.carImage)
            carIconBrand.setImageResource(car.carIconBrand)
            carBrand.text = car.carBrand
        }
    }

    override fun getItemCount() = carList.size
}