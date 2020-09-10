package io.jenkins.plugins.colorize;

import hudson.MarkupText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lixinguo
 */
public class LogMarkupText {
    static final String START_TAG = "<span style=\"color:%s\">";
    static final String END_TAG = "</span>";

    static final Pattern LEVEL_PATTERN = Pattern.compile("\\[(INFO|WARNING|ERROR)]");
    static final Pattern PLUGIN_PATTERN = Pattern.compile("\\[INFO]\\s+---\\s+(.*?)\\s+\\(.*?\\)\\s+@\\s+(.*?)\\s+--");
    static final Pattern MODULE_PATTERN = Pattern.compile("\\[INFO]\\s+-+-<\\s+(.*?)\\s+>-+.*$");
    static final Pattern SUCCESS_PATTERN = Pattern.compile("\\[INFO]\\s(BUILD)\\s+SUCCESS");
    static final Pattern SUCCESS2_PATTERN = Pattern.compile("\\s+(SUCCESS)");
    static final Pattern LOGBACK_LEVEL_PATTERN = Pattern.compile("\\s+(INFO|DEBUG|ERROR|WARN)\\s+");
    static final Pattern LOGBACK_THREAD_PATTERN = Pattern.compile("\\s+(\\[.*?])\\s+");
    static final Pattern LOGBACK_PACKAGE_PATTERN = Pattern.compile("\\s+(\\w+\\.\\w+\\..*?\\s+):\\s+");

    public static void markupAll(MarkupText text) {
        processMavenLevelColor(text, LEVEL_PATTERN.matcher(text.getText()));
        processMavenPluginColor(text, PLUGIN_PATTERN.matcher(text.getText()));
        processMavenModuleColor(text, MODULE_PATTERN.matcher(text.getText()));
        processMavenSuccessColor(text, SUCCESS_PATTERN.matcher(text.getText()));
        processMavenSuccessColor(text, SUCCESS2_PATTERN.matcher(text.getText()));
        processLogbackLevelColor(text, LOGBACK_LEVEL_PATTERN.matcher(text.getText()));
        processLogbackThreadColor(text, LOGBACK_THREAD_PATTERN.matcher(text.getText()));
        processLogbackPackageColor(text, LOGBACK_PACKAGE_PATTERN.matcher(text.getText()));
    }

    private static void addMarkup(MarkupText text, int startPos, int endPos, String color) {
        text.addMarkup(startPos, endPos, startTag(color), END_TAG);
    }

    private static void processLogbackLevelColor(MarkupText text, Matcher matcher) {
        if (matcher.find()) {
            addMarkup(text, matcher.start(1), matcher.end(1), color(matcher.group(1)));
        }
    }

    private static void processLogbackThreadColor(MarkupText text, Matcher matcher) {
        if (matcher.find()) {
            addMarkup(text, matcher.start(1), matcher.end(1), LogMarkupConfiguration.get().getThreadColor());
        }
    }

    private static void processLogbackPackageColor(MarkupText text, Matcher matcher) {
        if (matcher.find()) {
            addMarkup(text, matcher.start(1), matcher.end(1), LogMarkupConfiguration.get().getPackageColor());
        }
    }

    private static void processMavenLevelColor(MarkupText text, Matcher matcher) {
        if (matcher.find()) {
            addMarkup(text, matcher.start(1), matcher.end(1), color(matcher.group(1)));
        }
    }

    private static void processMavenPluginColor(MarkupText text, Matcher matcher) {
        if (matcher.find()) {
            addMarkup(text, matcher.start(1), matcher.end(1), LogMarkupConfiguration.get().getPluginColor());
            addMarkup(text, matcher.start(2), matcher.end(2), LogMarkupConfiguration.get().getModuleColor());
        }
    }

    private static void processMavenModuleColor(MarkupText text, Matcher matcher) {
        if (matcher.find()) {
            addMarkup(text, matcher.start(1), matcher.end(1), LogMarkupConfiguration.get().getModuleColor());
        }
    }

    private static void processMavenSuccessColor(MarkupText text, Matcher matcher) {
        if (matcher.find()) {
            addMarkup(text, matcher.start(1), matcher.end(1), LogMarkupConfiguration.get().getSuccessColor());
        }
    }

    private static String startTag(String color) {
        return String.format(START_TAG, color);
    }

    private static String color(String info) {
        switch (info) {
            case "INFO":
                return LogMarkupConfiguration.get().getInfoColor();
            case "DEBUG":
                return LogMarkupConfiguration.get().getDebugColor();
            case "ERROR":
                return LogMarkupConfiguration.get().getErrorColor();
            case "WARN":
            case "WARNING":
                return LogMarkupConfiguration.get().getWarningColor();
            default:
                return "white";
        }
    }
}
