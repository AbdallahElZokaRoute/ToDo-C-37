package com.route.todoappc_37.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.route.todoappc_37.R
import org.intellij.lang.annotations.Language
import java.util.Locale

class SettingsFragment : Fragment()  , OnItemSelectedListener{


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mode_spinner: Spinner = view.findViewById(R.id.spinner_mode)
      //  val language_spinner :Spinner =view.findViewById(R.id.spinner_language)

        mode_spinner.onItemSelectedListener = this

    //    language_spinner.onItemSelectedListener=this


           /*    val language_Adapter :ArrayAdapter<CharSequence> =ArrayAdapter.createFromResource(
               requireActivity().baseContext,
                 R.array.Language_list,
                   android.R.layout.simple_spinner_item
                    ).also { adapter ->
                        // Specify the layout to use when the list of choices appears
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                         // Apply the adapter to the spinner
                     language_spinner.adapter = adapter}   */




        val arrayAdapter :ArrayAdapter<CharSequence> =ArrayAdapter.createFromResource(
          requireActivity().baseContext,
          R.array.Mode_list,
          android.R.layout.simple_spinner_item
      ).also { adapter ->
          // Specify the layout to use when the list of choices appears
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          // Apply the adapter to the spinner
          mode_spinner.adapter = adapter
      }


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      if(parent?.id == R.id.spinner_mode)
      { if(parent?.getItemAtPosition(position).toString().equals("Night")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
             }}else if(parent?.id==R.id.spinner_language){
                 if(parent?.getItemAtPosition(position).toString().equals("English")) {
                     
                 }


             }



    }


    /*  fun changelang () {
         val langlist = arrayOf("Arabic", "English")

         val language = AlertDialog.Builder(requireActivity())
         language.setSingleChoiceItems(langlist, -1) { dialog, which ->
             if (which == 0) {
                 setlocate("ar")

             } else if(which ==1) {
                 setlocate("en")


             }
             dialog.dismiss()
         }
         val mdialog = language.create()
         mdialog.show()
     }

     private fun setlocate(lang :String){

        val locale = Locale(lang)
         Locale.setDefault(locale)
         val config = Configuration()

         config.locale = locale
         context?.resources?.updateConfiguration(config , context?.resources?.displayMetrics)

         val editor = sharedPreferences.getString("Settings", Context.MODE_PRIVATE).edit()
         editor.putString("My_Lang",lang)
         editor.apply()

     }


     private fun loadLocate() {
         val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
         val language = sharedPreferences.getString("My_Lang", "")
         setlocate(language)
     }





   */  override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")


    }
































}