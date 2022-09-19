package com.alphabetbook
/**
 * THe over view page
 * @author Thabang Thubane
 * @since 16 Sep 2022
 *
 */
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OverView : AppCompatActivity() {
    var adapter: CustomAdapter? = null
    var nameList= ArrayList<Item>()
    lateinit var shared:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_over_view)
        supportActionBar?.hide()
        shared = getSharedPreferences("SHARED", Context.MODE_PRIVATE)

        nameList.add(Item("Aa",R.drawable.slide01))
        nameList.add(Item("Bb",R.drawable.slide02))
        nameList.add(Item("Cc",R.drawable.slide03))
        nameList.add(Item("Dd",R.drawable.slide04))
        nameList.add(Item("Ee",R.drawable.slide05))
        nameList.add(Item("Ff",R.drawable.slide06))
        nameList.add(Item("Gg",R.drawable.slide07))
        nameList.add(Item("Hh",R.drawable.slide08))
        nameList.add(Item("Ii",R.drawable.slide09))
        nameList.add(Item("Jj",R.drawable.slide10))
        nameList.add(Item("Kk",R.drawable.slide11))
        nameList.add(Item("Ll",R.drawable.slide12))
        nameList.add(Item("Mm",R.drawable.slide13))
        nameList.add(Item("Nn",R.drawable.slide14))
        nameList.add(Item("Oo",R.drawable.slide15))
        nameList.add(Item("Pp",R.drawable.slide16))
        nameList.add(Item("Qq",R.drawable.slide17))
        nameList.add(Item("Rr",R.drawable.slide18))
        nameList.add(Item("Ss",R.drawable.slide19))
        nameList.add(Item("Tt",R.drawable.slide20))
        nameList.add(Item("Uu",R.drawable.slide21))
        nameList.add(Item("Vv",R.drawable.slide22))
        nameList.add(Item("Ww",R.drawable.slide23))
        nameList.add(Item("Xx",R.drawable.slide24))
        nameList.add(Item("Yy",R.drawable.slide25))
        nameList.add(Item("Zz",R.drawable.slide26))

        // initializing the adapter to correctly display the grid view
        adapter = CustomAdapter(nameList, this)
        findViewById<GridView>(R.id.gridView).adapter = adapter

        findViewById<GridView>(R.id.gridView).setOnItemClickListener { parent, view, position, id ->
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putBoolean("last_page", true)
            editor.putInt("INDEX",position)// get the index of the clicked image
            editor.apply()
            val intent = Intent(this, MainActivity::class.java);
            intent.putExtra("position", position);
            this.startActivity(intent);
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        val editor: SharedPreferences.Editor = shared.edit()
        editor.putString("NAME", "OverView")
        editor.putBoolean("last_page", false)
        editor.apply()
        println(shared.getBoolean("last_page",false))
        finish()
    }

}