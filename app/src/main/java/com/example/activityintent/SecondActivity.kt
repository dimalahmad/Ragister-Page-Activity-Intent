package com.example.activityintent

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Find Views by ID
        val loginButton = findViewById<Button>(R.id.loginButton)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordLoginText)
        val registerText = findViewById<TextView>(R.id.registerText)

        // Set button click listener to validate login
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val storedUsername = sharedPreferences.getString("username", null)
            val storedPassword = sharedPreferences.getString("password", null)

            if (username == storedUsername && password == storedPassword) {
                // Navigate to ThirdActivity with user data
                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // Register text click listener
        val registerTextView = findViewById<TextView>(R.id.registerText)
        registerTextView.setOnClickListener {
            // Create Intent to start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Optionally finish SecondActivity to remove it from back stack
            finish()
        }
    }
}
