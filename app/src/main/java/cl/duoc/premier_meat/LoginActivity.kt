package cl.duoc.premier_meat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.duoc.premier_meat.model.User

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(
                onLoginClick = { email, password ->  // Pasar los parámetros email y password
                    login(email, password)
                },
                onBackClick = { goHome() }
            )
        }
    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun login(email: String, password: String) {
        val user = findUser(email, password)

        if (user != null) {
            if (user.admin) {
                goToAdminMenu(user)
            } else {
                goToMenu(user)
            }
        } else {
            Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun findUser(email: String, password: String): User? {
        return MainActivity.UserData.userList.find {
            it.email == email && it.password == password
        }
    }

    private fun goToAdminMenu(user: User) {
        val intent = Intent(this, AdminMenuActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun goToMenu(user: User) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}
