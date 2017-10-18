package Manager;

import Pojo.Order;

import javax.xml.ws.WebServiceException;
import java.text.ParseException;
import java.util.List;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 05/10/17.
 */
public interface OrderManager {

    List<Order> getOrderList() throws WebServiceException, ParseException;
}
