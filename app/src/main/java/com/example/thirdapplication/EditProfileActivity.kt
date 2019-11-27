package com.example.thirdapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_edit_profile.*

// edit name, sex, age
class EditProfileActivity : AppCompatActivity() {
    private var selectedAge: Int = 0
    private var selectedSex: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        initialize()
        saveButton.setOnClickListener { onSaveButtonTapped() }
    }

    private fun initialize() {
        val pref = getSharedPreferences("PROFILE", Context.MODE_PRIVATE)
        val name = pref.getString("NAME", "")
        val sex = pref.getString("SEX", "")
        val age = pref.getInt("AGE", 0)

        nameInput.setText(name)

        sexOptions.setOnCheckedChangeListener { _, checkedId ->
            selectedSex = findViewById<RadioButton>(checkedId).text.toString()
        }

        ageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val spinner = parent as? Spinner
                    val item = spinner?.selectedItem as? String
                    item?.let {
                        if (it.isNotEmpty()) {
                            selectedAge = it.toInt()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun onSaveButtonTapped() {
        val pref = getSharedPreferences("PROFILE", Context.MODE_PRIVATE)


        pref.edit {
            putString("NAME", nameInput.text.toString())
            putString("SEX", selectedSex)
            putInt("AGE", selectedAge)
        }
        finish()
    }


}
