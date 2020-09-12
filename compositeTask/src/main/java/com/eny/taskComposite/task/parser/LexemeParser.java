package com.eny.taskComposite.task.parser;

import com.eny.taskComposite.task.composite.CompositeAtomic;
import com.eny.taskComposite.task.composite.CompositeBasis;
import com.eny.taskComposite.task.composite.CompositeComponent;
import com.eny.taskComposite.task.composite.type.AtomicType;
import com.eny.taskComposite.task.composite.type.CompositeType;

public class LexemeParser extends BasicParser{
    private static final LexemeParser instance = new LexemeParser();
    private static final String SYMBOL_SEPARATOR = "";
    private static final String PUNCTUATION_REGEX = "[\\p{Punct}]";

    private LexemeParser() {
        super(null);
    }

    public static LexemeParser getInstance() {
        return instance;
    }

    @Override
    public CompositeBasis parse(String data) {
        CompositeComponent component = new CompositeComponent(CompositeType.LEXEME);
        String[] separatedData = data.split(SYMBOL_SEPARATOR);

        for (String symbol : separatedData) {
            if (symbol.matches(PUNCTUATION_REGEX)) {
                component.addChild(new CompositeAtomic(AtomicType.PUNCTUATION_CHAR, symbol));
            }
            else {
                component.addChild(new CompositeAtomic(AtomicType.USUAL_CHAR, symbol));
            }
        }

        return component;
    }
}
