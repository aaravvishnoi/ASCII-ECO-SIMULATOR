import java.io.File;
import java.util.Scanner;

public class StructureFileReader {
    public static void main(String[] args) throws Exception {
        // read an arraylist of StockItem objects from a file and print them to the
        // console
        // Implementation for reading structured data from a file would go here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name to read from: ");
        String filename = "config.txt";
        scanner.close();

        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new java.io.File("data" + File.separator + "input" + File.separator + filename));
        } catch (Exception e) {

        }
        // do not skip the first line; process all lines including the first
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split("=");
            if (parts.length == 2) {
                if (parts[0].equals("generations")) {
                    int generation = Integer.parseInt(parts[1].trim());

                }
                int startingPopulation = Integer.parseInt(parts[1].trim());
                double mutation_rate = Double.parseDouble(parts[2].trim());
                int carryingCapacity = Integer.parseInt(parts[3].trim());
                int plantGrowthRate = Integer.parseInt(parts[4].trim());
                System.out.println("");
            }
        }
        fileScanner.close();
    }
}
