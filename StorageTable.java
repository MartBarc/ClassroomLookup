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
 *      This class saves the data from the RoomLookup session. So when the
 * program is terminated the data can be loaded into the next save file. To
 * save data, use the saveFile() method, and to load data use the loadFile()
 * method. Setter and getter methods are also present to transfer the saveFile
 * data to the saved Campus object.
 * ----------------------------------------------------------------------------
 */

import java.io.*;

public class StorageTable implements Serializable{
    
    /**
     * Initialize variables.
     */
    Campus saveFile;
    
    /**
     * Constructs the table.
     * @param input Campus file to be stored in the object
     */
    public StorageTable(Campus input) {
        saveFile = input;
    }
    
    /**
     * Saves the instance of the Campus saveFile.
     */
    public void saveFile() {
        try {
            FileOutputStream fileOutput = new FileOutputStream("storage.obj");
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            output.writeObject(saveFile);
            output.close();
            fileOutput.close();
            System.out.println("File hsa been saved!");
        } catch (IOException e) {
            System.out.println("Saveed file failed to save.");
        }
    }
    
    /**
     * Loads the instanced Campus saveFile from the storage.obj file.
     * @return true if file is found, false otherwise
     */
    public boolean loadFile() {
        try {
            FileInputStream fileInput = new FileInputStream("storage.obj");
            ObjectInputStream input = new ObjectInputStream(fileInput);
            saveFile = (Campus) input.readObject();
            input.close();
            fileInput.close();
            System.out.println("File has been found!");
            return true;
        }
        catch (Exception e){
            System.out.println("File not found.");
            return false;
        }
    }
    
    /**
     * Gets the saveFile.
     * @return saveFile
     */
    public Campus getSaveState() {
        return saveFile;
    }
    
    /**
     * Sets the saveFile from a Campus input.
     * @param input Campus object to be stored in the class
     */
    public void setSaveState(Campus input) {
        saveFile = input;
    }
    
}
