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

public class frm_Playfair extends JFrame {
    public JTextArea txt_plaintext;
    public JTextField txt_key;
    public JTextArea txt_matrix;
    public JButton btn_matrix;
    public JTextArea txt_ciphertext;
    public JButton btn_encrypt;
    public JButton btn_decrypt;
    public JButton btn_open;
    public JButton btn_save;
    public JLabel lbl_status;

    public frm_Playfair() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Playfair Cipher");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(650, 560);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 12, 4, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeader = new JLabel("MẬT MÃ PLAYFAIR");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblHeader, gbc);

        gbc.gridy = 1;
        mainPanel.add(new JLabel("Plaintext:"), gbc);

        txt_plaintext = new JTextArea(3, 40);
        txt_plaintext.setLineWrap(true);
        JScrollPane scrollPlain = new JScrollPane(txt_plaintext);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.2;
        mainPanel.add(scrollPlain, gbc);

        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        keyPanel.add(new JLabel("Key:"));
        txt_key = new JTextField("KEYWORD", 12);
        keyPanel.add(txt_key);
        keyPanel.add(new JLabel("Chỉ gồm chữ A-Z, ví dụ KEYWORD"));
        gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        mainPanel.add(keyPanel, gbc);

        JPanel matrixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        matrixPanel.add(new JLabel("Key Matrix:"));
        txt_matrix = new JTextArea(5, 9);
        txt_matrix.setEditable(false);
        txt_matrix.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        matrixPanel.add(new JScrollPane(txt_matrix));
        btn_matrix = new JButton("Generate Key Matrix");
        matrixPanel.add(btn_matrix);
        gbc.gridy = 4;
        mainPanel.add(matrixPanel, gbc);

        gbc.gridy = 5;
        mainPanel.add(new JLabel("Ciphertext:"), gbc);

        txt_ciphertext = new JTextArea(3, 40);
        txt_ciphertext.setLineWrap(true);
        JScrollPane scrollCipher = new JScrollPane(txt_ciphertext);
        gbc.gridy = 6; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.2;
        mainPanel.add(scrollCipher, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btn_encrypt = new JButton("Encrypt");
        btn_decrypt = new JButton("Decrypt");
        btn_open = new JButton("Open Ciphertext File");
        btn_save = new JButton("Save Ciphertext File");
        btnPanel.add(btn_encrypt);
        btnPanel.add(btn_decrypt);
        btnPanel.add(btn_open);
        btnPanel.add(btn_save);
        gbc.gridy = 7; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        mainPanel.add(btnPanel, gbc);

        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridy = 8;
        mainPanel.add(lbl_status, gbc);

        add(mainPanel, BorderLayout.CENTER);

        btn_matrix.addActionListener(evt -> {
            try {
                txt_matrix.setText(PlayfairCipher.getKeyMatrix(txt_key.getText()));
                txt_matrix.setCaretPosition(0);
                lbl_status.setText("Đã tạo ma trận khóa 5x5 (I và J dùng chung ô).");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Khóa không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_encrypt.addActionListener(evt -> {
            String plaintext = txt_plaintext.getText();
            if (plaintext.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
                return;
            }
            try {
                String key = txt_key.getText().trim();
                txt_ciphertext.setText(PlayfairCipher.encrypt(plaintext, key));
                lbl_status.setText("Đã mã hóa Playfair (bỏ dấu cách, J -> I, chèn X).");
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
                txt_plaintext.setText(PlayfairCipher.decrypt(ciphertext, key));
                lbl_status.setText("Đã giải mã Playfair (còn ký tự đệm X).");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_open.addActionListener(evt -> {
            String path = FileTextIO.openText(this, txt_ciphertext);
            if (path != null) lbl_status.setText("Đã mở tệp: " + path);
        });

        btn_save.addActionListener(evt -> {
            String path = FileTextIO.saveText(this, txt_ciphertext, "playfair_ciphertext.txt");
            if (path != null) lbl_status.setText("Đã lưu tệp: " + path);
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Playfair().setVisible(true));
    }
}
