package week_01;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbConfig {
    private static String server = "127.0.0.1";
    private static int port = 14333;
    private static String database = "LAB1_MaHoaCoDien";
    private static String user = "lab1_user";
    private static char[] password = "LabUser@2026".toCharArray();

    private DbConfig() { }

    public static synchronized void set(String newServer, int newPort, String newDatabase,
                                       String newUser, char[] newPassword) {
        if (newServer.isBlank() || newDatabase.isBlank() || newUser.isBlank()) {
            throw new IllegalArgumentException("Server, Database và User không được để trống.");
        }
        if (newPort < 1 || newPort > 65535) {
            throw new IllegalArgumentException("Port phải nằm trong khoảng 1 - 65535.");
        }
        server = newServer;
        port = newPort;
        database = newDatabase;
        user = newUser;
        password = newPassword.clone();
    }

    public static synchronized void clear() {
        server = null;
        password = null;
    }

    public static synchronized boolean isReady() {
        return server != null && password != null;
    }

    public static synchronized Connection open() throws SQLException {
        if (server == null || password == null) {
            throw new SQLException("Chưa cấu hình SQL Server. Hãy mở form Kết nối SQL Server và bấm Test Connection.");
        }
        String url = "jdbc:sqlserver://" + server + ":" + port
                + ";databaseName=" + database
                + ";encrypt=true;trustServerCertificate=true;loginTimeout=5";
        return DriverManager.getConnection(url, user, new String(password));
    }

    public static String test() throws SQLException {
        try (Connection con = open()) {
            DatabaseMetaData md = con.getMetaData();
            return "Kết nối thành công: " + md.getDatabaseProductName() + " "
                    + md.getDatabaseProductVersion() + " - database " + con.getCatalog();
        }
    }
}
