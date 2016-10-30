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

package net.etechnologic.ismail.bitm_todolist.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.etechnologic.ismail.bitm_todolist.R;
import net.etechnologic.ismail.bitm_todolist.SingleGroupActivity;
import net.etechnologic.ismail.bitm_todolist.database.TodoListGroupProvider;
import net.etechnologic.ismail.bitm_todolist.todoList.TodoListGroup;

import java.util.ArrayList;

import static net.etechnologic.ismail.bitm_todolist.MainActivity.TAG;

/**
 * Provides UI for the view with Tiles.
 */
public class TodoListGroupFragment extends Fragment {

    TodoListGroupProvider todoListGroupProvider;
    ArrayList<TodoListGroup> todoListGroups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        todoListGroupProvider = new TodoListGroupProvider(getContext());

        todoListGroups = todoListGroupProvider.getAllTodoListGroup();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), todoListGroups);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "RecyclerView Holder",Toast.LENGTH_LONG).show();
            }
        });

        return recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView picture;
        public TextView name;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_tile, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.tile_picture);
            name = (TextView) itemView.findViewById(R.id.tile_title);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "RecyclerView Holder",Toast.LENGTH_LONG).show();
//            String group = (String) parent.getItemAtPosition(position);
//            long group_id = Long.parseLong(group);
//            Log.e(TAG, "Group_id Send::" + group_id);
            Intent intent = new Intent(view.getContext(), SingleGroupActivity.class);
//            intent.putExtra("group_id", group_id);
            startActivity(intent);
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;

//        private final String[] mPlaces;
//        private final Drawable[] mPlacePictures;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<TodoListGroup> todoListGroups;
        Context context;

        public ContentAdapter(Context context, ArrayList<TodoListGroup> todoListGroups) {

            this.todoListGroups = todoListGroups;
            this.context = context;

//            Resources resources = context.getResources();
//            mPlaces = resources.getStringArray(R.array.places);
//            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
//            mPlacePictures = new Drawable[a.length()];
//            for (int i = 0; i < mPlacePictures.length; i++) {
//                mPlacePictures[i] = a.getDrawable(i);
//            }
//            a.recycle();

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
            holder.name.setText(String.valueOf(todoListGroups.get(position).getId()));
//            holder.name.setText(mPlaces[position % mPlaces.length]);

            holder.name.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

//                    String group = (String) parent.getItemAtPosition(position);
                    long group_id = Long.parseLong(String.valueOf(holder.name.getText()));
                    Log.e(TAG, "Group_id Send::" + group_id);
                    Intent intent = new Intent(getContext(), SingleGroupActivity.class);
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
            return todoListGroups.size();
        }


    }
}

