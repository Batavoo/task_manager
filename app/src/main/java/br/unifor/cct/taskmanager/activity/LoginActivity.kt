package br.unifor.cct.taskmanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import br.unifor.cct.taskmanager.R
import br.unifor.cct.taskmanager.repository.DatabaseUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mLoginEmail : EditText
    private lateinit var mLoginPw : EditText
    private lateinit var mLoginRegister : TextView
    private lateinit var mLoginSignIn : Button

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginEmail = findViewById(R.id.login_editext_email)
        mLoginPw = findViewById(R.id.login_editext_pw)

        mLoginRegister = findViewById(R.id.login_textview_register)
        mLoginRegister.setOnClickListener(this)

        mLoginSignIn = findViewById(R.id.login_button_signin)
        mLoginSignIn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {

            R.id.login_textview_register -> {
                val it = Intent(this, RegisterActivity::class.java)
                startActivity(it)
            }

            R.id.login_button_signin -> {
                val email = mLoginEmail.text.toString()
                val password = mLoginPw.text.toString()

                if (email.isBlank()){
                    mLoginEmail.error = "Este campo é obrigatório"
                    return
                }

                if (password.isBlank()){
                    mLoginEmail.error = "Este campo é obrigatório"
                    return
                }

                GlobalScope.launch {
                    val userDAO = DatabaseUtil.getInstance(applicationContext).getUserDAO()
                    val user = userDAO.findByEmail(email)

                    if (user != null){
                        if (user.password == password){
                            val it = Intent(applicationContext, MainActivity::class.java)
                            it.putExtra("userId", user.id)
                            startActivity(it)
                            finish()
                        } else {
                            mLoginEmail.text.clear()
                            mLoginPw.text.clear()

                            handler.post{showDialog("Email ou senha inválidos")}

                        }
                    } else {
                        handler.post{showDialog("Email ou senha inválidos")} // depois que eu coloquei esse else deu certo
                    }
                }
            }
        }
    }
    private fun showDialog(message:String){
        val dialog = AlertDialog.Builder(this)
            .setTitle("Task Manager")
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        dialog.show()
    }
}