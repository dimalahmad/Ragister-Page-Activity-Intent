package com.example.activityintent

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Find Views by ID
        val registerButton = findViewById<Button>(R.id.registerButton)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)

        // Set button click listener to register user
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save data to SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.putString("email", email)
                editor.putString("phone", phone)
                editor.apply()

                // Navigate to SecondActivity
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }
        val loginTextView = findViewById<TextView>(R.id.loginTextView)
        loginTextView.setOnClickListener {
            // Create Intent to start MainActivity
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            // Optionally finish SecondActivity to remove it from back stack
            finish()
        }
    }
}
