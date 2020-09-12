package com.eny.taskComposite.task.interpreter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class ExpressionInterpreterTest {

    @DataProvider(name = "expressions")
    public Object[][] createExpressions() {

        return new Object[][]{
                {"2+2", "4"},
                {"2-2", "0"},
                {"2*2", "4"},
                {"2/2", "1"}
        };
    }

    @Test
    void processExpressionTest(String expression, String expected) {
        String actual = ExpressionInterpreter.processExpression(expression);
        assertEquals(actual, expected);
    }
}