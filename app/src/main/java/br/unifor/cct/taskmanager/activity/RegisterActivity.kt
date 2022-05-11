package br.unifor.cct.taskmanager.activity

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import br.unifor.cct.taskmanager.R
import br.unifor.cct.taskmanager.entity.User
import br.unifor.cct.taskmanager.repository.DatabaseUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mRegisterName:EditText
    private lateinit var mRegisterEmail:EditText
    private lateinit var mRegisterPassword:EditText
    private lateinit var mRegisterPasswordConfirmation:EditText
    private lateinit var mRegisterSave:Button

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterName = findViewById(R.id.register_editext_name)
        mRegisterEmail = findViewById(R.id.register_editext_email)
        mRegisterPassword = findViewById(R.id.register_editext_pw)
        mRegisterPasswordConfirmation = findViewById(R.id.register_editext_pwconfirm)

        mRegisterSave = findViewById(R.id.register_button_save)
        mRegisterSave.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.register_button_save -> handlerSaveAction()
        }
    }

    private fun handlerSaveAction() {

        val name = mRegisterName.text.trim()
        val email = mRegisterEmail.text.trim()
        val password = mRegisterPassword.text.trim()
        val passwordConfirmation = mRegisterPasswordConfirmation.text.trim()

        var isFormFilled = true

        isFormFilled = isNameFilled(name) && isFormFilled
        isFormFilled = isEmailFilled(email) && isFormFilled
        isFormFilled = isPasswordFilled(password) && isFormFilled
        isFormFilled = isPasswordConfirmationFilled(passwordConfirmation) && isFormFilled

        if (isFormFilled){

            if (password == passwordConfirmation){
                val user = User(
                    name=name.toString(),
                    email=email.toString(),
                    password=password.toString()
                )

//                val dialog = AlertDialog.Builder(applicationContext)
//                    .setTitle("TaskManager")
//                    .setMessage("Usuário $name cadastrado com sucesso!")
//                    .setCancelable(false)
//                    .setPositiveButton("OK", DialogInterface.OnClickListener{dialog, _ ->
//                        dialog.dismiss()
//                        finish()
//                    }).create()
//
//                dialog.show()

                GlobalScope.launch {
                    val userDAO = DatabaseUtil.getInstance(applicationContext).getUserDAO()
                    userDAO.insert(user)

                    handler.post{

                        val dialog = AlertDialog.Builder(this@RegisterActivity)
                            .setTitle("Task Manager")
                            .setMessage("Usuário $name cadastrado com sucesso!")
                            .setPositiveButton("OK"){ dialog, _ ->
                                dialog.dismiss()
                                finish()
                            }
                        dialog.show()
                    }
                }

               // Toast.makeText(applicationContext,"Usuário $name cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
               //finish()

            } else {
                mRegisterPasswordConfirmation.error = "As senhas estão diferentes"
                return
            }

            //Toast.makeText(this, "Formulário preenchido!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun isPasswordConfirmationFilled(passwordConfirmation: CharSequence): Boolean {
        return if (passwordConfirmation.isBlank()) {
            mRegisterPasswordConfirmation.error = "Este campo é obrigatório"
            false
        }else {
            true
        }
    }

    private fun isPasswordFilled(password: CharSequence): Boolean {
        return if (password.isBlank()) {
            mRegisterPassword.error = "Este campo é obrigatório"
            false
        } else{
         true
        }
    }

    private fun isEmailFilled(
        email: CharSequence): Boolean {
        return if (email.isBlank()) {
            mRegisterEmail.error = "Este campo é obrigatório"
            false
        } else {
            true
        }
     }

    private fun isNameFilled(name: CharSequence): Boolean {
        return if (name.isBlank()) {
            mRegisterName.error = "Este campo é obrigatório"
            false
        }   else {
            true
        }
    }
}