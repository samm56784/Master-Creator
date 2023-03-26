package com.example.master_creator

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
                    Greeting("Android")
                    DefaultPreview()

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
    private var index = 0

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

@Composable
fun Greeting(name: String) {

    Text(text = "Hello $name!")
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

        Greeting("Android")
    }
}