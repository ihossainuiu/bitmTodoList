package net.etechnologic.ismail.bitm_todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.etechnologic.ismail.bitm_todolist.database.TodoListGroupProvider;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListGroup;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class AddTodoListGroupActivity extends AppCompatActivity {

    EditText groupNameET;
    Button addGroupBtn;
    TodoListGroupProvider todoListGroupProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo_list_group);
        groupNameET = (EditText) findViewById(R.id.groupNameET);
        addGroupBtn = (Button) findViewById(R.id.addGroupBtn);
        todoListGroupProvider = new TodoListGroupProvider(this);
        addGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoListGroup todoListGroup =
                        new TodoListGroup(groupNameET.getText().toString());
                todoListGroupProvider.addTodoListGroup(todoListGroup);
                Intent intent = new Intent(AddTodoListGroupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
