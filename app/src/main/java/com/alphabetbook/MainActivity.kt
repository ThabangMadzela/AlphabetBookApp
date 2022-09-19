package com.alphabetbook
/**
 * Display each image letter
 * @author Thabang Thubane
 * @since 16 Sep 2022
 *
 */
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageSwitcher
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_nav_bar) }
    private val alphabets = intArrayOf(R.drawable.slide01,R.drawable.slide02,R.drawable.slide03,R.drawable.slide04,
        R.drawable.slide05,R.drawable.slide06,R.drawable.slide07,R.drawable.slide08,
        R.drawable.slide09,R.drawable.slide10,R.drawable.slide11,R.drawable.slide12,
        R.drawable.slide13,R.drawable.slide14,R.drawable.slide15,R.drawable.slide16,
        R.drawable.slide17,R.drawable.slide18,R.drawable.slide19,R.drawable.slide20,
        R.drawable.slide21,R.drawable.slide22,R.drawable.slide23,R.drawable.slide24,
        R.drawable.slide25,R.drawable.slide26)
    private var index = 0 //position each image as an int in alphabets

    lateinit var shared:SharedPreferences
    var last = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        shared = getSharedPreferences("SHARED", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = shared.edit()
        index = shared.getInt("INDEX",0)

        val imgSwitcher = findViewById<ImageSwitcher>(R.id.bar) // image switcher in overview xml
        val prev = findViewById<ImageView>(R.id.prev)
        val next = findViewById<ImageView>(R.id.next)
        imgSwitcher?.setFactory ({
            val imgView = ImageView(applicationContext)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView
        })

        if(intent != null){
                index = intent.getIntExtra("position",0)

        }

        index = shared.getInt("INDEX",0) // get the index to open to the last letter (or page) viewed
        imgSwitcher?.setImageResource(alphabets[index]) // rendering the image to the image switcher"displaying it"
        editor.putInt("INDEX", index) // store the current index incase the app closes
        editor.apply()

        //animating the images when prev and next are pressed
        val imgIn = AnimationUtils.loadAnimation(
            this, android.R.anim.slide_in_left)
        imgSwitcher?.inAnimation = imgIn

        val imgOut = AnimationUtils.loadAnimation(
            this, android.R.anim.slide_out_right)
        imgSwitcher?.outAnimation = imgOut


        prev.setOnClickListener {
            // prev was pressed after pressing first or last page
            menu.setItemSelected(R.id.ic_first,false); //unselect the firstpage button from nav bar
            menu.setItemSelected(R.id.ic_last,false);//unselect the firstpage button from nav bar

            index = if (index - 1 >= 0) index - 1 else 0
            editor.putInt("INDEX", index)
            editor.apply()
            imgSwitcher?.setImageResource(alphabets[index])
        }
        // next button functionality

        next.setOnClickListener {
            // next was pressed after pressing first or last page
            menu.setItemSelected(R.id.ic_first,false); //unselect the firstpage button from nav bar
            menu.setItemSelected(R.id.ic_last,false);//unselect the firstpage button from nav bar

            index = if (index + 1 < alphabets.size) index +1 else index
            editor.putInt("INDEX", index)//save the current index in case the app closes on this page
            editor.apply()
            imgSwitcher?.setImageResource(alphabets[index])
        }

        menu.setOnItemSelectedListener { id ->
            when (id) {
                R.id.ic_overview ->{


                    editor.putBoolean("last_page", false)//last page is only set to true of the app closes on the main activity
                    editor.apply()
                    val intent = Intent(this, OverView::class.java)
                    startActivity(intent)
                    menu.setItemSelected(R.id.ic_overview,false)// unselect the overview button after pressing it
                }
                R.id.ic_first ->{
                    println(index)
                    index = 0
                    editor.putInt("INDEX", index)////save the current index in case the app closes on this page
                    editor.apply()
                    imgSwitcher?.setImageResource(alphabets[index])

                }
                R.id.ic_last ->{
                    index = (alphabets.size - 1)
                    editor.putInt("INDEX", index)//save the current index in case the app closes on this page
                    editor.apply()
                    imgSwitcher?.setImageResource(alphabets[index])
                }

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val editor: SharedPreferences.Editor = shared.edit()
        editor.putString("NAME", "MainActivity")
        editor.putBoolean("last_page", true)

        editor.putInt("INDEX", index)
        editor.apply()
        println("im here")

        finish()
    }
}