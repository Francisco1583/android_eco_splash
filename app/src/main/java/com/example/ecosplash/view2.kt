package com.example.ecosplash

import android.os.Bundle
import android.view.MotionEvent
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecosplash.ui.theme.EcosplashTheme

class MainActivity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcosplashTheme {
                val imagenes = listOf(
                painterResource(id = R.drawable.fondogato),
                painterResource(id = R.drawable.pecera),
                painterResource(id = R.drawable.desk1),
                painterResource(id = R.drawable.reloj1),
                painterResource(id = R.drawable.stats),
                painterResource(id = R.drawable.gancho),
                painterResource(id = R.drawable.logopecera),
                painterResource(id = R.drawable.sombrero_v2),
                    painterResource(id = R.drawable.rachav2),
                    painterResource(id = R.drawable.info),
                    painterResource(id = R.drawable.award),
                    painterResource(id = R.drawable.dinero),
                    painterResource(id = R.drawable.arrow_left)
                )
                Surface {
                    Greeting1(imagenes = imagenes)
                    //MainScreen()
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
fun Menu1(onClick: (Int) -> Unit, bgColor: Color = Color.Red,imagenes: List<Painter>, maxHeight: Dp, modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {onClick(2) },
            modifier = Modifier
                //.height((maxHeight * 0.08f))
                .align(Alignment.Top)
        )
        {
            Image(painter = imagenes[12],
                contentDescription = "icono de flecha hacia atrás",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Top)
                    .padding(maxHeight*0.005f)
            )
        }
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
                .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[6],
                contentDescription = "icono sombrero",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.02f)

            )
        }
        Spacer(modifier = Modifier.width(maxHeight*0.05f))

        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
                .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[7],
                contentDescription = "icono sombrero",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.02f)

            )
        }
        Spacer(modifier = Modifier.width(maxHeight*0.03f))

    }
}

@Composable
fun Menu2(onClick: (Int) -> Unit, bgColor: Color = Color.Red,imagenes: List<Painter>, maxHeight: Dp, modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {onClick(1) },
            modifier = Modifier
                //.height((maxHeight * 0.08f))
                .align(Alignment.Top)
        )
        {
            Image(painter = imagenes[12],
                contentDescription = "icono de flecha hacia atrás",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Top)
                    .padding(maxHeight*0.005f)
            )
        }
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
                .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[7],
                contentDescription = "icono sombrero",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.02f)

            )
        }
        Spacer(modifier = Modifier.width(maxHeight*0.05f))

        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
                .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[6],
                contentDescription = "icono sombrero",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.02f)

            )
        }
        Spacer(modifier = Modifier.width(maxHeight*0.03f))
    }
}


@Composable
fun Greeting1(modifier: Modifier = Modifier,imagenes: List<Painter>) {
    var boxVisible by remember { mutableIntStateOf(1) }

    val onClick = { newState : Int ->
        boxVisible = newState
    }
    var progress = remember { mutableFloatStateOf(0.5f) }
    val selected = remember { mutableStateOf(false) }
    var menu1Visible by remember { mutableStateOf(true) }
    //val scale = animateFloatAsState(if (selected.value) 2f else 1f)
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9F1FF))
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight
        Image(painter = imagenes[0],
            contentDescription = "imagen del cuarto",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = maxHeight * 0.25f)

        )
        Surface(color = Color.Transparent,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = maxHeight * 0.01f)
                .width(maxWidth * 0.9f)
                //.height(maxHeight * 0.13f)
                .statusBarsPadding()
                .navigationBarsPadding()

        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding((maxHeight * 0.01f)),
                horizontalArrangement = Arrangement.SpaceBetween

                ) {
                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height((maxHeight * 0.07f))

                )
                {
                    Image(painter = imagenes[10],
                        contentDescription = "icono de logros",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height((maxHeight * 0.8f))
                    )
                }
                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height((maxHeight * 0.07f))
                        .width(maxWidth * 0.14f)

                )
                {
                    Image(painter = imagenes[9],
                        contentDescription = "icono de informacion",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .height((maxHeight * 0.8f))
                    )
                }

            }

        }
        
        Text(text = "Nivel: 20",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = maxHeight * 0.08f),
            style = TextStyle(fontSize = 70.sp)
        )

        LinearProgressIndicator(
            progress = { 0.8f },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = maxHeight * 0.19f)
                .height(maxHeight * 0.04f)
                .width(maxWidth * 0.87f)
                .clip(RoundedCornerShape(20.dp))

        )

        Surface(color = Color.Transparent,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(maxHeight * 0.08f)
                .width(maxWidth * 0.6f)
                .offset(y = maxHeight * 0.27f))
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding((maxHeight * 0.01f)),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Row() {
                    Image(painter = imagenes[8],
                        contentDescription = "icono de racha",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                    )
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .padding((maxHeight * 0.01f))) {
                        Text(text = "20",
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }

                }

                Row() {
                    Image(painter = imagenes[11],
                        contentDescription = "icono del diner",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                    )
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .padding((maxHeight * 0.01f))) {
                        Text(text = "20",
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }

                }
            }

        }


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



            if (boxVisible == 1) {
                Surface(color = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomEnd)
                        //.height(maxHeight * 0.13f)
                        .height(maxHeight * 0.20f)
                ) {
                    Menu1(imagenes = imagenes, maxHeight = maxHeight, onClick = onClick)
                }
            }
            else {
                Surface(color = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomEnd)
                        .height(maxHeight * 0.13f)
                        //.height(maxHeight * 0.20f)
                ) {
                    Menu2(imagenes = imagenes, maxHeight = maxHeight, onClick = onClick)
                }
            }




    }
}

@Composable
fun CustomButton(text: String, targetState: Int,
                 onClick: (Int) -> Unit, bgColor: Color = Color.Red) {
    Button(onClick = {onClick(targetState) }) {
        Text("hola")
    }
}

@Composable
fun MainScreen() {


    var boxVisible by remember { mutableIntStateOf(1) }

    val onClick = { newState : Int ->
        boxVisible = newState
    }

    Column(
        Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "Show", targetState = 1, onClick = onClick)
            CustomButton(text = "Hide", targetState = 2, onClick = onClick)
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (boxVisible == 1) {
            Box(modifier = Modifier
                .size(height = 200.dp, width = 200.dp)
                .background(Color.Blue))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    EcosplashTheme {
            val imagenes = listOf(
                painterResource(id = R.drawable.fondogato),
                painterResource(id = R.drawable.pecera),
                painterResource(id = R.drawable.desk1),
                painterResource(id = R.drawable.reloj1),
                painterResource(id = R.drawable.stats),
                painterResource(id = R.drawable.gancho),
                painterResource(id = R.drawable.logopecera),
                painterResource(id = R.drawable.sombrero_v2),
                painterResource(id = R.drawable.rachav2),
                painterResource(id = R.drawable.info),
                painterResource(id = R.drawable.award),
                painterResource(id = R.drawable.dinero),
                painterResource(id = R.drawable.arrow_left)
            )
        Greeting1(imagenes = imagenes)
    }
}

