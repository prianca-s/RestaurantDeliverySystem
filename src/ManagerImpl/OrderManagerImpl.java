package ManagerImpl;

import Manager.OrderManager;
import Pojo.Order;

import javax.xml.ws.WebServiceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 05/10/17.
 */
public class OrderManagerImpl implements OrderManager{
    private static final OrderManagerImpl INSTANCE = new OrderManagerImpl();

    public static OrderManagerImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Order> getOrderList() throws WebServiceException, ParseException {
        List<Order> orderList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Order order1 = new Order(1, 1, 12.9626424, 77.6432587, format.parse("2017-10-07 20:00:00"));
            orderList.add(order1);

            Order order2 = new Order(2, 2, 12.9626424, 77.6432587, format.parse("2017-10-07 20:00:00"));
            orderList.add(order2);

            Order order3 = new Order(3, 3, 12.9115919,77.6478264, format.parse("2017-10-07 20:00:00"));
            orderList.add(order3);
        } catch (ParseException e) {
            throw new com.sun.tools.internal.ws.wsdl.framework.ParseException("Unable to parse string");
        } catch (WebServiceException e) {
            throw new WebServiceException("Error Occur while sending creating list");
        }
        return orderList;
    }

}
