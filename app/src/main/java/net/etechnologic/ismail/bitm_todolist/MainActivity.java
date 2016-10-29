package net.etechnologic.ismail.bitm_todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.etechnologic.ismail.bitm_todolist.database.TodoListGroupProvider;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    ListView groupListView;
    TodoListGroupProvider todoListGroupProvider;
    ArrayList<TodoListGroup> todoListGroups;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    FloatingActionButton addTodoListGroupFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupListView = (ListView) findViewById(R.id.groupListView);
        addTodoListGroupFab = (FloatingActionButton) findViewById(R.id.addTodoListGroupFab);

        todoListGroupProvider = new TodoListGroupProvider(this);

        todoListGroups = todoListGroupProvider.getAllTodoListGroup();

        for(TodoListGroup todoListGroup : todoListGroups) {
//            arrayList.add("ID:" + todoListGroup.getId()
//                    + "; Name:" + todoListGroup.getName());
            arrayList.add("" + todoListGroup.getId());
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        groupListView.setAdapter(arrayAdapter);

        groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String group = (String) parent.getItemAtPosition(position);
                long group_id = Long.parseLong(group);
                Log.e(TAG, "Group_id Send::" + group_id);
                Intent intent = new Intent(MainActivity.this, SingleGroupActivity.class);
                intent.putExtra("group_id", group_id);
                startActivity(intent);
            }
        });

        addTodoListGroupFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTodoListGroupActivity.class);
                startActivity(intent);
            }
        });
    }
}



