package Manager;

import Pojo.Assignment;

import java.text.ParseException;
import java.util.List;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 07/10/17.
 */
public interface AllocationManager {

    List<Assignment> performAllocation() throws ParseException;
}
