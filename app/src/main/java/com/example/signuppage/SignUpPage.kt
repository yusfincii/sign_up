package com.example.signuppage

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.signuppage.databinding.FragmentSignUpPageBinding

class SignUpPage : Fragment() {
    private lateinit var page : FragmentSignUpPageBinding

    private var countryList = ArrayList<String>()
    private lateinit var adapterX : ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        page = FragmentSignUpPageBinding.inflate(inflater, container, false)

        page.switchAccountType.setOnCheckedChangeListener { buttonView, isChecked ->
            // switch ON case
            if(isChecked){
                page.textViewPremium.setTextColor(resources.getColor(R.color.gold))
                page.textViewStandart.setTextColor(resources.getColor(R.color.gray))

            }
            // switch OFF case
            else{
                page.textViewPremium.setTextColor(resources.getColor(R.color.gray))
                page.textViewStandart.setTextColor(resources.getColor(R.color.black))
            }
        }

        page.editTextBirthDate.setOnClickListener{

            val calendar = Calendar.getInstance()

            val yearX = calendar.get(Calendar.YEAR)
            val monthX = calendar.get(Calendar.MONTH)
            val dayX = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val text = "$dayOfMonth-$month-$year"
                page.editTextBirthDate.setText(text)
            },yearX, monthX, dayX)

            datePicker.setTitle("Birth Date")
            datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Set", datePicker)
            datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", datePicker)
            datePicker.show()
        }

        countryList.add("Turkey")
        countryList.add("England")
        countryList.add("Rwanda")
        countryList.add("Iraq")
        countryList.add("Germany")

        adapterX = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
            android.R.id.text1, countryList)
        page.spinner.adapter = adapterX

        page.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                // val selectedCOuntry = countryList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?)
            {
                Toast.makeText(requireContext(), "Please select your country", Toast.LENGTH_SHORT).show()
            }

        }

        return page.root
    }
}