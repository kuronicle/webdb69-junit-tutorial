package net.kuronicle.tutorial.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class AssertionMessage implements TestRule {

    private StringBuilder msg = new StringBuilder();
    
    public void append(String format, Object... params) {
        msg.append(String.format(format, params));
    }
    
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } catch (AssertionError e) {
                    msg.append(System.getProperty("line.separator")).append(e.getMessage());
                    AssertionError e2 = new AssertionError(msg.toString());
                    e2.setStackTrace(e.getStackTrace());
                    throw e2;
                }
            }
            
        };
    }

}
