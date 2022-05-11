package br.unifor.cct.taskmanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.unifor.cct.taskmanager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var mMainTaskList: RecyclerView
    private lateinit var mMainTaskAdd: FloatingActionButton

    private var userId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userId = intent.getIntExtra("userId", -1)

        mMainTaskList = findViewById(R.id.main_recyclerview_tasklist)

        mMainTaskAdd = findViewById(R.id.main_fab_task_add)
        mMainTaskAdd.setOnClickListener{
            val it = Intent(this, TaskActivity::class.java)
            startActivity(it)
        }



    }
}