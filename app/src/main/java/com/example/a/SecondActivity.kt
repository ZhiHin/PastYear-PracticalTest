package com.example.a

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.test3.MainActivity
import com.example.test3.R
import org.w3c.dom.Text

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val inputName = intent.getStringExtra("input_name")
        val name: TextView = findViewById(R.id.nameDisplay)
        name.text = inputName

        val inputCourse = intent.getStringExtra("selected_Course")
        val course: TextView = findViewById(R.id.courseDisplay)
        course.text = inputCourse

        val inputScore = intent.getStringExtra("score_Input")
        val score: TextView = findViewById(R.id.scoreDisplay)
        score.text = inputScore

        val gradeGet = intent.getStringExtra("result_Get")
        val grade: TextView = findViewById(R.id.gradeDisplay)
        grade.text = gradeGet

        //create button onClick
        val emailButton: Button = findViewById(R.id.EmailButton)
        emailButton.setOnClickListener { 
            startActivity(emailDE())
        }
        val callButton: Button = findViewById(R.id.CallButton2)
        callButton.setOnClickListener {
            startActivity(callDE())
        }

        //back to main activity
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            //finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    private fun callDE(): Intent? {
        val phoneNumber = "01234567899"
        val callUs = Intent(Intent.ACTION_DIAL)
        callUs.data = Uri.parse("tel:$phoneNumber")
        return callUs
    }

    private fun emailDE(): Intent? {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("departmetnOfExam@gmail.com"))
        emailIntent.putExtra(
            Intent.EXTRA_TEXT,
        "Name: ${findViewById<TextView>(R.id.nameDisplay).text}\n"
                +"Course: ${findViewById<TextView>(R.id.courseDisplay).text}\n"
                +"Score: ${findViewById<TextView>(R.id.scoreDisplay).text}\n"
                +"Grade: ${findViewById<TextView>(R.id.gradeDisplay).text}"
        )
        return  emailIntent
    }


}