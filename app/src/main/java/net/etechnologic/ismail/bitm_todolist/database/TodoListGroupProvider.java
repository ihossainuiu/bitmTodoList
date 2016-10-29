package net.etechnologic.ismail.bitm_todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.etechnologic.ismail.bitm_todolist.todoList.TodoListGroup;

import java.util.ArrayList;

import static net.etechnologic.ismail.bitm_todolist.database.TodoListContractor.TodoList_group_Columns.GROUP_NAME;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class TodoListGroupProvider {

    private TodoListDatabase mOpenHelper;
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;

    public TodoListGroupProvider(Context context){
        mContext = context;
        mOpenHelper = new TodoListDatabase(mContext);
    }

    public long addTodoListGroup(TodoListGroup todoListGroup){
        sqLiteDatabase = mOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListContractor.TodoList_group_Columns.GROUP_NAME, todoListGroup.getName());
        long data = sqLiteDatabase.insert(TodoListContractor.Tables.TODO_LIST_GROUP, null, contentValues);
        sqLiteDatabase.close();
        return data;
    }

    public ArrayList<TodoListGroup> getAllTodoListGroup(){

        sqLiteDatabase = mOpenHelper.getReadableDatabase();
        ArrayList<TodoListGroup> todoListGroups = new ArrayList<>();
        String todoListGroupQuery = "select * from " + TodoListContractor.Tables.TODO_LIST_GROUP;
        Cursor cursor = sqLiteDatabase.rawQuery(todoListGroupQuery, null);
        TodoListGroup todoListGroup;
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(TodoListContractor.TodoList_group_Columns.GROUP_ID));
                String name = cursor.getString(cursor.getColumnIndex(GROUP_NAME));
                todoListGroup = new TodoListGroup(id, name);
                todoListGroups.add(todoListGroup);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return todoListGroups;
    }

    public long updateTodoListGroup(TodoListGroup todoListGroup){
        sqLiteDatabase = mOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListContractor.TodoList_group_Columns.GROUP_ID, todoListGroup.getId());
        contentValues.put(TodoListContractor.TodoList_group_Columns.GROUP_NAME, todoListGroup.getName());
        long result = sqLiteDatabase.update(TodoListContractor.Tables.TODO_LIST_GROUP,contentValues,
                TodoListContractor.TodoList_group_Columns.GROUP_ID +"=?",
                new String[]{String.valueOf(todoListGroup.getId())});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteTodoListGroup(long id){
        sqLiteDatabase = mOpenHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(TodoListContractor.Tables.TODO_LIST_GROUP,
                TodoListContractor.TodoList_group_Columns.GROUP_ID +"=?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }


}
