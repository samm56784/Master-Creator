package com.example.master_creator

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.master_creator.ui.theme.Master_CreatorTheme
import com.google.gson.Gson
import java.io.*
import java.lang.reflect.Field

var character = CharacterSheet();


class MainActivity : ComponentActivity() {

    lateinit var mediaPlayer: MediaPlayer
    private val arrayListMusique = ArrayList<String>();
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

                        //Bouton("Information personnage")

                        Bouton("Generation d'image")
                       // Bouton("Lancement de dée")
                       // Bouton("Musique")

                        lancerDee(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(x = 0.dp, y = 100.dp))
                        startServiceMusique(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(x = 0.dp, y = 200.dp))
                        characterSheet(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(x = 0.dp, y = 300.dp))



                        Spacer(modifier = Modifier.fillMaxHeight(0.45f))
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth())
                        {
                            Row(modifier = Modifier.fillMaxHeight())
                            {
                                Spacer(modifier = Modifier.fillMaxWidth(0.45f))
                                Text(
                                    text = "Comment puis-je vous aider ?",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 34.sp,
                                )
                            }
                        }
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
    @Composable
    fun Bouton(text: String,) {


        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable { val intent = Intent(this@MainActivity,ChoixDimage::class.java)
                    //intent.putExtra("array",arrayList)
                    startActivity(intent) }

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
    @Composable
    fun lancerDee(modifier: Modifier) {
        var nombreRandom by remember { mutableStateOf(0) }
        //characterSheet()

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable {

                    nombreRandom = (1..20).random()
                    System.out.println(nombreRandom)

                    // start activity pour lancer de dées

                    val intent = Intent(this@MainActivity,DiceSelectionActivity::class.java)
                    //intent.putExtra("array",arrayList)
                    startActivity(intent) }

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
                text = "Lancer de dée",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
            )
        }




        /*Button(onClick = {

            nombreRandom = (1..20).random()
            System.out.println(nombreRandom)

            // start activity pour lancer de dées

            val intent = Intent(this@MainActivity,DiceSelectionActivity::class.java)
            //intent.putExtra("array",arrayList)
            startActivity(intent)

            //Text(text = "Nombre: $nombreRandom!")
        },modifier=modifier) {
            Text(text = "Résultat: $nombreRandom", color = Color.Magenta)
        }*/
    }
    @Composable
    fun startServiceMusique(modifier: Modifier) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable {val intent = Intent(this, MusiqueMenu::class.java)
                    intent.putExtra("MUSIC_LIST", arrayListMusique)
                    //startService(intent)
                    startActivity(intent)
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
                text = "Musique",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    @Composable
    fun characterSheet(modifier: Modifier) {


        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable { val intent = Intent(this, CharacterActivity::class.java)
                    startActivity(intent)}

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
                text = "Character",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
            )
        }

    }

}

/*
class MainActivity : ComponentActivity() {
    lateinit var mediaPlayer: MediaPlayer

     private val arrayListMusique = ArrayList<String>();
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            Master_CreatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //DefaultPreview()
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        lancerDee(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(x = 0.dp, y = 100.dp))
                        startServiceMusique(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(x = 0.dp, y = 200.dp))
                        characterSheet(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(x = 0.dp, y = 300.dp))

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

    @Composable
    fun characterSheet(modifier: Modifier) {
        Button(onClick = {
            val intent = Intent(this, CharacterActivity::class.java)
            startActivity(intent)
        },modifier=modifier) {
            Text(text = "Character Sheet", color = Color.Red)

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
        //characterSheet()
        Button(onClick = {

            nombreRandom = (1..20).random()
            System.out.println(nombreRandom)

            // start activity pour lancer de dées

            val intent = Intent(this@MainActivity,DiceSelectionActivity::class.java)
            //intent.putExtra("array",arrayList)
            startActivity(intent)

            //Text(text = "Nombre: $nombreRandom!")
        },modifier=modifier) {
            Text(text = "Résultat: $nombreRandom", color = Color.Magenta)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //mediaPlayer.release()
    }
}*/
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


class MusiqueMenu : ComponentActivity() {
    lateinit var mediaPlayer: MediaPlayer
    private val arrayListMusique = ArrayList<String>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

            val fields : Array<Field> = R.raw::class.java.getFields();

            for (i in 0..fields.size-1)
                arrayListMusique.add(fields.get(i).name)

            for (i in 0..fields.size-1)
                System.out.println(arrayListMusique.get(i))

            Box(
                modifier = Modifier.fillMaxSize(),
                //contentAlignment = Alignment.Center,

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
                    update = {}
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .padding(8.dp)
                            .clickable {
                                val intent = Intent(this@MusiqueMenu, MusicService::class.java)
                                intent.putExtra("MUSIC_LIST", arrayListMusique)
                                startService(intent) }
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
                            text = "Start playlist",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
                        )
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

                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .padding(8.dp)
                            .clickable {stopService(Intent(this@MusiqueMenu, MusicService::class.java))
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
                            text = "Stop musique",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
                        )
                    }



                }
            }
        }



    }

}

class DiceSelectionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var dernierRoll by remember { mutableStateOf(0) }
            Box(
                modifier = Modifier.fillMaxSize(),
                //contentAlignment = Alignment.Center,

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
                    update = {}
                )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {


                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
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
                                setImageResource(R.drawable.diceroll)
                            }
                        },
                        update = {}
                    )
                    Text(
                        text = dernierRoll.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp, modifier = Modifier.align(Alignment.Center), color = Color.White
                    )
                }

                DiceSelectionButton(
                    text = "D20",
                    onClick = { dernierRoll = lancerDee(20) }
                )
                DiceSelectionButton(
                    text = "D12",
                    onClick = { dernierRoll = lancerDee(12) }
                )
                DiceSelectionButton(
                    text = "D10",
                    onClick = { dernierRoll = lancerDee(10) }
                )
                DiceSelectionButton(
                    text = "D6",
                    onClick = { dernierRoll = lancerDee(6) }
                )
                DiceSelectionButton(
                    text = "Retour",
                    onClick = { finish() }
                )
             }
            }
        }


    }

    @Composable
    fun DiceSelectionButton(text: String, onClick: () -> Unit) {

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable { onClick() }
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

    private fun lancerDee(Int: Int): Int {
        var nombreRandom = (1..Int).random()
        println(nombreRandom)

        return (nombreRandom)
    }


}


class CharacterActivity : ComponentActivity() {
    var hp by mutableStateOf<Int>(0)
    var def by mutableStateOf<Int>(0)
    var str by mutableStateOf<Int>(0)
    var dex by mutableStateOf<Int>(0)
    var int by mutableStateOf<Int>(0)
    var wis by mutableStateOf<Int>(0)
    var cha by mutableStateOf<Int>(0)
    var stam by mutableStateOf<Int>(0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            var isCharacterGenerated by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.fillMaxSize(),
                //contentAlignment = Alignment.Center,

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
                    update = {}
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {


                    chargerCharacter(
                        text = "Sauvegarder Character",
                        onClick = {

                            System.out.println("OKkkkkkkkkkk")
                            saveCharacterSheet(character)
                        }
                    )

                    chargerCharacter(
                        text = "Charger Character",
                        onClick = {
                             val character1 = loadCharacterSheet()
                            if (character1 != null) {
                                character = character1
                                hp = character.get("Health Point")
                                def = character.get("Defence")
                                str = character.get("Strength")
                                dex = character.get("Dexterity")
                                int = character.get("Intelligence")
                                wis = character.get("Wisdom")
                                cha = character.get("Charisma")
                                stam = character.get("Stamina")

                            };

                        }
                    )

                    genererCharacter(
                        text = "Generer Character",
                        onClick = { characterSheet()
                        }
                    )

                    attributeUI(text = "Health Point", onClick = { /*TODO*/ }, attribue = "hp", value = hp)
                    attributeUI(text = "Defence", onClick = { /* do something */ }, "def", value = def)
                    attributeUI(text = "Strength", onClick = { /* do something */ }, "strength", value = str)
                    attributeUI(text = "Dexterity", onClick = { /* do something */ }, "dex", value = dex)
                    attributeUI(text = "Intelligence", onClick = { /* do something */ }, "intelligence", value = int)
                    attributeUI(text = "Wisdom", onClick = { /* do something */ }, "wisdom", value = wis)
                    attributeUI(text = "Charisma", onClick = { /* do something */ }, "charisme", value = cha)
                    attributeUI(text = "Stamina", onClick = { /*TODO*/ }, attribue = "stam", value = stam)

                    chargerCharacter(
                        text = "Retour",
                        onClick = { finish() }
                    )
                }
            }
        }
    }
    @Composable
    fun chargerCharacter(text: String, onClick: () -> Unit) {

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable { onClick() }
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


    @Composable
    fun genererCharacter(text: String, onClick: () -> Unit) {

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable { onClick() }
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


    @Composable
    fun attributeUI(text: String, onClick: () -> Unit,attribue: String, value: Int) {


        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clickable { onClick() }
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
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        scaleType = ImageView.ScaleType.FIT_START
                        val imageN = attribue;
                        val ressource = resources.getIdentifier(imageN, "drawable", packageName)
                        setImageResource(ressource)
                    }
                },
                update = {}
            )
            Text(
                text = text+" : "+value.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    fun characterSheet() {
         character = CharacterSheet(rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),rollAttribute(),true,rollAttribute(),"Chad")

        hp = character.get("Health Point");
        str = character.get("Strength");
        wis = character.get("Wisdom");
        cha = character.get("Charisma");
        def = character.get("Defence");
        dex = character.get("Dexterity");
        int = character.get("Intelligence");
        stam = character.get("Stamina");

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
    fun saveCharacterSheet(character: CharacterSheet?) {
            try {

                val sharedPreferences = getSharedPreferences("Character", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                val gson = Gson()
                val json = gson.toJson(character)
                val random = (1000..9999).random()
                editor.putString("Character1", json)
                editor.apply()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    fun loadCharacterSheet(): CharacterSheet? {
        val sharedPreferences = this.getSharedPreferences("Character", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("Character1", null)
        return gson.fromJson(json, CharacterSheet::class.java)
    }

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