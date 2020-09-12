package com.eny.taskComposite.task.parser;

import com.eny.taskComposite.task.composite.CompositeBasis;

public abstract class BasicParser {
    BasicParser nextParseLevel;

    public BasicParser(BasicParser nextParseLevel) {
        this.nextParseLevel = nextParseLevel;
    }

    public abstract CompositeBasis parse(String data);

}
