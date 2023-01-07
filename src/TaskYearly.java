import java.util.Calendar;

public class TaskYearly extends Task {
    public TaskYearly(String title, String text, TypeOfTask typeOfTask, Calendar calendar, Repetition repetition) {
        super(title, text, typeOfTask, calendar, repetition);
    }

    @Override
    public boolean isTaskOrNot(Calendar calendar) {
        return false;
    }
}
