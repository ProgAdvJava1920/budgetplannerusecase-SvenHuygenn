package be.pxl.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private LocalDateTime date;
    private float amount;
    private String currency;
    private String detail;
    private String receivingIBAN;
    private int Id;
    private int accountId;
    private int counterAccountId;
    private int labelId;

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
