package com.route.todoappc_37.ui


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.route.todoappc_37.MyApplication
import com.route.todoappc_37.R

import com.route.todoappc_37.ui.Prefrences.PreferenceManager
import com.route.todoappc_37.ui.fragments.*


class MainActivity : AppCompatActivity() {


    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addTodo: FloatingActionButton
    lateinit var preferenceManager : PreferenceManager
    lateinit var settingsFragment : SettingsFragment
    lateinit var todoListFragment : TodoListFragment
    lateinit var addToDoBottomSheetFragment : AddTodoBottomSheetFragment
    lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        preferenceManager = PreferenceManager(this)
        if (preferenceManager.getLanguage() == 1){
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL}
        else if (preferenceManager.getLanguage() ==0 ){
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR}

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       textView = findViewById(R.id.fragment_name)
        initViews()


    }



    fun initViews() {
        todoListFragment = TodoListFragment()
        settingsFragment = SettingsFragment()
        bottomNavigationView = findViewById(R.id.home_bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.navigation_list) {

                pushFragment(todoListFragment)
                textView.text = getString(R.string.ToDoList)

            } else if (it.itemId == R.id.navigation_settings) {


                pushFragment(settingsFragment)
                textView.text = getString(R.string.Settings)

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
            addToDoBottomSheetFragment.onButtClickListner = object : OnButtonClickLitener{
                override fun onButtonCLick() {
                    if(todoListFragment.isHidden) return
                    todoListFragment.getTodos()
                }

            }
            addToDoBottomSheetFragment.show(supportFragmentManager, null)

        }

    }

    override fun onResume() {
        super.onResume()

        if (MyApplication.hasModeSwitched || MyApplication.hasLanguageSwitched) {

            pushFragment(settingsFragment)
            MyApplication.hasModeSwitched = false
            MyApplication.hasLanguageSwitched = false
            textView.text = getString(R.string.Settings)

        }}

    override fun attachBaseContext(newBase: Context?) {
        preferenceManager = PreferenceManager(newBase!!)

        val position = preferenceManager.getLanguage()
        var language : String
        if( position == 0){
            language = "en"
        }
        else{
            language = "ar"
        }

        super.attachBaseContext(LanguageUtils.setLocate(newBase,language))


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