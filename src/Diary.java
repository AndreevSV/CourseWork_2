import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Diary {
    private final Map<Integer, Task> mapOfTasks = new HashMap<>();

    public void addTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название задачи: ");
        String title;
        while (true) {
            title = scanner.nextLine();
            if (!title.isBlank()) break;
            else System.out.print("Вы не ввели название задачи. Пожалуйста, введите название: ");
        }

        System.out.print("Введите описание задачи: ");
        String text;
        while (true) {
            text = scanner.nextLine();
            if (!text.isBlank()) break;
            else System.out.print("Описание задачи не введено. Повтоите ввод: ");
        }

        System.out.print("Введите тип задачи (личная / рабочая): ");
        TypeOfTask typeOfTask = null;
        while (true) {
            try {
                typeOfTask = TypeOfTask.getTypeOfTaskByScanner(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Некорректный вод типа задачи. Повтоите ввод: ");
            }
            if (typeOfTask != null)
                break;
        }

        System.out.print("Введите дату и время задачи в формате dd.MM.yyyy HH.mm: ");
        LocalDateTime localDateTime = null;
        while (true) {
            try {
                localDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm"));
            } catch (DateTimeParseException e) {
                System.out.print("Некорректный вод даты и времени. Повтоите ввод в формате dd.MM.yyyy HH.mm:: ");
            }
            if (localDateTime != null)
                break;
        }

        System.out.print("Введите повторяемость задачи (однократная / ежедневная / еженедельная / ежемесячная / ежегодная): ");
        Repetition repetition = null;
        while (true) {
            try {
                repetition = Repetition.getRepetitionByScanner(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Некорректный ввод повторяемости задачи. Повтоите ввод: ");
            }
            if (repetition != null)
                break;
        }

        Task task = new Task(title, text, typeOfTask, localDateTime, repetition);

        System.out.println();
        System.out.println("Задача " + task.getId() + " создана");
        System.out.println(task);
        mapOfTasks.put(task.getId(), task);
        System.out.println();
    }

    public void deleteTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Имеющиеся задачи: ");
        for (Integer task : mapOfTasks.keySet()) {
            System.out.println("Порядковый номер " + task + " : " + mapOfTasks.get(task));
        }
        System.out.print("Какую задачу хотите удалить? Введите номер задачи: ");
        Integer id = null;
        while (true) {
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage() + "вы ввели не цифру ");
            }
            if (id != null) {
                if (mapOfTasks.containsKey(id)) {
                    mapOfTasks.remove(id);
                    break;
                } else
                    System.out.print("Задачи с таким номером не существует! Повторите ввод: ");
            }
        }
    }

    public void getATask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("На какой день вы хотите получить список задач? Введите дату в формате dd.MM.yyyy : ");
        LocalDate localDate = null;
        while (true) {
            try {
                localDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.print("Вы ввели некорректную дату. Повторите ввод: ");
            }
            if (localDate != null) {
                break;
            }
        }
//        while (localDate == null);
        System.out.println("На " + localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " установлены следующие задачи: ");
        for (Task task : mapOfTasks.values()) {
            switch (task.getRepetition()) {
                case ONECE -> {
                    if (Period.between(localDate, task.getLocalDateTime().toLocalDate()).getDays() == 0) {
                        System.out.println(task);
                    }
                }
                case EVERY_DAY -> System.out.println(task);
                case EVERY_WEEK -> {
                    if (task.getLocalDateTime().getDayOfWeek() == localDate.getDayOfWeek()) {
                        System.out.println(task);
                    }
                }
                case EVERY_MONTH -> {
                    if (task.getLocalDateTime().getDayOfMonth() == localDate.getDayOfMonth()) {
                        System.out.println(task);
                    }
                }
                case EVERY_YEAR -> {
                    if (task.getLocalDateTime().getDayOfYear() == localDate.getDayOfYear()) {
                        System.out.println(task);
                    }
                }
            }

        }
    }
}
