package com.example.master_creator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.master_creator.ui.theme.Master_CreatorTheme
import okhttp3.*
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

//fun initialisationListes() {
    var listeMapSize = arrayOf<String>("Petite", "Moyenne", "Grande")
    var listeUnivers = arrayOf<String>("Fantastique","Moderne","Science fiction")
    var listeEmplacement = arrayOf<String>("Ville", "Village","Château", "Forêt", "Auberge", "Grotte", "Plaine", "Champ", "Mer", "École", "Centre commercial", "Maison", "Usine"  )
    val listeAdjectifs = arrayOf<String>("Ténébreux", "Effrayant", "Dangereux", "Sinueux", "Sombre", "Obscur", "Malfamé", "Sinistre", "Inquiétant", "Mystérieux", "Délabré", "Hanté", "Labyrinthique", "Maudit", "Impénétrable", "Profond", "Impitoyable", "Infesté", "Épique", "Magique")
//}

class ListeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Master_CreatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    var mapSize: String = afficherListe(Liste = listeMapSize)
                    var univers = afficherListe(Liste = listeUnivers)
                    var emplacement = afficherListe(Liste = listeEmplacement)
                    var adjectifs = afficherListe(Liste = listeAdjectifs)
                    var miniPrompt: String = mapSize + univers + emplacement + adjectifs
                    var defaultPrompt:String = "highly detailed, dungeons and dragons battlemap, overhead view, intricate, photorealistic, 8k"
                    var finalPrompt:String = miniPrompt + defaultPrompt
                    générationImage(finalPrompt)

                }
            }
        }
    }
}


@Composable
fun afficherListe(Liste: Array<String>) :String{
    var retour: String = "okok"
    return(retour)
}

fun générationImage(prompt: String) {

    // Your OpenAI API key
    val apiKey = "YOUR_API_KEY"

    // The prompt for generating the image
    val prompt = "a cat sitting on a piano"

    // The URL of the OpenAI API
    val url = "https://api.openai.com/v1/images/generations"

    // The HTTP client
    val client = OkHttpClient()

    // The HTTP request body
    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("model", "image-alpha-001")
        .addFormDataPart("prompt", prompt)
        .build()

    // The HTTP request
    val request = Request.Builder()
        .url(url)
        .addHeader("Authorization", "Bearer $apiKey")
        .post(requestBody)
        .build()

    // The HTTP response
    val response = client.newCall(request).execute()

    // The binary image data
    val imageData = response.body?.bytes()

    // Write the image data to a file
    val file = File("generated-image.jpg")
    val outputStream = FileOutputStream(file)
    outputStream.write(imageData)
    outputStream.close()

    // Print the path of the generated image
    println("Generated image saved to ${file.absolutePath}")
}

