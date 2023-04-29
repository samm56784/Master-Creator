
package com.example.master_creator

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.master_creator.ui.theme.Master_CreatorTheme
import com.example.master_creator.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val description = "Le voleur de dnd"
            val title = "Mon image a moi"

            Box(
                modifier = Modifier
                    .fillMaxHeight(1f))
            {
                AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        setImageResource(R.drawable.menuprincipale)
                    }
                },
                update = {}
            )
            }


            Column(modifier = Modifier
                .fillMaxHeight(1f)

            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .fillMaxWidth())

                {
                    AndroidView(
                        factory = { context ->
                            ImageView(context).apply {
                                layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                scaleType = ImageView.ScaleType.CENTER_CROP
                                setImageResource(R.drawable.banner)
                            }
                        },
                        update = {}
                    )
                    Text(
                        text = "Master Creator",
                        fontWeight = FontWeight.Bold,
                        fontSize = 34.sp, modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                )
                {
                    Column()
                    {
                        Box(modifier = Modifier.fillMaxHeight(0.25f))

                        Bouton("Information personnage")
                        Bouton("Generation d'image")
                        Bouton("Lancement de dée")
                        Bouton("Musique")
                        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth())
                        {
                            Row(modifier = Modifier.fillMaxHeight())
                            {
                                Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                                Text(
                                    text = "Comment puis-je vous aider ?",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 34.sp,
                                )
                            }
                        }
                    }
                }

                     //Row(modifier = Modifier
                     //    .fillMaxHeight(1f)
                     //) {
                     //   IconDuBas(R.drawable.home)
                     //    IconDuBas(R.drawable.info)
                     //   IconDuBas(R.drawable.factory)
                     //    IconDuBas(R.drawable.dee)
                      //   IconDuBas(R.drawable.music)



                     }
            }


        }
    }


@Composable
fun ImageCard(
    painter: Painter,
    contentDescripion: String,
    title: String,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier.fillMaxWidth(1f),
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ){
        Box(modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
        )
            {
            Image(
                painter = painter,
                contentDescription = contentDescripion,
                contentScale = ContentScale.Crop
            )

            }




        }
    }
@Composable
fun IconDuBas(
    Id : Int
){
    Box (

        modifier = Modifier
            .width(95.dp)
            .height(95.dp)
            .padding(5.dp)
            .clickable { }
    ) {
        ImageCard(
            painter = painterResource(id = Id),
            contentDescripion = "test",
            title = "test"
        )
    }
}
@Composable
fun Bouton(text: String,) {



    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(8.dp)
            .clickable { }

    ) {

        AndroidView(
            factory = { context ->
                ImageView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImageResource(R.drawable.parchemin)
                }
            },
            update = {}
        )


        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
        )
    }
}
