package cl.duoc.premier_meat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuScreen(
    userName: String,
    onChangePasswordClick: () -> Unit,
    onGeolocateClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
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
            Text(
                text = "Bienvenido $userName, ¿qué te gustaría hacer?",
                fontSize = 37.sp,
                color = Color(0xFFDAA520),
                modifier = Modifier.padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onChangePasswordClick,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 40.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8B0000),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Cambiar Contraseña",
                    fontSize = 30.sp,
                    color = Color.White
                )
            }

            Button(
                onClick = onGeolocateClick,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 40.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8B0000),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Geolocalizar",
                    fontSize = 30.sp,
                    color = Color.White
                )
            }

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 40.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8B0000),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Volver",
                    fontSize = 37.sp,
                    color = Color.White
                )
            }
        }
    }
}
