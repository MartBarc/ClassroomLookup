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
 *      Classroom contains four private data fields: Whiteboard, Chalkboard,
 * numSeats, and AVEList. Whiteboard and Chalkboard are boolean values. num
 * -Seats holds the number of seats in the Classroom and AVEList holds an 
 * String array for AV Equipment in the Classroom. Each variable has its return
 * methods. A constructor method adds all the variables at once. The 
 * returnClassUtil method returns a rundown of the status of all the variables.
 * ----------------------------------------------------------------------------
 */

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Classroom implements Serializable{

    /**
     * Initializes variables.
     */
    private boolean Whiteboard;
    private boolean Chalkboard;
    private int numSeats;
    private String[] AVEList;

    /**
     * Constructs the Classroom.
     */
    public Classroom() {
        Whiteboard = false;
        Chalkboard = false;
        numSeats = 0;
        AVEList = new String[0];
    }

    /**
     * Constructs the Classroom with all variable parameters.
     * @param whiteboard whiteboard present status in the Classroom
     * @param chalkboard  chalkboard present status in the Classroom
     * @param seats number of seats in the Classroom
     * @param AVlist string of AV Equipment separated by commas
     */
    public Classroom
        (boolean whiteboard, boolean chalkboard, int seats, String AVlist) {
        Whiteboard = whiteboard;
        Chalkboard = chalkboard;
        numSeats = seats;
        List<String> list = Arrays.asList(AVlist.split("[, .]"));
        AVEList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            AVEList[i] = list.get(i);
        }
    }

    /**
     * Sets the Classroom with all variable parameters.
     * @param whiteboard whiteboard present status in the Classroom
     * @param chalkboard  chalkboard present status in the Classroom
     * @param seats number of seats in the Classroom
     * @param AVlist string of AV Equipment separated by commas
     */
    public void setClassroom
        (boolean whiteboard, boolean chalkboard, int seats, String AVlist) {
        Whiteboard = whiteboard;
        Chalkboard = chalkboard;
        numSeats = seats;
        List<String> list = Arrays.asList(AVlist.split("[, .]"));
        AVEList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            AVEList[i] = list.get(i);
        }
    }

    /**
     * Returns a full rundown of the status of the white/chalkboard as well as
     * the number of seats and AV Equipment List in the Classroom.
     */
    public void returnClassUtil() {
        System.out.println("\tSeats: " + numSeats);
        if (Whiteboard == true) {
            System.out.println("\tHas whiteboard.");
        } else {
            System.out.println("\tDoesn't have whiteboard");
        }
        if (Chalkboard == true) {
            System.out.println("\tHas chalkbaord.");
        } else {
            System.out.println("\tDoesn't have chalkbaord.");
        }
        String AVlist = "\t";
        for (int i = 0; i <= AVEList.length - 1; i++) {
            AVlist += AVEList[i] + ",";
        }
        System.out.println("AV Amenities: " + AVlist);
    }

    /**
     * Returns the status of the whiteboard in the Classroom.
     * @return true if whiteboard is present, false otherwise
     */
    public boolean hasWhiteboard() {
        return Whiteboard;
    }

    /**
     * Returns the status of the chalkboard in the Classroom.
     * @return true if chalkboard is present, false otherwise
     */
    public boolean hasChalkboard() {
        return Chalkboard;
    }

    /**
     * Returns the number of seats in the Classroom.
     * @return the number of seats (non-negative number)
     */
    public int numSeats() {
        return numSeats;
    }

    /**
     * Returns if the Classroom has the specified piece of AV Equipment.
     * @param input is the piece of AV Equipment sought
     * @return true if AVEList contains the input, false otherwise
     */
    public boolean hasAVEquipmentList(String input) {
        boolean hasAVE = Arrays.asList(AVEList).contains(input);
        return hasAVE;
    }

    /**
     * Returns the array of AV Equipment.
     * @return AVEList array of Strings
     */
    public String[] AVEquipmentList() {
        return AVEList;
    }

    /**
     * Returns a list of all the equipment present in the Classroom.
     * @return String of the equipment one by one separated by a comma
     */
    public String printAVEquipmentList() {
        String AVlist = "";
        for (int i = 0; i <= AVEList.length - 1; i++) {
            AVlist += AVEList[i] + ",";
        }
        return AVlist;
    }

    public class StorageTable implements Serializable {
        // Member methods as is
    }

}
