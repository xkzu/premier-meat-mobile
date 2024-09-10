package cl.duoc.premier_meat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import cl.duoc.premier_meat.model.User

class MenuActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getParcelableExtra<User>("user", User::class.java)
        val userBackup: String = user?.name ?: "Usuario"

        setContent {
            MenuScreen(
                userName = userBackup,
                onChangePasswordClick = {
                    goToChangePassword(user)
                },
                onBackClick = {
                    goToBack()
                }
            )
        }
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
