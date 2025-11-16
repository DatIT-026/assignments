-- them category 'Sports'
INSERT INTO Category(CategoryName)
VALUES ('Sports');

-- lay ID cua catelogy 'Sports'
DECLARE @CategoryID INT;
SELECT @CategoryID = ID FROM Category WHERE CategoryName = 'Sports';

-- them 2 subcatelogy 'Tennis' va 'Football' thuoc Catelogy 'Sports'
INSERT INTO SubCategory(SubCategoryName, CategoryID)
VALUES ('Tennis', @CategoryID),
       ('Football', @CategoryID);

-- test case
SELECT SubCategoryName, CategoryName FROM SubCategory s
JOIN Category c ON c.ID = s.CategoryID