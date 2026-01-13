package com.mastermovilesua.persistencia.preferencias.pddm_cp_client

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val AUTHORITY =
        "com.mastermovilesua.persistencia.preferencias.sqliteusers.usersprovider"
    private val USERS_URI: Uri = Uri.parse("content://$AUTHORITY/usuarios")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val user = etUser.text.toString().trim()
            val pass = etPass.text.toString()

            val ok = checkLoginViaProvider(user, pass)

            Log.d("CP_CLIENT", "user=$user pass=$pass ok=$ok")

            if (ok) {
                Toast.makeText(this, "Login OK", Toast.LENGTH_SHORT).show()

                // Navegar a la pantalla User
                val i = Intent(this, UserActivity::class.java).apply {
                    putExtra("username", user)
                }
                startActivity(i)

            } else {
                Toast.makeText(
                    this,
                    "Error usuario/password incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkLoginViaProvider(username: String, password: String): Boolean {
        if (username.isBlank() || password.isBlank()) return false

        // Con traer una columna basta; idealmente el ID.
        // Nota: tu BD usa "id" (no "_id"), así que "id" es correcto en tu caso.
        val projection = arrayOf("id")
        val selection = "username=? AND password=?"
        val selectionArgs = arrayOf(username, password)

        val cursor = contentResolver.query(
            USERS_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            return it.count > 0
        }
        return false
    }
}
