package com.hsbc.rtpbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Setter
@Getter
public class PaymentRequest {

    private String userId;
    private int type;
    private Long amount;
    private Timestamp createdAt;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "userId='" + userId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
