package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> purchaseResponse(String token, Seats seat) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("ticket", seat);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> refundResponse(Seats seat) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ticket", seat);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> statResponse(Cinema cinema) {
        Map<String, Object> map = new HashMap<>();
        map.put("income", cinema.getIncome());
        map.put("available", cinema.getSeats().size());
        map.put("purchased", cinema.getReservedSeats().size());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
