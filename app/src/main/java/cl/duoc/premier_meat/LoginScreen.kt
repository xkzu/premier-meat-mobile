package cl.duoc.premier_meat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onBackClick: () -> Unit,
    onVoiceInputClick: (String) -> Unit,
    emailState: MutableState<String>,
    passwordState: MutableState<String>
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 0.dp)
            )

            Text(
                text = stringResource(id = R.string.textLoginAccount),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFDAA520),
                modifier = Modifier.padding(top = 10.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.textEmail),
                        color = Color(0xFFF5F5F5),
                        fontSize = 24.sp
                    )
                },
                textStyle = TextStyle(fontSize = 28.sp, color = Color.White),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(vertical = 16.dp)
                    .height(80.dp),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None),
                colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = Color(0xFFDAA520),
                    unfocusedBorderColor = Color(0xFFF5F5F5),
                    textColor = Color.White
                ),
                trailingIcon = {
                    Button(
                        onClick = { onVoiceInputClick("email") },
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF8B0000),
                            contentColor = Color.White
                        )
                    ) {
                        Text("🎤")
                    }
                }
            )

            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.textPassword),
                        color = Color(0xFFF5F5F5),
                        fontSize = 24.sp
                    )
                },
                textStyle = TextStyle(fontSize = 28.sp, color = Color.White),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(vertical = 16.dp)
                    .height(80.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = Color(0xFFDAA520),
                    unfocusedBorderColor = Color(0xFFF5F5F5),
                    textColor = Color.White
                ),
                trailingIcon = {
                    Button(
                        onClick = { onVoiceInputClick("password") },
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF8B0000),
                            contentColor = Color.White
                        )
                    ) {
                        Text("🎤")
                    }
                }
            )

            Button(
                onClick = { onLoginClick(emailState.value, passwordState.value) },
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(top = 30.dp)
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8B0000),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.textLogin),
                    fontSize = 40.sp,
                    color = Color.White
                )
            }

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(top = 30.dp)
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8B0000),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.textBtnRBack),
                    fontSize = 40.sp,
                    color = Color.White
                )
            }
        }
    }
}
