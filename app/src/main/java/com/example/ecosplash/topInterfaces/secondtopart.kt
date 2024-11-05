package com.example.ecosplash.topInterfaces

import androidx.compose.foundation.Image
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ecosplash.montserratFontFamily

@Composable
fun Secondtopart(imagenes: List<Painter>, progress: Float, maxWidth: Dp, maxHeight: Dp, modifier: Modifier = Modifier) {
    Surface(color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()

            .height(maxHeight * 0.25f)
            .offset(y = maxHeight * 0.13f)
        //.height(maxHeight * 0.20f)
    ) { Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nivel: 500",
            modifier = Modifier,
            //.align(Alignment.TopCenter)
            // .offset(y = maxHeight * 0.08f),
            style = TextStyle(fontSize = 70.sp), fontFamily = montserratFontFamily
        )
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                //.align(Alignment.TopCenter)
                //.offset(y = maxHeight * 0.19f)
                .height(maxHeight * 0.04f)
                .width(maxWidth * 0.87f)
                .clip(RoundedCornerShape(20.dp)),
        )
        Surface(color = Color.Transparent,
            modifier = Modifier
                //.align(Alignment.TopCenter)
                .height(maxHeight * 0.08f)
                .width(maxWidth * 0.6f)
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding((maxHeight * 0.01f)),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Row {
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
                                .align(Alignment.Center), fontFamily = montserratFontFamily
                        )
                    }

                }

                Row {
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
                                .align(Alignment.Center), fontFamily = montserratFontFamily
                        )
                    }

                }
            }

        }
    }

    }
}
