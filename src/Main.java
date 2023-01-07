import java.util.*;

public class Main {
    private static Diary diary = new Diary();

    public static void main(String[] args) throws NoTaskException {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner); // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            getATask(scanner);// todo: обрабатываем пункт меню 3
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу \n" +
                        "2. Удалить задачу \n" +
                        "3. Получить задачу на указанный день \n" +
                        "0. Выход "
        );
    }

    private static void inputTask(Scanner scanner) {

        Calendar calendar = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();
        int MAX_YEAR = 2025;

        System.out.print("Введите название задачи: ");
        String title = scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String text = scanner.nextLine();

        System.out.print("Введите тип задачи (личная / рабочая): ");
        String typeOfTask = scanner.nextLine();
        TypeOfTask valueTypeOfTask = TypeOfTask.getTypeOfTaskByScanner(typeOfTask);

        System.out.print("На какой год хотите установить задачу: ");
        int year = scanner.nextInt();
        if (year >= cal.get(Calendar.YEAR) & year <= MAX_YEAR) {
            calendar.set(Calendar.YEAR, year);
        } else if (year < cal.get(Calendar.YEAR)) {
            System.out.println("Введенный год " + year + " меньше текущего. Год должен быть ,больше " + cal.get(Calendar.YEAR) + " и меньше "
                    + MAX_YEAR + ", по умолчанию установлен текущий год" + cal.get(Calendar.YEAR));
            calendar.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        } else {
            System.out.println("Введенный год " + year + " больше максимального. Год должен быть больше " + cal.get(Calendar.YEAR) + " и меньше "
                    + MAX_YEAR + ", по умолчанию установлен максимальный год " + MAX_YEAR);
            calendar.set(Calendar.YEAR, MAX_YEAR);
        }

        System.out.print("На какой месяц хотите установить задачу (введите цифровое значение от 1 до 12): ");
        int month = scanner.nextInt() - 1;
        if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                month >= cal.get(Calendar.MONTH) &
                month <= cal.getActualMaximum(Calendar.MONTH)) {
            System.out.println("Введен текущий год и установлен текущий месяц. Все корректно.");
            calendar.set(Calendar.MONTH, month);
        } else if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                month < cal.get(Calendar.MONTH)) {
            System.out.println("Введенный месяц " + month + " меньше текущего. Месяц должен быть в диапазоне " + cal.get(Calendar.MONTH) +
                    " и меньше " + cal.getActualMaximum(Calendar.MONTH) + ", по умолчанию установлен текущий месяц " + cal.get(Calendar.MONTH));
            calendar.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                month >= cal.getActualMinimum(Calendar.MONTH) &
                month <= cal.getActualMaximum(Calendar.MONTH)) {
            System.out.println("При вводе ошибок не обнаружено");
            calendar.set(Calendar.MONTH, month);
        } else if (month > cal.getActualMaximum(Calendar.MONTH)) {
            System.out.println("Введенный месяц " + (month - 1) + " не существует. Месяц должен быть от 1 (январь) до 12 (декабрь), " +
                    "по умолчанию установлен максимальный - 12 (декабрь)");
            calendar.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        }

        System.out.print("На какой день хотите установить задачу: ");
        int day = scanner.nextInt();
        if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &
                day <= cal.getActualMaximum(Calendar.DAY_OF_MONTH) &
                day >= cal.get(Calendar.DAY_OF_MONTH)) {
            System.out.println("Установлен текущий месяц и установлена валидная дата " + day);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            System.out.println(calendar);

        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                day <= cal.getActualMaximum(Calendar.DAY_OF_MONTH) &
                day >= cal.getActualMinimum(Calendar.DAY_OF_MONTH)) {
            System.out.println("Все в порядке. Введена корректная дата " + day);
            calendar.set(Calendar.DAY_OF_MONTH, day);
        } else if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &
                day >= cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            System.out.println("Вы вводите дату текущего месяца, которая прошла - " + day + ". Дата должна быть больше " + cal.get(Calendar.DAY_OF_MONTH) + " и меньше "
                    + cal.getActualMaximum(Calendar.DAY_OF_MONTH) + ", по умолчанию будет установлена текущая дата" + cal.get(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                day <= cal.getMinimum(Calendar.DAY_OF_MONTH)) {
            System.out.println("Вы вводите дату меньше возможной - " + day + ". Дата должна быть больше " + cal.getMinimum(Calendar.DAY_OF_MONTH) + " и меньше "
                    + cal.getMaximum(Calendar.DAY_OF_MONTH) + ", по умолчанию будет установлено первое число месяца");
            calendar.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.HOUR_OF_DAY));
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                day >= cal.getMaximum(Calendar.DAY_OF_MONTH)) {
            System.out.println("Вы вводите дату больше возможной - " + day + ". Дата должна быть больше " + cal.getMinimum(Calendar.DAY_OF_MONTH) + " и меньше "
                    + cal.getMaximum(Calendar.DAY_OF_MONTH) + ", по умолчанию будет установлено последнее число месяца");
            calendar.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.HOUR_OF_DAY));
        }

        System.out.print("Введите час установки задачи: ");
        int hourOfDay = scanner.nextInt();
        if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                hourOfDay > cal.getActualMinimum(Calendar.HOUR_OF_DAY) &
                hourOfDay <= cal.getActualMaximum(Calendar.HOUR_OF_DAY)) {
            System.out.println("Час установлен корректно " + hourOfDay);
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                hourOfDay >= cal.getMinimum(Calendar.HOUR_OF_DAY) &
                hourOfDay <= cal.getMaximum(Calendar.HOUR_OF_DAY)) {
            System.out.println("Час установлен корректно " + hourOfDay);
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        } else if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                hourOfDay <= cal.getActualMinimum(Calendar.HOUR_OF_DAY)) {
            System.out.println("Вы вводите час текущего дня, который прошел - " + hourOfDay + ". Час должен быть больше " + cal.get(Calendar.HOUR_OF_DAY) + " и меньше "
                    + cal.getActualMaximum(Calendar.HOUR_OF_DAY) + ", по умолчанию будет установлена текущий час" + cal.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                hourOfDay <= cal.getMinimum(Calendar.HOUR_OF_DAY)) {
            System.out.println("Вы вводите час меньше возможного - " + hourOfDay + ". Час должен быть больше " + cal.getMinimum(Calendar.HOUR_OF_DAY) + " и меньше "
                    + cal.getMaximum(Calendar.HOUR_OF_DAY) + ", по умолчанию будет установлено " + cal.getMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                hourOfDay >= cal.getMaximum(Calendar.HOUR_OF_DAY)) {
            System.out.println("Вы вводите час больше возможного - " + hourOfDay + ". Час должен быть больше " + cal.getMinimum(Calendar.HOUR_OF_DAY) + " и меньше "
                    + cal.getMaximum(Calendar.HOUR_OF_DAY) + ", по умолчанию будет установлено " + cal.getMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        }

        System.out.print("Введите минуты установки задачи: ");
        int minute = scanner.nextInt();
        if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                minute > cal.getActualMinimum(Calendar.MINUTE) &
                minute <= cal.getActualMaximum(Calendar.MINUTE)) {
            System.out.println("Минуты установлены корректно " + minute);
            calendar.set(Calendar.MINUTE, minute);
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                minute >= cal.getMinimum(Calendar.MINUTE) &
                minute <= cal.getMaximum(Calendar.MINUTE)) {
            System.out.println("Минуты установлены корректно " + minute);
            calendar.set(Calendar.MINUTE, minute);
        } else if (calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &
                minute <= cal.getActualMinimum(Calendar.MINUTE)) {
            System.out.println("Вы вводите минуты текущего часа, которые прошли - " + minute + ". Минуты должны быть больше " + cal.get(Calendar.MINUTE) + " и меньше "
                    + cal.getActualMaximum(Calendar.MINUTE) + ", по умолчанию будет установлено + 10 минут от текущего времени" + cal.get(Calendar.MINUTE) + 10);
            calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                minute <= cal.getMinimum(Calendar.MINUTE)) {
            System.out.println("Вы вводите минуты меньше возможного - " + minute + ". Минуты должны быть больше " + cal.getMinimum(Calendar.MINUTE) + " и меньше "
                    + cal.getMaximum(Calendar.MINUTE) + ", по умолчанию будет установлено " + cal.getMinimum(Calendar.MINUTE));
            calendar.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        } else if (calendar.get(Calendar.YEAR) > cal.get(Calendar.YEAR) &
                minute >= cal.getMaximum(Calendar.MINUTE)) {
            System.out.println("Вы вводите минуты больше возможного - " + minute + ". Минуты должны быть больше " + cal.getMinimum(Calendar.MINUTE) + " и меньше "
                    + cal.getMaximum(Calendar.MINUTE) + ", по умолчанию будет установлено " + cal.getMaximum(Calendar.MINUTE));
            calendar.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        }

        System.out.print("Введите повторяемость задачи (однократная / ежедневная / еженедельная / ежемесячная / ежегодная): ");
        String repetition = scanner.next();
        Repetition valueRepetition = Repetition.getRepetitionByScanner(repetition);

//        if (valueRepetition == Repetition.ONECE) {
//            Task task = new TaskExactDay(title, text, valueTypeOfTask, calendar, valueRepetition);
//        } else if (valueRepetition == Repetition.EVERY_DAY) {
//            Task task = new TaskDaily(title, text, valueTypeOfTask, calendar, valueRepetition);
//        } else if (valueRepetition == Repetition.EVERY_WEEK) {
//            Task task = new TaskWeekly(title, text, valueTypeOfTask, calendar, valueRepetition);
//        } else if (valueRepetition == Repetition.EVERY_MONTH) {
//            Task task = new TaskMonthly(title, text, valueTypeOfTask, calendar, valueRepetition);
//        } else if (valueRepetition == Repetition.EVERY_YEAR) {
//            Task task = new TaskYearly(title, text, valueTypeOfTask, calendar, valueRepetition);
//        }
        TaskYearly task = new TaskYearly(title, text, valueTypeOfTask, calendar, valueRepetition);

        System.out.println();
        System.out.println("Задача " + task.getId() + " создана");
        System.out.println(task);
        diary.addTaskToSet(task);
        System.out.println();
    }

    public static void deleteTask(Scanner scanner) throws NoTaskException {
        System.out.println("Имеющиеся задачи: ");
        for (Task task : diary.getSetOfAllTasks()) {
            System.out.println("Задача " + task);
        }
        System.out.println("Какую задачу хотите удалить? Введите номер задачи: ");
        int id = scanner.nextInt();
        try {
            diary.deleteTaskOfSet(id);
        } catch (NoTaskException e) {
            System.out.println("Вы ввели некорректный № задачи " + id + ". Введите один из следующих " );
            for (Task task : diary.getSetOfAllTasks()) {
                System.out.print(task.getId() + ", ");
                System.out.println();
            }
        }
    }

    public static void getATask(Scanner scanner) {
        System.out.println("На какой день вы хотите получить список задач?");
        System.out.print("Введите год задачи: ");
        int year = scanner.nextInt();
        System.out.print("Введите месяц задачи: ");
        int month = scanner.nextInt() - 1;
        System.out.print("Введите день задачу: ");
        int day = scanner.nextInt();
        Calendar calendar = new GregorianCalendar(year, month, day);

        diary.getSetOfTasksExactDay(calendar);
    }
}