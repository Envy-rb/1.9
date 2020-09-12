package com.eny.taskComposite.task.composite.type;

public enum CompositeType {
    TEXT("\n\t"), PARAGRAPH("\s"), SENTENCE("\s"), LEXEME(""), ATOMIC("");

    private String separator;

    CompositeType(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }

}
