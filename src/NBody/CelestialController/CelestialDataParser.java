package NBody.CelestialController;
/*
    This class is the controller of the application.  It reads data from the specified source (which is a file in this case) and
    populates model objects, which are then displayed on the UI.  This class has a custom CSV parser that is specific to the format
    which was specified in requirements section of the rubric: first two lines are metadata about the file (list type + scale factor)
    and the rest of the file are CSV records of celestials
*/

import NBody.CelestialModel.Celestial;
import NBody.CelestialModel.Container;
import NBody.CelestialModel.ContainerArrayList;
import NBody.CelestialModel.ContainerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CelestialDataParser {
    private Container<Celestial> celestialRecords; //Container of celestial objects
    private Container<Object> celestialMetaData; //Container of metadata for the celestials (list type + scale factor)
    /*
        * PARAMS: String fileName - the name of the file to be read
        * DESCRIPTION: constructs a new CelestialDataParser object, which in turn populates a list of celestials and
                       the metadata associated with the file
        * RETURN: none
    */
    public CelestialDataParser(String fileName) throws Exception {
        constructCelestialData(fileName);
    }

    //GETTERS

    public Container<Celestial> getCelestialRecords() {
        return this.celestialRecords;
    }
    public Container<Object> getCelestialMetaData() {
        return this.celestialMetaData;
    }
    /*
        * PARAMS: String fileName - the file to be read
        * DESCRIPTION: reads the file and constructs a list of metadata for the data and a list of celestial objects
                       from the CSV records
        * RETURN: none
    */
    private void constructCelestialData(String fileName) throws FileNotFoundException, Exception {
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        //counter variable to get first two lines of input(first two lines are metadata)
        int counter = 0;
        //initialize a factory object to create either a LL or ArrayList (based on specification from file)
        ContainerFactory<Celestial> containerFactory = new ContainerFactory<>();
        //initialize Container for the metadata from the file
        this.celestialMetaData = new ContainerArrayList<>(2);
        //initialize hashmap that will be used to load a single CSV record (keys of the hashmap will be the static 'FIELDS' data member of the Celestial class)
        HashMap<String, String> data = new HashMap<>();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            //skip empty lines
            if(line.trim().length() == 0) {
                continue;
            }
            //if counter < 2, then parser still reading first two lines with inputs as metadata
            if(counter < 2) {
                if(counter == 0) {
                    //first line with input is expected to be an indicator of the type of data structure to use (LL or ArrayList)
                    this.celestialRecords = containerFactory.getContainer(line.trim());
                }
                //keep metadata in an array
                this.celestialMetaData.add(line);
                counter++;
            }
            //once first two lines with input have been read, the parser expects the rest to be CSV records of celestials
            else {
                //initialize string to get values from CSV record
                String temp = "";
                //counter variable to keep track of which field is being added
                int n = 0;
                //loop through the line and put non comma values into a hashmap
                for (int i = 0; i < line.length(); i++) {
                    if(n >= Celestial.FIELDS.length) {
                        throw new Exception("Celestial CSV record has too many values. Must have 7 per record");
                    }
                    //if char is a non comma value, then append it to the current string
                    if (line.charAt(i) != ',') {
                        temp = temp + line.charAt(i);
                    }
                    else {
                        //if comma has been reached, put the current value of 'temp' to the hashmap with its corresponding key (based on static 'FIELDS' data member)
                        if(temp.trim().length() != 0) {
                            data.put(Celestial.FIELDS[n], temp);
                        }
                        //reset temp string to get the next value from the CSV record
                        temp = "";
                        n++; //increment to keep track of which key to add next to the hashmap
                    }
                }
                //put last value obtained from CSV record into hashmap
                data.put(Celestial.FIELDS[Celestial.FIELDS.length - 1], temp);
                //construct celestial from the hashmap
                Celestial c = this.constructCelestial(data);
                //add the new celestial to the Container of celestials (which will be passed to the view portion of the application later on)
                this.celestialRecords.add(c);
            }
        }
        //close scanner
        s.close();
    }
    /*
        * PARAMS: Hashmap<String, String> data - a hashmap containing the data for a celestial object
        * DESCRIPTION: constructs a celestial object based on the hashmap that was sent in as a parameter
        * RETURN: Celestial - the newly constructed celestial object
    */
    private Celestial constructCelestial(HashMap<String, String> data) throws NumberFormatException, Exception {
        String name = data.get("name");
        double mass = Double.parseDouble(data.get("mass"));
        double x = Double.parseDouble(data.get("x"));
        double y = Double.parseDouble(data.get("y"));
        double xVel = Double.parseDouble(data.get("xVel"));
        double yVel = Double.parseDouble(data.get("yVel"));
        double size = Double.parseDouble(data.get("size"));
        return new Celestial(name, mass, x, y, xVel, yVel, size, Double.parseDouble((String) this.celestialMetaData.get(1)));
    }
}
