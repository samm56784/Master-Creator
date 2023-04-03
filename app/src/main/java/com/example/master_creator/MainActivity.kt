package com.example.master_creator

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.os.IBinder
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.master_creator.ui.theme.Master_CreatorTheme
import java.lang.reflect.Field


class MainActivity : ComponentActivity() {
    lateinit var mediaPlayer: MediaPlayer

     private val arrayListMusique = ArrayList<String>();
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            Master_CreatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DefaultPreview()
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        lancerDee(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(x = 0.dp, y = 100.dp))
                        startServiceMusique( Modifier
                            .width(200.dp)
                            .height(80.dp)
                            .offset(x = 0.dp, y = 200.dp))

                    }


                }
            }
        }

        val fields : Array<Field> = R.raw::class.java.getFields();

        for (i in 0..fields.size-1)
            arrayListMusique.add(fields.get(i).name)

        for (i in 0..fields.size-1)
           System.out.println(arrayListMusique.get(i))

    }
    override fun onResume() {
        super.onResume()
       // play()


    }

    override fun onStart() {
        super.onStart()
        //val intent = Intent(this, MusicService::class.java)
       // intent.putExtra("MUSIC_LIST", arrayListMusique)
       // startService(intent)
    }

    @Composable
    fun startServiceMusique(modifier: Modifier) {

        Button(onClick = {
            val intent = Intent(this, MusicService::class.java)
            intent.putExtra("MUSIC_LIST", arrayListMusique)
            startService(intent)
        },modifier=modifier) {
            Text(text = "Starter playlist", color = Color.Red)

        }

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
      //  mediaPlayer.stop()
      //  mediaPlayer.release()
    }
    private var index = 0



    @Composable
    fun lancerDee(modifier: Modifier) {
        var nombreRandom by remember { mutableStateOf(0) }
        characterSheet()
        Button(onClick = {

            nombreRandom = (1..20).random()
            System.out.println(nombreRandom)

            //Text(text = "Nombre: $nombreRandom!")
        },modifier=modifier) {
            Text(text = "Résultat: $nombreRandom", color = Color.Magenta)
        }
    }
    fun characterSheet() {
        val character = CharacterSheet(rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),true,rollAttribute(),"Chad")

        System.out.println(character.charisme)
        System.out.println(character.defense)
        System.out.println(character.dexterity)
        System.out.println(character.intelligence)
    }
    fun rollAttribute(): Int {
        var valeurAttribue = 0
        val arrayListRoll = ArrayList<Int>();

        for (i in 0..3)
           arrayListRoll.add((1..6).random())

        arrayListRoll.sort()
        for (i in 1..3)
            valeurAttribue+=arrayListRoll.get(i)

        return valeurAttribue
    }

    override fun onDestroy() {
        super.onDestroy()
        //mediaPlayer.release()
    }
}
 class MusicService : Service(){
     private lateinit var mediaPlayer: MediaPlayer
     private lateinit var arrayListMusique: ArrayList<String>
     private var index = 0
     override fun onBind(intent: Intent): IBinder? {
         return null
     }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         arrayListMusique = intent?.getStringArrayListExtra("MUSIC_LIST") as ArrayList<String>
        play()

        return START_NOT_STICKY
    }
    private fun play() {

        var audioResourceId = resources.getIdentifier(arrayListMusique.get(index), "raw", packageName)

        mediaPlayer = MediaPlayer.create(this,audioResourceId)
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener{ mediaPlayer ->
            index += 1
            if (index == arrayListMusique.size) {
                index = 0
            }
            mediaPlayer.release()
            play()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}



fun MenuCreationMap(){

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Image(
        painter = painterResource(id = R.drawable.menuprincipale),
        contentDescription = null
    )

    Master_CreatorTheme {
    }
}