public enum Repetition {
    ONECE("однократная"),
    EVERY_DAY("ежедневная"),
    EVERY_WEEK("еженедельная"),
    EVERY_MONTH("ежемесячная"),
    EVERY_YEAR("ежегодная");
    private final String repetition;

    Repetition(String repetition) {
        this.repetition = repetition;
    }

    public String getRepetition() {
        return repetition;
    }

    public static Repetition getRepetitionByScanner(String repetition) {
        for (Repetition value : values()) {
            if (value.getRepetition().equalsIgnoreCase(repetition)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Вы ввели " + repetition + " и ошиблись при вводе значения повторямости задачи. " +
                " Правильное значение: однократная / ежедневная / еженедельная / ежемесячная / ежегодная ");
    }
}
