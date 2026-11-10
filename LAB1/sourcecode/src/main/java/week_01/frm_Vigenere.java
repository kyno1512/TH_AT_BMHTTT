package week_01;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class frm_Vigenere extends JFrame {

    public JTextArea txt_plaintext;
    public JTextField txt_key;
    public JTextArea txt_ciphertext;
    public JButton btn_encrypt;
    public JButton btn_decrypt;
    public JButton btn_open;
    public JButton btn_save;
    public JLabel lbl_status;

    public frm_Vigenere() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Vigenere Cipher");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(650, 480);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 12, 6, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel lblHeader = new JLabel("MẬT MÃ VIGENÈRE");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblHeader, gbc);

        // Plaintext label
        JLabel lblPlain = new JLabel("Plaintext:");
        gbc.gridy = 1; gbc.gridwidth = 2;
        mainPanel.add(lblPlain, gbc);

        // Plaintext area
        txt_plaintext = new JTextArea(3, 40);
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setWrapStyleWord(true);
        JScrollPane scrollPlain = new JScrollPane(txt_plaintext);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 0.3;
        mainPanel.add(scrollPlain, gbc);

        // Key row
        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        keyPanel.add(new JLabel("Key:"));
        txt_key = new JTextField("KEY", 15);
        keyPanel.add(txt_key);
        keyPanel.add(new JLabel("Chỉ gồm chữ A-Z, ví dụ KEY"));
        gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 0; gbc.weighty = 0;
        mainPanel.add(keyPanel, gbc);

        // Ciphertext label
        JLabel lblCipher = new JLabel("Ciphertext:");
        gbc.gridy = 4;
        mainPanel.add(lblCipher, gbc);

        // Ciphertext area
        txt_ciphertext = new JTextArea(3, 40);
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setWrapStyleWord(true);
        JScrollPane scrollCipher = new JScrollPane(txt_ciphertext);
        gbc.gridy = 5; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 0.3;
        mainPanel.add(scrollCipher, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btn_encrypt = new JButton("Encrypt");
        btn_decrypt = new JButton("Decrypt");
        btn_open = new JButton("Open Ciphertext File");
        btn_save = new JButton("Save Ciphertext File");
        btnPanel.add(btn_encrypt);
        btnPanel.add(btn_decrypt);
        btnPanel.add(btn_open);
        btnPanel.add(btn_save);

        gbc.gridy = 6; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 0; gbc.weighty = 0;
        mainPanel.add(btnPanel, gbc);

        // Status
        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridy = 7;
        mainPanel.add(lbl_status, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Events
        btn_encrypt.addActionListener(evt -> {
            String plaintext = txt_plaintext.getText();
            if (plaintext.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
                return;
            }
            try {
                String key = txt_key.getText().trim();
                txt_ciphertext.setText(VigenereCipher.encrypt(plaintext, key));
                lbl_status.setText("Đã mã hóa Vigenère với khóa " + key + ".");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_decrypt.addActionListener(evt -> {
            String ciphertext = txt_ciphertext.getText();
            if (ciphertext.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
                return;
            }
            try {
                String key = txt_key.getText().trim();
                txt_plaintext.setText(VigenereCipher.decrypt(ciphertext, key));
                lbl_status.setText("Đã giải mã Vigenère với khóa " + key + ".");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_open.addActionListener(evt -> {
            String path = FileTextIO.openText(this, txt_ciphertext);
            if (path != null) {
                lbl_status.setText("Đã mở tệp: " + path);
            }
        });

        btn_save.addActionListener(evt -> {
            String path = FileTextIO.saveText(this, txt_ciphertext, "vigenere_ciphertext.txt");
            if (path != null) {
                lbl_status.setText("Đã lưu tệp: " + path);
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Vigenere().setVisible(true));
    }
}
