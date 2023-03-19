package com.example.master_creator

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.master_creator.ui.theme.Master_CreatorTheme



class MainActivity : ComponentActivity() {
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Master_CreatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")



                }
            }

        }

    }
    override fun onResume() {
        super.onResume()
        play()



    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
    private fun play(){

        var index = 1;
        val audioFile = "chanson"+index

        val audioResourceId = resources.getIdentifier(audioFile, "raw", this.packageName)

        mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResourceId);
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener(MediaPlayer.OnCompletionListener() {

            mediaPlayer.release();
            index += 1;

            //ici on modifie
            val audioFile = "chanson"+index
            val audioResourceId = resources.getIdentifier(audioFile, "raw", packageName)


            mediaPlayer = android.media.MediaPlayer.create(getApplicationContext(), audioResourceId);
            mediaPlayer.start()
        });
    }
}




@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
fun MenuCreationMap(){

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Master_CreatorTheme {
        Greeting("Android")
    }
}