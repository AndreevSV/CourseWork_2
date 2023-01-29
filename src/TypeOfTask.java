public enum TypeOfTask {
    PRIVATE("личная"),
    WORKING("рабочая");
    private final String typeOfTask;

    TypeOfTask(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    public static TypeOfTask getTypeOfTaskByScanner(String typeOfTask) {
        for (TypeOfTask value : values()) {
            if (value.getTypeOfTask().equalsIgnoreCase(typeOfTask)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Вы ввели " + typeOfTask + " и ошиблись при вводе типа задачи. Правильный тип: личная / рабочая");
    }
}
