package ge.edu.freeuni.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.edu.freeuni.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StudentsWebSocket extends Endpoint {
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        final StudentDao students = (StudentDao) endpointConfig.getUserProperties().get("store");
        final RemoteEndpoint.Basic remote = session.getBasicRemote();
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String s) {
                try {
                    System.out.println(s);
                    Filter f = buildFilter(s);
                    List<Student> filtered = students.filter(f);
                    String ret = new ObjectMapper().writeValueAsString(filtered);
                    remote.sendText(ret);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private static Filter buildFilter(final String s) {
        Map<String, Object> data = null;
        try {
            data = new ObjectMapper().readValue(s, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        AndFilter f = new AndFilter();
        if (data.containsKey("first_name")) {
            f.add(new FirstNameFilter((String) data.get("first_name")));
        }
        if (data.containsKey("last_name")) {
            f.add(new LastNameFilter((String) data.get("last_name")));
        }
        if (data.containsKey("enrollment_year")) {
            f.add(new EnrollmentYearFilter((Integer) data.get("enrollment_year")));
        }
        if (f.size() > 0) {
            return f;
        } else {
            return new AllFilter();
        }
    }
}
