package bj.formation.demoprojet.handlers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class HttpResponseHandler {
    private final HttpServletRequest requestUrl;
    public static ResponseEntity<Object> generateResponse(Boolean success, String message, HttpStatus status, Object responseObj,  Map<String, String> errors, String endpoint) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("timestamp", new Date());
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("errors", errors);
        map.put("endpoint", endpoint);
        return new ResponseEntity<>(map, status);
    }
}
