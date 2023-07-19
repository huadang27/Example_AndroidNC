# Example_MyTechNC
I.	Tổng quan
1. Lý do chọn đề tài
-	Xây dựng một ứng dụng quản lý trung tâm dạy học lập trình là một đề tài rất hấp dẫn và có ý nghĩa đối với việc quản lý và phát triển một trung tâm giáo dục lập trình. Dưới đây là một số lý do nổi bật để chọn đề tài này: 
•	Tăng cường tính hiệu quả trong quản lý: Với ứng dụng quản lý trung tâm dạy học lập trình, quản lý trung tâm có thể dễ dàng theo dõi và quản lý thông tin của học viên, giảng viên, các lớp học, các khóa học và các hoạt động khác. Điều này giúp tăng cường tính hiệu quả trong quản lý, giảm thiểu tình trạng lộn xộn và khó khăn trong việc quản lý. 
•	Nâng cao trải nghiệm học tập của học viên: Với ứng dụng quản lý, học viên có thể dễ dàng truy cập và theo dõi thông tin về lớp học, giảng viên,  kết quả học tập. Điều này giúp nâng cao trải nghiệm học tập của học viên, giúp họ tiếp cận nội dung học tập một cách dễ dàng và hiệu quả hơn. 
•	Tối ưu hóa quá trình đăng ký và thanh toán: Với ứng dụng quản lý, học viên có thể đăng ký và thanh toán cho các khóa học trực tuyến, giúp tiết kiệm thời gian và giảm thiểu sự cố trong quá trình đăng ký và thanh toán. 
•	Phát triển trung tâm lập trình: Việc xây dựng một ứng dụng quản lý trung tâm dạy học lập trình có thể giúp trung tâm phát triển và nâng cao chất lượng giáo dục, tăng cường tính chuyên nghiệp và thúc đẩy tăng trưởng kinh doanh của trung tâm.

2. Mục tiêu đề tài
Mục tiêu của đề tài Xây dựng App Quản lý trung tâm dạy học lập trình là tạo ra một ứng dụng giúp quản lý trung tâm lập trình một cách hiệu quả và nâng cao trải nghiệm học tập của học viên
-	Quản lý lớp học
-	Quản lý điểm
-	Quản lý học viên
-	Điểm danh 
-	Quản lý lịch học

3. Yêu cầu
-	Giao diện thân thiện và dễ sử dụng: Ứng dụng cần phải có giao diện thân thiện và dễ sử dụng, đảm bảo các tính năng quản lý được hiển thị rõ ràng và dễ dàng sử dụng.
-	Quản lý thông tin học viên: Ứng dụng  cung cấp chức năng quản lý thông tin học viên, bao gồm danh sách học viên, thông tin cá nhân, các khóa học đã đăng ký và kết quả học tập.
-	Quản lý thông tin giảng viên: Ứng dụng cung cấp chức năng quản lý thông tin giảng viên, thông tin cá nhân và lịch giảng dạy.
-	Quản lý các khóa học: Ứng dụng cung cấp chức năng quản lý các khóa học, bao gồm thông tin chi tiết về khóa học, nội dung học tập, lịch học và giảng viên, đơn giá.
-	Quản lý lịch học: Ứng dụng cung cấp chức năng quản lý lịch học, bao gồm lịch học của từng khóa học, lịch giảng dạy của giảng viên và lịch nghỉ.

4. Giải pháp
-	Công cụ sử dụng Intellij, Android Studio,….
-	Sử dụng mySQL để xây dựng và lưu trữ CSDL
-	Xây dựng được chương trình, đóng gói chương trình, chạy được trên máy ảo và máy thật
-	Phân tích thiết kế hệ thống chương trình sao cho phù hợp.
-	Khảo sát thực tế, dựa trên các app có sẵn.
-	Xây dựng chương trình theo mục tiêu đã đề ra.

II. Module ứng dụng và thiết kế ứng dụng
1.	Module ứng dụng
-	Thiết kế các layout:
+	Trang đăng nhập, đăng ký, quên mật khẩu, đổi mật khẩu
+	Trang chủ, lóp học đã đăng kí, danh sách các lớp học 
+	Trang lịch học, danh sách học viên
+	Trang thông tin cá nhân

2.	Thư viện sử dụng
    implementation 'com.google.code.gson:gson:2.8.7'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'org.java-websocket:Java-WebSocket:1.5.3'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'androidx.databinding:databinding-runtime:4.3.4'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.8.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    implementation 'com.google.firebase:firebase-messaging-ktx:23.1.2'
    implementation 'androidx.navigation:navigation-fragment:2.5.3'
    implementation 'androidx.navigation:navigation-ui:2.5.3'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation'androidx.test.espresso:espresso-core:3.5.1'
    implementationplatform('com.google.firebase:firebase-bom:31.2.0')
    implementation 'com.google.firebase:firebase-analytics-ktx'
    implementation 'com.google.firebase:firebase-firestore'
    implementation 'com.google.firebase:firebase-storage'
    implementation 'com.google.firebase:firebase-auth'
    implementation 'com.google.firebase:firebase-core:17.5.0'
    implementation 'com.google.firebase:firebase-database-ktx'
    implementation 'com.github.bumptech.glide:glide:4.14.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'
    implementation 'com.google.firebase:firebase-auth:19.3.0'
    implementation 'com.google.android.material:material:1.1.0'
    implementation "com.github.firdausmaulan:GlideSlider:1.5.2"
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'com.wdullaer:materialdatetimepicker:4.2.3'
    implementation'com.chauthai.swipereveallayout:swipe-reveallayout:1.4.1'
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'


3. Thiết kế cơ sở dữ liệu
-	Sử dụng mySQL thiết kế cơ sở dữ liệu 
![â](https://github.com/huadang27/Example_MyTechNC/assets/78135100/83d06c9e-9703-4a32-b5bd-b5ac3f8ca1ca)


4. Chức năng cơ bản
![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/d0fed359-3aa9-4eb7-9c87-75c673495c92)



III. Giao diện 
1.	Đăng nhập
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/d783ba41-1c4e-4677-b253-492c030a66d9)
2.	Đăng ký
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/710fbc05-a120-4f92-a9aa-8910c8d6bb6c)
3.	Trang chủ
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/90f69ea9-e719-45c9-a3b6-153daf33f0c1)
4.	Khóa học của tôi
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/d14770ed-29d7-4497-aa95-f3c677380dd6)
5.	Chi tiết khóa học
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/57c90724-7d6f-480a-a6ad-f33c78b7e9ee)
6.	Tất cả khóa học
  ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/e3f5aada-bf77-4d00-b3c0-9e6004f052b8)
7.	Lịch học
  ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/55627d05-ab09-40fc-bd18-3750ba8ae125)
8.	Danh sách học viên của lớp 
![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/7984199e-1c2f-491b-aa63-0f1d15c3836f)
9.	Hiển thị thống kê điểm của lớp  
![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/00d24103-8290-472a-9b4f-ddf3b81ccdd4)
10.	Đổi mật khẩu
![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/d324707f-7553-42d7-be97-67c918aa8276)
11.	Lấy lại mật khẩu
  ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/4fdee317-b2f5-49f7-b16a-4680710ae14c)
12.	 Cập nhật Profile
![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/722da7b0-c17c-4b66-a6bb-86f14ffa4f2e)
13.	 Tin tức
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/ca618137-41db-4682-adbe-7d6c0f9fb9a8)
14.	 Chi tiết tin tức
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/afdb0674-5a53-4281-93df-91a432d1cf23)
15.	 Thông tin điểm của học viên
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/1785660a-9a16-433b-8e62-539e6af92fae)
16.	Nhập điểm học viên
 ![image](https://github.com/huadang27/Example_MyTechNC/assets/78135100/a1127b04-f18a-4298-9b28-27596b960acc)

