package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class CinemaService {
    private Cinema cinema;

    public CinemaService() {
        this.cinema = new Cinema(9, 9);
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public synchronized String generateToken() {
        return UUID.randomUUID().toString();
    }

    public synchronized ResponseEntity<Object> purchaseTicket(Seats seat) {
        int row = seat.getRow();
        int column = seat.getColumn();

        if (row < 0 || row > cinema.getRows() || column < 0 || column > cinema.getColumns()) {
            throw new NoSeatException();
        }

        Seats cinemaSeat = cinema.getSeat(row, column);
        if (cinemaSeat == null || !cinemaSeat.getIsAvailable()) {
            throw new SeatAvailabilityException();

        } else {
            cinemaSeat.setToken(generateToken());
            cinemaSeat.setBooked(false);
            cinema.addIncome(cinemaSeat.getPrice());
            cinema.getReservedSeats().add(cinemaSeat);
            cinema.getSeats().remove(cinemaSeat);
            return ResponseHandler.purchaseResponse(cinemaSeat.getToken(), seat);
        }
    }

    public synchronized ResponseEntity<Object> refundTicket(String token) {
        Seats seat = cinema.findReservedSeatByToken(token);

        if (seat != null && seat.getToken().equals(token)) {
            seat.setBooked(true);
            seat.setToken("");
            cinema.subtractIncome(seat.getPrice());
            cinema.getSeats().add(seat);
            cinema.getReservedSeats().remove(seat);
            return ResponseHandler.refundResponse(seat);
        } else {
            throw new TokenException();
        }
    }

    public ResponseEntity<Object> showStats(Optional<String> password, Cinema cinema) {
        if (password.isPresent() && password.get().equals("super_secret")) {
            return ResponseHandler.statResponse(cinema);
        } else {
            throw new PasswordException();
        }
    }
}
