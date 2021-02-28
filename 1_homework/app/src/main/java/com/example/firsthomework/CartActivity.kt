package com.example.firsthomework

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        findViewById<TextView>(R.id.orderPrice).text = intent.getStringExtra("carPrice")
        findViewById<TextView>(R.id.orderBodyType).text = intent.getStringExtra("carBodyType")
        findViewById<TextView>(R.id.orderYear).text = intent.getStringExtra("carYear")
        findViewById<TextView>(R.id.orderPower).text = intent.getStringExtra("carPower")
        findViewById<TextView>(R.id.orderEngineVolume).text = intent.getStringExtra("carEngineVolume")
        findViewById<TextView>(R.id.orderAcceleration).text = intent.getStringExtra("carAcceleration")
        findViewById<TextView>(R.id.orderCarName).text = intent.getStringExtra("carName")

        val image = findViewById<ImageView>(R.id.orderImage)

        val imageOrderIntent = intent.getSerializableExtra("carImage")

        image.setImageResource(imageOrderIntent as Int)


    }
}