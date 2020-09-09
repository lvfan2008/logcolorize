package io.jenkins.plugins.colorize;

import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author lixinguo
 */
@Extension
public class LogMarkupConfiguration extends GlobalConfiguration {

    static final Pattern PATTERN_COLOR = Pattern.compile("#[\\dABCDEF]{6}", Pattern.CASE_INSENSITIVE);

    static LogMarkupConfiguration logMarkupConfiguration = null;

    String debugColor;
    String infoColor;
    String warningColor;
    String errorColor;
    String threadColor;
    String packageColor;
    String successColor;
    String pluginColor;
    String moduleColor;

    /**
     * @return the singleton instance
     */
    public static LogMarkupConfiguration get() {
        if (logMarkupConfiguration == null) {
            logMarkupConfiguration = GlobalConfiguration.all().get(LogMarkupConfiguration.class);
        }
        return logMarkupConfiguration;
    }

    @DataBoundSetter
    public void setDebugColor(String debugColor) {
        this.debugColor = debugColor;
        save();
    }

    @DataBoundSetter
    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
        save();
    }

    @DataBoundSetter
    public void setWarningColor(String warningColor) {
        this.warningColor = warningColor;
        save();
    }

    @DataBoundSetter
    public void setErrorColor(String errorColor) {
        this.errorColor = errorColor;
        save();
    }

    @DataBoundSetter
    public void setThreadColor(String threadColor) {
        this.threadColor = threadColor;
        save();
    }

    @DataBoundSetter
    public void setPackageColor(String packageColor) {
        this.packageColor = packageColor;
        save();
    }

    @DataBoundSetter
    public void setSuccessColor(String successColor) {
        this.successColor = successColor;
        save();
    }

    @DataBoundSetter
    public void setPluginColor(String pluginColor) {
        this.pluginColor = pluginColor;
        save();
    }

    @DataBoundSetter
    public void setModuleColor(String moduleColor) {
        this.moduleColor = moduleColor;
        save();
    }

    public LogMarkupConfiguration() {
        // When Jenkins is restarted, load any saved configuration from disk.
        load();
        // Set default value
        debugColor = debugColor == null ? "#66FF99" : debugColor;
        infoColor = infoColor == null ? "#0000FF" : infoColor;
        warningColor = warningColor == null ? "CC9933" : warningColor;
        errorColor = errorColor == null ? "#FF0000" : errorColor;
        threadColor = threadColor == null ? "#FF00FF" : threadColor;
        packageColor = packageColor == null ? "#669999" : packageColor;
        successColor = successColor == null ? "#00FF00" : successColor;
        pluginColor = pluginColor == null ? "#00FF33" : pluginColor;
        moduleColor = moduleColor == null ? "#6699FF" : moduleColor;
    }


    private boolean isInValidColor(String color) {
        color = color.trim();
        return (!PATTERN_COLOR.matcher(color).matches());
    }

    public FormValidation doCheckDebugColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid debug color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckInfoColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid info color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckWarningColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid warning color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckErrorColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid error color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckThreadColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid thread color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckPackageColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid package color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckSuccessColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid success color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckPluginColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid plugin color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public FormValidation doCheckModuleColor(@QueryParameter String value) {
        if (isInValidColor(value)) {
            return FormValidation.error("Please input valid module color. For example #00FF00.");
        }
        return FormValidation.ok();
    }

    public String getDebugColor() {
        return debugColor;
    }

    public String getInfoColor() {
        return infoColor;
    }

    public String getWarningColor() {
        return warningColor;
    }

    public String getErrorColor() {
        return errorColor;
    }

    public String getThreadColor() {
        return threadColor;
    }

    public String getPackageColor() {
        return packageColor;
    }

    public String getSuccessColor() {
        return successColor;
    }

    public String getPluginColor() {
        return pluginColor;
    }

    public String getModuleColor() {
        return moduleColor;
    }
}
