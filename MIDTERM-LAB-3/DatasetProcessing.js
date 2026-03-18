/**
 * Machine Problems: MP06 (Unique), MP07 (Sort), MP08 (Filter)
 * This program reads a CSV file and performs specific data operations.
 */

const fs = require('fs');
const readline = require('readline');

// Initialize the terminal interface
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Start Program: Prompt user for CSV dataset file path
rl.question('Enter the full path to the CSV dataset file: ', (filePath) => {
    
    // Dataset handling: Remove double quotes (common when dragging files into terminal)
    const cleanPath = filePath.replace(/['"]/g, '').trim();

    try {
        // Read file using fs.readFileSync
        const fileContent = fs.readFileSync(cleanPath, 'utf8');
        const lines = fileContent.split(/\r?\n/);

        if (lines.length === 0 || (lines.length === 1 && lines[0] === "")) {
            console.log("The file is empty or contains no data.");
            rl.close();
            return;
        }

        // Processing logic: Parse CSV lines
        // Regex handles commas inside quotes: "New York, NY" remains one column
        const headers = lines[0].split(/,(?=(?:(?:[^"]*"){2})*[^["]*$)/);
        const dataset = lines.slice(1)
            .filter(line => line.trim() !== "") // Skip empty rows
            .map(line => line.split(/,(?=(?:(?:[^"]*"){2})*[^"]*$)/));

        // --- MP06: Display Unique Values ---
        runMP06(dataset, headers);

        // --- MP07: Sort Records Alphabetically ---
        runMP07(dataset);

        // --- MP08: Filter Records by Keyword ---
        runMP08(dataset, rl);

    } catch (err) {
        console.error("\nError: The file was not found or could not be read.");
        console.error("Path provided: " + cleanPath);
        rl.close();
    }
});

/**
 * MP06 - Extracts and displays unique values from the first column.
 */
function runMP06(data, headers) {
    const colName = headers ? headers[0] : "First Column";
    console.log(`\n[MP06] Unique Values in Column: ${colName}`);
    
    const uniqueValues = [...new Set(data.map(row => row[0]?.trim()))];
    uniqueValues.forEach(val => {
        if (val) console.log(` - ${val}`);
    });
}

/**
 * MP07 - Sorts the dataset alphabetically based on the first column.
 */
function runMP07(data) {
    console.log("\n[MP07] Records Sorted Alphabetically (Top 5 Displayed):");
    
    // Clone and sort the data
    const sortedData = [...data].sort((a, b) => {
        if (!a[0] || !b[0]) return 0;
        return a[0].toLowerCase().localeCompare(b[0].toLowerCase());
    });
    
    // Display top 5 as a simple formatted table
    sortedData.slice(0, 5).forEach(row => {
        console.log(`${(row[0] || "N/A").padEnd(20)} | ${(row[1] || "N/A")}`);
    });
}

/**
 * MP08 - Filters rows that contain a specific keyword.
 */
function runMP08(data, rl) {
    rl.question('\n[MP08] Enter keyword to filter the dataset: ', (keyword) => {
        const search = keyword.toLowerCase();
        console.log(`\nFiltering results for: "${keyword}"`);
        console.log("-------------------------------------------------");
        
        const matches = data.filter(row => 
            row.join(" ").toLowerCase().includes(search)
        );

        if (matches.length > 0) {
            matches.forEach(row => console.log(JSON.stringify(row)));
        } else {
            console.log("No records found matching that keyword.");
        }

        console.log("\n--- End of Program ---");
        rl.close();
    });
}