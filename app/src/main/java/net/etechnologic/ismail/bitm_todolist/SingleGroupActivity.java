package net.etechnologic.ismail.bitm_todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import net.etechnologic.ismail.bitm_todolist.database.TodoListTaskProvider;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListGroup;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListTask;

import java.util.ArrayList;

/**
 * Created by ismailhossain on 10/29/16.
 */

public class SingleGroupActivity extends AppCompatActivity {

    ListView groupTaskLV;
    EditText addTaskET;
    TodoListGroup todoListGroup;
    TodoListTaskProvider todoListTaskProvider;
    ArrayList<TodoListTask> todoListTasks;
    long group_id;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton addTodoListTaskFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_group);

        group_id = getIntent().getLongExtra("group_id", 0);
        Log.e(MainActivity.TAG, "Group_id Received: " + group_id);
        if(group_id==0){
            return;
        }

        Thread thread = new Thread(new MyRunnable());
        thread.start();

        long endTime = System.currentTimeMillis();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long threadEndDuraiton = System.currentTimeMillis() - startTime;
        long TimeDuration = endTime - startTime;
        Log.e(MainActivity.TAG, "ThreadEndDuration: " + threadEndDuraiton
                + "; TimeDuration: " + TimeDuration);


    }

    public class MyRunnable implements Runnable {
        public void run() {
            groupTaskLV = (ListView) findViewById(R.id.groupTaskLV);
            addTaskET = (EditText) findViewById(R.id.addTaskET);
            addTodoListTaskFab = (FloatingActionButton) findViewById(R.id.addTodoListTaskFab);

            todoListTaskProvider = new TodoListTaskProvider(SingleGroupActivity.this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            arrayList = new ArrayList<>();


            todoListTasks = todoListTaskProvider.getAllTodoListTaskGroupWise(group_id);

            for(TodoListTask todoListTask : todoListTasks) {
//            arrayList.add("ID:" + todoListGroup.getId()
//                    + "; Name:" + todoListGroup.getName());
                arrayList.add("" + todoListTask.getId());
            }

            arrayAdapter = new ArrayAdapter<>(SingleGroupActivity.this, android.R.layout.simple_list_item_1,arrayList);
            groupTaskLV.setAdapter(arrayAdapter);

            addTodoListTaskFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SingleGroupActivity.this, AddTodoListTaskActivity.class);
                    intent.putExtra("group_id", group_id);
                    startActivity(intent);
                }
            });
        }
    }
}
