package pl.xyz.demo.eventaggregator.rawevent;

import org.springframework.data.repository.CrudRepository;
import pl.xyz.demo.eventaggregator.rawevent.RawEvent;

import java.util.List;

public interface RawEventRepository extends CrudRepository<RawEvent, String> {
    List<RawEvent> findByProcessInstanceIdOrderByEndTimeAscEventIdAsc(String processInstanceId);
}
