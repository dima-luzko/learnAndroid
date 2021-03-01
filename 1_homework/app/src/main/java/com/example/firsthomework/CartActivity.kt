package com.example.firsthomework

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CartActivity : AppCompatActivity() {

    var addresses = arrayOf("dima1180807@gmail.com")
    var subject = "Order from car showroom"
    var emailText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val imageOrderIntent = intent.getSerializableExtra("carImage")
        val carPrice: String? = intent.getStringExtra("carPrice")
        val carBodyType: String? = intent.getStringExtra("carBodyType")
        val carYear: String? = intent.getStringExtra("carYear")
        val carPower: String? = intent.getStringExtra("carPower")
        val carEngineVolume: String? = intent.getStringExtra("carEngineVolume")
        val carAcceleration: String? = intent.getStringExtra("carAcceleration")
        val carName: String? = intent.getStringExtra("carName")


        findViewById<TextView>(R.id.orderPrice).text = carPrice
        findViewById<TextView>(R.id.orderBodyType).text = carBodyType
        findViewById<TextView>(R.id.orderYear).text = carYear
        findViewById<TextView>(R.id.orderPower).text = carPower
        findViewById<TextView>(R.id.orderEngineVolume).text = carEngineVolume
        findViewById<TextView>(R.id.orderAcceleration).text = carAcceleration
        findViewById<TextView>(R.id.orderCarName).text = carName

        emailText = getString(R.string.car) + " " + carName + "\n" +
                getString(R.string.body_type) + " " + carBodyType + "\n" +
                getString(R.string.years_of_issue) + " " + carYear + "\n" +
                getString(R.string.power) + " " + carPower + "\n" +
                getString(R.string.engine_volume) + " " +  carEngineVolume + "\n" +
                getString(R.string.acceleration) + " " +  carAcceleration + "\n" +
                getString(R.string.price) + " " + carPrice + "$" + "\n"

        val image = findViewById<ImageView>(R.id.orderImage)



        image.setImageResource(imageOrderIntent as Int)


    }

    override fun onStart() {
        super.onStart()
        sendMail()
    }


    private fun sendMail() {
        val button = findViewById<Button>(R.id.sendMail)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, emailText)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

    }


}

