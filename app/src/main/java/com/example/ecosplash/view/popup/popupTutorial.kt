package com.example.ecosplash.view.popup

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ecosplash.classes.Tutorial
import com.example.ecosplash.tutorials


@Composable
fun Indicator() {
    val color = Color.Black
    val size = 10.dp
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(size)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoreInfoTwo(
    onDismiss: () -> Unit,
    imagenes: List<Painter>,
    maxHeight: Dp,
    tutorials: List<Tutorial> = tutorials()
) {
    val listState = rememberLazyListState()
    val fontSizeSp = with(LocalDensity.current) { maxHeight.toSp() }

    androidx.compose.material3.AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ },
        text = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.height((maxHeight * 0.06f))
                    ) {
                        Image(
                            painter = imagenes[13],
                            contentDescription = "icono de X",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                LazyRow(
                    state = listState,
                    flingBehavior = rememberSnapFlingBehavior(listState),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    itemsIndexed(tutorials) { index, tutorial ->
                        Surface(
                            modifier = Modifier
                                .fillParentMaxWidth(),
                            color = Color(0xFFCBE2FE),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    tutorial.firstText,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    fontSize = fontSizeSp * 0.025f,
                                    modifier = Modifier.padding(bottom = maxHeight * tutorial.bottom)
                                )

                                Image(
                                    painter = tutorial.image,
                                    contentDescription = "icono sombrero",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .height(maxHeight * tutorial.imageSize)
                                )

                                Text(
                                    text = tutorial.secondText,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    fontSize = fontSizeSp * 0.025f,
                                    modifier = Modifier.padding(top = maxHeight * tutorial.top)
                                )
                            }
                        }
                    }
                }

                // Indicador de índice actual
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val visibleIndex = (listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size / 2)
                        .coerceIn(0, tutorials.size - 1)

                    tutorials.forEachIndexed { index, _ ->
                        val isSelected = index == visibleIndex
                        Spacer(Modifier.size(maxHeight*0.005f))
                        Box(
                            modifier = Modifier
                                .size(maxHeight*0.01f)
                                .background(
                                    color = if (isSelected) Color.Black else Color.Gray,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        },
        containerColor = Color(0xFFCBE2FE),
        modifier = Modifier.height(maxHeight * 0.7f)
    )
}