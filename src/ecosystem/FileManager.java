package ecosystem;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {

    public Config readConfig() throws Exception {
        int generations = 0;
        int startingPopulation = 0;
        double mutationRate = 0.0;
        int carryingCapacity = 0;
        int plantGrowthRate = 0;
        // Implementation for reading structured data from a file would go here
        String filename = "config.txt";

        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new java.io.File("data" + File.separator + "Input" + File.separator + filename));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File could not be opened.");
            return null;
        }


        // do not skip the first line; process all lines including the first
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split("=");
            if (parts.length == 2) {
                if (parts[0].equals("generations")) {
                    generations = Integer.parseInt(parts[1].trim());
                } else if (parts[0].equals("startingPopulation")) {
                    startingPopulation = Integer.parseInt(parts[1].trim());
                } else if (parts[0].equals("mutationRate")) {
                    mutationRate = Double.parseDouble(parts[1].trim());
                } else if (parts[0].equals("carryingCapacity")) {
                    carryingCapacity = Integer.parseInt(parts[1].trim());
                } else if (parts[0].equals("plantGrowthRate")) {
                    plantGrowthRate = Integer.parseInt(parts[1].trim());
                }

            }
        }
        fileScanner.close();

        return new Config(generations, startingPopulation, mutationRate, carryingCapacity, plantGrowthRate);
    }

    public void writeLog(String message) {

        String filename = "ecosystem_log.txt";

        // GURARENTEED: A File object has been opened that previously did not exist
        try {
            FileWriter writer = new FileWriter("data" + File.separator + "Output" + File.separator + filename,true);

            // Add a second parameter (true) if you want to open file in append mode
            writer.write(message + "\n");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
