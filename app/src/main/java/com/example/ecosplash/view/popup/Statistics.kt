package com.example.ecosplash.view.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecosplash.model.StatisticsManager
import com.example.ecosplash.montserratFontFamily

@Composable
fun Statistics(
    statisticsManager: StatisticsManager,
    onDismiss: () -> Unit,
    imagenes: List<Painter>,
    maxHeight: Dp
) {

    val totalShowers by statisticsManager.totalShowers.observeAsState(initial = 0)
    val quickShowers by statisticsManager.totalShowers.observeAsState(initial = 0)
    val litersSaved by statisticsManager.litersSaved.observeAsState(initial = 0)

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { /*TODO*/ },
        text = {
            Column {
                IconButton(
                    onClick = onDismiss,
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
                    text = "Duchas totales: $totalShowers \n" +
                            "\n" +
                            "Duchas menores a 5 munutos: $quickShowers \n" +
                            "\n" +
                            "Litros de agua ahorrados: $litersSaved L",
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
