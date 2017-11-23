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
 * This program, RoomLookup, allows people to quickly reference the
 * facilities present in each room in a campus, and search for rooms that meets
 * certain criteria. RoomLookup primarily uses hash maps from the Campus,
 * Building, and Classroom objects/hash maps. Another used function is adding,
 * editing, and deleting Buildings and Classrooms.As a driver class, RoomLookup
 * includes the following UI:
 *  A - Add Building:
 *  D - Delete Building:
 *  E - Edit Building:
 *      A - Add Room
 *      D - Delete Room
 *      E - Edit room
 *      B - Back to Menu
 *  F - Find Room:
 *  S - Search
 *      Options:
 *          W - Whiteboard:
 *          B - Blackboard:
 *          A - AV Keyword:
 *  C-List buildings on campus and their rooms.
 *  L-List summary of building:
 *      Total Seats
 *      Percent of rooms have whiteboard
 *      Percent of rooms have blackboard
 *      List of all AV Equipment
 *  Q - Quit
 *      S - save --- Saves the instance of the RoomLookup
 *      D - don't save
 * ----------------------------------------------------------------------------
 */

import java.io.*;
import java.util.Scanner;

public class RoomLookup implements Serializable {

    public static void main(String[] args) {

        System.out.println("Welcome to SBGetARoom, Stony Brook's premium room "
                + "lookup system.");

        /**
         * Initialize variables.
         */
        Scanner scan = new Scanner(System.in);
        Campus campus = new Campus();
        StorageTable storage = new StorageTable(campus);
        boolean exit = false;
        
        //Load save files if present
        boolean loadSuccess = storage.loadFile();
        if (loadSuccess == true) {
            campus = storage.getSaveState();
            System.out.println("File loaded.");
        }
        
        //Start loop
        do {
            System.out.println("Menu:");
            System.out.println("\tA) Add a building");
            System.out.println("\tD) Delete a building");
            System.out.println("\tE) Edit a building");
            System.out.println("\tF) Find a room");
            System.out.println("\tS) Search for rooms");
            System.out.println("\tC) List all buildings on Campus");
            System.out.println("\tL) List building details");
            System.out.println("\tQ) Quit");

            System.out.print("Please select an option: ");
            String menuInput = scan.nextLine();

            switch (menuInput.toUpperCase()) {
                case "A": //Add a building with name
                    System.out.print("Please enter building name:");
                    String A_input = scan.nextLine();
                    Building newBuilding = new Building();
                    try {
                        campus.addBuilding(A_input, newBuilding);
                        System.out.println("Building " + A_input + " added.");
                    } catch (Exception e) {
                        System.out.println("ERROR: Input Error");
                    }
                    break;
                case "D": //Delete a building by name
                    System.out.print("Please enter building name:");
                    String B_input = scan.nextLine();
                    try {
                        campus.removeBuilding(B_input);
                       System.out.println("Building " + B_input + " removed.");
                    } catch (Exception e) {
                        System.out.println("ERROR: Input Error");
                    }
                    break;
                case "E": //Edit a Building by name
                    boolean quitEditBuilding = false;

                    System.out.print("Please enter building name: ");
                    String buildingEdit = scan.nextLine();

                    if (campus.getBuilding(buildingEdit) != null) {
                        System.out.println("Editing " + buildingEdit + ".");
                        Building building = campus.getBuilding(buildingEdit);
                        do {
                            System.out.println("Options:");
                            System.out.println("\tA) Add room");
                            System.out.println("\tD) Delete room");
                            System.out.println("\tE) Edit room");
                            System.out.println("\tB) Back to menu");

                            System.out.print("Please select an option: ");
                            String editInput = scan.nextLine();

                            switch (editInput.toUpperCase()) {
                                case "A": //Add room
                                    System.out.print("Please enter room number"
                                            + ": ");
                                    int roomNumIn = scan.nextInt();
                                    System.out.print("Please enter number of "
                                            + "seats: ");
                                    int seatNum = scan.nextInt();
                                    System.out.print("Please enter AV Equip"
                                            + "ment (sperated by commas): ");
                                    String AVEquip = scan.nextLine();
                                    AVEquip = scan.nextLine();

                                    boolean wCont = false,
                                     cCont = false;
                                    boolean hasWBoard = false,
                                     hasCBoard = false;

                                    System.out.print("Does it have a white"
                                            + "board?(Y/n): ");
                                    do {
                                        String wBoard = scan.next();
                                        switch (wBoard.toUpperCase()) {
                                            case "Y":
                                                hasWBoard = true;
                                                wCont = true;
                                                break;
                                            case "N":
                                                hasWBoard = false;
                                                wCont = true;
                                                break;
                                            default:
                                                System.out.println("Whiteboard"
                                                        + " input not "
                                                        + "recognized,"
                                                        + " try again: ");
                                                break;
                                        }
                                    } while (wCont == false);
                                    System.out.print("Does it have a chalk"
                                            + "board?(Y/n): ");
                                    String cBoard = scan.nextLine();
                                    do {
                                        cBoard = scan.nextLine();
                                        switch (cBoard.toUpperCase()) {
                                            case "Y":
                                                hasCBoard = true;
                                                cCont = true;
                                                break;
                                            case "N":
                                                hasCBoard = false;
                                                cCont = true;
                                                break;
                                            default:
                                                System.out.println("Chalkboard"
                                                        + " input not"
                                                        + " recognized,"
                                                        + " try again: ");
                                                break;
                                        }
                                    } while (cCont == false);
                                    try {
                                        Classroom newClassroom =
                                                new Classroom
                                        (hasWBoard, hasCBoard, seatNum, AVEquip);
                                        building.addClassroom
                                        (roomNumIn, newClassroom);
                                    } catch (Exception e) {
                                        System.out.println("ERROR: "
                                                + "Input Error");
                                    }
                                    break;
                                case "D": //Delete room
                                    System.out.print("Please enter room number"
                                            + ": ");
                                    int removedRoomNum = scan.nextInt();
                                    try {
                                        building.removeClassroom
                                        (removedRoomNum);
                                    } catch (Exception e) {
                                        System.out.println("ERROR: "
                                                + "Input error");
                                    }
                                    break;
                                case "E": //Edit room
                                    System.out.print("Please enter room number"
                                            + ": ");
                                    int editedRoomNum = scan.nextInt();
                                    if (building.returnBuildingMap().
                                            containsKey(editedRoomNum)) {
                                        System.out.print("Please enter number"
                                                + " of seats: ");
                                        int EseatNum = scan.nextInt();
                                        System.out.print("Please enter AV "
                                                + "Equipment "
                                                + "(sperated by commas): ");
                                        String EAVEquip = scan.nextLine();
                                        EAVEquip = scan.nextLine();
                                        boolean EwCont = false,
                                                EcCont = false;
                                        boolean EhasWBoard = false,
                                                EhasCBoard = false;
                                        System.out.println("Does it have a "
                                                + "whiteboard?(Y/n): ");
                                        do {
                                            String EwBoard = scan.nextLine();
                                            switch (EwBoard.toUpperCase()) {
                                                case "Y":
                                                    EhasWBoard = true;
                                                    EwCont = true;
                                                    break;
                                                case "N":
                                                    EhasWBoard = false;
                                                    EwCont = true;
                                                    break;
                                                default:
                                                    System.out.println("White"
                                                            + "board input not"
                                                            + " recognized,"
                                                            + " try again: ");
                                                    break;
                                            }
                                        } while (EwCont == false);
                                        System.out.println("Does it have a "
                                                + "chalkboard?(Y/n): ");
                                        String EcBoard = scan.nextLine();
                                        do {
                                            EcBoard = scan.nextLine();
                                            switch (EcBoard.toUpperCase()) {
                                                case "Y":
                                                    EhasCBoard = true;
                                                    EcCont = true;
                                                    break;
                                                case "N":
                                                    EhasCBoard = false;
                                                    EcCont = true;
                                                    break;
                                                default:
                                                    System.out.println("Chalk"
                                                            + "board input not"
                                                            + " recognized,"
                                                            + " try again: ");
                                                    break;
                                            }
                                        } while (EcCont == false);
                                        try {
                                            building.removeClassroom
                                                (editedRoomNum);
                                            Classroom newClassroom = 
                                                    new Classroom
                                 (EhasWBoard, EhasCBoard, EseatNum, EAVEquip);
                                            building.addClassroom
                                                (editedRoomNum, newClassroom);
                                        } catch (Exception e) {
                                            System.out.println("ERROR:"
                                                    + " Input error");
                                        }
                                        System.out.println(editedRoomNum +
                                                " edited.");
                                    } else {
                                        System.out.println("Room not found.");
                                    }
                                    break;
                                case "B": //Return to menu
                                    quitEditBuilding = true;
                                    break;
                                default:
                                    System.out.println("Input not recognized,"
                                            + " try again.");
                                    break;
                            }
                        } while (quitEditBuilding == false);
                    } else {
                        System.out.println("Building not found.");
                    }
                    break;
                case "F": //Find room by name and print the details
                    System.out.print("Please enter room name: ");
                    String F_input = scan.nextLine();
                    boolean classFound = campus.findClassroom(F_input);
                    if (classFound == true) {

                    } else {
                        System.out.println("Classroom not found.");
                    }
                    break;
                case "S": //Search
                    boolean quitSearch = false;
                    do {
                        System.out.println("Options:");
                        System.out.println("\tB) Rooms w/ Blackboard");
                        System.out.println("\tW) Rooms w/ Whiteboard");
                        System.out.println("\tA) Rooms w/ AV Equipment");

                        System.out.print("Please select option: ");
                        String S_input = scan.nextLine();

                        switch (S_input.toUpperCase()) {
                            case "B": //Search for Chalkboards
                                System.out.println
                                (campus.findChalkboardRooms());
                                quitSearch = true;
                                break;
                            case "W": //Search for Whiteboards
                                quitSearch = true;
                                System.out.println
                                (campus.findWhiteboardRooms());
                                break;
                            case "A": //Search for AV Equipment
                                quitSearch = true;
                                System.out.print("Please enter a keyword: ");
                                String findAVEList = scan.nextLine();
                                System.out.println
                                (campus.findAVEListRooms(findAVEList));
                                break;
                            default:
                                System.out.println("Input not revognized,"
                                        + " try again.");
                                break;
                        }

                    } while (quitSearch == false);
                    break;
                case "C": //List buildings on campus and their respective rooms
                    campus.printBuildings();
                    break;
                case "L": //List summary of building
                    System.out.print("Please enter building name: ");
                    String L_input = scan.nextLine();
                    campus.printBuildingDetails(L_input);
                    break;
                case "Q": //Quit program
                    System.out.println(" S - Save");
                    System.out.println(" D - Don't Save");
                    System.out.println(" Enter - Cancel");

                    System.out.print("Please select an option: ");
                    String Q_input = scan.nextLine();

                    switch (Q_input.toUpperCase()) {
                        case "S":
                            exit = true;
                            storage.saveFile();
                            break;
                        case "D":
                            exit = true;
                            System.out.println("File not saved!");
                            break;
                        default :
                            System.out.println("Input not recognized, "
                                    + "returning to Menu.");
                            break;
                    }                    
                    break;
                default:
                    System.out.println("Input not recognized, try again");
                    break;
            }
        } while (exit == false);
        System.out.println("Have a good day!");
    }
}
