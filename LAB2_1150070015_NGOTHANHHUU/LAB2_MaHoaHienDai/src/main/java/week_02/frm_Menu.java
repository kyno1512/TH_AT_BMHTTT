package week_02;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class frm_Menu extends JFrame {

    public JButton btn_des;
    public JButton btn_tripledes;
    public JButton btn_aes;
    public JButton btn_rc4;
    public JButton btn_twofish;
    public JButton btn_aesctr;
    public JButton btn_sql;
    public JButton btn_history;

    public frm_Menu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("LAB 2 - Mã hóa đối xứng hiện đại");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 360);
        setLayout(new BorderLayout(15, 15));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.setBorder(new EmptyBorder(15, 20, 5, 20));

        JLabel lblTitle = new JLabel("LAB 2 - MÃ HÓA ĐỐI XỨNG HIỆN ĐẠI", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        topPanel.add(lblTitle);

        JLabel lblSub = new JLabel("Chọn một bài để mở form tương ứng:", SwingConstants.LEFT);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        topPanel.add(lblSub);

        add(topPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        gridPanel.setBorder(new EmptyBorder(10, 25, 10, 25));

        btn_des = new JButton("2.1 DES");
        btn_tripledes = new JButton("2.2 TripleDES");
        btn_aes = new JButton("2.3 AES + Login");
        btn_rc4 = new JButton("2.4 RC4");
        btn_twofish = new JButton("2.5 Twofish");
        btn_aesctr = new JButton("2.6 Nâng cao - AES-CTR");
        btn_sql = new JButton("Kết nối SQL Server");
        btn_history = new JButton("Xem lịch sử");

        gridPanel.add(btn_des);
        gridPanel.add(btn_tripledes);
        gridPanel.add(btn_aes);
        gridPanel.add(btn_rc4);
        gridPanel.add(btn_twofish);
        gridPanel.add(btn_aesctr);
        gridPanel.add(btn_sql);
        gridPanel.add(btn_history);

        add(gridPanel, BorderLayout.CENTER);

        JLabel lblFooter = new JLabel(
                "Bài nâng cao 02 (AES-CBC) viết bằng Python: chạy tệp aes_cbc_gui.py.",
                SwingConstants.CENTER);
        lblFooter.setBorder(new EmptyBorder(5, 10, 15, 10));
        lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        add(lblFooter, BorderLayout.SOUTH);

        btn_des.addActionListener(evt -> new frm_DES().setVisible(true));
        btn_tripledes.addActionListener(evt -> new frm_TripleDES().setVisible(true));
        btn_aes.addActionListener(evt -> new frm_AES().setVisible(true));
        btn_rc4.addActionListener(evt -> new frm_RC4().setVisible(true));
        btn_twofish.addActionListener(evt -> new frm_Twofish().setVisible(true));
        btn_aesctr.addActionListener(evt -> new frm_AES_CTR().setVisible(true));
        btn_sql.addActionListener(evt -> new frm_KetNoiSQL().setVisible(true));
        btn_history.addActionListener(evt -> new frm_LichSu().setVisible(true));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Menu().setVisible(true));
    }
}
