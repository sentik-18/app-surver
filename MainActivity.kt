
package com.example.appsurver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val sendButton = findViewById<Button>(R.id.sendButton)

        sendButton.setOnClickListener {
            val phone = phoneInput.text.toString().trim()
            val amount = amountInput.text.toString().trim()

            if (phone.isNotEmpty() && amount.isNotEmpty()) {
                val ref = FirebaseDatabase.getInstance().getReference("flexyRequests")
                val data = mapOf(
                    "phone" to phone,
                    "amount" to amount,
                    "timestamp" to System.currentTimeMillis()
                )
                ref.push().setValue(data)

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/mobaily_bot"))
                startActivity(intent)
            }
        }
    }
}
