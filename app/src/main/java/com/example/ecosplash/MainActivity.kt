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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecosplash.menus.EditMenu
import com.example.ecosplash.menus.MainMenu
import com.example.ecosplash.popups.MoreInfo
import com.example.ecosplash.popups.Stats
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
                Surface {
                    Greeting1(imagenes = images(), fishbowlanimation = fisbowlanimated(), ajoAnimated = ajoAnimated())
                }
            }
        }
    }
}


@Composable
fun formatTimer(timeMi: Long): String {
    val min = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60

    return String.format(Locale.getDefault(),"%02d:%02d",min,sec)
}

@Composable
fun Greeting1(imagenes: List<Painter>, fishbowlanimation: List<Painter>, ajoAnimated: List<Painter>) {

    // -------------DECLARACIÓN DE VARIABLES --------------------------
    // variable que se usa para el cambio de menus
    var boxVisible by remember { mutableIntStateOf(1) }
    // variable para mostrar o no el popup
    var showDialog by remember { mutableStateOf(false)}
    // variable para mostrar o no el popup1
    var showDialogStats by remember { mutableStateOf(false)}
    // variable que define el progreso de la barra de nivel
    var progress by remember { mutableFloatStateOf(0.5f) }
    //variable que almacena la cantidad de monedas
    var money by remember {mutableIntStateOf(0) }
    // variable que almacena la cantidad de las rachas
    var racha by remember {mutableIntStateOf(0)}
    //variable que almacena el tiempo del temporizador
    var time by remember { mutableLongStateOf(1200000L) }
    //variable que sirve para identificar si el temporizadore está corriendo o no
    var isRunning by remember { mutableStateOf(false) }
    //variable para animación de pecera
    var isRunningFishbowl by remember { mutableStateOf(true)}
    var isRunningAjoAnimated by remember { mutableStateOf(true) }
    // variable que cambia de acuerdo al frame de la animación de la pecera
    var fishbowlIndex by remember { mutableIntStateOf(0) }
    //variable que cambia de acuerdo al frame de la animación del ajolote
    var ajoIndex by remember { mutableIntStateOf(0) }
    // variable que probablemente la vaya a eliminar
    var startTime by remember { mutableLongStateOf(0L) }
    // variable que almacenará las duchas totales
    var duchasTotales by remember { mutableIntStateOf(0) }
    var duchasMen5 by remember { mutableIntStateOf(0) }
    var litrosAhorrados by remember { mutableFloatStateOf(0.0f) }


    // -----------------DECLARACIÓN DE VALORES PARA MODIFICAR LAS VARIABLES --------------
    // con esta variable es posible cambiar el valor de boxvisible
    val onClick = { newState : Int -> boxVisible = newState }
    // con esta variable es posible cambiar el valor de time
    val setTime: (Long) -> Unit = {newTime -> time = newTime}
    // con este valor es posible modificar isRunning
    val setIsRunning: (Boolean) -> Unit = {running -> isRunning = running}
    // se modifica starTime, probablemente ya no se use
    val setStartTime: (Long) -> Unit = {newStartTime -> startTime = newStartTime}
    // valor para cambiar la variable booleana con la que se decide si mostrar o no el popup de información
    val setInfoDialog: (Boolean) -> Unit = {newinfoDialog -> showDialog = newinfoDialog}
    //valor para modificar el booleano del popup de las estadisticas
    val setStatsDialog: (Boolean) -> Unit = {newinfoDialog -> showDialogStats = newinfoDialog}
    //valor para modificar lo del dinero
    val setMoney: (Int) -> Unit = {moreMoney -> money = moreMoney}
    // valor para modificar lo de la racha
    val setRacha: (Int) -> Unit = {masRacha -> racha = masRacha}
    // modifica el progreso de la barra
    val setProgress: (Float) -> Unit = {newProgress -> progress = newProgress}
    // actualiza la variable de las duchas totales
    val setduchasTotales: (Int) -> Unit = {masduchasT -> duchasTotales = masduchasT}
    // actializa la variable de las duchas menores a 5 minutos
    val setduchasMen5: (Int) -> Unit = {masduchasMen5 -> duchasMen5 = masduchasMen5}
    // actualiza la cantidad de litros ahorrados
    val setLitrosAhorrados: (Float) -> Unit = {masLitrosAhorrados -> litrosAhorrados = masLitrosAhorrados}
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
        Firstopart(maxWidth = maxWidth,
            maxHeight = maxHeight,
            imagenes = imagenes,
            setInfoDialog = setInfoDialog)
        Secondtopart(progress = progress,
            maxWidth = maxWidth,
            maxHeight = maxHeight,
            imagenes = imagenes,
            money = money,
            racha = racha)

        Image(painter = fishbowlanimation[fishbowlIndex],
            contentDescription = "imagen de la pecera",
            contentScale = ContentScale.Crop,
            modifier = Modifier

                .height(maxHeight * 0.4f)
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.07f)
        )
        Image(painter = ajoAnimated[ajoIndex],
            contentDescription = "imagen del ajolote",
            contentScale = ContentScale.Fit,
            modifier = Modifier

                .fillMaxSize()
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.1f)
                .offset(x = maxWidth * -0.1f)
                .padding(maxHeight * 0.12f)

        )
        if (isRunning) {
            Image(painter = imagenes[14],
                contentDescription = "imagen del cuarto",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = maxHeight * 0.78f)
                    .height(maxHeight * 0.1f)

            )
            Text(text = formatTimer(timeMi = time), style = TextStyle(fontSize = 40.sp), fontFamily = montserratFontFamily, modifier = Modifier
                .padding(9.dp)
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.33f),
                color = Color.White)
        }


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
            ) {
                MainMenu(imagenes = imagenes,
                    maxHeight = maxHeight,
                    onClick = onClick, time = setTime,
                    isRunning = setIsRunning,
                    isCurrentlyRunning = isRunning,
                    currentTime = time,
                    setMoney = setMoney,
                    money = money,
                    setRacha = setRacha,
                    racha = racha,
                    progress = progress,
                    setProgress = setProgress,
                    setStatsDialog = setStatsDialog,
                    showDialogStats = showDialogStats,
                    setduchasTotales = setduchasTotales,
                    setLitrosAhorrados = setLitrosAhorrados,
                    setduchasMen5 = setduchasMen5,
                    duchasTotales = duchasTotales,
                    duchasMen5 = duchasMen5,
                    litrosAhorrados = litrosAhorrados)
            }
        }
        else {
            boxVisible = 1
        }
        if (showDialog){
            MoreInfo(onDismiss = {showDialog = false},imagenes = imagenes, maxHeight = maxHeight)
        }
        if (showDialogStats) {
            Stats(onDismiss = {showDialogStats = false},
                imagenes = imagenes,
                maxHeight = maxHeight,
                duchasTotales = duchasTotales,
                duchasMen5 = duchasMen5,
                litrosAhorrados = litrosAhorrados)
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
    LaunchedEffect(isRunningFishbowl) {
        while (isRunningFishbowl) {
            delay(40)
            fishbowlIndex = (fishbowlIndex + 1) % 38
        }
    }

    LaunchedEffect(isRunningAjoAnimated) {
        while (isRunningAjoAnimated) {
            delay(100)
            ajoIndex = (ajoIndex + 1) % 14
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    EcosplashTheme {
        Greeting1(imagenes = images(),fishbowlanimation = fisbowlanimated(), ajoAnimated = ajoAnimated())
    }
}