package com.route.todoappc_37.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.route.todoappc_37.Handlers
import com.route.todoappc_37.R
import com.route.todoappc_37.ui.Prefrences.PreferenceManager
import java.util.*


class SettingsFragment : Fragment() {






    lateinit var langSpinner : Spinner
    lateinit var modeSpinner : Spinner
    lateinit var preferenceManger  : PreferenceManager




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        langSpinner = view.findViewById(R.id.language_spinner)
        preferenceManger = PreferenceManager(requireContext())
       var langSpinnerAdapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(requireContext(),R.array.languages_array,android.R.layout.simple_spinner_item)
        langSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        langSpinner.adapter = langSpinnerAdapter
        var selectionCurrent: Int = preferenceManger.getSelection()
        var selectionCurrent2 : Int = preferenceManger.getMode()
        langSpinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                preferenceManger.setSelection(position)

                var lang = parent!!.getItemAtPosition(position).toString()

                if (selectionCurrent != position){
                    Toast.makeText(context, "Language has been set to $lang", Toast.LENGTH_SHORT).show()
                    activity?.recreate()


                }
                selectionCurrent = position

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                langSpinner.setSelection(preferenceManger.getSelection())

            }

        }



        modeSpinner = view.findViewById(R.id.mode_spinner)
        var modeSpinnerAdapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(requireContext(),R.array.mode_array,android.R.layout.simple_spinner_item)
        langSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        modeSpinner.adapter = modeSpinnerAdapter

        modeSpinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                preferenceManger.setMode(position)
                var mode = parent!!.getItemAtPosition(position).toString()


                if (selectionCurrent2 != position){
                    Toast.makeText(context, "Mode has been set to $mode", Toast.LENGTH_SHORT).show()

                }
                selectionCurrent2 = position

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                modeSpinner.setSelection(preferenceManger.getMode())

            }


        }
        handleMode()
         }

    override fun onResume() {
        super.onResume()
        langSpinner.setSelection(preferenceManger.getSelection())
        modeSpinner.setSelection(preferenceManger.getMode())
    }


    fun handleMode(){

        if (preferenceManger.getMode()==0){


            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        else if (preferenceManger.getMode()==1){


            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }


    }

    }
