package io.jenkins.plugins.colorize;

import hudson.MarkupText;
import hudson.console.ConsoleAnnotator;
import hudson.console.ConsoleNote;
import hudson.console.LineTransformationOutputStream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author lixinguo
 */
public class LogColorizeOutputStream extends LineTransformationOutputStream.Delegating {

    protected LogColorizeOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    protected void eol(byte[] bytes, int i) throws IOException {
        LogLevelColorizeNote note = new LogLevelColorizeNote();
        note.encodeTo(out);
        out.write(bytes, 0, i);
    }


    public static class LogLevelColorizeNote extends ConsoleNote<Object> {
        @Override
        public ConsoleAnnotator annotate(Object context, MarkupText text, int charPos) {
            System.out.println("ConsoleAnnotator text: " + text.getText());
            LogMarkupText.markupAll(text);
            return null;
        }
    }

}
