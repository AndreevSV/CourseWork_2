import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Diary {
    private final Map<Integer, Task> setOfTasks = new HashMap<>();

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
        setOfTasks.put(task.getId(), task);
        System.out.println();
    }

    public void deleteTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Имеющиеся задачи: ");
        for (Integer task : setOfTasks.keySet()) {
            System.out.println("Порядковый номер " + task + " : " + setOfTasks.get(task));
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
                if (setOfTasks.containsKey(id)) {
                    setOfTasks.remove(id);
                    break;
                } else
                    System.out.print("Задачи с таким номером не существует! Повторите ввод: ");
            }
        }

    }

    public void getATask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("На какой день вы хотите получить список задач? Введите дату в формате dd.MM.yyyy");
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
        for (Integer key : setOfTasks.keySet()) {
            if (Period.between(localDate, setOfTasks.get(key).getLocalDateTime().toLocalDate()).getDays() == 0) {
                System.out.println(key + " " + setOfTasks.get(key));
            }
        }
    }
}
