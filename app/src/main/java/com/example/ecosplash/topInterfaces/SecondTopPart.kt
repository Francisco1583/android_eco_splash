package com.example.ecosplash.topInterfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecosplash.model.CoinManager
import com.example.ecosplash.model.LevelManager
import com.example.ecosplash.model.StrikeManager
import com.example.ecosplash.montserratFontFamily

@Composable
// esta función es un surface que contiene el nivel, la barra de nivel, la racha y el dinero
fun SecondTopPart(
    coinManager: CoinManager,
    strikeManager: StrikeManager,
    levelManager: LevelManager,
    imagenes: List<Painter>,
    maxWidth: Dp,
    maxHeight: Dp
) {
    val coins by coinManager.coins.observeAsState(initial = 0)
    val strikes by strikeManager.strikes.observeAsState(initial = 0)

    val userLevel by levelManager.level.observeAsState(initial = 0)
    val currentExperience by levelManager.currentExperience.observeAsState(initial = 0)
    val experienceToNextLevel by levelManager.experienceToNextLevel.observeAsState(initial = 0)

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()

            .height(maxHeight * 0.25f)
            .offset(y = maxHeight * 0.13f)
        //.height(maxHeight * 0.20f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nivel: $userLevel",
                modifier = Modifier,
                style = TextStyle(fontSize = 70.sp), fontFamily = montserratFontFamily,
                color = Color.Black
            )
            MaterialTheme( colorScheme =  MaterialTheme.colorScheme.copy(surfaceVariant = Color.White)) {
                LinearProgressIndicator(
                    progress = {
                        if (experienceToNextLevel > 0) {
                            currentExperience.toFloat() / experienceToNextLevel
                        } else {
                            0f // Valor predeterminado si experienceToNextLevel es 0 o no válido
                        }
                    },
                    modifier = Modifier
                        .height(maxHeight * 0.04f)
                        .width(maxWidth * 0.87f)
                        .clip(RoundedCornerShape(20.dp)),
                    color = Color(0xFF6483B9),
                )
            }

            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .height(maxHeight * 0.08f)
                    .width(maxWidth * 0.6f)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding((maxHeight * 0.01f)),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Row {
                        Image(
                            painter = imagenes[8],
                            contentDescription = "icono de racha",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding((maxHeight * 0.01f))
                        ) {
                            Text(
                                text = "$strikes",
                                modifier = Modifier
                                    .align(Alignment.Center), fontFamily = montserratFontFamily,
                                color = Color.Black

                            )
                        }

                    }

                    Row {
                        Image(
                            painter = imagenes[11],
                            contentDescription = "icono del diner",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding((maxHeight * 0.01f))
                        ) {
                            Text(
                                text = "$ $coins",
                                modifier = Modifier
                                    .align(Alignment.Center), fontFamily = montserratFontFamily,
                                color = Color.Black
                            )
                        }

                    }
                }

            }
        }

    }
}
