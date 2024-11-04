package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seats {
    private int row;
    private int column;
    private int price;
    public Boolean isAvailable;
    public String token;

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
        this.isAvailable = true;
        this.token = "";

        if (this.row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setBooked(Boolean status) {
        this.isAvailable = status;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
