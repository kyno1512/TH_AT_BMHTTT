package week_01;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class frm_TongHop extends JFrame {
    public JComboBox<String> cbo_algorithm;
    public JLabel lbl_hint;
    public JTextArea txt_plaintext;
    public JTextField txt_key;
    public JTextArea txt_ciphertext;
    public JButton btn_encrypt;
    public JButton btn_decrypt;
    public JButton btn_open;
    public JButton btn_save;
    public JButton btn_sql;
    public JButton btn_history;
    public JLabel lbl_status;

    public frm_TongHop() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Chương trình tổng hợp - 6 thuật toán");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(650, 520);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 12, 5, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeader = new JLabel("CHƯƠNG TRÌNH TỔNG HỢP 6 THUẬT TOÁN CỔ ĐIỂN");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblHeader, gbc);

        JPanel algoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        algoPanel.add(new JLabel("Thuật toán:"));
        cbo_algorithm = new JComboBox<>(CipherTongHop.ALGORITHMS);
        algoPanel.add(cbo_algorithm);
        lbl_hint = new JLabel("Khóa mẫu: 3");
        algoPanel.add(lbl_hint);
        gbc.gridy = 1;
        mainPanel.add(algoPanel, gbc);

        gbc.gridy = 2;
        mainPanel.add(new JLabel("Plaintext:"), gbc);

        txt_plaintext = new JTextArea(3, 40);
        txt_plaintext.setLineWrap(true);
        JScrollPane scrollPlain = new JScrollPane(txt_plaintext);
        gbc.gridy = 3; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.25;
        mainPanel.add(scrollPlain, gbc);

        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        keyPanel.add(new JLabel("Key:"));
        txt_key = new JTextField("3", 15);
        keyPanel.add(txt_key);
        gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        mainPanel.add(keyPanel, gbc);

        gbc.gridy = 5;
        mainPanel.add(new JLabel("Ciphertext:"), gbc);

        txt_ciphertext = new JTextArea(3, 40);
        txt_ciphertext.setLineWrap(true);
        JScrollPane scrollCipher = new JScrollPane(txt_ciphertext);
        gbc.gridy = 6; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.25;
        mainPanel.add(scrollCipher, gbc);

        JPanel btnRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btn_encrypt = new JButton("Encrypt");
        btn_decrypt = new JButton("Decrypt");
        btn_open = new JButton("Open File");
        btn_save = new JButton("Save File");
        btnRow1.add(btn_encrypt);
        btnRow1.add(btn_decrypt);
        btnRow1.add(btn_open);
        btnRow1.add(btn_save);
        gbc.gridy = 7; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        mainPanel.add(btnRow1, gbc);

        JPanel btnRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btn_sql = new JButton("Kết nối SQL Server");
        btn_history = new JButton("Xem lịch sử");
        btnRow2.add(btn_sql);
        btnRow2.add(btn_history);
        gbc.gridy = 8;
        mainPanel.add(btnRow2, gbc);

        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridy = 9;
        mainPanel.add(lbl_status, gbc);

        add(mainPanel, BorderLayout.CENTER);

        cbo_algorithm.addActionListener(evt -> {
            String algorithm = (String) cbo_algorithm.getSelectedItem();
            txt_key.setText(CipherTongHop.sampleKey(algorithm));
            lbl_hint.setText("Khóa mẫu: " + CipherTongHop.sampleKey(algorithm));
        });

        btn_encrypt.addActionListener(evt -> {
            String algorithm = (String) cbo_algorithm.getSelectedItem();
            String input = txt_plaintext.getText();
            try {
                String result = CipherTongHop.encrypt(algorithm, input, txt_key.getText());
                txt_ciphertext.setText(result);
                String saved = HistoryDao.tryInsert(algorithm, "ENCRYPT", input, result);
                lbl_status.setText("Đã mã hóa " + algorithm + ". " + saved);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_decrypt.addActionListener(evt -> {
            String algorithm = (String) cbo_algorithm.getSelectedItem();
            String input = txt_ciphertext.getText();
            try {
                String result = CipherTongHop.decrypt(algorithm, input, txt_key.getText());
                txt_plaintext.setText(result);
                String saved = HistoryDao.tryInsert(algorithm, "DECRYPT", input, result);
                lbl_status.setText("Đã giải mã " + algorithm + ". " + saved);
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
            String path = FileTextIO.saveText(this, txt_ciphertext, "ciphertext.txt");
            if (path != null) lbl_status.setText("Đã lưu tệp: " + path);
        });

        btn_sql.addActionListener(evt -> new frm_KetNoiSQL().setVisible(true));
        btn_history.addActionListener(evt -> new frm_LichSu().setVisible(true));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_TongHop().setVisible(true));
    }
}
