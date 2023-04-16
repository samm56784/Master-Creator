package com.example.master_creator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

val apiKey = "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"

val openAI = OpenAI(apiKey)
@OptIn(BetaOpenAI::class)
class ImageGenerationActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Master_CreatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    var url = generation_image("dnd map")
                    //val imageurl = URL(url.toString())
                    //val im = EnregistrementImage(imageurl.toString())


                    AffichageImage(url = url.toString())

                }
            }
        }
    }

}

@OptIn(BetaOpenAI::class)
fun generation_image(promptGenerationImage: String): Any = runBlocking {

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