package cl.duoc.premier_meat

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.duoc.premier_meat.model.User

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usamos Jetpack Compose para establecer el contenido de la pantalla principal
        setContent {
            MainScreen(
                onLoginClick = {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                },
                onRegisterClick = {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                }
            )
        }

        // Agregar usuarios a la lista
        UserData.userList.add(adminUser())
        addUsersToList()
    }

    private fun adminUser(): User {
        return User("admin@admin.com", "admin", "admin", true)
    }

    private fun addUsersToList() {
        val users = listOf(
            User("juanita@duoc.cl", "Juanita", "1234", false),
            User("fernanda@duoc.cl", "Fernanda", "1234", false),
            User("emilio@duoc.cl", "Emilio", "1234", false),
            User("javier@duoc.cl", "Javier", "1234", false),
            User("fernando@duoc.cl", "Fernando", "1234", false)
        )
        UserData.userList.addAll(users)
    }

    // Objeto para almacenar datos del usuario
    object UserData {
        val userList = mutableListOf<User>()
    }
}
