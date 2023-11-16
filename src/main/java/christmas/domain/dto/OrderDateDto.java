package christmas.domain.dto;

public class OrderDateDto {
    private final int date;

    public OrderDateDto(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
