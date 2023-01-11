import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int counter = 1;
    private final int id;
    private final String title; // заголовок
    private final String text; // текст задачи
    private final TypeOfTask typeOfTask; // тип задачи - личная, рабочая
    private final LocalDateTime time; // дата и время
    private final Repetition repetition; // повторяесть задачи

    public Task(String title, String text, TypeOfTask typeOfTask, LocalDateTime time, Repetition repetition) {
        id = counter++;
        this.title = title;
        this.text = text;
        this.typeOfTask = typeOfTask;
        this.time = time;
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

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public LocalDateTime getLocalDateTime() {
        return time;
    }

    public Repetition getRepetition() {
        return repetition;
    }

    private String getTimeNextTask() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        switch (repetition) {
            case ONECE:
                return " ";
            case EVERY_DAY:
                return ", следующая задача: " + time.plusDays(1).format(date);
            case EVERY_WEEK:
                return ", следующая задача: " + time.plusWeeks(1).format(date);
            case EVERY_MONTH:
                return ", следующая задача: " + time.plusMonths(1).format(date);
            case EVERY_YEAR:
                return ", следующая задача: " + time.plusYears(1).format(date);
            default:
                break;
        }
        return null;
    }

    @Override
    public String toString() {
        return "ЗАДАЧА № " + id + " {" +
                "название: " + title +
                ", описание: " + text +
                ", тип задачи: " + typeOfTask.getTypeOfTask() +
                ", дата и время установки задачи: " +  time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                getTimeNextTask() +
                ", повторяемость: " + repetition.getRepetition() +
                '}';
    }
}
