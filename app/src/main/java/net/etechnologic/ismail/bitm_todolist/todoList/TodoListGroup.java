package net.etechnologic.ismail.bitm_todolist.todoList;

import java.util.ArrayList;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class TodoListGroup {

    private long id;
    private String name;
    private ArrayList<TodoListTask> todoListTasks;

    public TodoListGroup(String name) {
        this.name = name;
    }

    public TodoListGroup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TodoListTask> getTodoListTasks() {
        return todoListTasks;
    }

    public void setTodoListTasks(ArrayList<TodoListTask> todoListTasks) {
        this.todoListTasks = todoListTasks;
    }
}
