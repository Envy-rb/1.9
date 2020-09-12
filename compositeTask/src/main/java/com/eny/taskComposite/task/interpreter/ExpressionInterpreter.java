package com.eny.taskComposite.task.interpreter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionInterpreter {
    private static final String INTERPRETER_ENGINE = "JavaScript";

    public static String processExpression(String expression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(INTERPRETER_ENGINE);
        String result;

        try {
            result = engine.eval(expression).toString();
        }
        catch (ScriptException e) {
            result = expression;
        }

        return result;
    }
}
