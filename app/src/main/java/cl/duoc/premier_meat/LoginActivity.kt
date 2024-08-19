package cl.duoc.premier_meat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val login: Button = findViewById(R.id.btnLogin)
        val backButton: Button = findViewById(R.id.btnBack)

        login.setOnClickListener {
            val email = findViewById<EditText?>(R.id.editTextEmail).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            if (!validateEditText(email, password)) {
                TODO()// IMPLEMENTAR LOGIN
            } else {
                Toast.makeText(this, "Ingresa los campos antes de ingresar", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            goHome()
        }
    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun validateEditText(email: String, password: String): Boolean {
        return email == "" || password == ""
    }
}