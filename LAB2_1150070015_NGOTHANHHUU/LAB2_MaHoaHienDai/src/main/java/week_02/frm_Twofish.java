package week_02;

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

public class frm_Twofish extends JFrame {

    public JTextArea txt_plaintext;
    public JTextField txt_key;
    public JTextField txt_iv;
    public JButton btn_iv;
    public JTextArea txt_ciphertext;
    public JButton btn_encrypt;
    public JButton btn_decrypt;
    public JButton btn_save;
    public JButton btn_open;
    public JLabel lbl_status;

    public frm_Twofish() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Twofish Cipher");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(720, 510);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 12, 6, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeader = new JLabel("MẬT MÃ TWOFISH (CBC)");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(lblHeader, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(new JLabel("Plaintext:"), gbc);

        txt_plaintext = new JTextArea(3, 45);
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setWrapStyleWord(true);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.35;
        mainPanel.add(new JScrollPane(txt_plaintext), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(new JLabel("Key:"), gbc);

        txt_key = new JTextField("Twofish@Lab2", 24);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txt_key, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Mật khẩu bất kỳ (khóa = SHA-256)"), gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("IV (HEX):"), gbc);

        txt_iv = new JTextField("", 28);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txt_iv, gbc);

        btn_iv = new JButton("Generate IV");
        gbc.gridx = 2;
        gbc.weightx = 0;
        mainPanel.add(btn_iv, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(new JLabel("Ciphertext:"), gbc);

        txt_ciphertext = new JTextArea(3, 45);
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setWrapStyleWord(true);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.35;
        mainPanel.add(new JScrollPane(txt_ciphertext), gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        btn_encrypt = new JButton("Encrypt");
        btn_decrypt = new JButton("Decrypt");
        btn_save = new JButton("Save File");
        btn_open = new JButton("Open File");
        btnPanel.add(btn_encrypt);
        btnPanel.add(btn_decrypt);
        btnPanel.add(btn_save);
        btnPanel.add(btn_open);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        mainPanel.add(btnPanel, gbc);

        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridy = 6;
        mainPanel.add(lbl_status, gbc);

        add(mainPanel, BorderLayout.CENTER);

        btn_iv.addActionListener(evt -> btn_ivActionPerformed());
        btn_encrypt.addActionListener(evt -> btn_encryptActionPerformed());
        btn_decrypt.addActionListener(evt -> btn_decryptActionPerformed());
        btn_save.addActionListener(evt -> btn_saveActionPerformed());
        btn_open.addActionListener(evt -> btn_openActionPerformed());
    }

    private void btn_ivActionPerformed() {
        txt_iv.setText(TwofishCipher.newIv());
        lbl_status.setText("Đã tạo IV ngẫu nhiên 16 byte. Giữ nguyên IV này để giải mã.");
    }

    private void btn_encryptActionPerformed() {
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String ciphertext = TwofishCipher.encrypt(plaintext, txt_key.getText(), txt_iv.getText());
            txt_ciphertext.setText(ciphertext);
            lbl_status.setText("<html>Đã mã hóa Twofish. " + HistoryDao.tryInsert("Twofish", "ENCRYPT", null,
                    plaintext.length(), ciphertext.length()) + "</html>");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Mã hóa thất bại:\n" + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed() {
        String ciphertext = txt_ciphertext.getText();
        if (ciphertext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
            return;
        }
        try {
            String plaintext = TwofishCipher.decrypt(ciphertext, txt_key.getText(), txt_iv.getText());
            txt_plaintext.setText(plaintext);
            lbl_status.setText("<html>Đã giải mã Twofish. " + HistoryDao.tryInsert("Twofish", "DECRYPT", null,
                    ciphertext.length(), plaintext.length()) + "</html>");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Giải mã thất bại: sai Key hoặc bản mã bị sửa.\n" + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_saveActionPerformed() {
        if (txt_ciphertext.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa có bản mã để lưu.");
            return;
        }
        String content = TwofishCipher.toFile(txt_iv.getText(), txt_ciphertext.getText());
        String path = FileTextIO.saveString(this, content, "twofish_ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu IV + bản mã vào: " + path);
        }
    }

    private void btn_openActionPerformed() {
        String content = FileTextIO.openString(this);
        if (content == null) {
            return;
        }
        try {
            String[] parts = TwofishCipher.fromFile(content);
            txt_iv.setText(parts[0]);
            txt_ciphertext.setText(parts[1]);
            lbl_status.setText("Đã mở tệp, khôi phục IV và bản mã. Nhập đúng Key rồi bấm Decrypt.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Tệp không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Twofish().setVisible(true));
    }
}
