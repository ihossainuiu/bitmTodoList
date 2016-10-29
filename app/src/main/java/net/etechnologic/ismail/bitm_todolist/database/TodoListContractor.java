package net.etechnologic.ismail.bitm_todolist.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class TodoListContractor {

    interface Tables {
        String TODO_LIST_GROUP = "todoList_group";
        String TODO_LIST_TASK = "todoList_task";
    }

    interface TodoList_group_Columns {
        String GROUP_ID = "_id";
        String GROUP_NAME = "group_name";
    }

    interface TodoList_task_Columns {
        String TASK_ID = "_id";
        String GROUP_ID = "group_id";
        String TASK_TITLE = "task_title";
        String TASK_DUE_DATE = "task_date";
        String TASK_NOTE = "task_note";
        String TASK_CURRENT_STATE = "task_current_state";
    }

    public static final String CONTENT_AUTHORITY = "net.etechnologic.ismail.bitm_todolist.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_TODOLIST = Tables.TODO_LIST_GROUP;
    public static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString() + "/" + PATH_TODOLIST);

    public static final String[] TOP_LEVEL_PATHS = {
            PATH_TODOLIST
    };

    public static class TodoList implements TodoList_task_Columns, TodoList_group_Columns, BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_TODOLIST).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + Tables.TODO_LIST_GROUP;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + Tables.TODO_LIST_GROUP;

        public static Uri buildFriendUri(String friendId) {
            return CONTENT_URI.buildUpon().appendEncodedPath(friendId).build();
        }

        public static String getTodoListId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

}
