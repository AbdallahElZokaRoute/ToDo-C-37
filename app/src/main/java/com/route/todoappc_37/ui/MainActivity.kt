package com.route.todoappc_37.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.route.todoappc_37.R
import com.route.todoappc_37.designPatterns.Main
import com.route.todoappc_37.designPatterns.Product
import com.route.todoappc_37.ui.fragments.AddTodoBottomSheetFragment
import com.route.todoappc_37.ui.fragments.SettingsFragment
import com.route.todoappc_37.ui.fragments.TodoListFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addTodo: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
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

        bottomNavigationView.selectedItemId = R.id.navigation_list
        addTodo = findViewById(R.id.add_todo)
        addTodo.setOnClickListener {
            val addToDoBottomSheetFragment = AddTodoBottomSheetFragment()
            addToDoBottomSheetFragment.show(supportFragmentManager, null)

        }

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