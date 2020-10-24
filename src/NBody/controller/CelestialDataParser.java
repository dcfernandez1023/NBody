package NBody.controller;

import NBody.model.Celestial;
import NBody.model.Container;
import NBody.model.ContainerFactory;

import java.io.File;
import java.io.FileNotFoundException;
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
                Container<Object> data = containerFactory.getContainer("arraylist", 7);
                String temp = "";
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ',') {
                        temp = temp + line.charAt(i);
                    }
                    else {
                        data.add(temp);
                        temp = "";
                    }
                }
                data.add(temp);
                Celestial celestial = new Celestial(data);
                this.celestialRecords.add(celestial);
            }
        }
        s.close();
    }
}
