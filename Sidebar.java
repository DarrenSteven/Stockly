import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sidebar extends JPanel {
    private JFrame currentPage; 

    public Sidebar() {
        setPreferredSize(new Dimension(250, getHeight())); 
        setBackground(Color.LIGHT_GRAY);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon logoIcon = new ImageIcon("assets/stockly.png");
        Image logoImage = logoIcon.getImage();
        Image scaledLogoImage = logoImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
        JLabel logoLabel = new JLabel(scaledLogoIcon);
        JLabel stocklyLabel = new JLabel("Stockly");
        stocklyLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setPreferredSize(new Dimension(250, 150));
        logoPanel.add(logoLabel);
        logoPanel.add(stocklyLabel);

        JButton[] navigationButtons = new JButton[6];
        String[] buttonLabels = {"List Produk", "List Pembelian", "List Penjualan", "Laporan Penjualan", "Laporan Pembelian", "Kartu Stock"};
        ImageIcon[] buttonIcons = {new ImageIcon("assets/list_produk.png"), new ImageIcon("assets/pembelian.png"), new ImageIcon("assets/penjualan.png"), new ImageIcon("assets/laporan_jual.png"), new ImageIcon("assets/laporan_beli.png"), new ImageIcon("assets/stock.png")};

        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.WHITE);
        navigationPanel.setLayout(new GridLayout(6, 1));
        navigationPanel.setPreferredSize(new Dimension(250, 300));

        ActionListener navigationListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonText = button.getText();
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
                }
                
                SwingUtilities.getWindowAncestor(Sidebar.this).dispose();
            }
        };

        for (int i = 0; i < 6; i++) {
            navigationButtons[i] = new JButton(buttonLabels[i], buttonIcons[i]);
            navigationButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            navigationButtons[i].setHorizontalAlignment(SwingConstants.LEFT);
            navigationButtons[i].setIconTextGap(10); 
            navigationButtons[i].addActionListener(navigationListener); 
            navigationPanel.add(navigationButtons[i]);
        }

        ImageIcon logoutIcon = new ImageIcon("assets/logout.png");
        Image scaledLogoutImage = logoutIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        ImageIcon scaledLogoutIcon = new ImageIcon(scaledLogoutImage);
        JButton logoutButton = new JButton("Logout", scaledLogoutIcon);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Window window : Window.getWindows()) {
                    window.dispose();
                }
                new LoginPage(); 
            }
        });

        JPanel logoutPanel = new JPanel();
        logoutPanel.setBackground(Color.WHITE);
        logoutPanel.setPreferredSize(new Dimension(250, 50));
        logoutPanel.add(logoutButton);

        add(logoPanel);
        add(navigationPanel);
        add(logoutPanel);
    }
}
