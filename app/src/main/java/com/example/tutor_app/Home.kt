package com.example.tutor_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        find_tutors.setOnClickListener {
            val intent=Intent(this, Find_tutors::class.java)
            startActivity(intent)
        }
        find_tutorss.setOnClickListener {
            val intent=Intent(this, Find_tutors::class.java)
            startActivity(intent)
        }
        account_me.setOnClickListener {
            val intent=Intent(this, Me::class.java)
            startActivity(intent)
        }
        me.setOnClickListener {
            val intent=Intent(this, Me::class.java)
            startActivity(intent)
        }
        become_tutors.setOnClickListener {
            val intent=Intent(this, Become_tutors::class.java)
            startActivity(intent)
        }
        beco_tutor.setOnClickListener {
            val intent=Intent(this, Become_tutors::class.java)
            startActivity(intent)
        }
        help_you.setOnClickListener {
            val intent=Intent(this, Help::class.java)
            startActivity(intent)
        }
        Helpp_you.setOnClickListener {
            val intent=Intent(this, Help::class.java)
            startActivity(intent)
        }
        danhgia.setOnClickListener {
            val intent=Intent(this, Parentt::class.java)
            startActivity(intent)
        }

    }
}