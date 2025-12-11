SELECT 
	g.GenreID,
	g.GenreName,
	COUNT(DISTINCT CASE WHEN m.Sex = 'Male' THEN m.MemberID END) AS NumberOfMaleMembers,
	COUNT(DISTINCT CASE WHEN m.Sex = 'Female' THEN m.MemberID END) AS NumberOfFemaleMembers
FROM Genres g
LEFT JOIN Books b ON b.GenreID = g.GenreID
LEFT JOIN BookCopies bc ON bc.BookID = b.BookID
LEFT JOIN LoanDetails ld ON ld.CopyID = bc.CopyID
LEFT JOIN Loans l ON l.LoanID = ld.LoanID AND YEAR(l.LoanDate) = 2020
LEFT JOIN Members m ON m.MemberID = l.MemberID
GROUP BY g.GenreID, g.GenreName
