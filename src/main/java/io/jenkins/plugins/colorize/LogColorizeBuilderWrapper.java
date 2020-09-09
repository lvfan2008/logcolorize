package io.jenkins.plugins.colorize;

import hudson.*;
import hudson.console.ConsoleLogFilter;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildWrapperDescriptor;
import jenkins.YesNoMaybe;
import jenkins.tasks.SimpleBuildWrapper;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author lixinguo
 */
public class LogColorizeBuilderWrapper extends SimpleBuildWrapper {

    @DataBoundConstructor
    public LogColorizeBuilderWrapper() {
    }

    @Override
    public void setUp(Context context, Run<?, ?> run, FilePath filePath, Launcher launcher, TaskListener taskListener, EnvVars envVars) throws IOException, InterruptedException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConsoleLogFilter createLoggerDecorator(Run<?, ?> build) {
        return new ConsoleLogFilterImpl();
    }

    public static class ConsoleLogFilterImpl extends ConsoleLogFilter implements Serializable {
        private static final long serialVersionUID = 1;

        @Override
        public OutputStream decorateLogger(AbstractBuild build, OutputStream logger) throws IOException, InterruptedException {
            System.out.println("ConsoleLogFilterImpl decorateLogger Called,new LogLevelColorizeOutputStream");
            return new LogColorizeOutputStream(logger);
        }
    }

    @Extension(dynamicLoadable = YesNoMaybe.YES)
    public static final class DescriptorImpl extends BuildWrapperDescriptor {

        @Override
        public String getDisplayName() {
            return "Log Colorize Plugin";
        }

        @Override
        public boolean isApplicable(AbstractProject<?, ?> abstractProject) {
            return true;
        }
    }
}
