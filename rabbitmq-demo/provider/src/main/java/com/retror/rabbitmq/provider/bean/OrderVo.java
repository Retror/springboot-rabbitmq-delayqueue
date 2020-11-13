package com.retror.rabbitmq.provider.bean;

import java.util.Date;

public class OrderVo {
    private long orderId;
    private String orderNo;
    private Date createdate;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", createdate=" + createdate +
                '}';
    }
}
