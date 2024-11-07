package com.example.ecosplash.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

@Composable
// mainmenu es el botón principal
fun MainMenu(onClick: (Int) -> Unit,
             time:(Long)-> Unit,
             isRunning:(Boolean)-> Unit,
             isCurrentlyRunning: Boolean,
             currentTime: Long,
             imagenes: List<Painter>,
             maxHeight: Dp,
             setMoney: (Int) -> Unit,
             money: Int,
             setRacha: (Int) -> Unit,
             racha: Int,
             progress: Float,
             setProgress: (Float) -> Unit,
             setStatsDialog: (Boolean) -> Unit,
             showDialogStats: Boolean,
             duchasMen5: Int,
             duchasTotales: Int,
             litrosAhorrados: Float,
             setduchasMen5: (Int) -> Unit,
             setduchasTotales: (Int) -> Unit,
             setLitrosAhorrados: (Float) -> Unit) {
    var remainingTime by remember { mutableLongStateOf(0L) }
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick(2)},
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
        )
        {

            Image(painter = imagenes[5],
                contentDescription = "icono de personalización",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.025f)

            )
        }
        //Spacer(modifier = Modifier.width(maxHeight*0.05f))

        IconButton(onClick = {
            if (isCurrentlyRunning) {
                isRunning(false)
                setduchasTotales(duchasTotales + 1)
                setLitrosAhorrados(((currentTime.toInt())/1000).toFloat() * 0.2f)
                if (currentTime.toInt() >= 900000 ) {
                    setduchasMen5(duchasMen5 + 1)
                    setMoney(money + 10)
                    setRacha(racha + 1)

                }
                time(1200000)

            }
            else {
                isRunning(true)
                //keyboardController?.hide()
            }
        },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
            //.background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[3],
                contentDescription = "icono de reloj",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                //.padding(maxHeight * 0.02f)

            )
        }
        //Spacer(modifier = Modifier.width(maxHeight*0.03f))
        IconButton(onClick = { setStatsDialog(!showDialogStats)},
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
            //.background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[4],
                contentDescription = "icono de actividad",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.025f)

            )
        }
    }
}