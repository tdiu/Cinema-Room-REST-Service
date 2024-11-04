package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;

public class Cinema {
    private int columns;
    private int rows;
    private ArrayList<Seats> seats;
    private ArrayList<Seats> reservedSeats;
    private int income;

    public Cinema(int columns, int rows) {
        this.seats = new ArrayList<Seats>();
        this.columns = columns;
        this.rows = rows;
        this.reservedSeats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.seats.add(new Seats(i + 1, j + 1));
            }
        }
    }

    public ArrayList<Seats> getSeats() {
        return seats;
    }

    public Seats getSeat(int row, int column) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst()
                .orElse(null);
    }

    public Seats findSeatByToken(String token) {
        return seats.stream()
                .filter(seat -> seat.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }

    public void setSeats(int rows, int columns) {
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < columns; j++) {
               this.seats.add(new Seats(i + 1, j + 1));
           }
       }
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @JsonIgnore
    public ArrayList<Seats> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(ArrayList<Seats> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Seats findReservedSeatByToken(String token) {
        return reservedSeats.stream()
                .filter(seat -> seat.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }

    @JsonIgnore
    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void addIncome(int income) {
        this.income += income;
    }

    public void subtractIncome(int income) {
        this.income -= income;
    }
}
