package NBody.CelestialController;

import NBody.CelestialModel.Celestial;
import NBody.CelestialModel.Container;
import NBody.CelestialModel.ContainerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class CelestialDataParser {
    private Container<Object> celestialRecords;
    private Container<Object> celestialMetaData;

    public CelestialDataParser(String fileName) throws Exception {
        constructCelestialData(fileName);
    }
    public Container<Object> getCelestialRecords() {
        return this.celestialRecords;
    }
    public Container<Object> getCelestialMetaData() {
        return this.celestialMetaData;
    }

    private void constructCelestialData(String fileName) throws FileNotFoundException, Exception {
        String csvString = "";
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        int counter = 0;
        ContainerFactory<Object> containerFactory = new ContainerFactory<>();
        this.celestialMetaData = containerFactory.getContainer("arraylist", 2);
        HashMap<String, String> data = new HashMap<>();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            if(line.trim().length() == 0) {
                continue;
            }
            if(counter < 2) {
                if(counter == 0) {
                    this.celestialRecords = containerFactory.getContainer(line);
                }
                this.celestialMetaData.add(line);
                counter++;
            }
            else {
                String temp = "";
                int n = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ',') {
                        temp = temp + line.charAt(i);
                    }
                    else {
                        data.put(Celestial.FIELDS[n], temp);
                        temp = "";
                        n++;
                    }
                }
                data.put(Celestial.FIELDS[Celestial.FIELDS.length-1], temp);
                Celestial c = this.constructCelestial(data);
                this.celestialRecords.add(c);
            }
        }
        s.close();
    }
    private Celestial constructCelestial(HashMap<String, String> data) throws NumberFormatException {
        String name = data.get("name");
        double mass = Double.parseDouble(data.get("mass"));
        double x = Double.parseDouble(data.get("x"));
        double y = Double.parseDouble(data.get("y"));
        double xVel = Double.parseDouble(data.get("xVel"));
        double yVel = Double.parseDouble(data.get("yVel"));
        double size = Double.parseDouble(data.get("size"));
        return new Celestial(name, mass, x, y, xVel, yVel, size);
    }
}
