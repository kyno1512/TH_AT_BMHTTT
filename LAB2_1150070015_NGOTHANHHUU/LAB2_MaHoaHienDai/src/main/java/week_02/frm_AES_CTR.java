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

public class frm_AES_CTR extends JFrame {

    public JTextArea txt_plaintext;
    public JTextField txt_key;
    public JButton btn_genkey;
    public JTextField txt_nonce;
    public JButton btn_gennonce;
    public JTextArea txt_ciphertext;
    public JButton btn_encrypt;
    public JButton btn_decrypt;
    public JButton btn_save;
    public JButton btn_open;
    public JLabel lbl_status;

    public frm_AES_CTR() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("AES-CTR Cipher");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(740, 510);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 12, 6, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeader = new JLabel("BÀI NÂNG CAO 01 - AES-256 CHẾ ĐỘ CTR");
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
        mainPanel.add(new JLabel("Key (Base64):"), gbc);

        txt_key = new JTextField("", 30);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txt_key, gbc);

        btn_genkey = new JButton("Generate Key");
        gbc.gridx = 2;
        gbc.weightx = 0;
        mainPanel.add(btn_genkey, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Nonce (Base64):"), gbc);

        txt_nonce = new JTextField("", 30);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txt_nonce, gbc);

        btn_gennonce = new JButton("Generate Nonce");
        gbc.gridx = 2;
        gbc.weightx = 0;
        mainPanel.add(btn_gennonce, gbc);

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

        btn_genkey.addActionListener(evt -> btn_genkeyActionPerformed());
        btn_gennonce.addActionListener(evt -> btn_gennonceActionPerformed());
        btn_encrypt.addActionListener(evt -> btn_encryptActionPerformed());
        btn_decrypt.addActionListener(evt -> btn_decryptActionPerformed());
        btn_save.addActionListener(evt -> btn_saveActionPerformed());
        btn_open.addActionListener(evt -> btn_openActionPerformed());
    }

    private void btn_genkeyActionPerformed() {
        txt_key.setText(AESCTRCipher.newKey());
        lbl_status.setText("Đã tạo khóa ngẫu nhiên 256 bit.");
    }

    private void btn_gennonceActionPerformed() {
        txt_nonce.setText(AESCTRCipher.newNonce());
        lbl_status.setText("Đã tạo nonce ngẫu nhiên 96 bit. Mỗi lần mã hóa nên tạo nonce mới.");
    }

    private void btn_encryptActionPerformed() {
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String ciphertext = AESCTRCipher.encrypt(plaintext, txt_key.getText(), txt_nonce.getText());
            txt_ciphertext.setText(ciphertext);
            lbl_status.setText("<html>Đã mã hóa AES-CTR. " + HistoryDao.tryInsert("AES-CTR", "ENCRYPT", null,
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
            String plaintext = AESCTRCipher.decrypt(ciphertext, txt_key.getText(), txt_nonce.getText());
            txt_plaintext.setText(plaintext);
            lbl_status.setText("<html>Đã giải mã AES-CTR. " + HistoryDao.tryInsert("AES-CTR", "DECRYPT", null,
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
        String content = AESCTRCipher.toFile(txt_nonce.getText(), txt_ciphertext.getText());
        String path = FileTextIO.saveString(this, content, "aes_ctr_ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu nonce + bản mã (không lưu Key) vào: " + path);
        }
    }

    private void btn_openActionPerformed() {
        String content = FileTextIO.openString(this);
        if (content == null) {
            return;
        }
        try {
            String[] parts = AESCTRCipher.fromFile(content);
            txt_nonce.setText(parts[0]);
            txt_ciphertext.setText(parts[1]);
            lbl_status.setText("Đã mở tệp. Nhập đúng Key rồi bấm Decrypt.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Tệp không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_AES_CTR().setVisible(true));
    }
}
