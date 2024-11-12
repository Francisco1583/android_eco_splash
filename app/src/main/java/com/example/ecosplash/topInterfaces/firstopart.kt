package com.example.ecosplash.topInterfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

@Composable
//esta función son los botones de logros e información
fun Firstopart (setInfoDialog: (Boolean) -> Unit,
                setAchivmentDialig: (Boolean) -> Unit,
                imagenes: List<Painter>,
                maxWidth: Dp, maxHeight: Dp,
                modifier: Modifier = Modifier) {
    Surface(color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .width(maxWidth * 0.9f)
            .statusBarsPadding()
            .navigationBarsPadding()

    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding((maxHeight * 0.01f)),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            IconButton(onClick = { setAchivmentDialig(true)},
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
            IconButton(onClick = { setInfoDialog(true) },
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
}