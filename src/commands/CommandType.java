package commands;

public enum CommandType {
    YELLOW_ELF("yellow elf"),
    BLACK_ELF("black elf"),
    PINK_ELF("pink elf");

    private final String text;

    public String getText() {
        return text;
    }

    CommandType(final String text) {
        this.text = text;
    }

    /** extracts a command type from a string */
    public static CommandType fromString(final String text) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.text.equalsIgnoreCase(text)) {
                return  commandType;
            }
        }

        return null;
    }
}
