package com.example.ecosplash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ecosplash.classes.Sombrero
import com.example.ecosplash.model.CoinManager
import com.example.ecosplash.model.InventoryManager
import com.example.ecosplash.model.UserData
import com.example.ecosplash.montserratFontFamily

@Composable
// editmenu es el menú que se despliega al darle al botón de editar
fun BackgroupsMenu(
    userData: UserData,
    coinManager: CoinManager,
    inventoryManager: InventoryManager,
    onClick: (Int) -> Unit,
    imagenes: List<Painter>,
    maxHeight: Dp,
    backgrounds: List<Sombrero>,
    modifier: Modifier = Modifier,
    switchMode: Int,
    currentImage: Int
) {

    var clicks by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var prevfishbowlass by remember { mutableIntStateOf(0) }
    var position by remember { mutableIntStateOf(currentImage) }
    Column {
        IconButton(
            onClick =
            {
                onClick(2)
                if (inventoryManager.getItemObtained(currentImage, switchMode)) {
                    userData.setCurrentImage(position, switchMode)
                } else if (!inventoryManager.getItemObtained(prevfishbowlass, switchMode)) {
                    userData.setCurrentImage(prevfishbowlass, switchMode)
                } else {
                    userData.setCurrentImage(0, switchMode)
                }
            },
            modifier = Modifier
            //.height((maxHeight * 0.08f))
            // .align(Alignment.Top)
        )
        {
            Image(
                painter = imagenes[12],
                contentDescription = "icono de flecha hacia atrás",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    //.align(Alignment.TopStart)
                    .padding(maxHeight * 0.009f)
            )
        }
        var selectedIndex by remember { mutableIntStateOf(currentImage) }
        LazyRow(//horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            items(backgrounds.size) { pos ->
                // Spacer(modifier = Modifier.padding(10.dp))
                IconButton(
                    onClick = {
                        if (selectedIndex != pos) {
                            clicks = 0
                            clicks += 1
                        } else {
                            clicks += 1
                        }

                        if (clicks == 2) {
                            showDialog = true
                        }
                        selectedIndex = pos
                        position = pos
                        prevfishbowlass = currentImage
                        userData.setCurrentImage(pos, switchMode)
                    },
                    modifier = Modifier
                        .height((maxHeight * 0.16f))
                        .width(maxHeight * 0.16f)
                        .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(20.dp))
                        .border(
                            width = if (selectedIndex == pos) 4.dp else 0.dp,
                            color = if (selectedIndex == pos) Color.Green else Color.Transparent,
                            shape = RoundedCornerShape(20.dp)
                        )

                )
                {

                    Image(
                        painter = backgrounds[pos].imagen,
                        contentDescription = "icono sombrero",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(maxHeight * 0.03f)

                    )
                    Row(modifier = Modifier.offset(y = maxHeight * 0.065f)) {
                        if (!inventoryManager.getItemObtained(pos, switchMode)) {
                            Text(
                                text = "$" + backgrounds[pos].precio,
                                fontFamily = montserratFontFamily,
                                color = Color.Black
                            )
                        }

                    }

                }
                Spacer(modifier.padding(maxHeight * 0.012f))
            }

        }
    }
    if (showDialog) {
        clicks = 0
        ItemDetails(
            coinManager = coinManager,
            inventoryManager = inventoryManager,
            onDismiss = { showDialog = false },
            imagenes = imagenes,
            maxHeight = maxHeight,
            accesorie = backgrounds[position],
            index = position,
            switchMode = switchMode,
        )
    }


}