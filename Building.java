/**
 * -------------------------------------Info-----------------------------------
 *	Name:				Date:			Current Version:
 *	Martin Barcelon		11-18-2016		1.0
 *
 *	Email:
 *	martin.barcelon@stonybrook.edu
 *
 *	Student ID:		
 *	110250249		
 */
/**
 * -------------------------------Short Description----------------------------
 *      This object, Building, stores the database of Classroom objects in a 
 * HashMap. The room number of the classrooms is the key for the BuildingMap.
 * BuildingMap takes the HashMap<Integer,Classroom>. Methods in the object 
 * include:
 *  addClassroom - adds Classrooms to the BuildingMap
 *  getClassroom - returns the specified Classroom
 *  printClassrooms - prints all the Classrooms
 *  returnClassUtil - returns the Classroom utilities
 *  removeClassroom - removes the Classroom from the BuildingMap
 *  getTotalSeats - returns the total amount of seats in the Building/Classrooms
 *  findWhiteboardPercent - returns the percent of Classrooms with whiteboards
 *  findChalkboardPercent - returns the percent of Classrooms with blackboards
 *  findAVE - returns a list of all the Classroom equipment
 *  returnBuildingMap - returns the BuildingMap
 * ----------------------------------------------------------------------------
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Building implements Serializable {

    /**
     * Initialize HashMap BuildingMap.
     */
    HashMap<Integer, Classroom> BuildingMap =
            new HashMap<Integer, Classroom>();

    public Building() {}

    /**
     * Adds a new classroom to the BuildingMap.
     * @param roomNumber key of the BuildingMap
     * @param classroom Value of the respective Key in the BuildingMap
     * @throws IllegalArgumentException when Key is already present or the 
     * roomNumber is negative.
     */
    public void addClassroom(int roomNumber, Classroom classroom) 
                                            throws IllegalArgumentException {
        if (BuildingMap.containsKey(roomNumber) || roomNumber < 0) {
            throw new IllegalArgumentException("ERROR");
        } else {
            BuildingMap.put(roomNumber, classroom);
        }
    }

    /**
     * Returns the specified Classroom based on classNumber.
     * @param roomNumber searched key of the BuildingMap
     * @return the Classroom object if roomNumber is found, null otherwise
     */
    public Classroom getClassroom(int roomNumber) {
        if (BuildingMap.containsKey(roomNumber)) {
            Classroom temp = BuildingMap.get(roomNumber);
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Prints all the Classrooms in the building.
     * @return String of Classrooms separated by a "/"
     */
    public String printClassrooms() {
        String rooms = "";
        Set set = BuildingMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            rooms += mentry.getKey() + "/";
        }
        return rooms;
    }

    /**
     * Prints a whole rundown of all the variables in the specified Classroom.
     * @param roomNumber the specified Classroom in the HashMap to be printed
     */
    public void returnClassUtil(int roomNumber) {
        boolean hasWBoard = BuildingMap.get(roomNumber).hasWhiteboard();
        boolean hasCBoard = BuildingMap.get(roomNumber).hasChalkboard();
        int numSeats = BuildingMap.get(roomNumber).numSeats();
        String[] list = BuildingMap.get(roomNumber).AVEquipmentList();

        System.out.println("Seats: " + numSeats);
        if (hasWBoard == true) {
            System.out.println("\tHas whiteboard.");
        } else {
            System.out.println("\tDoesn't have whiteboard");
        }
        if (hasCBoard == true) {
            System.out.println("\tHas chalkbaord.");
        } else {
            System.out.println("\tDoesn't have chalkbaord.");
        }
        String AVlist = "";
        for (int i = 0; i < list.length - 1; i++) {
            AVlist += list[i] + ",";
        }
        System.out.println("AV Amenities: " + AVlist);
    }

    /**
     * Removes the specified roomNumber in the Building.
     * @param roomNumber the number of the HashMap Key to be removed
     * @throws IllegalArgumentException if HashMap Key is not present or 
     * roomNumber is negative
     */
    public void removeClassroom(int roomNumber) 
                                        throws IllegalArgumentException {
        if (BuildingMap.containsKey(roomNumber) == false || roomNumber < 0) {
            throw new IllegalArgumentException("ERROR");
        } else {
            Classroom temp = BuildingMap.remove(roomNumber);
        }
    }

    /**
     * Returns the total amount of seats in the Building.
     * @return integer of total seats
     */
    public int getTotalSeats() {
        int seats = 0;
        Set set = BuildingMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Classroom temp = (Classroom) mentry.getValue();
            seats += temp.numSeats();
        }
        return seats;
    }

    /**
     * Returns a percent of all Whiteboards present in the Building.
     * @return double percent of Whiteboards in building per total Classrooms
     */
    public double findWhiteboardPercent() {
        double numerator = 0, denominator = 0, percent;
        Set set = BuildingMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            denominator++;
            Classroom temp = (Classroom) mentry.getValue();
            if (temp.hasWhiteboard()) {
                numerator++;
            }
        }
        percent = numerator / denominator;
        percent = percent * 100;
        return percent;
    }

    /**
     * Returns a percent of all Chalkboards present in the Building.
     * @return double percent of Chalkboards in building per total Classrooms
     */
    public double findChalkboardPercent() {
        double numerator = 0, denominator = 0, percent;
        Set set = BuildingMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            denominator++;
            Classroom temp = (Classroom) mentry.getValue();
            if (temp.hasChalkboard()) {
                numerator++;
            }
        }
        percent = numerator / denominator;
        percent = percent * 100;
        return percent;
    }

    /**
     * Returns a list of all the AV Equipment in the building.
     * @return String containing each equipment
     */
    public String findAVE() {
        String AllAVE = "";
        Set set = BuildingMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Classroom temp = (Classroom) mentry.getValue();
            AllAVE += temp.printAVEquipmentList();
        }
        return AllAVE;
    }

    /**
     * Returns the BuildingMap HashMap.
     * @return BuildingMap HashMap
     */
    public Map returnBuildingMap() {
        return BuildingMap;
    }

    public class StorageTable implements Serializable {
        // Member methods as is
    }
}
