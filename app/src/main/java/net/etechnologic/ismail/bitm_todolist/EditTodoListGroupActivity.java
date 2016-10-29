package net.etechnologic.ismail.bitm_todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import net.etechnologic.ismail.bitm_todolist.database.TodoListGroupProvider;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListGroup;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class EditTodoListGroupActivity extends AppCompatActivity {

    EditText groupNameET;
    TodoListGroupProvider todoListGroupProvider;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo_list_group);
        id = getIntent().getLongExtra("group_id", 0);
        groupNameET = (EditText) findViewById(R.id.groupNameET);
        groupNameET.setText(String.valueOf(id));
        todoListGroupProvider = new TodoListGroupProvider(this);
    }

    public void updateGroup(View view) {

        try {
            TodoListGroup todoListGroup = new TodoListGroup(
                    id, groupNameET.getText().toString());

            todoListGroupProvider.updateTodoListGroup(todoListGroup);
        } catch (Exception e){

            e.printStackTrace();
        }

        Intent intent = new Intent(EditTodoListGroupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

        Log.e(MainActivity.TAG, "Updated: " + id);
    }

    public void deleteGroup(View view) {
        todoListGroupProvider.deleteTodoListGroup(id);
        Log.e(MainActivity.TAG, "Deleted: " + id);
        Intent intent = new Intent(EditTodoListGroupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
