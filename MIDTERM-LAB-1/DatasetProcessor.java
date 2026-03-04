import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

public class DatasetProcessor extends JFrame {

    // Theme Colors
    private final Color BG_DARK = new Color(28, 30, 34);
    private final Color CARD_BG = new Color(45, 49, 54);
    private final Color ACCENT_BLUE = new Color(0, 122, 255);
    private final Color TEXT_PRIMARY = new Color(240, 240, 240);
    private final Color TEXT_SECONDARY = new Color(170, 180, 190);

    private JTextArea outputArea;
    private JLabel avgStatValue;
    private JLabel productCountValue;

    public DatasetProcessor() {
        setupWindow();
        initUI();
    }

    private void setupWindow() {
        setTitle("Inventory Analytics | Dataset Processor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
    }

    private void initUI() {
        setLayout(new BorderLayout(20, 20));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Product Performance Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(TEXT_PRIMARY);
        
        JButton importBtn = createStyledButton("Import CSV Data");
        importBtn.addActionListener(this::handleFileSelection);

        header.add(title, BorderLayout.WEST);
        header.add(importBtn, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        JPanel dashboard = new JPanel(new BorderLayout(0, 20));
        dashboard.setOpaque(false);

        JPanel statsRow = new JPanel(new GridLayout(1, 2, 20, 0));
        statsRow.setOpaque(false);
        avgStatValue = addStatCard(statsRow, "PERFORMANCE THRESHOLD (AVG)", "$0.00");
        productCountValue = addStatCard(statsRow, "TOTAL UNIQUE PRODUCTS", "0");
        dashboard.add(statsRow, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setBackground(CARD_BG);
        outputArea.setForeground(TEXT_PRIMARY);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(new LineBorder(CARD_BG.brighter(), 1));
        dashboard.add(scroll, BorderLayout.CENTER);

        add(dashboard, BorderLayout.CENTER);
    }

    private JLabel addStatCard(JPanel container, String title, String value) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_BG);
        card.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel tLabel = new JLabel(title);
        tLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        tLabel.setForeground(TEXT_SECONDARY);
        
        JLabel vLabel = new JLabel(value);
        vLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        vLabel.setForeground(ACCENT_BLUE);

        card.add(tLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(vLabel);
        container.add(card);
        return vLabel;
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(ACCENT_BLUE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return btn;
    }

    private void handleFileSelection(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            analyzeInventory(chooser.getSelectedFile());
        }
    }

    private void analyzeInventory(File file) {
        Map<String, Double> productSales = new LinkedHashMap<>();
        double totalRevenue = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                try {
                    String name = parts[1].trim();
                    double val = Double.parseDouble(parts[2].trim());
                    productSales.put(name, productSales.getOrDefault(name, 0.0) + val);
                    totalRevenue += val;
                } catch (NumberFormatException ignored) {} 
            }

            if (productSales.isEmpty()) {
                showError("No valid data found.");
                return;
            }

            double avg = totalRevenue / productSales.size();
            avgStatValue.setText(String.format("$%.2f", avg));
            productCountValue.setText(String.valueOf(productSales.size()));

            outputArea.setText("");
            outputArea.append(String.format("%-35s | %-15s | %-10s\n", "PRODUCT NAME", "TOTAL SALES", "ACTION"));
            outputArea.append("----------------------------------------------------------------------\n");

            productSales.forEach((name, sales) -> {
                if (sales < avg) {
                    outputArea.append(String.format("%-35s | $%-14.2f | [FLAGGED]\n", name, sales));
                }
            });

        } catch (Exception ex) {
            showError("Error: " + ex.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Fixed main method and closing braces
    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch (Exception ignored) {}
        
        SwingUtilities.invokeLater(() -> {
            new DatasetProcessor().setVisible(true);
        });
    }
} // Final closing brace for the class