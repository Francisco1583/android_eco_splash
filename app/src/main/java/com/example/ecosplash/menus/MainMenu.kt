package com.example.ecosplash.menus

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.example.ecosplash.model.CoinManager
import com.example.ecosplash.model.LevelManager
import com.example.ecosplash.model.StatisticsManager
import com.example.ecosplash.model.StrikeManager

@Composable
fun MainMenu(
    coinManager: CoinManager,
    strikeManager: StrikeManager,
    statisticsManager: StatisticsManager,
    onClick: (Int) -> Unit,
    time: (Long) -> Unit,
    isRunning: (Boolean) -> Unit,
    isCurrentlyRunning: Boolean,
    currentTime: Long,
    imagenes: List<Painter>,
    maxHeight: Dp,
    setStatsDialog: (Boolean) -> Unit,
    showDialogStats: Boolean,
    litrosAhorrados: Float,
    levelManager: LevelManager,
    setLitrosAhorrados: (Float) -> Unit,
    startTimerService: (Long) -> Unit,
    stopTimerService: () -> Unit
) {
    var remainingTime by remember { mutableLongStateOf(0L) }
    val value by coinManager.coins.observeAsState(initial = 0)
    val litersSaved by statisticsManager.litersSaved.observeAsState(initial = 0)
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onClick(2) },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
        )
        {

            Image(
                painter = imagenes[5],
                contentDescription = "icono de personalización",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.025f)

            )
        }

        IconButton(
            onClick = {
                Log.d("MainMenu", "Botón de temporizador presionado. isCurrentlyRunning: $isCurrentlyRunning")
                if (isCurrentlyRunning) {
                    // Detener el temporizador
                    isRunning(false) // Actualiza el estado
                    stopTimerService() // Detiene el servicio

                    // Actualizar estadísticas
                    statisticsManager.addShowers()
                    val actLitrosAhorrados = (((currentTime.toInt()) / 1000).toFloat() * 0.2f)
                    statisticsManager.addLitersSaved(actLitrosAhorrados)

                    // Recompensas o penalizaciones
                    if (currentTime.toInt() >= 900000) {
                        levelManager.addExperience(actLitrosAhorrados.toInt())
                        statisticsManager.addQuickShowers()
                        coinManager.addCoins(10)
                        strikeManager.addStrikes()
                    } else {
                        strikeManager.resetStrikes()
                    }

                    // Reinicia el temporizador
                    time(1200000)
                } else {
                    // Iniciar el temporizador
                    val safeCurrentTime = if (currentTime > 0) currentTime else 1200000L
                    isRunning(true) // Actualiza el estado
                    startTimerService(safeCurrentTime) // Inicia el servicio con un valor seguro
                }
            },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
        )


        {

            Image(
                painter = imagenes[3],
                contentDescription = "icono de reloj",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()

            )
        }
        IconButton(
            onClick = { setStatsDialog(!showDialogStats) },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
        )
        {

            Image(
                painter = imagenes[4],
                contentDescription = "icono de actividad",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.025f)

            )
        }
    }
}