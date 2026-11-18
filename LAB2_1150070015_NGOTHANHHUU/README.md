# BÀI THỰC HÀNH LAB 2: MÃ HÓA ĐỐI XỨNG HIỆN ĐẠI

* **Họ và tên sinh viên:** Ngô Thành Hữu
* **Mã số sinh viên (MSSV):** 1150070015
* **Lớp:** 11-TMĐT
* **Tên bài Lab:** Lab 2 - Mã hóa đối xứng hiện đại
* **Link YouTube:** https://youtu.be/AwcBaupaXFo

---

## Nội Dung Đã Thực Hiện
- Cài đặt các thuật toán mã hóa đối xứng hiện đại bằng Java Swing (`week_02`):
  - **2.1 DES:** `DESCipher` (`DES/CBC/PKCS5Padding`, khóa 8 byte, IV ngẫu nhiên 8 byte).
  - **2.2 TripleDES (3DES):** `TripleDESCipher` (`DESede/CBC/PKCS5Padding`, khóa 24 byte, IV ngẫu nhiên 8 byte).
  - **2.3 AES-256-GCM + Đăng ký / Đăng nhập SQL Server:** `AESCipher` (`AES/GCM/NoPadding`, dẫn xuất khóa PBKDF2-HMAC-SHA256 120.000 vòng), `UserDao` lưu salt & PBKDF2 hash vào `dbo.app_users`, cấp Ticket 128 bit sau khi đăng nhập.
  - **2.4 RC4:** `RC4Cipher` tự cài đặt KSA và PRGA bằng vòng lặp `for`, xuất kết quả dạng HEX.
  - **2.5 Twofish:** `TwofishCipher` sử dụng BouncyCastle (`CBC` + đệm `PKCS7`, khóa SHA-256, IV 16 byte HEX, lưu/đọc tệp kèm IV).
  - **2.6 Nâng cao 01 (Java AES-CTR):** `AESCTRCipher` (`AES/CTR/NoPadding`, Key 32 byte & Nonce 12 byte Base64).
- **2.6 Nâng cao 02 (Python AES-CBC):** `LAB2_Python/aes_cbc_gui.py` sử dụng thư viện `cryptography` và giao diện Tkinter, hỗ trợ `--self-test`.
- Kết nối SQL Server Express qua JDBC (cổng `14333`) để quản lý tài khoản và lưu/truy vấn lịch sử thao tác (`dbo.crypto_history`).
- Xây dựng Menu chính (`frm_Menu`) điều hướng thống nhất toàn bộ bài thực hành.

## Cấu Trúc Thư Mục
- `LAB2_MaHoaHienDai/` : Mã nguồn Java NetBeans (Maven), `pom.xml` và thư mục `sql/` chứa các tệp script SQL.
- `LAB2_Python/` : Chương trình Python `aes_cbc_gui.py`.
- `LAB2_1150070015_NgoThanhHuu.docx` : Báo cáo Word kèm ảnh chụp kết quả kiểm thử.

## Hướng Dẫn Chạy
1. **SQL Server (SSMS):** Chạy lần lượt `LAB2_MaHoaHienDai/sql/01_Tao_CSDL.sql` và `02_Tao_Tai_Khoan_SQL.sql` (cấu hình SQL Server TCP/IP cổng `14333`, bật Mixed Mode).
2. **Java NetBeans:**
   - Mở NetBeans -> **File** -> **Open Project...** -> Chọn thư mục `LAB2_MaHoaHienDai`.
   - Bấm **Clean and Build (`Shift+F11`)**, sau đó bấm **`F6`** để chạy `frm_Menu`.
   - Mở **Kết nối SQL Server**, nhập mật khẩu tài khoản `lab2_user` và bấm **Test Connection** trước khi chạy bài 2.3 hoặc xem lịch sử.
3. **Python:**
   ```cmd
   cd LAB2_Python
   py -m pip install cryptography
   py aes_cbc_gui.py --self-test
   py aes_cbc_gui.py
   ```
