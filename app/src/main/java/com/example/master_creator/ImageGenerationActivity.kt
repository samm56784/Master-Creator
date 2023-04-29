package com.example.master_creator

import java.lang.*
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.client.OpenAI
import com.example.master_creator.ui.theme.Master_CreatorTheme
import io.ktor.client.*
import io.ktor.client.engine.android.*
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL


@OptIn(BetaOpenAI::class)
class ImageGenerationActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        val os: String? = System.getenv("OPENAI")
        println(os)
        super.onCreate(savedInstanceState)
        setContent {

            Master_CreatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //val apiKey: String  = System.getenv("OPENAI")
                    //println(apiKey+"GG")
                    // Path: Windows_NT
                    val apiKey: String = "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"
                    val openAI = OpenAI(apiKey)
                    var url = generation_image("dnd map",openAI)
                    //val imageurl = URL(url.toString())
                    //val im = EnregistrementImage(imageurl.toString())

                    Box(
                        modifier = Modifier.fillMaxHeight()
                    ) {
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
                    }


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ){
                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.9f)){
                        AffichageImage(url = url.toString())
                            }


                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .padding(8.dp)
                            .clickable {finish()
                            }
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
                            text = "Retour",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
                }
            }
        }
    }

}

@OptIn(BetaOpenAI::class)
fun generation_image(promptGenerationImage: String,openAI:OpenAI): Any = runBlocking {

    var image = openAI.imageURL(
        creation = ImageCreation(
            prompt = promptGenerationImage,
            n = 2,
            size = ImageSize.is1024x1024
        )
    )
    val url = image[1].url
    return@runBlocking url

}



@Composable
fun AffichageImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Translated description of what the image contains"
    )

}

@Composable
fun AsyncImage(model: String, contentDescription: String) {
    TODO("Not yet implemented")
}

fun EnregistrementImage(url: String): Bitmap {
    val scope = CoroutineScope(Dispatchers.Default)
    var image: Bitmap
    val url2 = URL(url)
    val imageData = url2.readBytes()
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)

}
fun Enregistrement(bmp:Bitmap,name:String){
    val dir = Environment.getExternalStorageDirectory().toString()
    var filename = dir + name
    try {
        FileOutputStream(filename).use { out ->
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out) // bmp is your Bitmap instance
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }



}

suspend fun Context.saveBitmap(fileName: String, bitmap: Bitmap) = withContext(Dispatchers.IO) {
    val file = File(filesDir, fileName)
    file.outputStream().use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
    }
}
