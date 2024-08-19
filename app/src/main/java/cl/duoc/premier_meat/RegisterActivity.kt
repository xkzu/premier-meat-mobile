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
import cl.duoc.premier_meat.model.User

class RegisterActivity : AppCompatActivity() {

    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userList.add(adminUser())

        val registerButton: Button = findViewById(R.id.btnRegisterUser)
        val backButton: Button = findViewById(R.id.btnBack)

        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val name = findViewById<EditText>(R.id.editTextName).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            val password2 = findViewById<EditText>(R.id.editTextPassword2).text.toString()

            if (!validateEditText(email, name, password, password2)) {
                if (validatePassword(password, password2)) {
                    val newUser = User(email, name, password, false)

                    userList.add(newUser)
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    cleanEditText()
                    goToLogin()
                } else {
                    Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingresa los campos antes de registrar", Toast.LENGTH_SHORT).show()
            }

        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
    }

    private fun cleanEditText() {
        findViewById<EditText>(R.id.editTextEmail).text.clear()
        findViewById<EditText>(R.id.editTextName).text.clear()
        findViewById<EditText>(R.id.editTextPassword).text.clear()
        findViewById<EditText>(R.id.editTextPassword2).text.clear()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity:: class.java)
        startActivity(intent)
    }

    private fun validatePassword(password: String, password2: String): Boolean {
        return password == password2
    }

    private fun validateEditText(email: String, name: String, password: String, password2: String): Boolean {
        return email == ""
                || name == ""
                || password == ""
                || password2 == ""
    }

    private fun adminUser(): User {
        return User("admin@admin.com","admin","admin", true)
    }
}