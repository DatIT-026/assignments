#set page(paper: "a4")
#set list(marker: ([•], [‣]))
#set text(lang: "vi", font: "New Computer Modern", 13.1pt)
#show raw: set text(font: "Fira Code");
#set par(justify: true)

#align(center, text(20pt)[
  *Lab 2*\
  *Car Showroom Management*
])

#grid(
  columns: (1fr),
  align(center)[
    Author: SE203334 - Hà Nguyễn Tiến Đạt \
    Course: #underline[Lab211] |
    Instructor: #underline[Ms. Thân Thị Ngọc Vân]
  ],
)

#outline()

= I. Phân tích và thiết kế
== 1. Phân tích dữ liệu
    Dựa trên yêu cầu đề bài và ngữ cảnh bài toán là quản lý showroom ô tô BMW, công việc đầu tiên là cần phải xác định các thành phần chính trong hệ thống:

    a. Các đối tượng _(entities)_: *Brand*, *Car*, *Menu*. Chi tiết:
    - *Brand*: là đối tượng dữ liệu chính, quản lý thông tin thương hiệu xe BMW trong showroom. Sau khi xem xét cấu trúc của tập tin *brands.txt* được cung cấp sẵn kèm theo đề bài của đối tượng dữ liệu này, ta thấy danh sách các thương hiệu xe được mô tả qua nhiều dòng, mỗi dòng là một thương hiệu độc lập với các thuộc tính phụ thuộc bao gồm:
      - *ID* of brand _(là unique key, không thể thay đổi sau khi tạo)_
      - Name of brand
      - Sound Brand
      - *Price* _(giá, tính bằng Billion)._
      
    - *Car*: là đối tượng chính mô tả thông tin chi tiết của từng chiếc xe trong showroom. Tương tự, xem xét cấu trúc của tập tin *cars.txt* được cung cấp sẵn kèm theo đề bài, ta thấy danh sách các xe cũng được mô tả qua nhiều dòng, mỗi dòng là một xe độc lập với các thuộc tính phụ thuộc gồm:
      - *ID* of car
      - *ID* of brand _(liên kết với Brand)_
      - *Color*
      - *Frame ID* _(định dạng Fxxxxx, là duy nhất, ví dụ: F00000)_
      - *Engine ID* _(định dạng Exxxxx, là duy nhất, ví dụ: E00000)_
    
    
    b. Các thao tác _(operations)_: *Input* _(get data from keyboard by user)_, *Add*, *Update*, *Search*, *Get Info* _(object Information)_, *Display* _(display all data in the list)_, *Remove*, *Filter*, *Load* _(get data from file)_, *Save* _(store data to file)_
    
    Có hai nhóm thao tác chính:
    
    - i. Tác động lên một đối tượng độc lập (Brand hoặc Car): *_Input_*, *_Get_Info_*
    - ii. Tác động lên một nhóm đối tượng (Collections - danh sách Brand hoặc Car): *_Add_*, *_Update_*, *_Remove_*, *_Search_*, *_Filter_*, *_Load/Save_*.

== 2. Thiết kế
Các class sẽ được thiết kế dựa trên các entity đã xác định. Mỗi class sẽ _đóng gói_ các *_thuộc tính_* (attributes) và *_hành vi_* (methods) liên quan.

Theo nguyên tắc _encapsulation_, các attributes được khai báo *_private_* để bảo vệ dữ liệu, chỉ cho phép truy cập thông qua các getter/setter. Điều này là để giúp kiểm soát được cách dữ liệu được truy cập và thay đổi, dễ dàng bảo trì khi có thay đổi logic và tăng tính bảo mật. Ta có các thiết kế như sau:

a. Brand class

#align(center)[
  #table(
    columns: 35%,
    align: left,
    [#align(center)[*Brand*]],
    
    [
      \- brandID: String\ 
      \- brandName: String\ 
      \- soundBrand: String\ 
      \- price: double
    ],
    [
      \+ Brand()\ 
      \+ Brand(fields)\ 
      \+ Getters/Setters\ 
      \+ toString()
    ],
  )
]
\
\
\
b. Car class

#align(center)[
  #table(
    columns: (35%),
    align: left,
    [#align(center)[*Car*]],
    
    [
      \- carID: String\ 
      \- brand: Brand\ 
      \- color: String\ 
      \- frameID: String\
      \- engineID: String\
    ],
    [
      \+ Car()\ 
      \+ Car(fields)\ 
      \+ Getters/Setters\ 
      \+ toString()
    ],
  )
]

c. Lựa chọn cấu trúc quản lý dữ liệu

Trong quá trình hoạt động, chương trình cần lưu trữ và thao tác với nhiều đối tượng cùng loại (nhiều thương hiệu, nhiều xe). Do đó, cần có một cấu trúc dữ liệu phù hợp để đáp ứng cho nhu cầu này, các cấu trúc có thể liệt kê như: mảng, linked list,… là những lựa chọn phù hợp.

Mặt khác, có 2 loại quan hệ ta có thể sử dụng là "*_has A_*" và "*_is A_*":

- "*has A*": đối tượng chứa một collection bên trong như một thuộc tính. Class sở hữu collection nhưng không phải là collection.

- "*is A*": đối tượng chính là một collection thông qua kế thừa _(inheritance)_. Class vừa là collection, vừa có thể mở rộng thêm behaviors.

  Dự án chọn "*is A*" vì khi kế thừa từ ArrayList, *BrandList* và *CarList* tự động có sẵn các methods như ```java add()```, ```java remove()```, ```java size()```, ```java get()```... mà không cần phải viết lại. Ta chỉ cần thêm các methods như ```java searchID()```, ```java addBrand()```, ```java updateCar()```... mà sẽ được phân tích ở những phần sau.

Ta có thêm một số class với thiết kế như sau:

#align(center)[
  #table(
    columns: (85%),
    align: left,
    [
      #align(center)[
        BrandList *extends* ArrayList<Brand>
      ]
    ],
    [
      \
    ],
    [
      \+ *_BrandList_*()\
      \+ *_searchID_*(String bID): int\
      \+ *_isUnique_*(String id): boolean\
      \+ *_getUserChoice_*(): Brand\
      \+ *_displayBrands_*(ArrayList<\Brand\> brands): void\
      \+ *_listBrands_*(): void\
      \+ *_addBrand_*(): void\
      \+ *_searchBrand_*(): void\
      \+ *_updateBrand_*(): void\
      \+ *_listBrandsLessThanPrice_*(): void\
      \+ *_loadFromFile_*(String filename): void\
      \+ *_saveToFile_*(String filename): void
    ],
  )
]

#align(center)[
  #table(
    columns: (85%),
    align: left,
    [
      #align(center)[
        CarList *extends* ArrayList<Car>
      ]
    ],
    [
      \- brandList: BrandList
    ],
    [
      \+ *_CarList_*(BrandList bList)\
      \+ *_searchID_*(String carID): int\
      \+ *_searchFrame_*(String fID): int\
      \+ *_searchEngine_*(String eID): int\
      \+ *_isUnique_*(String id, String frameID, String engineID): boolean\
      \+ *_displayCars_*(ArrayList<Car> cars): void\
      \+ *_displayCarInfo_*(Car car): void\
      \+ *_listCars_*(): void\
      \+ *_printBasedBrandName_*(): void\
      \+ *_addCar_*(): void\
      \+ *_removeCar_*(): void\
      \+ *_updateCar_*(): void\
      \+ *_listCarsWithColor_*(): void\
      \+ *_loadFromFile_*(String filename): void\
      \+ *_saveToFile_*(String filename): void
    ],
  )
]


d. Công cụ nhập và kiểm tra dữ liệu

Nên kiểm tra dữ liệu trước khi đưa vào Object để đảm bảo tính đúng đắn và hợp lệ theo yêu cầu của project, ta cần xây dựng interface *iConstants* như thiết kế sau:

#align(center)[
  #table(
    columns: (85%),
    align: left,
    [
      #align(center)[
        <\<interface>>\ 
        iConstants
      ]
    ],
    
    [
      \+ CasualString: String <\<final>>\ 
      \+ StringButOnlyAlphabetAllowed: String <\<final>>\ 
      \+ StringButAlphabetAndNumberAllowed: String <\<final>>\ 
      \+ StringOnlyForFrameFormat: String <\<final>>\
      \+ StringOnlyForEngineFormat: String <\<final>>\ 
      \+ thePrice: String <\<final>>\
    ],
  )
]
\ \ 
*Giải thích các pattern:*

- _CasualString_: chấp nhận chữ cái _(bao gồm cả chữ thường, hoa)_, số, dấu gạch ngang, gạch dưới, ngoặc đơn và khoảng trắng.
- _StringButOnlyAlphabetAllowed_: chỉ chấp nhận chữ cái và khoảng trắng.
- _StringButAlphabetAndNumberAllowed_: chỉ chấp nhận chữ cái và chữ số.
- _StringOnlyForFrameFormat_: format đặc biệt dành cho Frame ID _(Fxxxxx)_.
- _StringOnlyForEngineFormat_: format đặc biệt dành cho Engine ID _(Exxxxx)_.
- _thePrice_: chỉ chấp nhận số thực dương khác 0 với phần thập phân tùy chọn.

Class *Inputter* chứa các phương thức phục vụ cho việc hiển thị thông báo và nhập dữ liệu từ bàn phím, kết hợp với *iConstants* để validation:

#align(center)[
  #table(
    columns: (95%),
    align: left,
    [
      #align(center)[
        *Inputter*
      ]
    ],
    
    [
      \- sc: Scanner <\<static>> \ 

    ],
    [
      \+ *_getString_*(String msg, String pattern): String <\<static>>\ 
      \+ *_readUniqueStringWithPattern_*(String msg, String pattern,\
      \String formatErrMsg, String uniqueErrMsg,\
      \Predicate<String> existsChecker): String <\<static>>\
      \+ *_getStringUpdate_*(String msg, String pattern): String <\<static>>\
      \+ *_readString_*(String msg): String <\<static>>\
      \+ *_confirmation_*(String msg): boolean <\<static>>\
      \+ *_getInt_*(String msg, int min, int max): int <\<static>>\
      \+ *_readPositiveDouble_*(String msg, String errMsg): double <\<static>>\
      \+ *_capitalizeWords_*(String str): String <\<static>>\
    ],
  )
]

*Mô tả các phương thức*:

- _getString()_: Nhập chuỗi không rỗng và phải khớp với pattern regex.
- _readUniqueStringWithPattern()_: Nhập chuỗi phải khớp pattern và đảm bảo tính duy nhất (unique).
- _getStringUpdate()_: Dùng cho update, nếu input rỗng thì giữ nguyên giá trị cũ.
- _readString()_: Đọc chuỗi, có thể rỗng.
- _confirmation()_: Yêu cầu xác nhận (Y/N).
- _getInt()_: Nhập số nguyên trong khoảng *[min, max]*.
- _readPositiveDouble()_: Nhập số thực dương.
- _capitalizeWords()_: Viết hoa chữ cái đầu mỗi từ, các chữ cái sau đều là chữ thường.

= II. Triển khai kỹ thuật và điều chỉnh thiết kế

== 1. Kiểm tra dữ liệu
Về nguyên tắc, dữ liệu khi được đưa vào chương trình phải được đảm bảo tính hợp lệ. Vì vậy sau khi được nhập vào bởi người dùng, dữ liệu cần phải được kiểm tra.

a. Kiểm tra format với Regex Pattern

Sử dụng interface *iConstants* để định nghĩa các pattern validation. Ví dụ:

- Kiểm tra id của Brand:

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [
      ```java
      // Check Brand ID
      String brandID = Inputter.getString("Enter Brand ID: ", iConstants.CasualString);
      ```
    ],
  )
]

- Kiểm tra id của Frame:

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [ 
      ```java
      // Check Frame ID format (F00000)
      if (frameID.matches(iConstants.StringOnlyForFrameAndEngineFormat)) {
          // Valid format
      }
      ```
    ],
  )
]

b. Kiểm tra tính duy nhất (Uniqueness)

*_Predicate_* (hay Vị ngữ) là một _functional interface_ trong Java đại diện cho một hàm nhận một tham số và trả về giá trị boolean (true/false). Trong validation, Predicate được dùng để kiểm tra một điều kiện có thỏa mãn hay không.

Ta sẽ sử dụng ```java Predicate<String>``` để kiểm tra xem ID đã tồn tại hay chưa. Ví dụ:

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [
      ```java
      String carID = Inputter.readUniqueStringWithPattern(
          "Enter Car ID: ",
          iConstants.StringButAlphabetAndNumberAllowed,
          "Invalid Input!",
          "Car ID already exists!",
          id -> searchID(id) >= 0     // lambda expression (Predicate)
      );
      ```
    ],
  )
]

Ở đoạn code trên, ```java id -> searchID(id) >= 0``` là một _lambda expression_ thay thế cho Predicate, nó có nghĩa là nhận tham số *`id`* _(String)_, sau đó gọi method *`searchID(id)`* để tìm vị trí và cuối cùng trả về một kết quả boolean:

- Nếu ```java >= 0``` nghĩa là ID này đã tồn tại trong danh sách → trả về *`true`* _(reject vì không unique)_. Method ```java readUniqueStringWithPattern()``` sẽ tiếp tục yêu cầu nhập lại cho đến khi Predicate trả về false.
- Nếu ```java < 0``` nghĩa là id đó chưa tồn tại → trả về *`false`* _(accept vì unique)_.

c. Kiểm tra ràng buộc dữ liệu

- _Brand ID_: Phải unique, không rỗng, khớp pattern.
- _Brand Name_: Không rỗng.
- _Sound Brand_: Không rỗng, chỉ chứa chữ cái và khoảng trắng
- _Price_: Phải là số thực dương khác 0.
- _Color_: Không rỗng, không chứa số.
- _Frame ID_: Format F00000, phải unique.
- _Engine ID_: Format E00000, phải unique.

== 2. File I/O Operations

Các thao tác đọc/ghi file luôn tiềm ẩn rủi ro (file không tồn tại, quyền truy cập bị từ chối, lỗi định dạng dữ liệu,...), do đó cần sử dụng *try-catch* để xử lý exception và đảm bảo chương trình không crash.

a. Đọc dữ liệu từ file

Để load data từ file, ta sẽ tạo một method với tên gọi *loadFromFile* và thực hiện theo các bước sau:
+ _Kiểm tra file có tồn tại_: Nếu file không tồn tại, tạo một file rỗng.
+ _Đọc từng dòng_: Sử dụng *`BufferedReader`* để đọc data.
+ _Parse và validate_: Mỗi dòng được tách thành các field, kiểm tra tính hợp lệ trước khi add vào list.
+ _Bỏ qua dòng lỗi_: Nếu dòng nào không hợp lệ (thiếu field, sai format, trùng ID), bỏ qua và tiếp tục.

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void loadFromFile(String filename) {
          try {
              File f = new File(filename);
              if (!f.exists()) { // Kiem tra file co ton tai hay khong
                f.createNewFile();
                return;
              }
              
              BufferedReader r = new BufferedReader(new FileReader(f));
              String line;
              // Doc tung dong trong file cho den khi doc het
              while ((line = r.readLine()) != null) {
                  String[] parts = line.split(",");
                  
                  // Parse and validate data
                  // Neu hop le thì add vao list
              }

              // dong luong sau khi doc de giai phong bo nho
              r.close();
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage());
          }
      }
      ```
    ],
  )
]

b. Ghi dữ liệu vào file

Để save data vào file, ta sẽ tạo một hàm với tên gọi *saveToFile*:

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void saveToFile(String filename) {
          try {
            PrintWriter w = new PrintWriter(new FileWriter(filename));
            
            for (Brand brand : this) {
              // ghi data
            }
            w.close();
          } catch (Exception e) {
              System.err.println("Error: " + e.getMessage());
          }
      }
      ```
    ],
  )
]

== 3. Các chức năng chính

=== 3.1. Add

a. Add a new brand

Chức năng này cho phép thêm brand mới vào danh sách:

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void addBrand() {
          String id = Inputter.readUniqueStringWithPattern(
              "Enter Brand ID: ",
              iConstants.CasualString,
              "Invalid format!",
              "Brand ID already exists! Please enter a different ID.",
              brandId -> searchID(brandId) >= 0
          );

          String name = Inputter.getString("Enter brand name: ", 
          iConstants.CasualString);
          
          String sound = Inputter.getString("Enter Sound Brand: ", 
          iConstants.StringButOnlyAlphabetAllowed);
          
          double price = Inputter.readPositiveDouble(
            "Enter Price (e.g: 3.0): ",
            "Price must be positive!"
          );
            
          // Tao object Brand moi
          Brand newBrand = new Brand(id.toUpperCase(), name, 
          Inputter.capitalizeWords(sound), price);
          this.add(newBrand); // Them vao danh sach
      }
      ```
    ],
  )
]

*Lưu ý:*
- Brand ID được chuyển về chữ HOA để đồng nhất.
- Sound Brand được capitalize (vd: Harman Kardon).
- Price phải > 0 (được validate bởi *`readPositiveDouble()`*).

b. Add a new car

Chức năng này phức tạp hơn vì phải chọn brand từ menu và validate nhiều field:

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void addCar() {
          // nhap car id (unique, khong rong)
          
          // Chon brand tu menu
          Brand brand = brandList.getUserChoice();

          // Nhap color (dung ham getString())
          
          // Nhap Frame ID (format F00000, unique)
          String frameID = Inputter.readUniqueStringWithPattern(
            "Enter Frame ID (F00000 format): ",
            iConstants.StringOnlyForFrameFormat,
            "Invalid Input!",
            "Frame ID must be in F00000 format and unique!",
            fId -> !fId.matches(iConstants.StringOnlyForFrameFormat) || 
            searchFrame(fId) >= 0
          );

          // Nhap engine ID (cach thuc tuong tu nhu frame ID)
          
          // Tao Object Car moi
          Car newCar = new Car(carID.toUpperCase(), brand, 
          color.toLowerCase(), frameID, engineID);
          
          this.add(newCar);   // Them vao danh sach
          
          System.out.println("Car added successfully!");
      }
      ```
    ],
  )
]

*Lưu ý:* cần phải _predicate validation_ cho Frame/Engine ID, ví dụ:

#align(center)[
  ```java fId -> !fId.matches(...) || searchFrame(fId) >= 0```   
]

Đoạn code trên sẽ trả về *true* (reject) nếu: ```java !fId.matches(...)``` _(sai format)_ HOẶC ```java searchFrame(fId) >= 0``` _(đã tồn tại)_.

=== 3.2. Search

a. Tìm brand theo ID

Cần tìm kiếm brand theo ID chính xác. Nếu tìm thấy, hiển thị đầy đủ thông tin brand:

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void searchBrand() {
          String brandID = Inputter.getString("Enter Brand ID to search: ", 
          iConstants.CasualString);
          
          int pos = searchID(brandID); // tim vi tri brand trong danh sach
      
          if (pos < 0) System.out.println("This brand does not exist!");
            else {
                Brand found = this.get(pos);
                System.out.printf("%s %s %s %.1fB\n", 
                found.getBrandID(), found.getBrandName(), 
                found.getSoundBrand(), found.getPrice());
          }
      }
      ```
    ],
  )
]

b. Tìm cars theo 1 phần tên brand

Sau khi người dùng nhập một chuỗi bất kì _(ở đây input của người dùng sẽ được đưa vào chuỗi có tên là partOfBrandName)_.
Ta sẽ thực hiện lấy input của người dùng và so sánh nó với từng tên brand có trong danh sách. Để tránh xảy ra trường hợp lỗi không mong muốn, ta nên thực hiện đưa tất cả các chữ về cùng một format _(đều cùng là chữ hoa hoặc thường)_.

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      for (Car c : this) {
          String partOfBrandName = Inputter.getString(
            "Enter part of brand name: ",
            iConstants.CasualString
          );
          
          ArrayList<Car> cars = new ArrayList<>();
          
          // Lay ten brand cua xe hien tai va chuyen thanh chu thuong
          String brandName = c.getBrand().getBrandName().toLowerCase();
          
          // Kiem tra xem ten brand co chua phan text ma user nhap khong
          if (brandName.contains(partOfBrandName.toLowerCase())) {
              cars.add(c);
          }
      }
      ```
    ],
  )
]

=== 3.3. Sorting và Filtering
a. Lọc các brand theo giá

Lọc các brand có giá nhỏ hơn hoặc bằng giá trị price nhập vào:

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [
      ```java
      public void listBrandsLessThanPrice() {
          double price = Inputter.readPositiveDouble("Enter Price: ", 
            "Price must be positive!"
          );
            
          ArrayList<Brand> result = new ArrayList<>();
          
          for (Brand b : this) {
              if (b.getPrice() <= price) result.add(b);
          }
          
          displayBrands(result);
      }
      ```
    ],
  )
]

b. Sắp xếp cars theo giá trị tăng dần của brand names _(ascending)_, nếu brand names trùng thì sắp xếp theo giá trị price giảm dần _(descending)_.

Chức năng này yêu cầu sắp xếp xe theo *_brand name_* tăng dần (A → Z), nếu trùng brand thì sắp xếp theo *_price_* giảm dần (cao → thấp). Để thực hiện, ta cần tạo bản sao của danh sách gốc nhằm tránh làm thay đổi thứ tự ban đầu, sau đó áp dụng method ```java sort()``` kết hợp lambda expression để định nghĩa logic so sánh hai mức. Mức đầu tiên so sánh brand name bằng *`compareToIgnoreCase`*() (không phân biệt hoa/thường). Nếu brand giống nhau, chuyển sang mức hai là so sánh price bằng *`Double.compare`*() với tham số đảo ngược (c2, c1) để đạt thứ tự giảm dần.

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [
      ```java
      public void listCars() {
          // tao bang sao danh sach car
          ArrayList<Car> cars = new ArrayList<>(this);
          cars.sort((c1, c2) -> {
              // so sanh ten brand khong phan biet chu hoa, thuong
              int brandCompare = c1.getBrand().getBrandName()
                            .compareToIgnoreCase(c2.getBrand().getBrandName());                         
              // neu brand khac nhau -> return ket qua so sanh brand
              if (brandCompare != 0) return brandCompare;
              // Neu brand trung nhau, sap xep theo price giam dan
              return Double.compare(
                  c2.getBrand().getPrice(), c1.getBrand().getPrice()
              );
          });
          displayCars(cars);
      }
      ```
    ],
  )
]

c. Hiển thị danh sách cars theo màu được chọn

Tìm và lọc tất cả các xe có màu khớp với màu mà user nhập vào. Ví dụ, nếu người dùng nhập màu đỏ (Red) thì sẽ trả về tất cả những chiếc xe có màu đỏ. 

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [
      ```java
      public void listCarsWithColor() {
          String color = Inputter.getString(
            "Enter color: ", 
            iConstants.StringButOnlyAlphabetAllowed
          );
          
          ArrayList<Car> cars = new ArrayList<>();
          
          for (Car c : this) {
              if (c.getColor().equalsIgnoreCase(color)) cars.add(c);
          }
  
          if (cars.isEmpty()) {
              System.out.print("No car found with color: " + color);
          } else displayCars(cars);
      }
      ```
    ],
  )
]
=== 3.4. Update

Khi cập nhật, sẽ có trường hợp user muốn thay đổi giá trị của một hay nhiều dữ liệu nào đó (không nhất thiết phải là thay đổi toàn bộ). Thay vì bắt buộc người dùng nhập giá trị mới cho tất cả các field, ta nên cho phép họ nhấn Enter (chuỗi rỗng) để giữ nguyên giá trị hiện tại của những field không muốn thay đổi.

Ví dụ: Cập nhật field color trong hàm *```java updateCar```*.

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [
      ```java
      String newColor = Inputter.getStringUpdate(
          "Enter new Color: ", pattern);
          
      if (!newColor.isEmpty()) {
          currentCar.setColor(Inputter.capitalizeWords(newColor));
      }
      
      // Neu rong thi khong thay doi gi
       
      ```
    ],
  )
]

Cơ chế hoạt động của hàm *`getStringUpdate()`*:

#align(center)[
  #table(
    columns: (97%),
    align: left,
    [
      ```java
      public static String getStringUpdate(String msg, String pattern) {
          while (true) {
              System.out.print(msg);
              
              // doc va cat khoang trang
              String input = sc.nextLine().trim();
              if (input.isEmpty()) {
                  System.out.println("No change!");
                  return input; // tra ve chuoi rong (khong thay doi)
              } else {
                  if (input.matches(pattern)) {
                      System.out.println("New data has been updated.");
                      return input;
                  } else System.err.println("Input not valid.");
              }
          }
      }
      ```
    ],
  )
]

- Nếu người dùng nhấn Enter _(chuỗi rỗng)_: trả về "" và in "No change!".
- Nếu là một _chuỗi hợp lệ_: trả về chuỗi đó và in "New data updated".
- Nếu _không hợp lệ:_ yêu cầu nhập lại.

a. Update brand

Trước khi update, cần tìm brand theo ID và kiểm tra sự tồn tại. Nếu không tồn tại, dừng ngay để tránh chương trình báo lỗi. Mỗi field vẫn nên được validate qua pattern, đảm bảo dữ liệu mới (nếu có) vẫn hợp lệ. Sound Brand nên được capitalize để đồng nhất format (vd: "harman kardon" → "Harman Kardon").

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void updateBrand() {
          String brandID = Inputter.getString(
          "Enter Brand ID to update: ", iConstants.CasualString);
          
          int pos = searchID(brandID); // tim vi tri brand trong danh sach
          
          if (pos < 0) {
              System.out.println("\nThis brand does not exist!");
              return;
          }
  
          // Update Brand Name
          String name = Inputter.getStringUpdate(
            "Enter new Brand Name: ", 
            iConstants.CasualString
          );
          if (!name.isEmpty()) {
              this.get(pos).setBrandName(name);
          }
  
          // Update Sound Brand
          String sound = Inputter.getStringUpdate(
            "Enter new Sound Brand: ", 
            iConstants.StringButOnlyAlphabetAllowed
          );
          if (!sound.isEmpty()) {
              this.get(pos).setSoundBrand(Inputter.capitalizeWords(sound));
          }
  
          // Update Price
          String priceStr = Inputter.getStringUpdate(
            "Enter new Price: ", 
            iConstants.thePrice
          );
          if (!priceStr.isEmpty()) {
              this.get(pos).setPrice(Double.parseDouble(priceStr));
          }
          
          System.out.println("\nBrand updated successfully!");
      }
      ```
    ],
  )
]

b. Update car

Tương tự với chức năng Update của brand, nhưng Update car _phức tạp hơn_ do phải xử lý nhiều yếu tố. 

Brand là một *relational data* _(object reference)_, không phải là _primitive type_ như *String* hay *double*, nên việc thay đổi brand cần có sự xác nhận từ user. Ngoài ra, Frame ID và Engine ID phải là _duy nhất_ trong toàn bộ danh sách, ngoại trừ chính xe đang được update để tránh báo lỗi khi user giữ nguyên giá trị cũ.

Để thực hiện việc cập nhật, đầu tiên hệ thống cần hiển thị thông tin hiện tại của xe để user có thể đối chiếu và quyết định xem cần thay đổi những gì. Tiếp theo, vì brand là thuộc tính quan trọng ảnh hưởng đến giá trị xe, hệ thống sẽ hỏi trước "_Bạn có muốn update brand không?_". Nếu user chọn "Không" thì chương trình thoát. Ngược lại, nếu chọn "Có", hệ thống gọi *`getUserChoice`*() để hiển thị menu cho phép chọn brand mới.

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void updateCar() {
          String carID = Inputter.readString("Enter Car ID to update: ");
          int pos = searchID(carID);

          if (pos < 0) {
              System.err.println("Car ID '" + carID + "' does not exist!");
              return;
          }
  
          Car currentCar = this.get(pos);
          
          System.out.println("Current Car Information:");
          displayCarInfo(currentCar);
          boolean updateBrand = Inputter.confirmation(
            "Do you want to update the brand? (Y/N): "
          );
          
          if (!updateBrand) {
              System.out.println("Car update cancelled.");
              return;
          }
          
          // Update brand
          Brand newBrand = brandList.getUserChoice();
          if (newBrand != null) currentCar.setBrand(newBrand);
  
          // Update Color
          String newColor = Inputter.getStringUpdate(
            "Enter new Color: ", 
            iConstants.StringButOnlyAlphabetAllowed
          );
          if (!newColor.isEmpty()) 
          currentCar.setColor(Inputter.capitalizeWords(newColor));
  
          // Update Frame ID
          String newFrameID = Inputter.getStringUpdate(
            "Enter new Frame ID (F00000 format): ", 
            iConstants.StringOnlyForFrameFormat
          );
          
          if (!newFrameID.isEmpty()) {
              int framePos = searchFrame(newFrameID);
              if (framePos >= 0 && framePos != pos) {
                System.err.println("Frame ID already exists!");
              } else currentCar.setFrameID(newFrameID.toUpperCase());
          }
  
          // Update Engine ID
          String newEngineID = Inputter.getStringUpdate(
            "Enter new Engine ID (E00000 format): ", 
            iConstants.StringOnlyForEngineFormat
          );
          
          if (!newEngineID.isEmpty()) {
              int enginePos = searchEngine(newEngineID);
              if (enginePos >= 0 && enginePos != pos) {
                System.err.println("Engine ID already exists!");
            } else currentCar.setEngineID(newEngineID.toUpperCase());
          }
  
          System.out.println("Car updated successfully!");
      }
      ```
    ],
  )
]

=== 3.5. Remove a car by ID

Ta nên yêu cầu confirmation để tránh xóa nhầm dữ liệu quan trọng:

#align(center)[
  #table(
    columns: (100%),
    align: left,
    [
      ```java
      public void removeCar() {
          String removeID = Inputter.readString("Enter car id to remove: ");
          
          // Lay thong tin car can xoa
          Car removedCar = this.get(pos);
          
          // Hien thi thong tin de xac nhan va yeu cau xac nhan
          boolean confirm = Inputter.confirmation(
              "Are you sure you want to remove this car? (Y/N): "
          );
          
          if (confirm) this.remove(pos); // Thuc hien xoa neu confirm
            else System.out.println("Car removal cancelled.");
      }
      ```
    ],
  )
]

*Tại sao ta lại cần phải confirmation?*

Chúng ta cần *confirmation* là để tránh phải xóa nhầm dữ liệu quan trọng và để cho user có cơ hội kiểm tra lại thông tin trước khi xóa.

== 4. Menu-driven Architecture

Class Manager chính là nơi để điều khiển flow chính của chương trình. Nó cũng sẽ đảm nhiệm hai vai trò: tải dữ liệu từ các tệp *_brands.txt_*, *_cars.txt_* lúc khởi động và quản lý việc lưu dữ liệu vào các tệp này khi cần thiết.

#align(center)[
  #table(
    columns: (60%),
    align: left,
    [
      #align(center)[
        *Manager*
      ]
    ],
    
    [

    ],
    
    [
      \+ main(String[] args): void <\<static>>\ 
    ],
  )
]

= III. Mô hình triển khai mã nguồn

\
Trong các dự án phần mềm thực tế, việc quản lý source code sẽ trở nên ngày càng phức tạp khi số lượng files mã nguồn dần tăng lên cùng với việc nhiều nhà phát triển cùng làm việc chung với những file mã nguồn đấy. Vấn đề không chỉ dừng lại ở giai đoạn phát triển mà còn ảnh hưởng đến khả năng bảo trì và mở rộng hệ thống về sau. 

Để giải quyết vấn đề này, project áp dụng *_n-Layer Architecture_* (mô hình tổ chức code theo các tầng), mỗi tầng đảm nhiệm vai trò cụ thể và được triển khai bằng một Java package để gom nhóm các lớp có liên quan.


Dự án Car Showroom này được tổ chức thành 4 tầng chính:

- Những mã nguồn chỉ phục vụ cho việc mô tả dữ liệu chính như: *Brand* class, *Car* class thì được quản lý bởi *model* pakage, sẽ được gọi là tầng thứ nhất: _*models layer*_.

- Những mã nguồn chuyên thực hiện các nghiệp vụ (add, showAll, searchByName, update, delete, filter, …) có liên quan đến *models layer* như Brand class, Car class thì được quản lý bởi *services* package, sẽ được gọi là tầng thứ hai: _*business layer*_.

- *Manager* class có nhiệm vụ xây dựng giao diện, xử lý tương tác với người dùng đồng thời gọi *business layer* để thực thi yêu cầu thì được quản lý bởi *view* package, tầng thứ ba này sẽ được gọi là _*control layer*_.

- Những mã nguồn còn lại không tham gia vào quy trình xử lý theo các tầng đã thiết kế nhưng có thể tham gia ở bất kỳ nơi nào trong chương trình để phục vụ cho quá trình xử lý thì được quản lý bởi *mylib* package và tầng này sẽ được gọi là _*neutral layer*_.

Sơ đồ thiết kế được dùng cho project sẽ có dạng như sau:
\ \

#figure(
  image("1.jpg", width: 111%),
)

\

Với *Car Showroom* project, áp dụng thiết kế trên sẽ có cấu trúc mô tả sau:

\ \ \ \ \

#align(center)[
  #table(
    columns: (60%),
    align: left,
    [
      ```
      
        src
        ├── model (Models layer)
        │   ├── Brand.java
        │   └── Car.java
        ├── services (Business layer)
        │   ├── BrandList.java
        │   ├── CarList.java
        │   └── iConstants.java
        ├── view (Control layer)
        │   └── Manager.java
        └── mylib (Neutral layer)
            └── Inputter.java
            
      ```
    ],
  )
]

#figure(
  image("2.png", width: 45%),
)

= IV. Kết luận
Chương trình *Car Showroom Management* đã được triển khai thành công với đầy đủ *13 chức năng* theo yêu cầu đề bài. Hệ thống áp dụng các nguyên tắc OOP, đảm bảo tính mở rộng và bảo trì. Validation được thực hiện nghiêm ngặt ở mọi điểm nhập liệu và đảm bảo tính toàn vẹn dữ liệu.
