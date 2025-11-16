package q1;

public class Task {
    int priority;
    String taskId;
    String description;

    public Task(String taskId, String description, int priority) {
        this.taskId = taskId;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "(" + taskId + ", " + description + ", P:" + priority + ")";
    }
}