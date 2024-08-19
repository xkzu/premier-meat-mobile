package cl.duoc.premier_meat

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.duoc.premier_meat.model.User
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        UserData.userList.add(adminUser())
        addUsersToList()

        val loginButton: Button = findViewById(R.id.btnLogin)
        val registerButton: Button = findViewById(R.id.btnRegister)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity:: class.java)
            startActivity(intent)
        }
    }

    private fun adminUser(): User {
        return User("admin@admin.com","admin","admin", true)
    }

    private fun addUsersToList() {
        val user1 = User("juanita@duoc.cl", "Juanita", "1234", false)
        val user2 = User("fernanda@duoc.cl", "Fernanda", "1234", false)
        val user3 = User("emilio@duoc.cl", "Emilio", "1234", false)
        val user4 = User("javier@duoc.cl", "Javier", "1234", false)
        val user5 = User("fernando@duoc.cl", "Fernando", "1234", false)

        UserData.userList.add(user1)
        UserData.userList.add(user2)
        UserData.userList.add(user3)
        UserData.userList.add(user4)
        UserData.userList.add(user5)
    }

    object UserData {
        val userList = mutableListOf<User>()
    }
}