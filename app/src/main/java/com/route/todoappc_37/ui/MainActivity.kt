package com.route.todoappc_37.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.preference.PreferenceManager.*
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.route.todoappc_37.R
import com.route.todoappc_37.designPatterns.Main
import com.route.todoappc_37.designPatterns.Product
import com.route.todoappc_37.ui.fragments.AddTodoBottomSheetFragment
import com.route.todoappc_37.ui.fragments.SettingsFragment
import com.route.todoappc_37.ui.fragments.TodoListFragment
import java.util.*
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addTodo: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    override fun attachBaseContext(newBase: Context?) {

        val language =
            LocalStorageUtils.getInstance(newBase!!).getString(Constants.LANGUAGE_KEY, "en")
        super.attachBaseContext(LanguageUtils.setLocale(newBase, language!!))
    }

    fun initViews() {
        bottomNavigationView = findViewById(R.id.home_bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.navigation_list) {

                pushFragment(TodoListFragment())
            } else if (it.itemId == R.id.navigation_settings) {
                pushFragment(SettingsFragment())

            }
            return@setOnItemSelectedListener true
        }


        addTodo = findViewById(R.id.add_todo)
        // onStop

        // onStart
        addTodo.setOnClickListener {
            val addToDoBottomSheetFragment = AddTodoBottomSheetFragment()
            addToDoBottomSheetFragment.show(supportFragmentManager, null)

        }

    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navigation_list
    }

    fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()

    }

}


/*
/*
    Database Concept ->
               1- Data Persistance (Data Storage)
                    Data Stored As Files
                    (Files)
                    (CRUD) Create Read Update Delete
                     // Files is hard to manipulate
                     // Consume Time
                Developer -> Database -> Files
                              SQLite
              1- SQLite ->  Consume a lot of Time
              2- Query Validation
               val query = "Selec * From Data"
                // Error In RunTime ->
              3- Learn SQL (Structured Query Language)

           what did Room Solved ? {
                1-validate  query on compile Time
                2- OOP -> Object Relational Mapping->

           }
 */

    data class SchoolEntry(
        val id: Int?,
        val name: String,
        val gender: String,
        val mark: Int,
        val `class`: String

    )

    val data = mutableListOf<SchoolEntry>()
val main = Main()
        main.doWorkOnProductFactory()
        val entry1 = SchoolEntry(1, "John Deo", "MALE", 80, "FOUR")
        val intent = Intent().also {
            it.putExtra("Sura_content", "SURA_CONTENT")
            it.putExtra("Sura_position", 1)
        }
        val intent2 = Intent()
        intent2.let {

        }
        val product = intent.getStringExtra("Sura_content")?.let {
            return@let Product().apply { name = it + "Sura_End" }
        }

 */