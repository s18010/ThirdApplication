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
    private var height: Int = 0
    private var weight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_body_info)

        saveButton.setOnClickListener { onSaveButtonTapped() }
        seekBarHandler()
    }

    private fun seekBarHandler() {
        val pref = getSharedPreferences("PROFILE", Context.MODE_PRIVATE)
        height = pref.getInt("HEIGHT", 160)
        weight = pref.getInt("WEIGHT", 52)

        value_height.text = "%dcm".format(height)
        heightSeekBar.progress = height

        value_weight.text = "%.1fkg".format(weight.toDouble() / 10)
        weightSeekBar.progress = weight / 10

        heightSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    value_height.text = "%dcm".format(progress)
                    height = progress
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        weightSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    value_weight.text = "%.1fkg".format(progress.toDouble() / 10)
                    weight = progress
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
    }

    override fun onPause() {
        super.onPause()
        val pref = getSharedPreferences("PROFILE", Context.MODE_PRIVATE)
        pref.edit {
            putInt("HEIGHT", height)
            putInt("WEIGHT", weight)
        }
    }

    private fun onSaveButtonTapped() {
        finish()
    }
}
