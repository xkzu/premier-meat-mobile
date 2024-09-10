package cl.duoc.premier_meat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.premier_meat.model.User

class ChangePasswordActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getParcelableExtra<User>("user", User::class.java)

        setContent {
            ChangePasswordScreen(
                onChangePasswordClick = { currentPassword, newPassword, newPassword2 ->
                    if (!emptyValues(currentPassword, newPassword, newPassword2)) {
                        if (passwordValidate(newPassword, newPassword2)) {
                            user?.let { validUser ->
                                val userFound = getUserByEmail(validUser.email)
                                if (userFound != null && userFound.password == currentPassword) {
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
                },
                onBackClick = { goBack() }
            )
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
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goBack() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun ChangePasswordScreen(
    onChangePasswordClick: (String, String, String) -> Unit,
    onBackClick: () -> Unit
) {
    val currentPasswordState = remember { mutableStateOf("") }
    val newPasswordState = remember { mutableStateOf("") }
    val newPasswordConfirmState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cambia tu contraseña",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFDAA520),
            modifier = Modifier.padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = currentPasswordState.value,
            onValueChange = { currentPasswordState.value = it },
            label = {
                Text(
                    text = "Contraseña actual",
                    color = Color(0xFFF5F5F5),
                    fontSize = 24.sp
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(80.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.Transparent,
                focusedBorderColor = Color(0xFFDAA520),
                unfocusedBorderColor = Color(0xFFF5F5F5),
                textColor = Color.White
            ),
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = newPasswordState.value,
            onValueChange = { newPasswordState.value = it },
            label = {
                Text(
                    text = "Nueva contraseña",
                    color = Color(0xFFF5F5F5),
                    fontSize = 24.sp
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(80.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.Transparent,
                focusedBorderColor = Color(0xFFDAA520),
                unfocusedBorderColor = Color(0xFFF5F5F5),
                textColor = Color.White
            ),
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = newPasswordConfirmState.value,
            onValueChange = { newPasswordConfirmState.value = it },
            label = {
                Text(
                    text = "Confirmar nueva contraseña",
                    color = Color(0xFFF5F5F5),
                    fontSize = 24.sp
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(80.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.Transparent,
                focusedBorderColor = Color(0xFFDAA520),
                unfocusedBorderColor = Color(0xFFF5F5F5),
                textColor = Color.White
            ),
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { onChangePasswordClick(currentPasswordState.value, newPasswordState.value, newPasswordConfirmState.value) },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF8B0000),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Cambiar contraseña",
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF8B0000),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Volver",
                fontSize = 37.sp
            )
        }
    }
}
