package week_01;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class frm_LichSu extends JFrame {
    public JTable tbl_history;
    public JButton btn_refresh;
    public JLabel lbl_status;

    public frm_LichSu() {
        initComponents();
        setLocationRelativeTo(null);
        tbl_history.setModel(HistoryDao.emptyModel());
        loadData();
    }

    private void initComponents() {
        setTitle("Lịch sử thao tác (SQL Server)");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(780, 420);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        JLabel lblHeader = new JLabel("LỊCH SỬ THAO TÁC - BẢNG DBO.HISTORY");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        topPanel.add(lblHeader);
        add(topPanel, BorderLayout.NORTH);

        tbl_history = new JTable();
        add(new JScrollPane(tbl_history), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        btn_refresh = new JButton("Tải lại");
        btnPanel.add(btn_refresh);
        bottomPanel.add(btnPanel, BorderLayout.WEST);

        lbl_status = new JLabel("Đang tải...");
        bottomPanel.add(lbl_status, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btn_refresh.addActionListener(evt -> loadData());
    }

    private void loadData() {
        try {
            tbl_history.setModel(HistoryDao.loadLatest(50));
            lbl_status.setText("Đã tải " + tbl_history.getRowCount() + " dòng mới nhất từ dbo.history.");
        } catch (java.sql.SQLException ex) {
            lbl_status.setText("Không đọc được lịch sử: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_LichSu().setVisible(true));
    }
}
