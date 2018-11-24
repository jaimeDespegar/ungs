package ungs.utils;

import ungs.executors.TaskExecutor;
import ungs.executors.TaskJob;
import ungs.executors.tasks.interfaces.MyTask;

public class JobUtils {

    public static void runJob(MyTask task, Long minutes) {
        TaskExecutor executor = new TaskExecutor(new TaskJob(task), minutes);
        executor.run();
    }

}