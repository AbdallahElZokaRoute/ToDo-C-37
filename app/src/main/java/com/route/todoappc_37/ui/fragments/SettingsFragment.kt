package com.route.todoappc_37.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.route.todoappc_37.R
import com.route.todoappc_37.ui.Constants
import com.route.todoappc_37.ui.LanguageUtils
import com.route.todoappc_37.ui.LocalStorageUtils
import com.route.todoappc_37.ui.MainActivity

class SettingsFragment : Fragment() {

    lateinit var languageSpinner: Spinner
    lateinit var modeSpinner: Spinner
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languageSpinner = view.findViewById(R.id.spinner_language)
        modeSpinner = view.findViewById(R.id.spinner_mode)
        languageSpinner.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Language_list,
            android.R.layout.simple_dropdown_item_1line
        )

        sharedPreferences = requireContext().getSharedPreferences(
            Constants.SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        languageSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    return
                } else if (position == 1) {
                    //Switch to english language
                    LanguageUtils.setLocale(requireContext(), "en")
                    val intent = Intent(requireContext().applicationContext, MainActivity::class.java)
                    activity?.finish()
                    startActivity(intent)
                } else if (position == 2) {
                    //Switch to Arabic Language
                    LanguageUtils.setLocale(requireContext().applicationContext, "ar")
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    activity?.finish()
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        modeSpinner.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Mode_list,
            android.R.layout.simple_dropdown_item_1line
        )
        modeSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    return
                } else if (position == 1) {

                    //Switch to Light language
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                } else if (position == 2) {
                    //Switch to Dark Language
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

}