/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.etechnologic.ismail.bitm_todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import net.etechnologic.ismail.bitm_todolist.database.TodoListTaskProvider;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListTask;

import java.util.ArrayList;

import static net.etechnologic.ismail.bitm_todolist.MainActivity.TAG;

/**
 * Provides UI for the view with List.
 */
public class SingleGroupTaskActivity extends AppCompatActivity {

    RecyclerView taskRecyclerView;
    long group_id;
    FloatingActionButton addTodoListGroupTaskFab;
//    EditText addGroupTaskET;
    TodoListTaskProvider todoListTaskProvider;
    ArrayList<String> arrayList;
    ArrayList<TodoListTask> todoListTasks;
    private DrawerLayout mDrawerLayout;
    ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_group_recycler_view_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        group_id = getIntent().getLongExtra("group_id", -1);
        Log.e(MainActivity.TAG, "Group_id Received: " + group_id);
        if(group_id == -1){
            return;
        }

        taskRecyclerView = (RecyclerView) findViewById(R.id.task_recycler_view);
//        addGroupTaskET = (EditText) findViewById(R.id.addGroupTaskET);
        addTodoListGroupTaskFab = (FloatingActionButton) findViewById(R.id.addTodoListGroupTaskFab);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);

                        // TODO: handle navigation

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        todoListTaskProvider = new TodoListTaskProvider(SingleGroupTaskActivity.this);
        todoListTasks = todoListTaskProvider.getAllTodoListTaskGroupWise(group_id);

        Log.e(MainActivity.TAG, "TodoListTasks: " + todoListTasks.size());

        adapter = new ContentAdapter(this, todoListTasks);
        taskRecyclerView.setAdapter(adapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoListTaskProvider = new TodoListTaskProvider(SingleGroupTaskActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        arrayList = new ArrayList<>();


        for(TodoListTask todoListTask : todoListTasks) {
//            arrayList.add("ID:" + todoListGroup.getId()
//                    + "; Name:" + todoListGroup.getName());
            arrayList.add("" + todoListTask.getId());
        }

        addTodoListGroupTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleGroupTaskActivity.this, AddTodoListTaskActivity.class);
                intent.putExtra("group_id", group_id);
                startActivity(intent);
                finish();
            }
        });

//        addGroupTaskET.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                Log.d(TAG, keyCode+" "+event.getKeyCode());
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (KeyEvent.KEYCODE_ENTER == event.getKeyCode() ||
//                                keyCode == EditorInfo.IME_ACTION_NONE)) {
//                    // Perform action on key press
//                    TodoListTask todoListTask = new TodoListTask(group_id,
//                            addGroupTaskET.getText().toString());
//                    long data = todoListTaskProvider.addTodoListTask(todoListTask);
//                    todoListTasks.add(todoListTask);
//                    adapter.notifyDataSetChanged();
//
//
//                    return true;
//                }
//                return false;
//            }
//        });




    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox taskCheckBox;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.view_holder_todo_list_task, parent, false));
            taskCheckBox = (CheckBox) itemView.findViewById(R.id.list_check_box);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
//        private static final int LENGTH = 18;
//        private final String[] mPlaces;
//        private final String[] mPlaceDesc;
//        private final Drawable[] mPlaceAvators;

        ArrayList<TodoListTask> todoListTasks;
        Context context;

        public ContentAdapter(Context context,  ArrayList<TodoListTask> todoListTasks) {
            this.todoListTasks = todoListTasks;
            this.context = context;

//            Resources resources = context.getResources();
//            mPlaces = resources.getStringArray(R.array.places);
//            mPlaceDesc = resources.getStringArray(R.array.place_desc);
//            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
//            mPlaceAvators = new Drawable[a.length()];
//            for (int i = 0; i < mPlaceAvators.length; i++) {
//                mPlaceAvators[i] = a.getDrawable(i);
//            }
//            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.name.setText(String.valueOf(todoListTasks.get(position).getTitle()));
            holder.description.setText(String.valueOf(
                    todoListTasks.get(position).getDueDate() + "  "
                    + todoListTasks.get(position).getCurrentState()
            ));
//            holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
//            holder.name.setText(mPlaces[position % mPlaces.length]);
//            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);

            holder.name.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    group_id = todoListTasks.get(holder.getAdapterPosition()).getId();
                    Log.e(TAG, "Group_id Send::" + group_id);
                    Intent intent = new Intent(view.getContext(), DetailsTaskActivity.class);
                    intent.putExtra("group_id", group_id);
                    startActivity(intent);

                    //Here goes your desired onClick behaviour. Like:
                    Toast.makeText(view.getContext(), "You have clicked " + view.getId(), Toast.LENGTH_SHORT).show(); //you can add data to the tag of your cardview in onBind... and retrieve it here with with.getTag().toString()..
//                    //You can change the fragment, something like this, not tested, please correct for your desired output:
//                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                    MyFragment myFragment = new MyFragment();
//                    //Create a bundle to pass data, add data, set the bundle to your fragment and:
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.yourContainerId, myFragment).addToBackStack(null).commit();
                }
            });

        }

        @Override
        public int getItemCount() {
            return todoListTasks.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}
