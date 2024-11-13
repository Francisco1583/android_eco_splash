package com.example.ecosplash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ecosplash.classes.Sombrero
import com.example.ecosplash.model.CoinManager
import com.example.ecosplash.model.InventoryManager
import com.example.ecosplash.montserratFontFamily

@Composable
fun ItemDetails(
    coinManager: CoinManager,
    inventoryManager: InventoryManager,
    onDismiss: () -> Unit,
    imagenes: List<Painter>,
    maxHeight: Dp,
    accesorie: Sombrero,
    index: Int,
    switchMode: Int,
) {

    var noMoney by remember { mutableStateOf(false) }
    val coins by coinManager.coins.observeAsState(initial = 0)

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { /*TODO*/ },
        text = {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .height((maxHeight * 0.07f))
                    ) {
                        Image(
                            painter = imagenes[13],
                            contentDescription = "icono de X",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }

                Image(
                    painter = accesorie.imagen,
                    contentDescription = "icono del accesorio",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(maxHeight * 0.1f)
                        .align(Alignment.CenterHorizontally)

                )
                Text(
                    text = accesorie.descripcion,
                    modifier = Modifier
                        .padding(top = maxHeight * 0.01f),
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    color = Color.Black
                )
                if (noMoney) {
                    Text(
                        text = "DINERO INSUFICIENTE",
                        modifier = Modifier
                            .padding(top = maxHeight * 0.01f)
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        color = Color.Red
                    )
                }


                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = maxHeight * 0.01f)
                ) {
                    if (!inventoryManager.getItemObtained(index, switchMode)) {
                        Button(onClick = {
                            if (coins >= accesorie.precio) {
                                inventoryManager.unlockItem(index, switchMode)
                                coinManager.substractCoins(accesorie.precio)
                                onDismiss()

                            } else {
                                noMoney = true
                            }
                        }, modifier = Modifier.weight(1f)) {
                            Text(
                                text = "comprar",
                                fontFamily = montserratFontFamily,
                                color = Color.Black
                            )
                        }
                    } else {
                        Text(
                            text = "DESBLOQUEADO",
                            fontFamily = montserratFontFamily,
                            color = Color.Blue,
                            modifier = Modifier
                                .padding(top = maxHeight * 0.02f)
                        )
                    }

                }
            }

        },
        containerColor = Color(0xFFCBE2FE),
        modifier = Modifier
            .height(maxHeight * 0.5f)
            .padding(8.dp)
    )
}