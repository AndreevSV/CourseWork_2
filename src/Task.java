import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Task {
    private static int counter = 1;
    private final int id;
    private final String title; // заголовок
    private final String text; // текст задачи
    private final TypeOfTask typeOfTask; // тип задачи - личная, рабочая
    private final Calendar calendar; // дата и время
    private final Repetition repetition; // повторяесть задачи

    public Task(String title, String text, TypeOfTask typeOfTask, Calendar calendar, Repetition repetition) {
        this.id = counter++;
        this.title = title;
        this.text = text;
        this.typeOfTask = typeOfTask;
        this.calendar = calendar;
        this.repetition = repetition;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

//    public TypeOfTask getTypeOfTask() {
//        return typeOfTask;
//    }
//
//    public Calendar getCalendar() {
//        return calendar;
//    }
//
//    public Repetition getRepetition() {
//        return repetition;
//    }

    public abstract boolean isTaskOrNot(Calendar calendar);

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");

        return "ЗАДАЧА № " + getId() + " {" +
                "название: " + getTitle() +
                ", описание: " + getText() +
                ", тип задачи: " + typeOfTask.getTypeOfTask() +
                ", дата и время установки задачи: " + dateFormat.format(calendar.getTime()) +
                ", повторяемость: " + repetition.getRepetition() +
                '}';
    }
}
