import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddProductFrame extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField; // JTextField untuk jumlah
    private JTextField unitField; // JTextField untuk satuan

    public AddProductFrame() {
        setTitle("Tambahkan Produk yang Akan Dijual"); // Judul frame
        setSize(400, 300); // Mengubah ukuran frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Tambahkan padding

        // Panel untuk judul
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Tambahkan Produk yang Akan Dijual");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(titleLabel);

        // Panel untuk input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5)); // Grid layout dengan 5 baris, 2 kolom, jarak vertikal 5px, horizontal 5px

        JLabel idLabel = new JLabel("ID:");
        inputPanel.add(idLabel);
        idField = new JTextField(15);
        idField.setPreferredSize(new Dimension(200, 25)); // Atur tinggi JTextField
        inputPanel.add(idField);

        JLabel nameLabel = new JLabel("Nama Barang:");
        inputPanel.add(nameLabel);
        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(200, 25)); // Atur tinggi JTextField
        inputPanel.add(nameField);

        JLabel priceLabel = new JLabel("Harga Jual:");
        inputPanel.add(priceLabel);
        priceField = new JTextField(15);
        priceField.setPreferredSize(new Dimension(200, 25)); // Atur tinggi JTextField
        inputPanel.add(priceField);

        // Label dan JTextField untuk jumlah
        JLabel quantityLabel = new JLabel("Jumlah:");
        inputPanel.add(quantityLabel);
        quantityField = new JTextField(15);
        quantityField.setPreferredSize(new Dimension(200, 25)); // Atur tinggi JTextField
        inputPanel.add(quantityField);

        // Label dan JTextField untuk satuan
        JLabel unitLabel = new JLabel("Satuan:");
        inputPanel.add(unitLabel);
        unitField = new JTextField(15);
        unitField.setPreferredSize(new Dimension(200, 25)); // Atur tinggi JTextField
        inputPanel.add(unitField);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Batal");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tutup frame pop-up
            }
        });
        buttonPanel.add(cancelButton);

        JButton addButton = new JButton("Tambah");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle logic to add product
                // For now, let's just close the pop-up frame
                dispose();
            }
        });
        buttonPanel.add(addButton);

        // Tambahkan komponen ke mainPanel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tambahkan mainPanel ke frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddProductFrame();
    }
}
