package com.route.todoappc_37.ui


import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.route.todoappc_37.Handlers
import com.route.todoappc_37.R
import com.route.todoappc_37.database.MyDataBase

import com.route.todoappc_37.ui.Prefrences.PreferenceManager
import com.yariksoffice.lingver.Lingver
import com.route.todoappc_37.MyApplication
import com.route.todoappc_37.ui.fragments.*


class MainActivity : AppCompatActivity() {


    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addTodo: FloatingActionButton
    lateinit var preferenceManger  : PreferenceManager
    lateinit var settingsFragment : SettingsFragment
    lateinit var todoListFragment : TodoListFragment
    lateinit var addToDoBottomSheetFragment : AddTodoBottomSheetFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        preferenceManger = PreferenceManager(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()


    }



    fun initViews() {
        todoListFragment = TodoListFragment()
        settingsFragment = SettingsFragment()
        bottomNavigationView = findViewById(R.id.home_bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.navigation_list) {

                pushFragment(todoListFragment)
            } else if (it.itemId == R.id.navigation_settings) {
                pushFragment(settingsFragment)

            }
            return@setOnItemSelectedListener true
        }
        //Communication between 2 fragments
        // TodoList Fragment <-->Add TodoFragment
        //Shared ViewModel Mvvm



        bottomNavigationView.selectedItemId = R.id.navigation_list
        addTodo = findViewById(R.id.add_todo)
        addTodo.setOnClickListener {
            addToDoBottomSheetFragment = AddTodoBottomSheetFragment()
            addToDoBottomSheetFragment.onButtClickListner = object : OnButtClickLitener{
                override fun onButtCLick() {
                    if(todoListFragment.isHidden) return
                    todoListFragment.getTodos()
                }

            }
            addToDoBottomSheetFragment.show(supportFragmentManager, null)

        }
        settingsFragment.onButtClickLitener = object : OnButtClickListener{
            override fun onButtClick() {


         supportFragmentManager.beginTransaction().apply {
             replace(R.id.fragment_container,settingsFragment)
             addToBackStack(null)
             commit()



         }



            }
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