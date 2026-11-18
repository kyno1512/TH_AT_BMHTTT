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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class frm_AES extends JFrame {

    /** Tên người dùng đã Login thành công; null = chưa đăng nhập. */
    private String currentUser = null;

    public JTextField txt_username;
    public JPasswordField txt_password;
    public JButton btn_sql;
    public JButton btn_register;
    public JButton btn_login;
    public JTextField txt_ticket;
    public JTextArea txt_plaintext;
    public JTextField txt_key;
    public JTextArea txt_ciphertext;
    public JButton btn_encrypt;
    public JButton btn_decrypt;
    public JButton btn_save;
    public JButton btn_open;
    public JLabel lbl_status;

    public frm_AES() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("AES Cipher - Register / Login");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(740, 610);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 12, 5, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeader = new JLabel("AES-GCM - ĐĂNG KÝ / ĐĂNG NHẬP (SQL SERVER)");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(lblHeader, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        txt_username = new JTextField("sinhvien01", 20);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txt_username, gbc);

        gbc.weightx = 0;
        gbc.gridy = 2;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Password:"), gbc);
        txt_password = new JPasswordField("", 20);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txt_password, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("(ít nhất 8 ký tự)"), gbc);

        JPanel authBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        btn_sql = new JButton("Kết nối SQL Server");
        btn_register = new JButton("Register");
        btn_login = new JButton("Login");
        authBtnPanel.add(btn_sql);
        authBtnPanel.add(btn_register);
        authBtnPanel.add(btn_login);
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(authBtnPanel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Ticket:"), gbc);
        txt_ticket = new JTextField("", 32);
        txt_ticket.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        mainPanel.add(txt_ticket, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.weightx = 0;
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
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(new JLabel("Key AES:"), gbc);
        txt_key = new JTextField("", 24);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txt_key, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Mật khẩu để tạo khóa AES-256"), gbc);

        gbc.gridy = 7;
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
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        mainPanel.add(btnPanel, gbc);

        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridy = 9;
        mainPanel.add(lbl_status, gbc);

        add(mainPanel, BorderLayout.CENTER);

        btn_sql.addActionListener(evt -> new frm_KetNoiSQL().setVisible(true));
        btn_register.addActionListener(evt -> btn_registerActionPerformed());
        btn_login.addActionListener(evt -> btn_loginActionPerformed());
        btn_encrypt.addActionListener(evt -> btn_encryptActionPerformed());
        btn_decrypt.addActionListener(evt -> btn_decryptActionPerformed());
        btn_save.addActionListener(evt -> btn_saveActionPerformed());
        btn_open.addActionListener(evt -> btn_openActionPerformed());
    }

    private void btn_registerActionPerformed() {
        try {
            String user = txt_username.getText().trim();
            UserDao.register(user, txt_password.getPassword());
            HistoryDao.tryInsert("AES", "REGISTER", user, 0, 0);
            lbl_status.setText("Register thành công: " + user + ". Bấm Login để đăng nhập.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi SQL Server: " + ex.getMessage(),
                    "Lỗi SQL Server", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_loginActionPerformed() {
        try {
            String user = txt_username.getText().trim();
            if (UserDao.login(user, txt_password.getPassword())) {
                currentUser = user;
                txt_ticket.setText(UserDao.newTicket());
                HistoryDao.tryInsert("AES", "LOGIN", user, 0, 0);
                lbl_status.setText("Login thành công: " + user + ". Có thể dùng Encrypt/Decrypt.");
            } else {
                currentUser = null;
                txt_ticket.setText("");
                JOptionPane.showMessageDialog(this, "Sai username hoặc mật khẩu.",
                        "Login thất bại", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi SQL Server: " + ex.getMessage(),
                    "Lỗi SQL Server", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_encryptActionPerformed() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Hãy Login trước khi dùng AES.");
            return;
        }
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String ciphertext = AESCipher.encrypt(plaintext, txt_key.getText());
            txt_ciphertext.setText(ciphertext);
            lbl_status.setText("<html>Đã mã hóa AES. " + HistoryDao.tryInsert("AES", "ENCRYPT", currentUser,
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
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Hãy Login trước khi dùng AES.");
            return;
        }
        String ciphertext = txt_ciphertext.getText();
        if (ciphertext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
            return;
        }
        try {
            String plaintext = AESCipher.decrypt(ciphertext, txt_key.getText());
            txt_plaintext.setText(plaintext);
            lbl_status.setText("<html>Đã giải mã AES. " + HistoryDao.tryInsert("AES", "DECRYPT", currentUser,
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
        String path = FileTextIO.saveText(this, txt_ciphertext, "aes_ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu tệp: " + path);
        }
    }

    private void btn_openActionPerformed() {
        String path = FileTextIO.openText(this, txt_ciphertext);
        if (path != null) {
            lbl_status.setText("Đã mở tệp: " + path);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_AES().setVisible(true));
    }
}
