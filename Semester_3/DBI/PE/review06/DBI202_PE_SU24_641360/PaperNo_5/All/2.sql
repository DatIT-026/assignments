SELECT MemberID, MemberName, Address, Email, Sex, BirthDate
FROM Members
WHERE Sex = 'Male' AND YEAR(BirthDate) > 1990;