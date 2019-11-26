package com.example.thirdapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_edit_body_info.*
import kotlinx.android.synthetic.main.activity_edit_body_info.saveButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.value_height
import kotlinx.android.synthetic.main.activity_main.value_weight

class EditBodyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_body_info)

        saveButton.setOnClickListener { onSaveButtonTapped() }
        seekBarHandler()
    }

    private fun seekBarHandler() {
        val pref = getSharedPreferences("PROFILE", Context.MODE_PRIVATE)
        val height = pref.getInt("HEIGHT", 160)
//        val weight = pref.getFloat("WEIGHT", 52.5f)
        val weight = pref.getInt("WEIGHT", 52)

        val heightText = "${height}cm"
        val weightText = "${weight}kg"

        value_height.text = heightText
        heightSeekBar.progress = height

        value_weight.text = weightText
        weightSeekBar.progress = weight

        heightSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    value_height.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        weightSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
//                    progress: Float,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    value_weight.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
    }

    override fun onPause() {
        super.onPause()
        val pref = getSharedPreferences("PROFILE", Context.MODE_PRIVATE)
        pref.edit {
            putInt("HEIGHT", value_height.text.toString().toInt())
            putInt("WEIGHT", value_weight.text.toString().toInt())
        }
    }

    private fun onSaveButtonTapped() {
        finish()
    }
}
