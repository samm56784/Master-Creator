package com.example.master_creator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.master_creator.ui.theme.Master_CreatorTheme

val listeMapSize = listOf<String>("Petite", "Moyenne", "Grande")
val listeUnivers = listOf<String>("Fantastique","Moderne","Science fiction")
val listeEmplacement = listOf<String>("Ville", "Village","Château", "Forêt", "Auberge", "Grotte", "Plaine", "Champ", "Mer", "École", "Centre commercial", "Maison", "Usine"  )
val listeAdjectifs = listOf<String>("Ténébreux", "Effrayant", "Dangereux", "Sinueux", "Sombre", "Obscur", "Malfamé", "Sinistre", "Inquiétant", "Mystérieux", "Délabré", "Hanté", "Labyrinthique", "Maudit", "Impénétrable", "Profond", "Impitoyable", "Infesté", "Épique", "Magique")

//}
class ChoixDimage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


                Box() {


                AndroidView(
                    factory = { context ->
                        ImageView(context).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            scaleType = ImageView.ScaleType.CENTER_CROP
                            setImageResource(R.drawable.backgrounddice)
                        }
                    },
                    update = {})
                // A surface container using the 'background' color from the theme

                    var MapState:MutableState<Int>
                    var UniversState:MutableState<Int>
                    var EmplacementState:MutableState<Int>
                    var AdjectifsState:MutableState<Int>
                    GroupedCheckbox(mItemsList = listeMapSize, mItemsList2 = listeUnivers, mItemsList3 = listeEmplacement, mItemsList4 = listeAdjectifs )
                    //GroupedCheckbox(mItemsList = listeUnivers)
                    //GroupedCheckbox(mItemsList = listeEmplacement)
                    //GroupedCheckbox(mItemsList = listeAdjectifs)


            }
        }
    }
}






/*@Composable
fun afficherListe(items: List<String>) {
    var statut: Boolean = true
    val taille= items.size
    var choisi: Int
    Column() {
        for (i in 0..taille-1)
            Text(text=items[i],
            modifier = Modifier.clickable { choisi = i})
        Text(text = "Confirmer",modifier = Modifier.clickable { statut = false})
        }

    }*/

@SuppressLint("UnrememberedMutableState")
@Composable
fun afficherListe(list: List<String>):MutableState<Int> {
    val textList = mutableStateListOf(*list.toTypedArray())
    val clickedIndex = remember { mutableStateOf(-1) }
    var confirmed:Boolean = false
    if (!confirmed)
    {
        Column {
            textList.forEachIndexed { index, text ->
                Text(text = text, modifier = Modifier.clickable { clickedIndex.value = index })
            }
            Text(text = "Confirmer", modifier = Modifier.clickable { confirmed=true })
        }}
    return clickedIndex
}

@Composable
fun GroupedCheckbox(mItemsList: List<String>,mItemsList2: List<String>,mItemsList3: List<String>,mItemsList4: List<String>,) {
    val isConfirmed = remember{ mutableStateOf(false) }
    val isConfirmed2 = remember{ mutableStateOf(false) }
    val isConfirmed3 = remember{ mutableStateOf(false) }
    val isConfirmed4 = remember{ mutableStateOf(false) }

    if(!isConfirmed.value) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            mItemsList.forEach { items ->
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(8.dp)


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

                    Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()) {
                        val isChecked = remember { mutableStateOf(false) }

                        Checkbox(
                            checked = isChecked.value,
                            onCheckedChange = { isChecked.value = it },
                            enabled = true,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Magenta,
                                uncheckedColor = Color.DarkGray,
                                checkmarkColor = Color.Cyan
                            )
                        )
                        Text(text = items,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp)
                    }
                }
            }
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
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(8.dp)
                        .clickable {  isConfirmed.value = true }

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
                        text = "Confirmer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
                    )
                }
                //ClickableText(
                //    text = AnnotatedString("Confirmer"),
                //    onClick = { isConfirmed.value = true })
            }
        }
    }
    if(isConfirmed.value and !isConfirmed2.value and !isConfirmed3.value and !isConfirmed3.value) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            mItemsList2.forEach { items ->
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



                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()) {
                        val isChecked = remember { mutableStateOf(false) }

                        Checkbox(
                            checked = isChecked.value,
                            onCheckedChange = { isChecked.value = it },
                            enabled = true,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Magenta,
                                uncheckedColor = Color.DarkGray,
                                checkmarkColor = Color.Cyan
                            )
                        )
                    Text(text = items,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp)
                    }
                }
            }
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
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(8.dp)
                        .clickable {  isConfirmed2.value = true }

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
                        text = "Confirmer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
                    )
                }
                //ClickableText(
                 //   text = AnnotatedString("Confirmer"),
                 //   onClick = { isConfirmed2.value = true })
            }
        }
    }
    if(isConfirmed.value and isConfirmed2.value and !isConfirmed3.value and !isConfirmed4.value) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            mItemsList3.forEach { items ->
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



                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth())
                     {
                        val isChecked = remember { mutableStateOf(false) }

                        Checkbox(
                            checked = isChecked.value,
                            onCheckedChange = { isChecked.value = it },
                            enabled = true,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Magenta,
                                uncheckedColor = Color.DarkGray,
                                checkmarkColor = Color.Cyan
                            )
                        )
                        Text(text = items,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp)
                    }
                }
            }
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
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(8.dp)
                        .clickable {  isConfirmed3.value = true }

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
                        text = "Confirmer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
                    )
                }
                //ClickableText(
                //    text = AnnotatedString("Confirmer"),
                //    onClick = { isConfirmed3.value = true })
            }
        }
    }
    if(isConfirmed.value and isConfirmed2.value and isConfirmed3.value and !isConfirmed4.value) {

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            mItemsList4.forEach { items ->
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


                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()) {
                        val isChecked = remember { mutableStateOf(false) }

                        Checkbox(
                            checked = isChecked.value,
                            onCheckedChange = { isChecked.value = it },
                            enabled = true,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Magenta,
                                uncheckedColor = Color.DarkGray,
                                checkmarkColor = Color.Cyan
                            )
                        )
                        Text(text = items,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp)
                    }
                }
            }
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
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(8.dp)
                        .clickable {  isConfirmed4.value = true }

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
                        text = "Confirmer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
                    )
                }
                //ClickableText(
                //    text = AnnotatedString("Confirmer"),
                //    onClick = { isConfirmed4.value = true })
            }
        }
    }
}