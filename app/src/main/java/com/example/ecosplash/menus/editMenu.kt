package com.example.ecosplash.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun EditMenu(onClick: (Int) -> Unit, bgColor: Color = Color.Red, imagenes: List<Painter>, maxHeight: Dp, modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {onClick(1) },
            modifier = Modifier
                //.height((maxHeight * 0.08f))
                .align(Alignment.Top)
        )
        {
            Image(painter = imagenes[12],
                contentDescription = "icono de flecha hacia atrás",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Top)
                    .padding(maxHeight * 0.005f)
            )
        }
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
                .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[6],
                contentDescription = "icono sombrero",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.02f)

            )
        }
        Spacer(modifier = Modifier.width(maxHeight*0.05f))

        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .height((maxHeight * 0.16f))
                .weight(1f)
                .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(40.dp))
        )
        {

            Image(painter = imagenes[7],
                contentDescription = "icono sombrero",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(maxHeight * 0.02f)

            )
        }
        Spacer(modifier = Modifier.width(maxHeight*0.03f))

    }
}

