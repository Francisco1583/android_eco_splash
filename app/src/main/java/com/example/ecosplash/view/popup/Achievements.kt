package com.example.ecosplash.view.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ecosplash.classes.Achievements
import com.example.ecosplash.model.AchievementsManager
import com.example.ecosplash.model.InventoryManager
import com.example.ecosplash.model.StatisticsManager

@Composable
fun PopAchivement(
    onDismiss: () -> Unit,
    statisticsManager: StatisticsManager,
    inventoryManager: InventoryManager,
    achievementsManager: AchievementsManager,
    imagenes: List<Painter>,
    maxHeight: Dp,
    achivements: List<Achievements>,

    ) {
    var selection by remember { mutableIntStateOf(0) }

    val quickShowers by statisticsManager.totalShowers.observeAsState(initial = 0)
    val litersSaved by statisticsManager.litersSaved.observeAsState(initial = 0)

    val purchasedItems by inventoryManager.purchasedItems.observeAsState(initial = 0)

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
                Column {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // Configura dos renglones
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(maxHeight * 0.27f) // Ajusta la altura según sea necesario
                            .background(Color(0xFFCBE2FE))
                    ) {
                        items(achivements.size) { index ->

                            if(!achievementsManager.isUnlocked(index)) {
                                if (achivements[index].id == 0) {
                                    if(quickShowers >= achivements[index].goal) {
                                        achievementsManager.unlockAchievement(index)
                                    }
                                    else {
                                        achivements[index].progress = quickShowers
                                    }
                                }
                                if (achivements[index].id == 1) {
                                    if(litersSaved.toInt() >= achivements[index].goal) {
                                        achievementsManager.unlockAchievement(index)
                                    }
                                    else {
                                        achivements[index].progress = litersSaved.toInt()
                                    }
                                }
                                if (achivements[index].id == 2) {
                                    if(purchasedItems >= achivements[index].goal) {
                                        achievementsManager.unlockAchievement(index)
                                    }
                                    else {
                                        achivements[index].progress = purchasedItems
                                    }
                                }
                            }
                            IconButton(
                                onClick =
                                { selection = index },
                                modifier = Modifier
                                    .height((maxHeight * 0.13f))
                            )
                            {
                                val matrix = ColorMatrix()
                                matrix.setToSaturation(0F)
                                Image(
                                    painter = achivements[index].imagen,
                                    contentDescription = "icono de flecha hacia atrás",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(maxHeight * 0.0f),
                                    colorFilter = if (!achievementsManager.isUnlocked(index)) ColorFilter.colorMatrix(matrix) else null
                                )
                            }
                        }
                    }

                    Text(

                        text = achivements[selection].descripcion, modifier = Modifier.align(
                            Alignment.CenterHorizontally
                        )
                    )
                    LinearProgressIndicator(
                        progress = {if(!achievementsManager.isUnlocked(selection)) achivements[selection].progress.toFloat()/achivements[selection].goal.toFloat() else 1.0f},
                        color = if(achievementsManager.isUnlocked(selection)) Color.Green else Color(0xFF6483B9),
                        modifier = Modifier
                            .height(maxHeight * 0.04f)
                            .clip(RoundedCornerShape(20.dp)),
                    )


                }
            }

        },
        containerColor = Color(0xFFCBE2FE),
        modifier = Modifier
            .height(maxHeight * 0.56f)
            .padding(8.dp)
    )
}