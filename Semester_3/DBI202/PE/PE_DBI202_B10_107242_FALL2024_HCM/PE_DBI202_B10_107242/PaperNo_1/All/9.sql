-- cach 1: Recommended
UPDATE Staffs
SET phone = '999' + SUBSTRING(phone, 4, len(phone))
WHERE phone LIKE '555%'

-- cach 2: dung CASE
UPDATE Staffs
SET phone = CASE 
    WHEN phone LIKE '555%' THEN '999' + SUBSTRING(phone, 4, len(phone))
    ELSE phone
END

-- tool support for test case
SELECT * FROM Staffs

DELETE FROM Staffs
WHERE Phone LIKE '%999%';

INSERT INTO Staffs (name, Phone) VALUES
('John Doe', '555-987-6543'),
('Jane Smith', '555-654-3210'),
('Alice Brown', '555-123-7890'),
('Michael Clark', '555-234-9876'),
('Emily Johnson', '555-567-2345'),
('Kevin Wright', '555-789-6543')
