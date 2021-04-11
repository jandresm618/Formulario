package com.jandres.practica1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jandres.practica1.databinding.ActivityMainBinding
import java.net.UnknownServiceException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.E

private const val EMPTY = ""
private const val SPACE = " "
private const val ENTER = "\n"

class MainActivity : AppCompatActivity() {

    private lateinit var mainBindig: ActivityMainBinding
    private var users : MutableList<User> = mutableListOf()
    private var cal = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBindig.root)

        var birthday: String = ""

        mainBindig.saveButton.setOnClickListener {
            val email = mainBindig.emailEditText.text.toString()
            val password = mainBindig.passwordInputText.text.toString()
            val repassword = mainBindig.password2InputText.text.toString()
            val genre = if (mainBindig.maleRadioButton.isChecked) getString(R.string.male) else getString(R.string.female)
            var hobbies: MutableList<String> = mutableListOf()
            hobbies.add(if (mainBindig.sportsCheckBox.isChecked) getString(R.string.sport) + ENTER else SPACE + ENTER)
            hobbies.add(if (mainBindig.readCheckBox.isChecked) getString(R.string.read) + ENTER else SPACE + ENTER)
            hobbies.add(if (mainBindig.eatCheckBox.isChecked) getString(R.string.eat) + ENTER else SPACE + ENTER)
            hobbies.add(if (mainBindig.danceCheckBox.isChecked) getString(R.string.dance) + ENTER else SPACE + ENTER)
            val city = mainBindig.citySpinner.selectedItem.toString()


            if (email.isNotEmpty() and password.isNotEmpty() and repassword.isNotEmpty()) {
                if (password == repassword) {
                    mainBindig.password2InputLayout.error = null
                    saveUser(email, password, genre, hobbies,birthday.toString(),city)
                } else {
                    mainBindig.password2InputLayout.error = getString(R.string.password_error)
                }
            } else {
                Toast.makeText(this, getString(R.string.email_error), Toast.LENGTH_SHORT).show()
            }
            cleanViews()
        }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                birthday = sdf.format(cal.time).toString()
                mainBindig.etBirthday.setText(birthday)

            }

        mainBindig.etBirthday.setOnClickListener{
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun saveUser(email: String, password: String, genre: String, hobbies: MutableList<String>,birthday: String,city : String) {
        val hobbiesString = list2String(hobbies)
        val newUser = User(email, password, genre,hobbiesString,birthday,city)
        users.add(newUser)
        printUsersData()
    }

    private fun printUsersData() {
        var aux : String = ""
        for (user in users){
            aux = aux + user.email + SPACE + user.genre + SPACE + user.hobbies + ENTER + user.birthday + ENTER + user.city + ENTER
        }
        mainBindig.infoTextView.setText(aux)
    }

    private fun cleanViews() {
        with(mainBindig){
            emailEditText.setText(EMPTY)
            passwordInputText.setText(EMPTY)
            password2InputText.setText(EMPTY)
            maleRadioButton.isChecked = true
            sportsCheckBox.isChecked = false
            readCheckBox.isChecked = false
            eatCheckBox.isChecked = false
            danceCheckBox.isChecked = false
            etBirthday.setText(EMPTY)
        }
    }

    private fun list2String(list_aux : MutableList<String>): String {
        var string_aux : String = EMPTY
        for (item in list_aux){
            string_aux = string_aux + item
        }
        return string_aux
    }

}

