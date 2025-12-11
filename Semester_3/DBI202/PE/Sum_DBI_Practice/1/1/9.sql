-- Tạo trigger tên InsertSubCategory
-- nó sẽ kích hoạt tự động mỗi khi có lệnh INSERT vào bảng SubCategory.
-- AFTER INSERT nghĩa là sau khi dữ liệu được chèn thành công, trigger mới chạy.
CREATE TRIGGER InsertSubCategory ON SubCategory
AFTER INSERT
AS

BEGIN
    SET NOCOUNT ON;
    SELECT i.SubCategoryName, c.CategoryName FROM inserted i
    JOIN Category c ON i.CategoryID = c.ID;
END;

-- test case
insert into SubCategory(SubCategoryName, CategoryID)
values('Beds', 2)

SELECT SubCategoryName, CategoryName FROM SubCategory s
JOIN Category c ON c.ID = s.CategoryID