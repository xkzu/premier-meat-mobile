package cl.duoc.premier_meat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.duoc.premier_meat.model.User

class AdminMenuActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getParcelableExtra<User>("user", User::class.java)
        val backButton: Button = findViewById(R.id.btnBack)
        var userBackup: String = ""

        if (user != null && user.name != "") {
            userBackup = user.name
        }
        val allUsersButton: Button = findViewById(R.id.btnListUsers)

        if (user != null && user.name != "") {
            welcome(user.name)
        } else {
            welcome(userBackup)
        }

        allUsersButton.setOnClickListener {
            goToAllUsers()
        }

        backButton.setOnClickListener {
            goToBack()
        }
    }

    private fun welcome(name: String) {
        findViewById<TextView>(R.id.textWelcome).text = "Bienvenido ${name}, que te gustaría hacer?"
    }

    private fun goToAllUsers() {
        val intent = Intent(this, AllUsersActivity::class.java)
        startActivity(intent)
    }

    private fun goToBack() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}