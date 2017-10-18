package ManagerImpl;

import Manager.DeliveryManager;
import Pojo.Assignment;
import Pojo.DeliveryExecutive;
import Pojo.Order;
import Util.DistanceUtil;

import javax.xml.ws.WebServiceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Constants.CommonConstants.*;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 05/10/17.
 */


public class DeliveryManagerImpl implements DeliveryManager{
    private static final DeliveryManagerImpl INSTANCE = new DeliveryManagerImpl();
    private static List<DeliveryExecutive> deliveryExecutivesList = new ArrayList<>();
    private DeliveryManagerImpl() {
    }

    public static DeliveryManagerImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Assignment allocateDeliveryPerson(Order order, List<DeliveryExecutive> deliveryExecutiveList) {
        Assignment assignment = null;

        DeliveryExecutive deliveryPerson = findIdleDeliveryExecutive(order, deliveryExecutiveList);

//      NO DELIVERY PERSON AVAILABLE RIGHT NOW, TRY TO FIND DELIVERY PERSON WHO ARE ABOUT TO COMPLETE BOOKING
        if (deliveryPerson == null)
            deliveryPerson = findOngoingDeliveryExecutive(order, deliveryExecutiveList);

//      NO DELIVERY PERSON AVAILABLE RIGHT NOW, TRY TO FIND BY GIVING SOME BUFFER TIME
        if (deliveryPerson == null)
            deliveryPerson = findDeliveryExecutiveWithBuffer(order, deliveryExecutiveList);

        if (deliveryPerson != null) {
            Date updatedDeliveryTime = new Date(order.getOrderedTime().getTime() + (MAX_DELIVERY_TIME * ONE_MINUTE_IN_MILLIS));
            deliveryPerson.setLastDeliveredTime(null);
            deliveryPerson.setEstimatedDeliveredTime(updatedDeliveryTime);
            assignment = new Assignment(order.getId(), deliveryPerson.getId());
        }
        return assignment;
    }

    private static DeliveryExecutive findIdleDeliveryExecutive(Order order, List<DeliveryExecutive> deliveryExecutiveList) {
        double distance;
        DeliveryExecutive finalDeliveryExecutive = null;
        Date finalOrderTime = new Date(order.getOrderedTime().getTime() + ((FOOD_PREPARATION_TIME) * ONE_MINUTE_IN_MILLIS));

        for (DeliveryExecutive deliveryExecutive : deliveryExecutiveList) {
            distance = DistanceUtil.getDistanceFromLatLon(order.getRestaurantLat(), order.getRestaurantLng(), deliveryExecutive.getCurrentLat(), deliveryExecutive.getCurrentLng(), 0, 0);
            if (deliveryExecutive.getLastDeliveredTime() != null && distance < MAX_DISTANCE) {
                if (deliveryExecutive.getLastDeliveredTime().before(finalOrderTime) || deliveryExecutive.getLastDeliveredTime().equals(finalOrderTime)) {
                    if (finalDeliveryExecutive != null)
                        finalDeliveryExecutive = deliveryExecutive.getLastDeliveredTime().after(finalDeliveryExecutive.getLastDeliveredTime()) ? deliveryExecutive : finalDeliveryExecutive;
                    else
                        finalDeliveryExecutive = deliveryExecutive;
                }
            }
        }
        return finalDeliveryExecutive;
    }

    private static DeliveryExecutive findDeliveryExecutiveWithBuffer(Order order, List<DeliveryExecutive> deliveryExecutiveList) {
        double distance;
        DeliveryExecutive finalDeliveryExecutive = null;
        Date finalOrderTime = new Date(order.getOrderedTime().getTime() + ((FOOD_PREPARATION_TIME*BUFFER_TIME) * ONE_MINUTE_IN_MILLIS));

        for (DeliveryExecutive deliveryExecutive : deliveryExecutiveList) {
            distance = DistanceUtil.getDistanceFromLatLon(order.getRestaurantLat(), order.getRestaurantLng(), deliveryExecutive.getCurrentLat(), deliveryExecutive.getCurrentLng(), 0, 0);
            if (deliveryExecutive.getEstimatedDeliveredTime() != null && distance < MAX_DISTANCE) {
                if (deliveryExecutive.getEstimatedDeliveredTime().before(finalOrderTime) || deliveryExecutive.getEstimatedDeliveredTime().equals(finalOrderTime)) {
                    if (finalDeliveryExecutive != null)
                        finalDeliveryExecutive = deliveryExecutive.getEstimatedDeliveredTime().before(finalDeliveryExecutive.getEstimatedDeliveredTime()) ? deliveryExecutive : finalDeliveryExecutive;
                    else
                        finalDeliveryExecutive = deliveryExecutive;
                }
            }
        }
        return finalDeliveryExecutive;
    }

    private static DeliveryExecutive findOngoingDeliveryExecutive(Order order, List<DeliveryExecutive> deliveryExecutiveList) {
        double distance;
        DeliveryExecutive finalDeliveryExecutive = null;
        Date finalOrderTime = new Date(order.getOrderedTime().getTime() + ((FOOD_PREPARATION_TIME*BUFFER_TIME) * ONE_MINUTE_IN_MILLIS));

        for (DeliveryExecutive deliveryExecutive : deliveryExecutiveList) {
            distance = DistanceUtil.getDistanceFromLatLon(order.getRestaurantLat(), order.getRestaurantLng(), deliveryExecutive.getCurrentLat(), deliveryExecutive.getCurrentLng(), 0, 0);
            if (deliveryExecutive.getEstimatedDeliveredTime() != null && distance < MAX_DISTANCE) {
//              Delivery person comes before time , (orderTime+cookTime+BufferTime
                if (deliveryExecutive.getEstimatedDeliveredTime().before(finalOrderTime) || deliveryExecutive.getEstimatedDeliveredTime().equals(finalOrderTime)) {
                    if (finalDeliveryExecutive != null)
                        finalDeliveryExecutive = deliveryExecutive.getEstimatedDeliveredTime().after(finalDeliveryExecutive.getEstimatedDeliveredTime()) ? deliveryExecutive : finalDeliveryExecutive;
                    else
                        finalDeliveryExecutive = deliveryExecutive;
                }
            }
        }
        return finalDeliveryExecutive;
    }

    /*STATIC DATA FOR NOW FOR TEST PURPOSE*/
    public static List<DeliveryExecutive> findAllDeliveryPerson() {
        if (deliveryExecutivesList != null && !deliveryExecutivesList.isEmpty())
            return deliveryExecutivesList;

        List<DeliveryExecutive> deliveryExecutivesList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            DeliveryExecutive deliveryExecutive1 = new DeliveryExecutive(126,  12.9668141,77.6421751, format.parse("2017-10-07 19:00:00"), null);
            deliveryExecutivesList.add(deliveryExecutive1);

            DeliveryExecutive deliveryExecutive2 = new DeliveryExecutive(127,  12.9668141,77.6421751, format.parse("2017-10-07 18:00:00"), null);
            deliveryExecutivesList.add(deliveryExecutive2);

//            DeliveryExecutive deliveryExecutive3 = new DeliveryExecutive(128,  12.9668141,77.6421751, null, format.parse("2017-10-07 20:00:00"));
//            deliveryExecutivesList.add(deliveryExecutive3);
//
//            DeliveryExecutive deliveryExecutive4 = new DeliveryExecutive(129,  12.9626197,77.6392586, null, format.parse("2017-10-07 20:10:00"));
//            deliveryExecutivesList.add(deliveryExecutive4);
//
//            DeliveryExecutive deliveryExecutive5 = new DeliveryExecutive(130,  12.9115919,77.6478264, null, format.parse("2017-10-07 20:15:00"));
//            deliveryExecutivesList.add(deliveryExecutive5);
        } catch (ParseException e) {
            throw new com.sun.tools.internal.ws.wsdl.framework.ParseException("Unable to parse string");
        } catch (WebServiceException e) {
            throw new WebServiceException("Error Occur while sending creating list");
        }
        return deliveryExecutivesList;
    }

}
