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
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance() // Inicializar FirebaseAuth

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val registerButton: Button = findViewById(R.id.btnRegisterUser)
        val backButton: Button = findViewById(R.id.btnBack)

        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val name = findViewById<EditText>(R.id.editTextName).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            val password2 = findViewById<EditText>(R.id.editTextPassword2).text.toString()

            if (!validateEditText(email, name, password, password2)) {
                if (validatePassword(password, password2)) {
                    registerUserWithFirebase(email, password)
                } else {
                    Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingresa todos los campos antes de registrar", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUserWithFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                    goToLogin()
                } else {
                    Toast.makeText(this, "Error al registrar usuario: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun validatePassword(password: String, password2: String): Boolean {
        return password == password2
    }

    private fun validateEditText(email: String, name: String, password: String, password2: String): Boolean {
        return email.isEmpty() || name.isEmpty() || password.isEmpty() || password2.isEmpty()
    }
}
