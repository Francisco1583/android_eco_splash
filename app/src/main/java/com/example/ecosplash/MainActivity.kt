package com.example.ecosplash

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecosplash.ui.theme.EcosplashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcosplashTheme {
                Surface {
                    Greeting()
                }
            }
        }
    }
}


@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 2f else 1f)
    val fondo = painterResource(id = R.drawable.cuarto)
    val pecera = painterResource(id = R.drawable.pecera)
    val mueble = painterResource(id = R.drawable.desk1)
    val reloj_icono = painterResource(id = R.drawable.reloj1)
    val estadisticas_icono = painterResource(id = R.drawable.stats)
    val gancho = painterResource(id = R.drawable.gancho)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight
        Image(painter = fondo,
            contentDescription = "hola",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 10.dp)
        )
        Image(painter = mueble,
            contentDescription = "imagen del mueble",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                // .size(400.dp)
                //.width(maxWidth * 0.8f)
                .height(maxHeight * 0.48f)
                .align(Alignment.BottomCenter)
                .offset(y = maxHeight * 0.16f)
        )
        Image(painter = pecera,
            contentDescription = "imagen de la pecera",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                //.size(300.dp)
                //.width(maxWidth * 0.4f)
                .height(maxHeight * 0.35f)
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.07f)
        )


        Surface(color = Color(0xFFFED682),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .height(maxHeight * 0.13f)

        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(maxHeight * 0.02f)
            ) {

                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(maxHeight * 0.08f)
                        .weight(1f)
                )
                {
                    Image(painter = estadisticas_icono,
                        contentDescription = "icono del reloj",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
                {
                    Image(painter = reloj_icono,
                        contentDescription = "icono del reloj",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .height(maxHeight * 0.08f))
                {
                    Image(painter = gancho,
                        contentDescription = "icono del reloj",
                        contentScale = ContentScale.Fit,
                    )
                }

            }
        }
        HorizontalDivider(
            color = Color(0xFF80684D), // Color de la línea
            thickness = maxHeight*0.01f,  // Grosor de la línea
            modifier = Modifier
                .fillMaxWidth()  // La línea ocupará todo el ancho de la pantalla
                .offset(y = maxHeight * 0.87f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcosplashTheme {
        Greeting()
    }
}