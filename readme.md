# Lab week 01 - JakartaEE, Servlet

## Giới thiệu

* Viết trang web đơn giản chạy local với Servlet. Bao gồm các chức năng:
  * CRUD (Create, Read, Update, Delete) với các đối tượng: `Acconut`, `Grant Access`, `Log`, `Role`
  * Đăng nhập, đăng xuất
  * Hiển thị thông tin tài khoản khi đăng nhập thành công
  * Hiển thị các quyền của một `Role`
  * Hiển thị các quyền của một `Account`
  * Cấp quyền cho một `Account`
  * Ghi `Log` mỗi lần đăng nhập, đăng xuất
* Mục đích của project:
  * Làm quen với Servlet, cấu trúc của một project Java Web.
  * Mô hình MVC (Model - View - Controller)
  * Các khái niệm cơ bản: `Request`, `Response`, `Session`, `Cookie`, `Filter`, `Listener`, `JSP`, `Taglib`, `JDBC`, `DataSource`, `Connection`, `Statement`, `ResultSet`, `PreparedStatement`, `CallableStatement`, `Transaction`, `Connection Transaction`, `Container Transaction`.
* Đối tượng sử dụng:
  * `Account`: Tài khoản người dùng
  * `Role`: Quyền của tài khoản
  * `Grant Access`: Cấp quyền cho tài khoản
  * `Log`: Lịch sử đăng nhập, đăng xuất
  * `AccountDAO`: Lớp truy xuất dữ liệu của `Account`
  * `RoleDAO`: Lớp truy xuất dữ liệu của `Role`
  * `GrantAccessDAO`: Lớp truy xuất dữ liệu của `Grant Access`
  * `LogDAO`: Lớp truy xuất dữ liệu của `Log`
  * `ControlServlet`: Lớp điều khiển
  * `ConnectDB`: Lớp kết nối với cơ sở dữ liệu

## Cài đặt

* Hướng dẫn cài đặt dự án
  * Cài đặt môi trường
    * JDK 17
    * Apache Tomcat 10
    * HeidiSQL
  * Cài đặt các công cụ cần thiết
    * Gradle 8.2.1
    * IntelliJ IDEA
  * Cài đặt các thư viện cần thiết
    * JDBC Driver
    * Servlet
    * JSP
    * SLF4J
  * Cấu hình cơ sở dữ liệu
    * Tạo cơ sở dữ liệu
    * Tạo bảng
    * Tạo dữ liệu mẫu
* Yêu cầu hệ thống
  * Hệ điều hành: Windows 7 or higher 
  * Bộ nhớ: 4GB RAM or more
  * Ổ cứng: 10GB free space or more
  * Cổng kết nối: localhost:8080
  * Trình duyệt: Chrome, Firefox, Edge, etc.

## Sử dụng

* Hướng dẫn sử dụng dự án
  * Cấu trúc dự án
    * `src/main/java`: Chứa các package `dao`, `model`, `servlet`, `util`
    * `src/main/resources`: Chứa các file `.xml`
    * `src/main/webapp`: Chứa các file `.jsp`, `.css`
    * `build.gradle`: File cấu hình Gradle
* Các tính năng chính
  * Đăng nhập
  * Đăng xuất
  * Hiển thị thông tin tài khoản
  * Hiển thị các quyền của một `Role`
  * Hiển thị các quyền của một `Account`
  * Cấp quyền cho một `Account`
  * Ghi `Log` mỗi lần đăng nhập, đăng xuất
  * CRUD (Create, Read, Update, Delete) với các đối tượng: `Acconut`, `Grant Access`, `Log`, `Role`
## Công nghệ sử dụng

* Danh sách các công nghệ được sử dụng
  * Servlet
  * JSP
  * JDBC
  * Gradle
  * Apache Tomcat
  * HeidiSQL

## Liên kết

* [Liên kết đến trang web của dự án](https://github.com/qt-github/week01_lab_PhamHuuQuocToan_19487901)
* [Liên kết đến tài liệu của dự án](https://vovanhai.wordpress.com/webp/)
