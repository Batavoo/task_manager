package br.unifor.cct.taskmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import br.unifor.cct.taskmanager.R
import br.unifor.cct.taskmanager.entity.Task
import br.unifor.cct.taskmanager.repository.DatabaseUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskActivity : AppCompatActivity() {

    private lateinit var mTaskName: EditText
    private lateinit var mTaskDescripction: EditText
    private lateinit var mTaskFinished: CheckBox
    private lateinit var mTaskAction: Button

    private var mUserId = -1
    private var mTaskId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        mUserId = intent.getIntExtra("userId", -1)
        mTaskId = intent.getIntExtra("taskId", -1)


        mTaskName = findViewById(R.id.task_edittext_name)
        mTaskDescripction = findViewById(R.id.task_edittext_description)
        mTaskFinished = findViewById(R.id.task_checkbutton_finished)
        //mTaskFinished.visibility = View.GONE

        mTaskAction = findViewById(R.id.task_button)
        mTaskAction.setOnClickListener{
            if (mTaskId != -1){
                // TODO: Alterando uma tarefa existente no banco

            }else {
                // TODO: Criando uma nova tarefa no banco
                val name = mTaskName.text.toString().trim()
                val description = mTaskDescripction.text.toString().trim()
                val isFinished = mTaskFinished.isChecked

                if (name.isBlank()){
                    mTaskName.error = "Campo obrigatório"
                    return@setOnClickListener
                }

                GlobalScope.launch {
                    val taskDAO = DatabaseUtil
                        .getInstance(applicationContext)
                        .getTaskDAO()
                    val task = Task(
                        name = name,
                        description = description,
                        isFinished = isFinished,
                        userId = mUserId
                        )

                        taskDAO.insert(task)

                        //TODO : Adicionar um handler para exibir uma caixa de diálogo
                        //  informando a pessoa usuário que a tarefa foi cadastrada

                }

            }
        }

    }

    override fun onResume () {
        super.onResume()

        if (mTaskId != -1){
            // TODO: Alterando uma tarefa existente
            mTaskAction.text = "Atualizar"
        }else {
            // TODO: Criando uma nova tarefa
            mTaskAction.text = "Cadastrar"
        }
    }

}