package com.example.test3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.a.SecondActivity

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create spinner object
        val spinner: Spinner = findViewById(R.id.CourseSpinner)
        spinner.onItemSelectedListener = this
        //create array adapter
        ArrayAdapter.createFromResource(
            this,
            R.array.courseCode,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        //create button click listener
        val scoreButton: Button = findViewById(R.id.scoreButton)
        scoreButton.setOnClickListener {
            val score: EditText = findViewById(R.id.Score)

            val result = when{
                score.text.toString().toDouble() >= 80 -> "A"
                score.text.toString().toDouble() >= 75 -> "A-"
                score.text.toString().toDouble() >= 70 -> "B+"
                score.text.toString().toDouble() >= 65 -> "B"
                score.text.toString().toDouble() >= 60 -> "B-"
                score.text.toString().toDouble() >= 55 -> "C+"
                score.text.toString().toDouble() >= 50 -> "C"
                else -> "D"
            }

            //store name input to second activity
            val nameEditText: EditText = findViewById(R.id.nameEdit)
            val inputName = nameEditText.text.toString()
            //store course selection result to second activity
            val courseSelection: Spinner = findViewById(R.id.CourseSpinner)
            val selectedItem = courseSelection.selectedItem.toString()
            //store score to second activity
            val scoreEditText: EditText = findViewById(R.id.Score)
            val scoreInput = scoreEditText.text.toString()
            //store grade to second activity
            val resultText: TextView = findViewById(R.id.resultText)
            resultText.text = getString(R.string.resultText) + " " + result
            val resultGet = resultText.text.toString()

            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("input_name", inputName)
            intent.putExtra("selected_Course", selectedItem)
            intent.putExtra("score_Input", scoreInput)
            intent.putExtra("result_Get", resultGet)
            startActivity(intent)
        }

        //create intent for contact button
        /*findViewById<Button>(R.id.ContactUsButton).setOnClickListener {
            startActivity(contactDE())
        }*/
        val contactButton: Button = findViewById(R.id.ContactUsButton)
        contactButton.setOnClickListener {
            startActivity(contactDE())
        }

        //create intent for call us button
        val callUSButton: Button = findViewById(R.id.callButton)
        callUSButton.setOnClickListener {
            startActivity(callUS())
        }

    }

    private fun callUS(): Intent? {
        val phoneNumber = "012345678"
        val dailIntent = Intent(Intent.ACTION_DIAL)
        dailIntent.data = Uri.parse("tel:$phoneNumber")
        return dailIntent
    }

    private fun contactDE(): Intent? {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("departmentOfExam@gmail.com"))
        emailIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Name: ${findViewById<EditText>(R.id.nameEdit).text}\n"
                    +"Course: ${findViewById<TextView>(R.id.SelectionResults).text}\n"
                    +"Score: ${findViewById<EditText>(R.id.Score).text}\n"
                    +"Grade: ${findViewById<EditText>(R.id.resultText).text}\n"
        )
        return contactDE()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectionTv: TextView = findViewById(R.id.SelectionResults)
        selectionTv.text = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        val selectionTy: TextView = findViewById(R.id.SelectionResults)
        selectionTy.text = "You have not make a selection yet"
    }


}