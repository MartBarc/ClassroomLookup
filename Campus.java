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
 *      This object, Campus, stores the database of Building objects in a Hash
 * -Map. The name of the Building is the key for the hashing. The CampusMap 
 * takes the HashMap<String,Building>. Methods in the object include:
 *  addBuilding - adds buildings to the CampusMap
 *  getBuilding - returns the building from the CampusMap
 *  removeBuilding - removes the building from the CampusMap
 *  printBuildings - prints all buildings in CampusMap
 *  findAVEListRooms - searches for AV in all buildings/rooms
 *  findChalkboardRooms - searches for chalkboards in all buildings/rooms
 *  findWhiteboardRooms - searches for whiteboards in all buildings/rooms
 *  findClassroom -  searches for specified classroom
 *  printBuildingDetails - prints specifies building details
 * ----------------------------------------------------------------------------
 */
import java.util.HashMap;
import java.io.Serializable;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Campus implements Serializable{

    /**
     * Initializes HashMap CampusMap.
     */
    HashMap<String, Building> CampusMap = new HashMap<String, Building>();

    public Campus() {}

    /**
     * Adds a building to the campus.
     * @param buildingName the Key of the CampusMap Value
     * @param building the Value of the entered CampusMap Key
     * @throws IllegalArgumentException when the buildingName is null or if the
     * CampusMap already contains the buildingName
     */
    public void addBuilding(String buildingName, Building building) 
                                            throws IllegalArgumentException {
        if (buildingName == null || CampusMap.containsKey(buildingName)) {
            throw new IllegalArgumentException("ERROR");
        } else {
            CampusMap.put(buildingName, building);
        }
    }

    /**
     * Returns the Building based on the buildingName.
     * @param buildingName the Key for the returned Value
     * @return Building from the specified CampusMap
     */
    public Building getBuilding(String buildingName) {
        if (CampusMap.containsKey(buildingName)) {
            Building temp = CampusMap.get(buildingName);
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Removes the specified Building from the campus.
     * @param buildingName the Key of the CampusMap to be removed
     * @throws IllegalArgumentException when the buildingName is null or if the
     * CampusMap does not contain the buildingName Key
     */
    public void removeBuilding(String buildingName) 
                                        throws IllegalArgumentException {
        if (buildingName == null || 
                CampusMap.containsKey(buildingName) == false) {
            throw new IllegalArgumentException("ERROR");
        } else {
            CampusMap.remove(buildingName);
        }
    }

    /**
     * Prints the full list of Buildings and their respective Classrooms.
     */
    public void printBuildings() {
        Set set = CampusMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Building temp = (Building) mentry.getValue();
            System.out.print("Building: " + mentry.getKey() + " | Rooms: ");
            System.out.println(temp.printClassrooms());
        }
    }
    
    /**
     * Returns a list of all the Classrooms with the specified AV Equipment 
     * present on the campus.
     * @param input the AV Equipment sought
     * @return String of the Buildings with Classrooms containing the AV 
     * Equipment
     */
    public String findAVEListRooms(String input) {
        String build = "";
        Set set1 = CampusMap.entrySet();
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()) {
            Map.Entry mentry1 = (Map.Entry) iterator1.next();
            Building temp1 = (Building) mentry1.getValue();
            build += mentry1.getKey() + ": ";
            Set set2 = temp1.returnBuildingMap().entrySet();
            Iterator iterator2 = set2.iterator();
            while (iterator2.hasNext()) {
                Map.Entry mentry2 = (Map.Entry) iterator2.next();
                Classroom temp2 = temp1.getClassroom((int) mentry2.getKey());
                if (temp2.hasAVEquipmentList(input) == true) {
                    build += mentry2.getKey() + "/";
                }
            }
            build += "\n";
        }
        return build;
    }

    /**
     * Returns a list of all the Classrooms with the Chalkboards from each 
     * Building.
     * @return String of the Buildings with Classrooms containing Chalkboards
     */
    public String findChalkboardRooms() {
        String build = "";
        Set set1 = CampusMap.entrySet();
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()) {
            Map.Entry mentry1 = (Map.Entry) iterator1.next();
            Building temp1 = (Building) mentry1.getValue();
            build += mentry1.getKey() + ": ";
            Set set2 = temp1.returnBuildingMap().entrySet();
            Iterator iterator2 = set2.iterator();
            while (iterator2.hasNext()) {
                Map.Entry mentry2 = (Map.Entry) iterator2.next();
                Classroom temp2 = temp1.getClassroom((int) mentry2.getKey());
                if (temp2.hasChalkboard() == true) {
                    build += mentry2.getKey() + "/";
                }
            }
            build += "\n";
        }
        return build;
    }

    /**
     * Returns a list of all the Classrooms with a Whiteboard in each Building.
     * @return String of the Buildings with Classrooms containing Whiteboards
     */
    public String findWhiteboardRooms() {
        String build = "";
        Set set1 = CampusMap.entrySet();
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()) {
            Map.Entry mentry1 = (Map.Entry) iterator1.next();
            Building temp1 = (Building) mentry1.getValue();
            build += mentry1.getKey() + ": ";
            Set set2 = temp1.returnBuildingMap().entrySet();
            Iterator iterator2 = set2.iterator();
            while (iterator2.hasNext()) {
                Map.Entry mentry2 = (Map.Entry) iterator2.next();
                Classroom temp2 = temp1.getClassroom((int) mentry2.getKey());
                if (temp2.hasWhiteboard() == true) {
                    build += mentry2.getKey() + "/";
                }
            }
            build += "\n";
        }
        return build;
    }

    /**
     * Finds if the Classroom exits in the campus.
     * @param input the Building followed by the room number of the Classroom
     * @return true if the Classroom exits, false otherwise
     */
    public boolean findClassroom(String input) {
        String[] search = input.split("\\s+");
        boolean classFound = false;
        if (CampusMap.containsKey(search[0])) {
            Building temp1 = CampusMap.get(search[0]);
            if (temp1.returnBuildingMap().containsKey
                                            (Integer.parseInt(search[1]))) {
                Classroom temp2 = temp1.getClassroom
                                            (Integer.parseInt(search[1]));
                temp2.returnClassUtil();
                classFound = true;
            }
        }
        return classFound;
    }

    /**
     * Prints the Building details such as rooms that exits, total seats in the
     * Building, percent of whiteboards/chalkboards present, and AV Equipment
     * present.
     * @param input Building to print the details for
     */
    public void printBuildingDetails(String input) {
        Building temp = getBuilding(input);
        System.out.println("Details:");
        System.out.println("\tRooms: " + temp.printClassrooms());
        System.out.println("\tTotal Seats: " + temp.getTotalSeats());
        System.out.println("\t" + temp.findWhiteboardPercent() 
                + "% of rooms have whiteboards.");
        System.out.println("\t" + temp.findChalkboardPercent() 
                + "% of rooms have blackboards.");
        System.out.println("\tAV Equipment present: " + temp.findAVE());
    }

    public class StorageTable implements Serializable {
        // Member methods as is
    }
}
