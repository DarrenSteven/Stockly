import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class PurchaseListPage extends JFrame {
    public PurchaseListPage() {
        setTitle("Stockly - List Pembelian");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 850); // Set frame size
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding to the frame

        // Add sidebar
        Sidebar sidebar = new Sidebar();
        mainPanel.add(sidebar, BorderLayout.WEST);

        // Add content on the right side of the sidebar
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding to the content

        // Add title
        JLabel titleLabel = new JLabel("List Pembelian");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Add search bar with placeholder
        PlaceholderTextField searchBar = new PlaceholderTextField("Search...");
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
        searchBar.setPreferredSize(new Dimension(searchBar.getPreferredSize().width, 40)); // Set the height of searchBar
        searchBar.setBorder(BorderFactory.createCompoundBorder(
                searchBar.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Add padding to the search bar

        // Add the position of title and search bar in the contentPanel
        JPanel titleSearchPanel = new JPanel(new BorderLayout());
        titleSearchPanel.add(titleLabel, BorderLayout.NORTH);
        titleSearchPanel.add(searchBar, BorderLayout.SOUTH);
        contentPanel.add(titleSearchPanel, BorderLayout.NORTH);

        // Add table
        String[] columnNames = {"Nomor", "Tanggal Pembelian", "ID Pembelian", "Supplier", "Harga", "Aksi"};
        Object[][] data = {
                {"1", "01/04/2024", "PB001", "Richard", "Rp100.000", ""},
                {"2", "02/04/2024", "PB002", "Nieven", "Rp150.000", ""},
                {"3", "03/04/2024", "PB003", "Dustin", "Rp200.000", ""}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // Override isCellEditable to make cells not editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model) {
            // Override getRowHeight to adjust row height
            @Override
            public int getRowHeight(int row) {
                return 40; // Set row height
            }
        };

        // Set text alignment in each cell to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Set renderer for the "Aksi" column
        table.getColumnModel().getColumn(5).setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Set padding and space around the buttons
            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Hapus");
            panel.add(editButton);
            panel.add(deleteButton);
            return panel;
        });

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the "Tambah" button at the bottom right
        JButton addButton = new JButton("Tambah Pembelian");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the frame to add a new product
                new AddPurchaseFrame();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new PurchaseListPage();
    }
}
