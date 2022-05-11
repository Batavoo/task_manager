package br.unifor.cct.taskmanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import br.unifor.cct.taskmanager.R
import br.unifor.cct.taskmanager.repository.DatabaseUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.myLooper()!!) //objeto do tipo handler
        //sempre que abrimos uma tread pra executat uma tarefa que necessite atualizar uma coisa
    // na UI da aplicação, vai dar erro, pois apenas a thread principal manipula a interface
    // gráfica do usuário
        //isso vai enviar para uma fila de operações para a main thread, porém, com a função
        // abaixo, ele tem um atraso de 3 segundos antes de colocar na fila de execução

        handler.postDelayed({
                            //vai abrir a tela de login
                            val it = Intent(SplashActivity@this,LoginActivity::class.java)
            //o this sozinho pode fazer com q aponte apenas pro escopo dessa função
            //então usamos SplashActivity@this para apontar para o exterior dessa funçao
                            startActivity(it)
                            finish()
                            //isso garante que quando ir para uma outra acitivy, encerre essa
                            // activity, impedindo que o usuário volte a página para essa
        },3000L) //vai executar após 3 segundos (o L é pq é um Long)

        DatabaseUtil.getInstance(applicationContext)

    }
}