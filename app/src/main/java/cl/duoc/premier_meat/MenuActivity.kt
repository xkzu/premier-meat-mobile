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

class MenuActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getParcelableExtra<User>("user", User::class.java)
        val changePasswordButton: Button = findViewById(R.id.btnChangePassword)
        val backButton: Button = findViewById(R.id.btnBack)
        var userBackup: String = ""

        if (user != null && user.name != "") {
            userBackup = user.name
        }

        if (user != null && user.name != "") {
            welcome(user.name)
        } else {
            welcome(userBackup)
        }

        changePasswordButton.setOnClickListener {
            goToChangePassword(user)
        }

        backButton.setOnClickListener {
            goToBack()
        }
    }

    private fun welcome(name: String) {
        findViewById<TextView>(R.id.textWelcome).text = "Bienvenido ${name}, que te gustaría hacer?"
    }

    private fun goToChangePassword(user: User?) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun goToBack() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}