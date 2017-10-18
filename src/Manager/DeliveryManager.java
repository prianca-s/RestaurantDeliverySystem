package Manager;

import Pojo.Assignment;
import Pojo.DeliveryExecutive;
import Pojo.Order;

import java.util.List;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 05/10/17.
 */
public interface DeliveryManager {

    Assignment allocateDeliveryPerson(Order order, List<DeliveryExecutive> list);

}
