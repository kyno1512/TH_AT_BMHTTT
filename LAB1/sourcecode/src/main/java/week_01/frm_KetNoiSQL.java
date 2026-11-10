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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class frm_KetNoiSQL extends JFrame {
    public JTextField txt_server;
    public JTextField txt_port;
    public JTextField txt_database;
    public JTextField txt_user;
    public JPasswordField txt_password;
    public JButton btn_test;
    public JButton btn_history;
    public JLabel lbl_status;

    public frm_KetNoiSQL() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Kết nối Microsoft SQL Server");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(520, 380);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 12, 6, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeader = new JLabel("KẾT NỐI MICROSOFT SQL SERVER");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblHeader, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0; mainPanel.add(new JLabel("Server:"), gbc);
        txt_server = new JTextField("127.0.0.1", 20);
        gbc.gridx = 1; mainPanel.add(txt_server, gbc);

        gbc.gridy = 2; gbc.gridx = 0; mainPanel.add(new JLabel("Port:"), gbc);
        txt_port = new JTextField("14333", 20);
        gbc.gridx = 1; mainPanel.add(txt_port, gbc);

        gbc.gridy = 3; gbc.gridx = 0; mainPanel.add(new JLabel("Database:"), gbc);
        txt_database = new JTextField("LAB1_MaHoaCoDien", 20);
        gbc.gridx = 1; mainPanel.add(txt_database, gbc);

        gbc.gridy = 4; gbc.gridx = 0; mainPanel.add(new JLabel("User:"), gbc);
        txt_user = new JTextField("lab1_user", 20);
        gbc.gridx = 1; mainPanel.add(txt_user, gbc);

        gbc.gridy = 5; gbc.gridx = 0; mainPanel.add(new JLabel("Password:"), gbc);
        txt_password = new JPasswordField("LabUser@2026", 20);
        gbc.gridx = 1; mainPanel.add(txt_password, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btn_test = new JButton("Test Connection");
        btn_history = new JButton("Xem lịch sử");
        btnPanel.add(btn_test);
        btnPanel.add(btn_history);
        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 2;
        mainPanel.add(btnPanel, gbc);

        lbl_status = new JLabel("Chưa kết nối.");
        gbc.gridy = 7;
        mainPanel.add(lbl_status, gbc);

        add(mainPanel, BorderLayout.CENTER);

        btn_test.addActionListener(evt -> {
            try {
                int port = Integer.parseInt(txt_port.getText().trim());
                DbConfig.set(txt_server.getText().trim(), port, txt_database.getText().trim(),
                        txt_user.getText().trim(), txt_password.getPassword());
                lbl_status.setText("<html>" + DbConfig.test() + "</html>");
            } catch (Exception ex) {
                DbConfig.clear();
                lbl_status.setText("<html>Kết nối thất bại: " + ex.getMessage() + "</html>");
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Không kết nối được SQL Server", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_history.addActionListener(evt -> new frm_LichSu().setVisible(true));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_KetNoiSQL().setVisible(true));
    }
}
