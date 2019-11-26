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
//        val weight = pref.getFloat("WEIGHT", 52.5f)
        val weight = pref.getInt("WEIGHT", 52)

        val ageText = "${age}歳"
        val heightText = "${height}cm"
        val weightText = "${weight}kg"

        value_name.text = name
        value_sex.text = sex
        value_age.text = ageText
        value_height.text = heightText
        value_weight.text = weightText

        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        editBodyInfoButton.setOnClickListener {
            val intent = Intent(this, EditBodyInfoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        initialize()
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show()
    }
}
