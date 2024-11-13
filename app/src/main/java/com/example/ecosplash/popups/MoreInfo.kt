package com.example.ecosplash.popups

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecosplash.montserratFontFamily

@Composable
fun MoreInfo(onDismiss:()-> Unit, imagenes: List<Painter>, maxHeight: Dp) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest =  onDismiss,
        confirmButton = { /*TODO*/ },
        text = {
            Column {
                IconButton(onClick = onDismiss,
                    modifier = Modifier
                        .height((maxHeight * 0.06f))
                ) {
                    Image(
                        painter = imagenes[13],
                        contentDescription = "icono de X",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(
                    text = "El objetivo de la aplicación es concientizar sobre el desperdicio y uso desmedido del agua, ya que este es un recurso limitado del cual todos debemos cuidar, generando una red de acción ciudadana. Para mas información visita el Facebook de @Eco Espacio Digital",
                    modifier = Modifier.padding(16.dp),
                    fontFamily = montserratFontFamily,
                    color = Color.Black
                )
            }

        },
        containerColor = Color(0xFFCBE2FE),
        modifier = Modifier
            .height(maxHeight * 0.5f)
            .padding(8.dp)
    )
}

@Composable
fun Stats(onDismiss:()-> Unit,
          imagenes: List<Painter>,
          maxHeight: Dp,
          duchasTotales: Int,
          duchasMen5: Int,
          litrosAhorrados: Float) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest =  onDismiss,
        confirmButton = { /*TODO*/ },
        text = {
            Column {
                IconButton(onClick = onDismiss,
                    modifier = Modifier
                        .height((maxHeight * 0.06f))
                ) {
                    Image(painter = imagenes[13],
                        contentDescription = "icono de X",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxSize()

                    )
                }
                Text(
                    text = "Duchas totales: $duchasTotales \n" +
                            "\n" +
                    "Duchas menores a 5 munutos: $duchasMen5 \n" +
                            "\n" +
                    "Litros de agua ahorrados: $litrosAhorrados L",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(fontSize = 20.sp),
                    fontFamily = montserratFontFamily,
                    color = Color.Black
                )
            }

        },
        containerColor = Color(0xFFCBE2FE),
        modifier = Modifier
            .height(maxHeight * 0.45f)
            .padding(8.dp)
    )
}


