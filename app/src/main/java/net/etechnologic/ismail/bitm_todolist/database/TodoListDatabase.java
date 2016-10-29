package net.etechnologic.ismail.bitm_todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class TodoListDatabase extends SQLiteOpenHelper {

    private static final String TAG = TodoListDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "todoList.db";
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;

    private String db_table_todoList_group_query = "CREATE TABLE " + TodoListContractor.Tables.TODO_LIST_GROUP + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TodoListContractor.TodoList_group_Columns.GROUP_NAME + " TEXT NOT NULL)";

    private String db_table_todoList_task_query = "CREATE TABLE " + TodoListContractor.Tables.TODO_LIST_TASK + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TodoListContractor.TodoList_task_Columns.GROUP_ID + " TEXT,"
            + TodoListContractor.TodoList_task_Columns.TASK_TITLE + " TEXT,"
            + TodoListContractor.TodoList_task_Columns.TASK_DUE_DATE + " TEXT,"
            + TodoListContractor.TodoList_task_Columns.TASK_NOTE + " TEXT,"
            + TodoListContractor.TodoList_task_Columns.TASK_CURRENT_STATE + " TEXT)";

    public TodoListDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_table_todoList_group_query);
        db.execSQL(db_table_todoList_task_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoListContractor.Tables.TODO_LIST_GROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TodoListContractor.Tables.TODO_LIST_TASK);
        onCreate(db);
    }
}
