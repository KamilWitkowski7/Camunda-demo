package pl.xyz.demo.eventaggregator.processtrace;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessEventTracer {
    private final ProcessTraceRepository traceRepository;
    public void updateLastEvent(String processInstanceId, Long timestamp, String eventId) {
        ProcessTrace processTrace = traceRepository.findById(processInstanceId)
                .orElseGet(() -> new ProcessTrace(processInstanceId));
        processTrace.update(timestamp, eventId);
        traceRepository.save(processTrace);
    }

    /**
     * Return true, if we have newer timestamp or the same timestamp
     */
    public boolean isAfterLast(String processInstanceId, Long timestamp, String eventId) {
        if(timestamp == null) return false;
        return traceRepository.findById(processInstanceId)
                .map(trace -> trace.getTimestamp() == null ||
                        timestamp > trace.getTimestamp() ||
                                (timestamp.equals(trace.getTimestamp()) &&
                                        (trace.getEventId() == null || eventId == null || eventId.compareTo(trace.getEventId()) > 0)))
                .orElse(true);
    }
}
