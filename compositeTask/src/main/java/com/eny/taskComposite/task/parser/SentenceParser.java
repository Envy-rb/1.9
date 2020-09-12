package com.eny.taskComposite.task.parser;

import com.eny.taskComposite.task.composite.CompositeBasis;
import com.eny.taskComposite.task.composite.CompositeComponent;
import com.eny.taskComposite.task.composite.type.CompositeType;
import com.eny.taskComposite.task.interpreter.ExpressionInterpreter;

import java.util.regex.Pattern;

public class SentenceParser extends BasicParser{
    private static final SentenceParser instance = new SentenceParser();
    private static final String LEXEME_SEPARATOR = "\\s";
    private static final String EXPRESSION = "\\p{N}+";

    private SentenceParser() {
        super(LexemeParser.getInstance());
    }

    public static SentenceParser getInstance() {
        return instance;
    }

    @Override
    public CompositeBasis parse(String data) {
        CompositeComponent component = new CompositeComponent(CompositeType.SENTENCE);
        Pattern pattern = Pattern.compile(EXPRESSION);
        String[] separatedData = data.split(LEXEME_SEPARATOR);

        for (String lexeme : separatedData) {
            if (pattern.matcher(lexeme).find()) {
                lexeme = ExpressionInterpreter.processExpression(lexeme);
            }
            component.addChild(nextParseLevel.parse(lexeme));
        }

        return component;
    }
}
