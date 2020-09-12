package com.eny.taskComposite.task.parser;

import com.eny.taskComposite.task.composite.CompositeBasis;
import com.eny.taskComposite.task.composite.CompositeComponent;
import com.eny.taskComposite.task.composite.type.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends BasicParser{
    private static final ParagraphParser instance = new ParagraphParser();
    private static final String SENTENCE_SEPARATOR = "[^.!?\u2026]+[.!?\u2026]";

    private ParagraphParser() {
        super(SentenceParser.getInstance());
    }

    public static ParagraphParser getInstance() {
        return instance;
    }

    @Override
    public CompositeBasis parse(String data) {
        CompositeComponent component = new CompositeComponent(CompositeType.PARAGRAPH);
        Pattern pattern = Pattern.compile(SENTENCE_SEPARATOR);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            component.addChild(nextParseLevel.parse(matcher.group().trim()));
        }

        return component;
    }
}
