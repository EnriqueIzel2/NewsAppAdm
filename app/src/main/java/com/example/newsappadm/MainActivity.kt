package com.example.newsappadm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsappadm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar!!.hide()

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnNextScreen.setOnClickListener {
      goToNextScreen()
    }
  }

  private fun goToNextScreen() {
    val nextScreen = Intent(this, CreateNews::class.java)
    startActivity(nextScreen)
  }
}