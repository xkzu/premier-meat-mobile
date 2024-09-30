package cl.duoc.premier_meat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PasswordChangeScreen(
                onChangePasswordClick = { currentPassword, newPassword, newPassword2 ->
                    if (currentPassword.isNotEmpty() && newPassword.isNotEmpty() && newPassword2.isNotEmpty()) {
                        if (newPassword == newPassword2) {
                            updatePassword(currentPassword, newPassword)
                        } else {
                            Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                onBackClick = { goBack() }
            )
        }
    }

    private fun updatePassword(currentPassword: String, newPassword: String) {
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null && user.email != null) {
            val credential = EmailAuthProvider.getCredential(user.email!!, currentPassword)
            user.reauthenticate(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.updatePassword(newPassword).addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            Toast.makeText(this, "Contraseña actualizada con éxito", Toast.LENGTH_SHORT).show()
                            goToLogin()
                        } else {
                            Toast.makeText(this, "Error al actualizar la contraseña", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goBack() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}
