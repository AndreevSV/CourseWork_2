import java.util.*;

public class Main {
    private static final Diary diary = new Diary();

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int index = scanner.nextInt();
                    switch (index) {
                        case 1:
                            diary.addTask();
                            break;
                        case 2:
                            diary.deleteTask();
                            break;
                        case 3:
                            diary.getATask();
                            break;
                        case 0:
                            System.out.println("Вы закрыли ежедневник. До свидания.");
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println(
                "1. Добавить задачу \n" +
                        "2. Удалить задачу \n" +
                        "3. Получить задачу на указанный день \n" +
                        "0. Выход "
        );
    }
}