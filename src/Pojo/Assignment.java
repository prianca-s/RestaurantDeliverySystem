package Pojo;

import java.util.Objects;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 07/10/17.
 */
public class Assignment {
    private int orderId;
    private int deliveryExecutiveId;

    public Assignment() {
    }

    public Assignment(int orderId, int deliveryExecutiveId) {
        this.orderId = orderId;
        this.deliveryExecutiveId = deliveryExecutiveId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeliveryExecutiveId() {
        return deliveryExecutiveId;
    }

    public void setDeliveryExecutiveId(int deliveryExecutiveId) {
        this.deliveryExecutiveId = deliveryExecutiveId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        Assignment that = (Assignment) o;
        return orderId == that.orderId &&
                deliveryExecutiveId == that.deliveryExecutiveId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, deliveryExecutiveId);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "orderId=" + orderId +
                ", deliveryExecutiveId=" + deliveryExecutiveId +
                '}';
    }
}
