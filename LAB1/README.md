# BÀI THỰC HÀNH LAB 1: MÃ HÓA ĐỐI XỨNG CỔ ĐIỂN

* **Họ và tên sinh viên:** Ngô Thành Hữu
* **Mã số sinh viên (MSSV):** 1150070015
* **Lớp:** 11-TMĐT
* **Tên bài Lab:** Lab 1 – Mã hóa đối xứng cổ điển
* **Link YouTube:** https://youtu.be/w7VYkJIHcKQ

---

## 📌 Nội Dung Đã Thực Hiện
- Cài đặt 6 thuật toán mật mã cổ điển: Caesar, Vigenère, Rail Fence, Playfair, Hoán vị cột, Autokey.
- Xây dựng giao diện Java Swing cho từng thuật toán, hỗ trợ Mã hóa, Giải mã, Mở/Lưu file văn bản.
- Kết nối CSDL SQL Server 2022 qua JDBC (cổng 14333) để tự động lưu và truy vấn lịch sử thao tác (`dbo.history`).
- Triển khai các phương pháp thám mã cổ điển: Vét cạn (Brute-force) Caesar, Phân tích tần số và Tấn công biết trước bản rõ trên Vigenère.
- Xây dựng Menu chính (`frm_Menu`) điều hướng thống nhất toàn bộ bài thực hành.

## 🎯 Kết Quả Thực Hiện
- 100% thuật toán mã hóa/giải mã chính xác tuyệt đối theo test case chuẩn:
  - Caesar: `HELLO` (k=3) -> `KHOOR`
  - Vigenère: `HELLO` (KEY) -> `RIJVS`
  - Rail Fence: `HELLO WORLD` (k=3) -> `HOREL OLLWD`
  - Playfair: `HELLOWORLD` (KEYWORD) -> `GYIZSCOKCFBU`
  - Hoán vị cột: `BAOMAT` (2,3,1) -> `AAOTBM`
  - Autokey: `HELLO` (KEY) -> `RIJSS`
- Kết nối SQL Server thành công, bảo mật chống SQL Injection, xem lịch sử hoạt động tốt.
- Thám mã thành công, khôi phục được bản rõ và từ khóa bí mật.
- Đã hoàn thành video demo trên YouTube và chèn đủ 10 hình ảnh minh chứng vào file Word.

## 🛠️ Hướng Dẫn Chạy
1. Mở NetBeans -> **File** -> **Open Project...** -> Chọn thư mục `sourcecode`.
2. Bấm **`F6`** để chạy Menu chính.