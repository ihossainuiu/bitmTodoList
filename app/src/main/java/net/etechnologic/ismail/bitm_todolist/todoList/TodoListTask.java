package net.etechnologic.ismail.bitm_todolist.todoList;

/**
 * Created by ismailhossain on 10/28/16.
 */

public class TodoListTask {

    private long id;
    private long group_id;
    private String title;
    private String dueDate;
    private String note;
    private String currentState;

    public TodoListTask(long group_id, String title) {
        this.group_id = group_id;
        this.title = title;
    }

    public TodoListTask(long group_id, String title, String dueDate, String note, String currentState) {
        this.group_id = group_id;
        this.title = title;
        this.dueDate = dueDate;
        this.note = note;
        this.currentState = currentState;
    }

    public TodoListTask(long id, long group_id, String title, String dueDate, String note, String currentState) {
        this.id = id;
        this.group_id = group_id;
        this.title = title;
        this.dueDate = dueDate;
        this.note = note;
        this.currentState = currentState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
