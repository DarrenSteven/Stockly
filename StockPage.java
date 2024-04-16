import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

// Kelas placeholder untuk JTextField
class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Jika teks kosong, tampilkan placeholder
        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            int x = getInsets().left;
            int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2 + g2.getFontMetrics().getAscent();
            g2.drawString(placeholder, x, y);
            g2.dispose();
        }
    }
}

public class StockPage extends JFrame {
    public StockPage() {
        setTitle("Stockly - Stock Barang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300,850); // Set frame size
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Tambahkan padding ke frame

        // Tambahkan sidebar
        Sidebar sidebar = new Sidebar();
        mainPanel.add(sidebar, BorderLayout.WEST);

        // Tambahkan konten di kanan sidebar
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Tambahkan padding ke konten

        // Tambahkan judul
        JLabel titleLabel = new JLabel("List Produk");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Tambahkan search bar dengan placeholder
        PlaceholderTextField searchBar = new PlaceholderTextField("Search...");
        searchBar.setFont(new Font("Arial", Font.PLAIN, 14));
        searchBar.setPreferredSize(new Dimension(searchBar.getPreferredSize().width, 40)); // Atur tinggi searchBar
        searchBar.setBorder(BorderFactory.createCompoundBorder(
            searchBar.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Tambahkan padding ke search bar

        // Tambahkan posisi judul dan search bar di dalam contentPanel
        JPanel titleSearchPanel = new JPanel(new BorderLayout());
        titleSearchPanel.add(titleLabel, BorderLayout.NORTH);
        titleSearchPanel.add(searchBar, BorderLayout.SOUTH);
        contentPanel.add(titleSearchPanel, BorderLayout.NORTH);

        // Tambahkan tabel
        String[] columnNames = {"ID", "Nama Barang", "Jumlah", "Satuan", "Harga Pembelian", "Harga Penjualan", "Aksi"};
        Object[][] data = {
            {"P001", "Chocolatos", "10", "box", "Rp10.000", "Rp15.000", ""},
            {"P002", "Tango", "15", "box", "Rp20.000", "Rp25.000", ""},
            {"P003", "Momogi", "20", "box", "Rp5.000", "Rp7.000", ""}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // Override isCellEditable untuk membuat sel tidak dapat diubah
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model) {
            // Override getRowHeight untuk menyesuaikan tinggi baris
            @Override
            public int getRowHeight(int row) {
                return 40; // Atur tinggi baris
            }
        };

        // Atur alignment teks di dalam setiap sel
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Atur alignment teks untuk kolom Nama Barang
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

        // Atur renderer untuk kolom Aksi
        table.getColumnModel().getColumn(6).setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Atur padding dan ruang di sekitar tombol
            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Hapus");
            panel.add(editButton);
            panel.add(deleteButton);
            return panel;
        });

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Tambahkan tombol Tambah di pojok kanan bawah
        JButton addButton = new JButton("Tambah Produk");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tambahkan logika untuk tombol Tambah di sini (jika dibutuhkan)
                new AddProductFrame();
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
        new StockPage();
    }
}
