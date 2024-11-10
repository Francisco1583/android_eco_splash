package com.example.ecosplash.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
// editmenu es el menú que se despliega al darle al botón de editar
fun HatMenu(onClick: (Int) -> Unit,
                   bgColor: Color = Color.Red,
                   imagenes: List<Painter>,
                   maxHeight: Dp,
                    hatOptions: List<Painter>,
                   modifier: Modifier = Modifier) {
    Row(modifier = Modifier.padding(top = maxHeight*0.02f)) {
        IconButton(onClick = {onClick(2) },
            modifier = Modifier
            //.height((maxHeight * 0.08f))
            // .align(Alignment.Top)
        )
        {
            Image(painter = imagenes[12],
                contentDescription = "icono de flecha hacia atrás",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    //.align(Alignment.TopStart)
                    .padding(maxHeight * 0.009f)
            )
        }
        LazyRow(//horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()) {
            item {
                // Spacer(modifier.padding(maxHeight*0.024f))

            }
            items(hatOptions.size) { pos ->
                // Spacer(modifier = Modifier.padding(10.dp))
                IconButton(onClick = {onClick(3)},
                    modifier = Modifier
                        .height((maxHeight * 0.16f))
                        .width(maxHeight*0.16f)
                        .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))

                )
                {

                    Image(painter = hatOptions[pos],
                        contentDescription = "icono sombrero",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(maxHeight * 0.02f)

                    )
                }
                Spacer(modifier.padding(maxHeight*0.012f))
            }
        }
    }
}
