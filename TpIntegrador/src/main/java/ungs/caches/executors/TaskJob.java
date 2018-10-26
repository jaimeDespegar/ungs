package ungs.caches.executors;

import ungs.caches.executors.tasks.interfaces.MyTask;

import java.util.Date;
import java.util.TimerTask;

public class TaskJob<T extends MyTask> extends TimerTask {

    private T task;

    public TaskJob(T task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println("CORRO JOB " + new Date().toString());
        task.doExecute();
    }

}