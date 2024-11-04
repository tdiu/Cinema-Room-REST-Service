package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class CinemaController {

    private CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinemaService.getCinema();
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam Optional<String> password) {
       return cinemaService.showStats(password, cinemaService.getCinema());
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> seatRequest(@RequestBody Seats seat) {
        return cinemaService.purchaseTicket(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> seatReturn(@RequestBody Token tok) {
        return cinemaService.refundTicket(tok.getToken());
    }
}
