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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PasswordChangeScreen(
    onChangePasswordClick: (String, String, String) -> Unit,
    onBackClick: () -> Unit
) {
    val currentPasswordState = remember { mutableStateOf("") }
    val newPasswordState = remember { mutableStateOf("") }
    val confirmNewPasswordState = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),  // Asegúrate de que 'background' está correcto
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
            Text(
                text = stringResource(id = R.string.textWlcomeChangePassword),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFDAA520),
                modifier = Modifier.padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = currentPasswordState.value,
                onValueChange = { currentPasswordState.value = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.textEditChangePassword1),
                        color = Color(0xFFF5F5F5),
                        fontSize = 24.sp
                    )
                },
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
                )
            )

            OutlinedTextField(
                value = newPasswordState.value,
                onValueChange = { newPasswordState.value = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.textEditChangePassword2),
                        color = Color(0xFFF5F5F5),
                        fontSize = 24.sp
                    )
                },
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
                )
            )

            OutlinedTextField(
                value = confirmNewPasswordState.value,
                onValueChange = { confirmNewPasswordState.value = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.textEditChangePassword3),
                        color = Color(0xFFF5F5F5),
                        fontSize = 24.sp
                    )
                },
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
                )
            )

            Button(
                onClick = { onChangePasswordClick(currentPasswordState.value, newPasswordState.value, confirmNewPasswordState.value) },
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(top = 30.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8B0000),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.textBtnChangePassword),
                    fontSize = 30.sp,
                    color = Color.White
                )
            }

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(top = 40.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8B0000),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.textBtnRBack),
                    fontSize = 37.sp,
                    color = Color.White
                )
            }
        }
    }
}
