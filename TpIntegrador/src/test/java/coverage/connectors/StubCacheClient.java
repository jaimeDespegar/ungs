package coverage.connectors;

import com.google.common.collect.Lists;
import ungs.caches.client.CacheClient;
import ungs.model.InformationDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StubCacheClient implements CacheClient<InformationDto> {

    private List<InformationDto> listDB;

    public StubCacheClient () {
        this.listDB = Lists.newArrayList();
    }

    @Override
    public void insert(InformationDto informationDto) {
        this.listDB.add(informationDto);
    }

    @Override
    public void update(InformationDto informationDto) {
        Optional<InformationDto> info = listDB.stream().filter(i->i.getId().equals(informationDto.getId())).findFirst();
        info.ifPresent(i->{
            this.listDB.remove(i);
            this.listDB.add(informationDto);
        });
    }

    @Override
    public void delete(InformationDto informationDto) {
        this.listDB.remove(informationDto);
    }

    @Override
    public void cleanCache() {
        this.listDB.clear();
    }

    @Override
    public List<InformationDto> readAll() {
        return listDB;
    }

    @Override
    public List<InformationDto> findByOrigin(String origin) {
        return listDB.stream().filter(i->i.getOrigin().equals(origin))
                              .collect(Collectors.toList());
    }

}