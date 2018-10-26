package ungs.caches.executors;

import ungs.utils.TimeUtils;

import java.util.Timer;

public class TaskExecutor {

    private Timer timer;
    private TaskJob taskJob;
    private Long minutes;
    private static final Long DEFAULT_DELAY = 0L;

    public TaskExecutor(TaskJob taskJob, Long minutes) {
        this.timer = new Timer();
        this.taskJob = taskJob;
        this.minutes = TimeUtils.getMinutes(minutes);
    }

    public void run() {
        timer.scheduleAtFixedRate(taskJob, DEFAULT_DELAY, minutes);
    }

}