import java.io.*;
import java.util.*;

/**
 * Machine Problems: MP06 (Unique), MP07 (Sort), MP08 (Filter)
 * This program reads a CSV file and performs specific data operations.
 */
public class DatasetProcessing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for CSV dataset file path
        System.out.print("Enter the full path to the CSV dataset file: ");
        String filePath = scanner.nextLine();
        
        // CLEANUP: Remove double quotes if the user used "Copy as Path" or dragged the file
        filePath = filePath.replace("\"", "");

        List<String[]> dataset = new ArrayList<>();
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Split by comma while ignoring commas inside quotes
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                if (isFirstLine) {
                    headers = values;
                    isFirstLine = false;
                } else {
                    dataset.add(values);
                }
            }
            
            if (dataset.isEmpty()) {
                System.out.println("The file is empty or contains no data.");
                return;
            }

            // --- MP06: Display Unique Values ---
            runMP06(dataset, headers);

            // --- MP07: Sort Records Alphabetically ---
            runMP07(dataset, headers);

            // --- MP08: Filter Records by Keyword ---
            // Pass the existing scanner to avoid closing System.in prematurely
            runMP08(dataset, scanner);

        } catch (FileNotFoundException e) {
            System.err.println("Error: The file was not found. Check if the path is correct: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\n--- End of Program ---");
        }
    }

    /**
     * MP06 - Unique Values
     */
    private static void runMP06(List<String[]> data, String[] headers) {
        System.out.println("\n[MP06] Unique Values in Column: " + (headers != null ? headers[0] : "Col 1"));
        Set<String> uniqueValues = new HashSet<>();
        for (String[] row : data) {
            if (row.length > 0) uniqueValues.add(row[0].trim());
        }
        uniqueValues.forEach(val -> System.out.println(" - " + val));
    }

    /**
     * MP07 - Sort Records
     */
    private static void runMP07(List<String[]> data, String[] headers) {
        System.out.println("\n[MP07] Records Sorted Alphabetically (Top 5 Displayed):");
        data.sort((a, b) -> a[0].compareToIgnoreCase(b[0]));
        displayTable(data.subList(0, Math.min(5, data.size())));
    }

    /**
     * MP08 - Filter Records by Keyword
     * Logic: Iterates through each row and checks if any column contains the keyword.
     */
    private static void runMP08(List<String[]> data, Scanner sc) {
        System.out.print("\n[MP08] Enter keyword to filter the dataset: ");
        String keyword = sc.nextLine().toLowerCase();

        System.out.println("Filtering results for: \"" + keyword + "\"");
        System.out.println("-------------------------------------------------");
        
        boolean found = false;
        for (String[] row : data) {
            // Join all columns into one string to search across the whole row
            String rowContent = String.join(" ", row).toLowerCase();
            
            if (rowContent.contains(keyword)) {
                System.out.println(Arrays.toString(row));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No records found matching that keyword.");
        }
    }

    private static void displayTable(List<String[]> rows) {
        for (String[] row : rows) {
            System.out.printf("%-20s | %-20s\n", 
                row[0], (row.length > 1 ? row[1] : "N/A"));
        }
    }
}