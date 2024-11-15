package com.example.ecosplash

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecosplash.classes.Achievements
import com.example.ecosplash.classes.Sombrero
import com.example.ecosplash.menus.EditMenu
import com.example.ecosplash.menus.MainMenu
import com.example.ecosplash.model.AchievementsManager
import com.example.ecosplash.model.CoinManager
import com.example.ecosplash.model.InventoryManager
import com.example.ecosplash.model.LevelManager
import com.example.ecosplash.model.StatisticsManager
import com.example.ecosplash.model.StrikeManager
import com.example.ecosplash.model.UserData
import com.example.ecosplash.popups.MoreInfo
import com.example.ecosplash.topInterfaces.FirstTopPart
import com.example.ecosplash.topInterfaces.SecondTopPart
import com.example.ecosplash.ui.theme.EcosplashTheme
import com.example.ecosplash.view.BackgroupsMenu
import com.example.ecosplash.view.popup.PopAchivement
import com.example.ecosplash.view.popup.Statistics
import kotlinx.coroutines.delay
import java.util.Locale
import java.util.concurrent.TimeUnit

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal)
)
val tekoFontFamily = FontFamily(
    Font(R.font.teko, FontWeight.Normal)
)

class MainActivity1 : ComponentActivity() {

    private val userData: UserData by viewModels()
    private val coinManager: CoinManager by viewModels()
    private val strikeManager: StrikeManager by viewModels()
    private val inventoryManager: InventoryManager by viewModels()
    private val statisticsManager: StatisticsManager by viewModels()
    private val levelManager: LevelManager by viewModels()
    private val achievementsManager: AchievementsManager by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcosplashTheme {
                Surface {
                    Greeting1(
                        userData = userData,
                        coinManager = coinManager,
                        strikeManager = strikeManager,
                        inventoryManager = inventoryManager,
                        statisticsManager = statisticsManager,
                        levelManager = levelManager,
                        achievementsManager = achievementsManager,
                        imagenes = images(),
                        backgrounds = backgrounds()
                    )
                }
            }
        }
    }
}


@Composable
fun formatTimer(timeMi: Long): String {
    val min = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60

    return String.format(Locale.getDefault(), "%02d:%02d", min, sec)
}

@Composable
fun Greeting1(
    userData: UserData,
    coinManager: CoinManager,
    strikeManager: StrikeManager,
    inventoryManager: InventoryManager,
    statisticsManager: StatisticsManager,
    levelManager: LevelManager,
    achievementsManager: AchievementsManager,
    imagenes: List<Painter>,
    backgrounds: List<Sombrero>,
    hats: List<Sombrero> = hats(),
    achievements:   List<Achievements> = achivements()
) {

    // -------------DECLARACIÓN DE VARIABLES --------------------------
    // variable que se usa para el cambio de menus
    var boxVisible by remember { mutableIntStateOf(1) }
    // variable para mostrar o no el popup
    var showDialog by remember { mutableStateOf(false) }
    // mostrar dialogo de logros
    var showAchivments by remember { mutableStateOf(false) }
    // variable para mostrar o no el popup1
    var showDialogStats by remember { mutableStateOf(false) }
    // variable que define el progreso de la barra de nivel
    var progress by remember { mutableFloatStateOf(0.0f) }
    // nivel actual
    var level by remember { mutableIntStateOf(0) }
    //variable que almacena el tiempo del temporizador
    var time by remember { mutableLongStateOf(1200000L) }
    //variable que sirve para identificar si el temporizadore está corriendo o no
    var isRunning by remember { mutableStateOf(false) }
    //variable para animación de pecera
    val isRunningFishbowl by remember { mutableStateOf(true) }
    val isRunningAjoAnimated by remember { mutableStateOf(true) }
    // variable que cambia de acuerdo al frame de la animación de la pecera
    var fishbowlIndex by remember { mutableIntStateOf(0) }
    //variable que cambia de acuerdo al frame de la animación del ajolote
    var ajoIndex by remember { mutableIntStateOf(0) }
    var litrosAhorrados by remember { mutableFloatStateOf(0.0f) }

    // -----------------DECLARACIÓN DE VALORES PARA MODIFICAR LAS VARIABLES --------------
    // con esta variable es posible cambiar el valor de boxvisible
    val onClick = { newState: Int -> boxVisible = newState }
    // con esta variable es posible cambiar el valor de time
    val setTime: (Long) -> Unit = { newTime -> time = newTime }
    // con este valor es posible modificar isRunning
    val setIsRunning: (Boolean) -> Unit = { running -> isRunning = running }
    // valor para cambiar la variable booleana con la que se decide si mostrar o no el popup de información
    val setInfoDialog: (Boolean) -> Unit = { newinfoDialog -> showDialog = newinfoDialog }
    //valor para modificar el booleano del popup de las estadisticas
    val setStatsDialog: (Boolean) -> Unit = { newinfoDialog -> showDialogStats = newinfoDialog }
    //para modificar el booleano del popup de las logros
    val setAchivmentDialig: (Boolean) -> Unit = { newAchivmentD -> showAchivments = newAchivmentD }
    val setLitrosAhorrados: (Float) -> Unit = { masLitrosAhorrados -> litrosAhorrados = masLitrosAhorrados }

    val currentTank by userData.currentTank.observeAsState(initial = 0)
    val currentSkin by userData.currentSkin.observeAsState(initial = 0)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9F1FF))
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight

        Image(
            painter = imagenes[0],
            contentDescription = "imagen del cuarto",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = maxHeight * 0.25f)

        )
        FirstTopPart(
            maxWidth = maxWidth,
            maxHeight = maxHeight,
            imagenes = imagenes,
            setInfoDialog = setInfoDialog,
            setAchivmentDialig = setAchivmentDialig
        )
        SecondTopPart(
            coinManager = coinManager,
            strikeManager = strikeManager,
            levelManager = levelManager,
            progress = progress,
            maxWidth = maxWidth,
            maxHeight = maxHeight,
            imagenes = imagenes,
            level = level
        )
        Image(
            painter = backgrounds[currentTank].frames[fishbowlIndex],
            contentDescription = "imagen de la pecera",
            contentScale = ContentScale.Crop,
            modifier = Modifier

                .height(maxHeight * 0.4f)
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.07f)
        )
        Image(
            painter = hats[currentSkin].frames[ajoIndex],
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
            Image(
                painter = imagenes[14],
                contentDescription = "imagen del reloj",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = maxHeight * 0.78f)
                    .height(maxHeight * 0.1f)

            )
            Text(
                text = formatTimer(timeMi = time),
                style = TextStyle(fontSize = 40.sp),
                fontFamily = tekoFontFamily,
                modifier = Modifier
                    .padding(9.dp)
                    .align(Alignment.Center)
                    .offset(y = maxHeight * 0.33f),
                color = Color.White
            )
        }


        if (boxVisible == 2 && !isRunning) {
            Surface(
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .height(maxHeight * 0.20f)
            ) {
                EditMenu(imagenes = imagenes, maxHeight = maxHeight, onClick = onClick)
            }
        } else if (boxVisible == 1) {
            Surface(
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .height(maxHeight * 0.13f)
            ) {
                MainMenu(
                    coinManager = coinManager,
                    strikeManager = strikeManager,
                    statisticsManager = statisticsManager,
                    imagenes = imagenes,
                    maxHeight = maxHeight,
                    onClick = onClick, time = setTime,
                    isRunning = setIsRunning,
                    isCurrentlyRunning = isRunning,
                    currentTime = time,
                    setStatsDialog = setStatsDialog,
                    showDialogStats = showDialogStats,
                    setLitrosAhorrados = setLitrosAhorrados,
                    litrosAhorrados = litrosAhorrados,
                    levelManager = levelManager,
                )

            }
        } else if (boxVisible == 3) {
            Surface(
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .height(maxHeight * 0.24f)
            ) {
                BackgroupsMenu(
                    userData = userData,
                    coinManager = coinManager,
                    inventoryManager = inventoryManager,
                    imagenes = imagenes,
                    maxHeight = maxHeight,
                    onClick = onClick,
                    backgrounds = backgrounds,
                    switchMode = 0,
                    currentImage = currentTank
                )
            }
        } else if (boxVisible == 4) {
            Surface(
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .height(maxHeight * 0.24f)
            ) {
                BackgroupsMenu(
                    userData = userData,
                    coinManager = coinManager,
                    inventoryManager = inventoryManager,
                    imagenes = imagenes,
                    maxHeight = maxHeight,
                    onClick = onClick,
                    backgrounds = hats,
                    switchMode = 1,
                    currentImage = currentSkin
                )
            }
        } else {
            boxVisible = 1
        }
        if (showDialog) {
            MoreInfo(onDismiss = { showDialog = false }, imagenes = imagenes, maxHeight = maxHeight)
        }
        if (showDialogStats) {
            Statistics(
                statisticsManager = statisticsManager,
                onDismiss = { showDialogStats = false },
                imagenes = imagenes,
                maxHeight = maxHeight
            )
        }
        if (showAchivments) {
            PopAchivement(
                onDismiss = { showAchivments = false },
                statisticsManager = statisticsManager,
                achievementsManager = achievementsManager,
                inventoryManager = inventoryManager,
                imagenes = imagenes,
                maxHeight = maxHeight,
                achivements = achievements
            )
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
            delay(85)
            fishbowlIndex = (fishbowlIndex + 1) % 44
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

        Greeting1(
            userData = UserData(Application()),
            coinManager = CoinManager(Application()),
            strikeManager = StrikeManager(Application()),
            inventoryManager = InventoryManager(Application()),
            statisticsManager = StatisticsManager(Application()),
            levelManager = LevelManager(Application()),
            achievementsManager = AchievementsManager(Application()),
            imagenes = images(),
            backgrounds = backgrounds()
        )
    }
}