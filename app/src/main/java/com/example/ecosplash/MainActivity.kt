package com.example.ecosplash
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecosplash.menus.EditMenu
import com.example.ecosplash.menus.MainMenu
import com.example.ecosplash.popups.MoreInfo
import com.example.ecosplash.topInterfaces.Firstopart
import com.example.ecosplash.topInterfaces.Secondtopart
import com.example.ecosplash.ui.theme.EcosplashTheme
import kotlinx.coroutines.delay
import java.util.Locale
import java.util.concurrent.TimeUnit

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal)
)

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
                    painterResource(id = R.drawable.clock),
                    painterResource(id = R.drawable.activity),
                    painterResource(id = R.drawable.edit_button),
                    painterResource(id = R.drawable.logopecera),
                    painterResource(id = R.drawable.sombrero_v2),
                    painterResource(id = R.drawable.rachav2),
                    painterResource(id = R.drawable.info),
                    painterResource(id = R.drawable.award),
                    painterResource(id = R.drawable.dinero),
                    painterResource(id = R.drawable.arrow_left),
                    painterResource(id = R.drawable.x)
                )
                Surface {
                    Greeting1(imagenes = imagenes)

                }

            }
        }
    }
}


@Composable
fun formatTimer(timeMi: Long): String {
    val hours = TimeUnit.MICROSECONDS.toHours(timeMi)
    val min = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60

    return String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,min,sec)
}

@Composable
fun Greeting1(imagenes: List<Painter>) {
    // -------------DECLARACIÓN DE VARIABLES --------------------------
    // variable que se usa para el cambio de menus
    var boxVisible by remember { mutableIntStateOf(1) }
    // variable para mostrar o no el popup
    var showDialog by remember { mutableStateOf(false)}
    // variable para mostrar o no el popup1
    //var showDialogONE by remember { mutableStateOf(true)}
    // variable que define el progreso de la barra de nivel
    var progress by remember { mutableFloatStateOf(0.5f) }

    //variable que almacena el tiempo del temporizador
    var time by remember { mutableLongStateOf(600000L) }
    //variable que sirve para identificar si el temporizadore está corriendo o no
    var isRunning by remember { mutableStateOf(false) }
    // variable que probablemente la vaya a eliminar
    var startTime by remember { mutableLongStateOf(0L) }


    // -----------------DECLARACIÓN DE VALORES PARA MODIFICAR LAS VARIABLES --------------
    // con esta variable es posible cambiar el valor de boxvisible
    val onClick = { newState : Int -> boxVisible = newState }
    // con esta variable es posible cambiar el valor de time
    val setTime: (Long) -> Unit = {newTime -> time = newTime}
    // con este valor es posible modificar isRunning
    val setIsRunning: (Boolean) -> Unit = {running -> isRunning = running}
    // se modifica starTime, probablemente ya no se use
    val setStartTime: (Long) -> Unit = {newStartTime -> startTime = newStartTime}
    //
    val setInfoDialog: (Boolean) -> Unit = {newinfoDialog -> showDialog = newinfoDialog}
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
        Firstopart(maxWidth = maxWidth, maxHeight = maxHeight, imagenes = imagenes, setInfoDialog = setInfoDialog)
        Secondtopart(progress = progress, maxWidth = maxWidth, maxHeight = maxHeight, imagenes = imagenes)

        Image(painter = imagenes[1],
            contentDescription = "imagen de la pecera",
            contentScale = ContentScale.Fit,
            modifier = Modifier

                .height(maxHeight * 0.35f)
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.07f)
        )
        Text(text = formatTimer(timeMi = time), style = MaterialTheme.typography.headlineLarge, modifier = Modifier
            .padding(9.dp)
            .align(Alignment.Center)
            .offset(y = maxHeight * 0.33f))

        if (boxVisible == 2 && !isRunning) {
            Surface(color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    //.height(maxHeight * 0.13f)
                    .height(maxHeight * 0.20f)
            ) {
                EditMenu(imagenes = imagenes, maxHeight = maxHeight, onClick = onClick)
            }
        }
        else if(boxVisible == 1){
            Surface(color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .height(maxHeight * 0.13f)
                //.height(maxHeight * 0.20f)
            ) {
                MainMenu(imagenes = imagenes, maxHeight = maxHeight, onClick = onClick, time = setTime, isRunning = setIsRunning, startTime = setStartTime, isCurrentlyRunning = isRunning, currentTime = time)
            }
        }
        else {
            boxVisible = 3
        }
        if (showDialog){
            MoreInfo(onDismiss = {showDialog = false},imagenes = imagenes, maxHeight = maxHeight)
        }
    }
    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000)
            time -= 1000
            if (time.toInt() == 0) {
                isRunning = false
                time = 600000
            }
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
            painterResource(id = R.drawable.clock),
            painterResource(id = R.drawable.activity),
            painterResource(id = R.drawable.edit_button),
            painterResource(id = R.drawable.logopecera),
            painterResource(id = R.drawable.sombrero_v2),
            painterResource(id = R.drawable.rachav2),
            painterResource(id = R.drawable.info),
            painterResource(id = R.drawable.award),
            painterResource(id = R.drawable.dinero),
            painterResource(id = R.drawable.arrow_left),
            painterResource(id = R.drawable.x)
        )
        Greeting1(imagenes = imagenes)
    }
}