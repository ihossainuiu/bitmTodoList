package net.etechnologic.ismail.bitm_todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.etechnologic.ismail.bitm_todolist.todoList.TodoListTask;

import java.util.ArrayList;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class TodoListTaskProvider {

    private TodoListDatabase mOpenHelper;
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;

    public TodoListTaskProvider(Context context){
        mContext = context;
        mOpenHelper = new TodoListDatabase(mContext);
    }

    public long addTodoListTask(TodoListTask todoListTask){
        sqLiteDatabase = mOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListContractor.TodoList_task_Columns.GROUP_ID, todoListTask.getGroup_id());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_TITLE, todoListTask.getTitle());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_DUE_DATE, todoListTask.getDueDate());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_NOTE, todoListTask.getNote());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_CURRENT_STATE, todoListTask.getCurrentState());

        long data = sqLiteDatabase.insert(TodoListContractor.Tables.TODO_LIST_TASK, null, contentValues);
        sqLiteDatabase.close();
        return data;
    }

    public ArrayList<TodoListTask> getAllTodoListTaskGroupWise(long group_id){

        sqLiteDatabase = mOpenHelper.getReadableDatabase();

        String todoListTaskGroupQuery = "select * from " + TodoListContractor.Tables.TODO_LIST_TASK
                + " WHERE " + TodoListContractor.TodoList_task_Columns.GROUP_ID + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(todoListTaskGroupQuery,
                new String[]{String.valueOf(group_id)});

        ArrayList<TodoListTask> todoListTasks = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                long id = cursor.getLong(
                        cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_ID));
                String title = cursor.getString(
                        cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_TITLE));
                String dueDate = cursor.getString(
                        cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_DUE_DATE));
                String note = cursor.getString(
                        cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_NOTE));
                String current_state = cursor.getString(
                        cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_CURRENT_STATE));

                TodoListTask todoListTask = new TodoListTask(id, group_id, title, dueDate, note, current_state);
                todoListTasks.add(todoListTask);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return todoListTasks;
    }

    public TodoListTask singleTodoListTaskGroupWise(long task_id){

        sqLiteDatabase = mOpenHelper.getReadableDatabase();

        String todoListTaskGroupQuery = "select * from " + TodoListContractor.Tables.TODO_LIST_TASK
                + " WHERE " + TodoListContractor.TodoList_task_Columns.TASK_ID + " = ? ";
        Cursor cursor = sqLiteDatabase.rawQuery(todoListTaskGroupQuery,
                new String[]{String.valueOf(task_id)});
        if(cursor.getCount() == 0){
            return null;
        }
        TodoListTask todoListTask;
        if(cursor.moveToFirst()){
            long group_id = cursor.getLong(
                    cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.GROUP_ID));
            String title = cursor.getString(
                    cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_TITLE));
            String dueDate = cursor.getString(
                    cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_DUE_DATE));
            String note = cursor.getString(
                    cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_NOTE));
            String current_state = cursor.getString(
                    cursor.getColumnIndex(TodoListContractor.TodoList_task_Columns.TASK_CURRENT_STATE));
            todoListTask = new TodoListTask(group_id, title, dueDate, note, current_state);

            cursor.close();
            sqLiteDatabase.close();
            return todoListTask;
        } else {
            return null;
        }
    }


    public long updateTodoListTask(TodoListTask todoListTask){
        sqLiteDatabase = mOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_ID, todoListTask.getId());
        contentValues.put(TodoListContractor.TodoList_task_Columns.GROUP_ID, todoListTask.getGroup_id());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_TITLE, todoListTask.getGroup_id());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_DUE_DATE, todoListTask.getGroup_id());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_NOTE, todoListTask.getGroup_id());
        contentValues.put(TodoListContractor.TodoList_task_Columns.TASK_CURRENT_STATE, todoListTask.getGroup_id());

        long result = sqLiteDatabase.update(TodoListContractor.Tables.TODO_LIST_TASK, contentValues,
                TodoListContractor.TodoList_group_Columns.GROUP_ID + "=?",
                new String[]{String.valueOf(todoListTask.getId())});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteTodoListGroup(long id){
        sqLiteDatabase = mOpenHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(TodoListContractor.Tables.TODO_LIST_GROUP,
                TodoListContractor.TodoList_group_Columns.GROUP_ID + "=?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }


}
