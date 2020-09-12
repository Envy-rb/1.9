package com.eny.taskComposite.task.parser;

import com.eny.taskComposite.task.composite.CompositeComponent;
import com.eny.taskComposite.task.composite.type.CompositeType;

public class TextParser extends BasicParser{
    private static final TextParser instance = new TextParser();
    private static final String PARAGRAPH_SEPARATOR = "\\p{Blank}{4}|\\t";
    private static final String EMPTY_SYMBOL = "";

    private TextParser() {
        super(ParagraphParser.getInstance());
    }

    public static TextParser getInstance() {
        return instance;
    }

    @Override
    public CompositeComponent parse(String data) {
        CompositeComponent component = new CompositeComponent(CompositeType.TEXT);
        data = data.replaceFirst(PARAGRAPH_SEPARATOR, EMPTY_SYMBOL);
        String[] paragraphs = data.split(PARAGRAPH_SEPARATOR);

        for (String paragraph : paragraphs) {
            component.addChild(nextParseLevel.parse(paragraph));
        }

        return component;
    }
}
