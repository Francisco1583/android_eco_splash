package com.example.ecosplash.menus

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import com.example.ecosplash.model.CoinManager
import com.example.ecosplash.model.LevelManager
import com.example.ecosplash.model.StatisticsManager
import com.example.ecosplash.model.StrikeManager
import com.example.ecosplash.model.UserData
import java.util.Calendar

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
    levelManager: LevelManager,
    startTimerService: (Long) -> Unit,
    stopTimerService: () -> Unit,
    userData: UserData,
) {
    val clockActivation by userData.clockAction.observeAsState(initial = 0)
    val strikes by strikeManager.strikes.observeAsState(initial = 0)
    val mContext = LocalContext.current
    val calendar = Calendar.getInstance()
    val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
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
                if(clockActivation != dayOfYear) {
                    if (isCurrentlyRunning && currentTime.toInt() < 1080000) {

                        isRunning(false)
                        stopTimerService()
                        userData.setClockAction(dayOfYear)

                        statisticsManager.addShowers()
                        val actLitrosAhorrados = ((currentTime.toInt() / 1000).toFloat() * 0.2f)
                        statisticsManager.addLitersSaved(actLitrosAhorrados)

                        if (currentTime.toInt() >= 900000) {
                            levelManager.addExperience(actLitrosAhorrados.toInt())
                            statisticsManager.addQuickShowers()
                            coinManager.addCoins(10 + (strikes / 5))
                            strikeManager.addStrikes()
                        }
                        else {
                            strikeManager.resetStrikes()
                        }
                        time(1200000)
                    }
                    else if(isCurrentlyRunning) {
                        Toast.makeText(mContext, "Es muy pronto para acabar la ducha", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val safeCurrentTime = if (currentTime > 0) currentTime else 1200000L
                        isRunning(true)
                        startTimerService(safeCurrentTime)
                    }
                }
                else {
                    Toast.makeText(mContext, "Solo es posible hacer una ducha por día", Toast.LENGTH_SHORT).show()
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