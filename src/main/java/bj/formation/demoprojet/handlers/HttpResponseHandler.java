package bj.formation.demoprojet.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseHandler {
    public static ResponseEntity<Object> generateResponse(Boolean success, String message, HttpStatus status, Object responseObj, String endpoint) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("timestamp", new Date());
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("endpoint", endpoint);



        return new ResponseEntity<>(map, status);
    }
}
