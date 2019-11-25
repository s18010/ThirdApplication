package com.example.thirdapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        val pref = getSharedPreferences("PROFILE", Context.MODE_PRIVATE)
        val name = pref.getString("NAME", "テスト 太郎")
        val sex = pref.getString("SEX", "男")
        val age = pref.getInt("AGE", 42)
        val height = pref.getInt("HEIGHT", 160)
        val weight = pref.getFloat("WEIGHT", 52.5f)

        value_name.text = name
        value_sex.text = sex
        value_age.text = age.toString()

        editProfileButton.setOnClickListener { onEditProfileButtonTapped(it) }
        editBodyInfoButton.setOnClickListener { onEditBodyInfoButtonTapped(it) }
    }

    private fun onEditProfileButtonTapped(view: View?) {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }

    private fun onEditBodyInfoButtonTapped(view: View?) {
        val intent = Intent(this, EditBodyInfoActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        initialize()
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show()
    }
}
