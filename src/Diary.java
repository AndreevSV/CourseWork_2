import java.util.*;

public class Diary {
    private final Map<Integer, Task> setOfTasks = new HashMap<>();

    public void addTaskToSet(Task task) {
        this.setOfTasks.put(task.getId(), task);
    }

    public Collection<Task> getSetOfAllTasks() {
        return this.setOfTasks.values();
    }

    public void getSetOfTasksExactDay(Calendar calendar) {
        Collection<Task> setOfTasksExactDay = new ArrayList<>();
        for (Task task: setOfTasks.values()) {
            if (task.isTaskOrNot(calendar)) {
                setOfTasksExactDay.add(task);
            }
        }
        System.out.println(setOfTasksExactDay);
    }

    public void deleteTaskOfSet(int id) throws NoTaskException {
        if (this.setOfTasks.containsKey(id)) {
            this.setOfTasks.remove(id);
        } else {
            throw new NoTaskException();
        }
    }

}
