package ungs.caches.executors.tasks.impl;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import ungs.caches.client.CacheClient;
import ungs.model.InformationDto;

public class CleanCacheTask extends CacheTask {

    private Long minutesOfVigency;

    public CleanCacheTask(CacheClient client, Long minutesOfVigency) {
        super(client);
        this.minutesOfVigency = minutesOfVigency;
    }

    @Override
    public void doExecute() {
        this.client.readAll().forEach(i->deleteIfhasNotAPeriodValid((InformationDto) i));
    }

    private void deleteIfhasNotAPeriodValid(InformationDto informationDto) {
        if(!hasAValidPeriod(informationDto)) {
            this.client.delete(informationDto);
        }
    }

    private boolean hasAValidPeriod(InformationDto informationDto) {
        DateTime date = DateTime.parse(informationDto.getDate());
        Duration duration = new Duration(date, DateTime.now());
        return duration.getStandardMinutes() <= minutesOfVigency;
    }

}