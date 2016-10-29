package net.etechnologic.ismail.bitm_todolist.todoList;

import java.util.ArrayList;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class TodoList {

    private ArrayList<TodoListGroup> todoListGroups;

    public ArrayList<TodoListGroup> getTodoListGroups() {
        return todoListGroups;
    }

    public void setTodoListGroups(ArrayList<TodoListGroup> todoListGroups) {
        this.todoListGroups = todoListGroups;
    }
}
