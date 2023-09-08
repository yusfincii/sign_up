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
import android.widget.Toast
import com.example.signuppage.databinding.FragmentSignUpPageBinding

class SignUpPage : Fragment() {
    // binding
    private lateinit var page : FragmentSignUpPageBinding

    private var countryList = ArrayList<String>()
    private lateinit var adapterX : ArrayAdapter<String>

    // this string variable represents the invalid characters list
    private val invalid = "0123456789/?!:;%.,"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        page = FragmentSignUpPageBinding.inflate(inflater, container, false)

        page.apply {
            buttonSubmit.setOnClickListener {
                // name control
                if(editTextName.text.toString().trim() == "")
                { Toast.makeText(requireContext(), "Please enter your name!", Toast.LENGTH_SHORT).show() }

                // name invalid character control
                else if(editTextName.text.any { it in invalid })
                { Toast.makeText(requireContext(), "Please type name without invalid characters!", Toast.LENGTH_SHORT).show() }

                // surname control
                else if(editTextSurname.text.toString().trim() == "")
                { Toast.makeText(requireContext(), "Please enter your surname!", Toast.LENGTH_SHORT).show() }

                // surname invalid character control
                else if(editTextSurname.text.any { it in invalid })
                { Toast.makeText(requireContext(), "Please type surname without invalid characters!", Toast.LENGTH_SHORT).show() }

                // date null control
                else if(editTextBirthDate.text.toString().trim() == "")
                { Toast.makeText(requireContext(), "Please enter your birth date!", Toast.LENGTH_SHORT).show() }

                // gender control
                else if(!(radioButtonMale.isChecked || radioButtonFemale.isChecked))
                { Toast.makeText(requireContext(), "Please choose gender!", Toast.LENGTH_SHORT).show() }

                // confirmation control
                else if (!checkBoxTerm.isChecked)
                { Toast.makeText(requireContext(), "Please read and tick the confirm checkbox!", Toast.LENGTH_SHORT).show() }

                // successful scenario
                else { Toast.makeText(requireContext(), "Succesfully submitted!", Toast.LENGTH_SHORT).show() }
            }
        }

        page.switchAccountType.setOnCheckedChangeListener { buttonView, isChecked ->
            // switch ON case - premium
            if(isChecked){
                page.textViewPremium.setTextColor(resources.getColor(R.color.gold))
                page.textViewStandart.setTextColor(resources.getColor(R.color.gray))
                Toast.makeText(requireContext(), "Account Type : PREMIUM", Toast.LENGTH_SHORT).show()

            }
            // switch OFF case - standart
            else{
                page.textViewPremium.setTextColor(resources.getColor(R.color.gray))
                page.textViewStandart.setTextColor(resources.getColor(R.color.black))
                Toast.makeText(requireContext(), "Account Type : STANDART", Toast.LENGTH_SHORT).show()
            }
        }

        page.editTextBirthDate.setOnClickListener{
            // a calendar object
            val calendar = Calendar.getInstance()

            val yearX = calendar.get(Calendar.YEAR)
            val monthX = calendar.get(Calendar.MONTH)
            val dayX = calendar.get(Calendar.DAY_OF_MONTH)

            // it will display current date when opened
            val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val text = "$dayOfMonth-$month-$year"
                page.editTextBirthDate.setText(text)
            },yearX, monthX, dayX)

            datePicker.setTitle("Birth Date")
            datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Set", datePicker)
            datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", datePicker)
            datePicker.show()
        }

        // random countries
        countryList.add("Turkey")
        countryList.add("England")
        countryList.add("Rwanda")
        countryList.add("Iraq")
        countryList.add("Germany")

        // spinner adapter
        adapterX = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
            android.R.id.text1, countryList)
        page.spinner.adapter = adapterX

        page.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            { val selectedCountry = countryList[position]
                Toast.makeText(requireContext(), "Selected country : $selectedCountry", Toast.LENGTH_SHORT).show() }
            override fun onNothingSelected(parent: AdapterView<*>?)
            { Toast.makeText(requireContext(), "Please select your country", Toast.LENGTH_SHORT).show() }
        }
        return page.root
    }
}