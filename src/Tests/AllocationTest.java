package Tests;

import Manager.DeliveryManager;
import ManagerImpl.DeliveryManagerImpl;
import Pojo.Assignment;
import Pojo.DeliveryExecutive;
import Pojo.Order;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static ManagerImpl.DeliveryManagerImpl.findAllDeliveryPerson;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 07/10/17.
 */
public class AllocationTest {
    @Test
    public void returnAllocationFromIdleDeliveryExecutives() throws ParseException {
        List<DeliveryExecutive> deliveryExecutiveList = findAllDeliveryPerson();
        DeliveryManager deliveryManager = DeliveryManagerImpl.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Order order = new Order(1, 1, 12.9626424, 77.6432587, format.parse("2017-10-07 20:00:00"));
        Assignment assignment = deliveryManager.allocateDeliveryPerson(order, deliveryExecutiveList);

        Assert.assertEquals(assignment.getOrderId(), order.getId());
        Assert.assertEquals(assignment.getDeliveryExecutiveId(), deliveryExecutiveList.get(0).getId());
    }

    @Test
    public void returnAllocationFromInProgressDeliveryExecutives() throws ParseException {
        List<DeliveryExecutive> deliveryExecutiveList = findAllDeliveryPerson();
        DeliveryManager deliveryManager = DeliveryManagerImpl.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Order order = new Order(3, 3, 12.9115919,77.6478264, format.parse("2017-10-07 20:00:00"));
        Assignment assignment = deliveryManager.allocateDeliveryPerson(order, deliveryExecutiveList);

        Assert.assertEquals(assignment.getOrderId(), order.getId());
        Assert.assertEquals(assignment.getDeliveryExecutiveId(), deliveryExecutiveList.get(deliveryExecutiveList.size()-1).getId());
    }
}
