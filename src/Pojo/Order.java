package Pojo;

import java.util.Date;
import java.util.Objects;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 05/10/17.
 */
public class Order {
    private int id;
    private int userId;
    private double restaurantLat;
    private double restaurantLng;
    private Date orderedTime;

    public Order() {
    }

    public Order(int id, int userId, double restaurantLat, double restaurantLng, Date orderedTime) {
        this.id = id;
        this.userId = userId;
        this.restaurantLat = restaurantLat;
        this.restaurantLng = restaurantLng;
        this.orderedTime = orderedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRestaurantLat() {
        return restaurantLat;
    }

    public void setRestaurantLat(double restaurantLat) {
        this.restaurantLat = restaurantLat;
    }

    public double getRestaurantLng() {
        return restaurantLng;
    }

    public void setRestaurantLng(double restaurantLng) {
        this.restaurantLng = restaurantLng;
    }

    public Date getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Date orderedTime) {
        this.orderedTime = orderedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                Double.compare(order.restaurantLat, restaurantLat) == 0 &&
                Double.compare(order.restaurantLng, restaurantLng) == 0 &&
                Objects.equals(orderedTime, order.orderedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, restaurantLat, restaurantLng, orderedTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", restaurantLat=" + restaurantLat +
                ", restaurantLng=" + restaurantLng +
                ", orderedTime=" + orderedTime +
                '}';
    }
}
