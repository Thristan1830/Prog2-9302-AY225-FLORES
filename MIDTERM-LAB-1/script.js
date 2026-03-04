document.getElementById('csv-upload').addEventListener('change', function(e) {
    const file = e.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    
    reader.onload = function(event) {
        const content = event.target.result;
        processData(content);
    };

    reader.readAsText(file);
});

function processData(csvText) {
    const lines = csvText.split('\n');
    const productSales = new Map();
    let totalRevenue = 0;

    lines.forEach(line => {
        const parts = line.split(',');
        if (parts.length < 3) return;

        const name = parts[1]?.trim();
        const sales = parseFloat(parts[2]?.trim());

        if (name && !isNaN(sales)) {
            const currentTotal = productSales.get(name) || 0;
            productSales.set(name, currentTotal + sales);
            totalRevenue += sales;
        }
    });

    if (productSales.size === 0) {
        alert("No valid data found in CSV.");
        return;
    }

    const average = totalRevenue / productSales.size;

    // Update UI Stats
    document.getElementById('avg-threshold').textContent = `$${average.toFixed(2)}`;
    document.getElementById('product-count').textContent = productSales.size;

    // Generate Report
    let report = `${"PRODUCT NAME".padEnd(30)} | ${"TOTAL SALES".padEnd(15)} | STATUS\n`;
    report += "-".repeat(65) + "\n";

    productSales.forEach((sales, name) => {
        if (sales < average) {
            report += `${name.padEnd(30)} | $${sales.toFixed(2).padEnd(14)} | [FLAGGED]\n`;
        }
    });

    document.getElementById('output-area').textContent = report;
}