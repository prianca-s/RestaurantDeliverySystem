package ManagerImpl;

import Manager.AllocationManager;
import Manager.DeliveryManager;
import Manager.OrderManager;
import Pojo.Assignment;
import Pojo.DeliveryExecutive;
import Pojo.Order;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static ManagerImpl.DeliveryManagerImpl.findAllDeliveryPerson;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 07/10/17.
 */
public class AllocationManagerImpl implements AllocationManager {
    private static final AllocationManagerImpl INSTANCE = new AllocationManagerImpl();
    private OrderManager orderManager;
    private DeliveryManager deliveryManager;

    public static AllocationManagerImpl getInstance() {
        return INSTANCE;
    }

    private AllocationManagerImpl() {
        orderManager = OrderManagerImpl.getInstance();
        deliveryManager = DeliveryManagerImpl.getInstance();
    }

    @Override
    public List<Assignment> performAllocation() throws ParseException {
        List<Order> orderStream = orderManager.getOrderList();
        List<Assignment> assignmentList = new ArrayList<>();
        List<DeliveryExecutive> deliveryExecutiveList = findAllDeliveryPerson();

        for (Order order : orderStream) {
            Assignment assignment = deliveryManager.allocateDeliveryPerson(order, deliveryExecutiveList);
            if (assignment != null)
                assignmentList.add(assignment);
        }
        return assignmentList;
    }
}
