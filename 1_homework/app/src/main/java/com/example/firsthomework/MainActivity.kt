package com.example.firsthomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        clicks()
    }


    private fun clicks() {
        var currentIndex = 0

        val carBodyTypeArray: Array<Int> = arrayOf(R.string.sedan, R.string.jeep, R.string.sedan)
        val carBodyType = findViewById<TextView>(R.id.body_type)

        val carYearArray: Array<Int> = arrayOf(R.string.porsche_911_year,
            R.string.porsche_cayenne_year,
            R.string.porsche_taycan_year)
        val carYear = findViewById<TextView>(R.id.carYear)

        val carPowerArray: Array<Int> = arrayOf(R.string.porsche_911_power_value,
            R.string.porsche_cayenne_power_value,
            R.string.porsche_taycan_power_value)
        val carPowerValue = findViewById<TextView>(R.id.carPowerValue)

        val carVolumeArray: Array<Int> = arrayOf(R.string.porsche_911_volume,
            R.string.porsche_cayenne_volume,
            R.string.porsche_taycan_volume)
        val carVolume = findViewById<TextView>(R.id.carVolume)

        val carAccelerationArray: Array<Int> = arrayOf(R.string.porsche_911_acceleration_value,
            R.string.porsche_cayenne_acceleration_value,
            R.string.porsche_taycan_acceleration_value)
        val carAccelerationValue = findViewById<TextView>(R.id.carAccelerationValue)

        val carPriceArray: Array<Int> = arrayOf(R.string.porsche_911_price_value,
            R.string.porsche_cayenne_price_value,
            R.string.porsche_taycan_price_value)
        val carPrice = findViewById<TextView>(R.id.carPrice)

        val carArrayName: Array<Int> =
            arrayOf(R.string.porsche_911, R.string.porsche_cayenne, R.string.porsche_taycan)
        val carName = findViewById<TextView>(R.id.carName)

        val carImagesArray: Array<Int> = arrayOf(R.drawable.porsche_911_carrera,
            R.drawable.porsche_cayenne,
            R.drawable.porsche_taycan)
        val imageCar = findViewById<ImageView>(R.id.car)

        val imageArrowLeft = findViewById<ImageButton>(R.id.arrowLeft)
        val imageArrowRight = findViewById<ImageButton>(R.id.arrowRight)

        imageArrowLeft.setOnClickListener {
            if (currentIndex < carImagesArray.size - 1 && currentIndex < carArrayName.size - 1 && currentIndex < carPriceArray.size - 1
                && currentIndex < carBodyTypeArray.size - 1 && currentIndex < carYearArray.size - 1 && currentIndex < carPowerArray.size - 1
                && currentIndex < carVolumeArray.size - 1 && currentIndex < carAccelerationArray.size - 1
            ) {
                currentIndex++
                imageCar.setImageResource(carImagesArray[currentIndex])
                carName.text = getString(carArrayName[currentIndex])
                carPrice.text = getString(carPriceArray[currentIndex])
                carBodyType.text = getString(carBodyTypeArray[currentIndex])
                carYear.text = getString(carYearArray[currentIndex])
                carPowerValue.text = getString(carPowerArray[currentIndex])
                carVolume.text = getString(carVolumeArray[currentIndex])
                carAccelerationValue.text = getString(carAccelerationArray[currentIndex])

            } else if (currentIndex >= 3) {
                imageCar.setImageResource(carImagesArray[currentIndex])
                carName.text = getString(carArrayName[currentIndex])
                carPrice.text = getString(carPriceArray[currentIndex])
                carBodyType.text = getString(carBodyTypeArray[currentIndex])
                carPowerValue.text = getString(carPowerArray[currentIndex])
                carVolume.text = getString(carVolumeArray[currentIndex])
                carAccelerationValue.text = getString(carAccelerationArray[currentIndex])
            }
        }

        imageArrowRight.setOnClickListener {
            if (currentIndex >= carImagesArray.size - 1 && currentIndex >= carArrayName.size - 1 && currentIndex >= carPriceArray.size - 1
                && currentIndex >= carBodyTypeArray.size - 1 && currentIndex >= carYearArray.size - 1 && currentIndex >= carPowerArray.size - 1
                && currentIndex >= carVolumeArray.size - 1 && currentIndex >= carAccelerationArray.size - 1
            ) {
                currentIndex--
                imageCar.setImageResource(carImagesArray[currentIndex])
                carName.text = getString(carArrayName[currentIndex])
                carPrice.text = getString(carPriceArray[currentIndex])
                carBodyType.text = getString(carBodyTypeArray[currentIndex])
                carYear.text = getString(carYearArray[currentIndex])
                carPowerValue.text = getString(carPowerArray[currentIndex])
                carVolume.text = getString(carVolumeArray[currentIndex])
                carAccelerationValue.text = getString(carAccelerationArray[currentIndex])

            } else if (currentIndex == 1) {
                currentIndex = 0
                imageCar.setImageResource(carImagesArray[currentIndex])
                carName.text = getString(carArrayName[currentIndex])
                carPrice.text = getString(carPriceArray[currentIndex])
                carBodyType.text = getString(carBodyTypeArray[currentIndex])
                carYear.text = getString(carYearArray[currentIndex])
                carPowerValue.text = getString(carPowerArray[currentIndex])
                carVolume.text = getString(carVolumeArray[currentIndex])
                carAccelerationValue.text = getString(carAccelerationArray[currentIndex])
            }
        }
    }

    private fun changeCharacteristicsOfCar() {
        val characteristics = CarCharacteristics()
        characteristics.carBodyType = getString(R.string.jeep)
        characteristics.carYearsOfIssue
        characteristics.carPower
        characteristics.carEngineVolume
        characteristics.carAcceleration

    }
}

