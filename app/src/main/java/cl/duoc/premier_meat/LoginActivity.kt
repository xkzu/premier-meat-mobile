package cl.duoc.premier_meat

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class LoginActivity : ComponentActivity() {

    private val VOICE_REQUEST_CODE = 100
    private var campoActual = ""

    private val emailState = mutableStateOf("")
    private val passwordState = mutableStateOf("")
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos Firebase Auth
        auth = FirebaseAuth.getInstance()

        setContent {
            LoginScreen(
                onLoginClick = { email, password ->
                    iniciarSesionConFirebase(email, password)
                },
                onBackClick = { volver() },
                onVoiceInputClick = { campo ->
                    campoActual = campo
                    iniciarReconocimientoDeVoz()
                },
                emailState = emailState,
                passwordState = passwordState
            )
        }
    }

    private fun iniciarReconocimientoDeVoz() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga el $campoActual")
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
            val resultado = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            var textoHablado = resultado?.get(0)?.toLowerCase(Locale("es", "ES")) ?: ""

            if (campoActual == "email") {
                textoHablado = textoHablado.replace("arroba", "@").replace(" ", "")
                emailState.value = textoHablado
            } else if (campoActual == "contraseña") {
                passwordState.value = textoHablado
            }
        }
    }

    private fun iniciarSesionConFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MenuActivity::class.java)
                    intent.putExtra("user_email", email)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Autenticación fallida: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun volver() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
