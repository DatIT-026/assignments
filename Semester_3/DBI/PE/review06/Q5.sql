SELECT 
	m.MemberID,
	m.MemberName,
	m.Sex,
	COUNT(bc.CopyID) AS NumberOfBookCopies
FROM Members m 
LEFT JOIN Loans l ON 
	l.MemberID = m.MemberID 
	AND YEAR(l.LoanDate) = 2020
LEFT JOIN LoanDetails ld ON ld.LoanID = l.LoanID
LEFT JOIN BookCopies bc ON bc.CopyID = ld.CopyID 
WHERE m.Sex = 'Female'
GROUP BY m.MemberID, m.MemberName, m.Sex
ORDER BY 
	COUNT(bc.CopyID) DESC,
	m.MemberName ASC