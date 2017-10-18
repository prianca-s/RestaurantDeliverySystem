package Pojo;

import java.util.Date;
import java.util.Objects;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 07/10/17.
 */
public class DeliveryExecutive {
    private int id;
    private double currentLat;
    private double currentLng;
    private Date lastDeliveredTime;
    private Date estimatedDeliveredTime;

    public DeliveryExecutive() {
    }

    public DeliveryExecutive(int id, double currentLat, double currentLng, Date lastDeliveredTime, Date estimatedDeliveredTime) {
        this.id = id;
        this.currentLat = currentLat;
        this.currentLng = currentLng;
        this.lastDeliveredTime = lastDeliveredTime;
        this.estimatedDeliveredTime = estimatedDeliveredTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getCurrentLng() {
        return currentLng;
    }

    public void setCurrentLng(double currentLng) {
        this.currentLng = currentLng;
    }

    public Date getLastDeliveredTime() {
        return lastDeliveredTime;
    }

    public void setLastDeliveredTime(Date lastDeliveredTime) {
        this.lastDeliveredTime = lastDeliveredTime;
    }

    public Date getEstimatedDeliveredTime() {
        return estimatedDeliveredTime;
    }

    public void setEstimatedDeliveredTime(Date estimatedDeliveredTime) {
        this.estimatedDeliveredTime = estimatedDeliveredTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryExecutive)) return false;
        DeliveryExecutive that = (DeliveryExecutive) o;
        return id == that.id &&
                Double.compare(that.currentLat, currentLat) == 0 &&
                Double.compare(that.currentLng, currentLng) == 0 &&
                Objects.equals(lastDeliveredTime, that.lastDeliveredTime) &&
                Objects.equals(estimatedDeliveredTime, that.estimatedDeliveredTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentLat, currentLng, lastDeliveredTime, estimatedDeliveredTime);
    }

    @Override
    public String toString() {
        return "DeliveryExecutive{" +
                "id=" + id +
                ", currentLat=" + currentLat +
                ", currentLng=" + currentLng +
                ", lastDeliveredTime=" + lastDeliveredTime +
                ", estimatedDeliveredTime=" + estimatedDeliveredTime +
                '}';
    }
}
