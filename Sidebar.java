import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sidebar extends JPanel {
    private JFrame currentPage; // Menyimpan referensi ke halaman saat ini

    public Sidebar() {
        setPreferredSize(new Dimension(250, getHeight())); // Set sidebar width to 250 pixels
        setBackground(Color.LIGHT_GRAY);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Logo and Stockly text
        JLabel logoLabel = new JLabel(new ImageIcon("logo.png")); // Change "logo.png" to your logo file path
        JLabel stocklyLabel = new JLabel("Stockly");
        stocklyLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setPreferredSize(new Dimension(250, 150));
        logoPanel.add(logoLabel);
        logoPanel.add(stocklyLabel);

        // Navigation buttons
        JButton[] navigationButtons = new JButton[6];
        String[] buttonLabels = {"List Produk", "List Pembelian", "List Penjualan", "Laporan Penjualan", "Laporan Pembelian", "Kartu Stock"};

        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.WHITE);
        navigationPanel.setLayout(new GridLayout(6, 1));
        navigationPanel.setPreferredSize(new Dimension(250, 300));

        // ActionListener for navigation buttons
        ActionListener navigationListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonText = button.getText();

                // Redirect to corresponding pages
                switch (buttonText) {
                    case "List Produk":
                        new StockPage().setVisible(true);
                        break;
                    case "List Pembelian":
                        new PurchaseListPage().setVisible(true);
                        break;
                    case "List Penjualan":
                        new SalesListPage().setVisible(true);
                        break;
                    case "Kartu Stock":
                        new StockCardFrame().setVisible(true);
                        break;
                    case "Laporan Pembelian":
                        new PurchaseReportFrame().setVisible(true);
                        break;
                    case "Laporan Penjualan":
                        new SalesReportFrame().setVisible(true);
                        break;
                    // Add cases for other buttons if needed
                }
                
                // Tutup frame yang sebelumnya terbuka
                SwingUtilities.getWindowAncestor(Sidebar.this).dispose();
            }
        };

        for (int i = 0; i < 6; i++) {
            navigationButtons[i] = new JButton(buttonLabels[i]);
            navigationButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            navigationButtons[i].addActionListener(navigationListener); // Add ActionListener to each button
            navigationPanel.add(navigationButtons[i]);
        }

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close all frames and open the login frame again
                for (Window window : Window.getWindows()) {
                    window.dispose();
                }
                new LoginPage(); // Change to the name of your login page class
            }
        });
        JPanel logoutPanel = new JPanel();
        logoutPanel.setBackground(Color.WHITE);
        logoutPanel.setPreferredSize(new Dimension(250, 50));
        logoutPanel.add(logoutButton);

        // Add components to sidebar
        add(logoPanel);
        add(navigationPanel);
        add(logoutPanel);
    }
}
