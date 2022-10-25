package util;

import dao.LookupRepository;
import entity.LookupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Constants {
    private Map<String, String> messages;

    @Autowired
    LookupRepository lookupRepo;

    @PostConstruct
    public void init() {
        messages = new HashMap<>();
        List<LookupEntity> studentMessages = lookupRepo.findByType("STUDENT_MESSAGE");
        studentMessages.forEach(m -> messages.put(m.getName(), m.getValue()));
    }

    public String getMessage(String msgName) {
        return messages.get(msgName);
    }
}
