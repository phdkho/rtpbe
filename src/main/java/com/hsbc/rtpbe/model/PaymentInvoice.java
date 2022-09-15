package com.hsbc.rtpbe.model;

public class PaymentInvoice {

    private String id;
    private PaymentRequest detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PaymentRequest getDetail() {
        return detail;
    }

    public void setDetail(PaymentRequest detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "PaymentInvoice{" +
                "id='" + id + '\'' +
                ", detail=" + detail.toString() +
                '}';
    }
}
