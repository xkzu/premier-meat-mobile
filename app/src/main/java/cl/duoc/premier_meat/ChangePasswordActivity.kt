package cl.duoc.premier_meat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.duoc.premier_meat.model.User

class ChangePasswordActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getParcelableExtra<User>("user", User::class.java)
        val changePasswordButton: Button = findViewById(R.id.btnChangePassword)
        val backButton: Button = findViewById(R.id.btnBack)

        changePasswordButton.setOnClickListener {
            val currectPassword = findViewById<EditText>(R.id.editTextPasswordCurrent).text.toString()
            val newPassword = findViewById<EditText>(R.id.editTextNewPassword).text.toString()
            val newPassword2 = findViewById<EditText>(R.id.editTextNewPassword2).text.toString()

            if (!emptyValues(currectPassword, newPassword, newPassword2)) {
                if (passwordValidate(newPassword, newPassword2)) {
                    user?.let { validUser ->
                        val userFound = getUserByEmail(validUser.email)
                        if (userFound != null && userFound.password == currectPassword) {

                            val updatedUser = userFound.copy(password = newPassword)

                            val userIndex = MainActivity.UserData.userList.indexOf(userFound)
                            if (userIndex != -1) {
                                MainActivity.UserData.userList[userIndex] = updatedUser
                                Toast.makeText(this, "Contraseña actualizada exitosamente", Toast.LENGTH_SHORT).show()
                                goToLogin()
                            }

                        } else {
                            Toast.makeText(this, "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show()
                        }
                    } ?: run {
                        Toast.makeText(this, "Usuario no válido", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingresa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            goBack()
        }
    }

    private fun emptyValues(currentPassword: String, newPassword: String, newPassword2: String): Boolean {
        return currentPassword.isEmpty() || newPassword.isEmpty() || newPassword2.isEmpty()
    }

    private fun passwordValidate(newPassword: String, newPassword2: String): Boolean {
        return newPassword == newPassword2
    }

    private fun getUserByEmail(email: String): User? {
        return MainActivity.UserData.userList.find {
            email == it.email
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity:: class.java)
        startActivity(intent)
    }

    private fun goBack() {
        val intent = Intent(this, MenuActivity:: class.java)
        startActivity(intent)
    }
}
