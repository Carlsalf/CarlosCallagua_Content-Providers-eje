package com.mastermovilesua.persistencia.preferencias.pddm_cp_client

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val tvUserName = findViewById<TextView>(R.id.tvUserNameValue)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val username = intent.getStringExtra("username") ?: ""
        tvUserName.text = username

        btnBack.setOnClickListener { finish() }
    }
}
