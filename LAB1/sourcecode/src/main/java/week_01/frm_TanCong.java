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

public class frm_TanCong extends JFrame {
    public JTextArea txt_cipher;
    public JButton btn_bruteforce;
    public JButton btn_frequency;
    public JTextField txt_known_plain;
    public JTextField txt_known_cipher;
    public JButton btn_known;
    public JTextArea txt_result;
    public JLabel lbl_status;

    public frm_TanCong() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Phân tích mật mã cổ điển");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(650, 560);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 12, 5, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeader = new JLabel("PHÂN TÍCH (TẤN CÔNG) MẬT MÃ CỔ ĐIỂN");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblHeader, gbc);

        gbc.gridy = 1;
        mainPanel.add(new JLabel("Ciphertext:"), gbc);

        txt_cipher = new JTextArea(2, 40);
        txt_cipher.setLineWrap(true);
        JScrollPane scrollCipher = new JScrollPane(txt_cipher);
        gbc.gridy = 2; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.15;
        mainPanel.add(scrollCipher, gbc);

        JPanel btnRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btn_bruteforce = new JButton("Brute Force Caesar");
        btn_frequency = new JButton("Frequency Analysis");
        btnRow1.add(btn_bruteforce);
        btnRow1.add(btn_frequency);
        gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        mainPanel.add(btnRow1, gbc);

        JPanel knownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        knownPanel.add(new JLabel("Bản rõ đã biết:"));
        txt_known_plain = new JTextField("HELLO", 10);
        knownPanel.add(txt_known_plain);
        knownPanel.add(new JLabel("Bản mã:"));
        txt_known_cipher = new JTextField("RIJVS", 10);
        knownPanel.add(txt_known_cipher);
        gbc.gridy = 4;
        mainPanel.add(knownPanel, gbc);

        JPanel knownBtnRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btn_known = new JButton("Known Plaintext (Vigenere)");
        knownBtnRow.add(btn_known);
        gbc.gridy = 5;
        mainPanel.add(knownBtnRow, gbc);

        gbc.gridy = 6;
        mainPanel.add(new JLabel("Kết quả:"), gbc);

        txt_result = new JTextArea(8, 40);
        txt_result.setEditable(false);
        txt_result.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollResult = new JScrollPane(txt_result);
        gbc.gridy = 7; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.45;
        mainPanel.add(scrollResult, gbc);

        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridy = 8; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        mainPanel.add(lbl_status, gbc);

        add(mainPanel, BorderLayout.CENTER);

        btn_bruteforce.addActionListener(evt -> {
            String cipher = txt_cipher.getText().trim();
            if (cipher.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext.");
                return;
            }
            txt_result.setText(AttackDemos.caesarBruteForce(cipher));
            txt_result.setCaretPosition(0);
            lbl_status.setText("Đã thử đủ 26 khóa Caesar.");
        });

        btn_frequency.addActionListener(evt -> {
            String cipher = txt_cipher.getText().trim();
            if (cipher.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext.");
                return;
            }
            txt_result.setText(AttackDemos.frequency(cipher));
            txt_result.setCaretPosition(0);
            lbl_status.setText("Đã thống kê tần suất chữ cái.");
        });

        btn_known.addActionListener(evt -> {
            try {
                String key = AttackDemos.knownPlaintext(txt_known_plain.getText(), txt_known_cipher.getText());
                txt_result.setText("Khóa Vigenère suy ra từng vị trí: " + key
                        + "\nNếu khóa ngắn hơn bản rõ, khóa sẽ lặp lại (KEY KEY ...).");
                lbl_status.setText("Khóa suy ra: " + key);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_TanCong().setVisible(true));
    }
}
