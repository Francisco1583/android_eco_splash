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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ecosplash.ui.theme.EcosplashTheme

class MainActivity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcosplashTheme {
                val imagenes = listOf(
                painterResource(id = R.drawable.cuarto),
                painterResource(id = R.drawable.pecera),
                painterResource(id = R.drawable.desk1),
                painterResource(id = R.drawable.reloj1),
                painterResource(id = R.drawable.stats),
                painterResource(id = R.drawable.gancho),
                painterResource(id = R.drawable.logopecera),
                painterResource(id = R.drawable.sombrero_logo1)
                )
                Surface {
                    Greeting1(imagenes = imagenes)
                }
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //Greeting(
                //     modifier = Modifier.padding(innerPadding)
                //  )
                //}
            }
        }
    }
}

@Composable
fun menu1(imagenes: List<Painter>, maxHeight: Dp, modifier: Modifier = Modifier ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding((maxHeight * 0.02f))
    ) {

        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .height((maxHeight * 0.08f))
                .weight(1f)
        )
        {
            Image(painter = imagenes[7],
                contentDescription = "icono del reloj",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height((maxHeight * 0.065f))
            )
        }


        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .height((maxHeight * 0.08f)))
        {
            Image(painter = imagenes[6],
                contentDescription = "icono del reloj",
                contentScale = ContentScale.Fit,
            )
        }

    }
}

@Composable
fun Greeting1(modifier: Modifier = Modifier,imagenes: List<Painter>) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 2f else 1f)
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight
        Image(painter = imagenes[0],
            contentDescription = "hola",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 10.dp)
        )
        Image(painter = imagenes[2],
            contentDescription = "imagen del mueble",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                // .size(400.dp)
                //.width(maxWidth * 0.8f)
                .height(maxHeight * 0.48f)
                .align(Alignment.BottomCenter)
                .offset(y = maxHeight * 0.16f)
        )
        Image(painter = imagenes[1],
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
            menu1(imagenes = imagenes, maxHeight = maxHeight)
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
fun GreetingPreview1() {
    EcosplashTheme {
            val imagenes = listOf(
                painterResource(id = R.drawable.cuarto),
                painterResource(id = R.drawable.pecera),
                painterResource(id = R.drawable.desk1),
                painterResource(id = R.drawable.reloj1),
                painterResource(id = R.drawable.stats),
                painterResource(id = R.drawable.gancho),
                painterResource(id = R.drawable.logopecera),
                painterResource(id = R.drawable.sombrero_logo1)
            )
        Greeting1(imagenes = imagenes)
    }
}