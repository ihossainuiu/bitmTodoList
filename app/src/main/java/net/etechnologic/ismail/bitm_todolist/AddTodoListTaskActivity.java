package net.etechnologic.ismail.bitm_todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.etechnologic.ismail.bitm_todolist.database.TodoListTaskProvider;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListTask;

/**
 * Created by ismailhossain on 10/29/16.
 */

public class AddTodoListTaskActivity extends AppCompatActivity {

    EditText titleET, dueDateET, taskNoteET, taskStateET;
    Button addTaskBtn;
    TodoListTaskProvider todoListTaskProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo_list_task);
        todoListTaskProvider = new TodoListTaskProvider(this);
        final long taskGroupId = getIntent().getLongExtra("group_id", 0);

        Log.e(MainActivity.TAG, "Group_id_AddTodoListTaskActivityReceived: "  + taskGroupId);

        titleET = (EditText) findViewById(R.id.taskTitleET);
        dueDateET = (EditText) findViewById(R.id.taskDueDateET);
        taskNoteET = (EditText) findViewById(R.id.taskNoteET);
        taskStateET = (EditText) findViewById(R.id.taskStateET);
        addTaskBtn = (Button) findViewById(R.id.addTaskBtn);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoListTask todoListTask = new TodoListTask(taskGroupId,
                        titleET.getText().toString(),
                        dueDateET.getText().toString(),
                        taskNoteET.getText().toString(),
                        taskStateET.getText().toString());
                long data = todoListTaskProvider.addTodoListTask(todoListTask);
                Log.e(MainActivity.TAG, "Data Inserted:" + data);
            }
        });
    }
}
