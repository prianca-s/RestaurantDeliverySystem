package Main;

import Manager.AllocationManager;
import ManagerImpl.AllocationManagerImpl;
import Pojo.Assignment;

import java.text.ParseException;
import java.util.List;

/**
 * @author priyanka_s [priyanka.singh@zoomcar.com]
 *         Part of delivery
 *         on 05/10/17.
 */
public class SwiggyAllocation {
    public static void main(String[] args) throws ParseException {
        AllocationManager allocationManager = AllocationManagerImpl.getInstance();
        List<Assignment> assignmentList = allocationManager.performAllocation();
        for (Assignment assignment: assignmentList) {
            System.out.println(assignment);
        }
    }
}
