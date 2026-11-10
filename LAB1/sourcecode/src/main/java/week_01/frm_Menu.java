package week_01;

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
    public JButton btn_caesar;
    public JButton btn_vigenere;
    public JButton btn_railfence;
    public JButton btn_playfair;
    public JButton btn_transposition;
    public JButton btn_autokey;
    public JButton btn_tonghop;
    public JButton btn_tancong;
    public JButton btn_sql;

    public frm_Menu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("LAB 1 - Mã hóa đối xứng cổ điển");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 360);
        setLayout(new BorderLayout(15, 15));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.setBorder(new EmptyBorder(15, 20, 5, 20));

        JLabel lblTitle = new JLabel("LAB 1 - MÃ HÓA ĐỐI XỨNG CỔ ĐIỂN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        topPanel.add(lblTitle);

        JLabel lblSub = new JLabel("Chọn một bài để mở form tương ứng:", SwingConstants.LEFT);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        topPanel.add(lblSub);

        add(topPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        gridPanel.setBorder(new EmptyBorder(10, 25, 10, 25));

        btn_caesar = new JButton("1.1 Caesar");
        btn_vigenere = new JButton("1.2 Vigenère");
        btn_railfence = new JButton("1.3 Rail Fence");
        btn_playfair = new JButton("1.4 Playfair");
        btn_transposition = new JButton("1.5 Transposition");
        btn_autokey = new JButton("1.6 Autokey");
        btn_tonghop = new JButton("Nâng cao 01 - Tổng hợp");
        btn_tancong = new JButton("Nâng cao 02 - Tấn công");
        btn_sql = new JButton("Kết nối SQL Server");

        gridPanel.add(btn_caesar);
        gridPanel.add(btn_vigenere);
        gridPanel.add(btn_railfence);
        gridPanel.add(btn_playfair);
        gridPanel.add(btn_transposition);
        gridPanel.add(btn_autokey);
        gridPanel.add(btn_tonghop);
        gridPanel.add(btn_tancong);
        gridPanel.add(btn_sql);

        add(gridPanel, BorderLayout.CENTER);

        JLabel lblFooter = new JLabel("Mỗi form mở ra là một cửa sổ riêng; đóng form con không làm tắt chương trình.", SwingConstants.CENTER);
        lblFooter.setBorder(new EmptyBorder(5, 10, 15, 10));
        lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        add(lblFooter, BorderLayout.SOUTH);

        btn_caesar.addActionListener(evt -> new frm_Caesar().setVisible(true));
        btn_vigenere.addActionListener(evt -> new frm_Vigenere().setVisible(true));
        btn_railfence.addActionListener(evt -> new frm_RailFence().setVisible(true));
        btn_playfair.addActionListener(evt -> new frm_Playfair().setVisible(true));
        btn_transposition.addActionListener(evt -> new frm_Transposition().setVisible(true));
        btn_autokey.addActionListener(evt -> new frm_Autokey().setVisible(true));
        btn_tonghop.addActionListener(evt -> new frm_TongHop().setVisible(true));
        btn_tancong.addActionListener(evt -> new frm_TanCong().setVisible(true));
        btn_sql.addActionListener(evt -> new frm_KetNoiSQL().setVisible(true));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Menu().setVisible(true));
    }
}
