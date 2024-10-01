package cl.duoc.premier_meat

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.duoc.premier_meat.model.User
import java.util.Locale

class MainActivity : ComponentActivity() {

    private val VOICE_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                onLoginClick = {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                },
                onRegisterClick = {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                },
                onVoiceInputClick = {
                    iniciarReconocimientoDeVoz()
                }
            )
        }

        UserData.userList.add(adminUser())
        agregarUsuariosALista()
    }

    private fun iniciarReconocimientoDeVoz() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga el comando o el email")
        }

        try {
            startActivityForResult(intent, VOICE_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Reconocimiento de voz no soportado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VOICE_REQUEST_CODE && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val textoHablado = result?.get(0)?.toLowerCase(Locale("es", "ES"))

            if (textoHablado != null) {
                val textoProcesado = manejarEntradaDeVoz(textoHablado)

                when (textoProcesado) {
                    "ingresar" -> iniciarSesion()
                    "registrar" -> navegarARegistro()
                    else -> Toast.makeText(this, "Texto procesado: $textoProcesado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun manejarEntradaDeVoz(textoHablado: String): String {
        return textoHablado
            .replace("aurora", "@")
            .replace("arroba", "@")
            .replace("at", "@")
            .replace("signo arroba", "@")
            .replace("símbolo arroba", "@")
            .replace("espacio", "") // Para evitar espacios en los emails
    }

    private fun iniciarSesion() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun navegarARegistro() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun adminUser(): User {
        return User("admin@admin.com", "admin", "admin", true)
    }

    private fun agregarUsuariosALista() {
        val usuarios = listOf(
            User("juanita@duoc.cl", "Juanita", "1234", false),
            User("fernanda@duoc.cl", "Fernanda", "1234", false),
            User("emilio@duoc.cl", "Emilio", "1234", false),
            User("javier@duoc.cl", "Javier", "1234", false),
            User("fernando@duoc.cl", "Fernando", "1234", false)
        )
        UserData.userList.addAll(usuarios)
    }

    object UserData {
        val userList = mutableListOf<User>()
    }
}
