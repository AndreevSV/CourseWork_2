import java.util.Calendar;

public class TaskMonthly extends Task {
    public TaskMonthly(String title, String text, TypeOfTask typeOfTask, Calendar calendar, Repetition repetition) {
        super(title, text, typeOfTask, calendar, repetition);
    }

    @Override
    public boolean isTaskOrNot(Calendar calendar) {
        return false;
    }
}
