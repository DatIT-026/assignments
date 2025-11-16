WITH CountLoans AS 
(
	SELECT
		YEAR(l.LoanDate) AS year,
		m.MemberID,
		m.MemberName,
		COUNT(l.LoanID) AS NumberOfLoans
	FROM Loans l
	JOIN Members m ON m.MemberID = l.MemberID
	GROUP BY YEAR(l.LoanDate) ,m.MemberID, m.MemberName
)

SELECT cl.*
FROM CountLoans cl
WHERE cl.NumberOfLoans = (
	SELECT MAX(cl2.NumberOfLoans)
	FROM CountLoans cl2
	WHERE cl2.year = cl.year
)
ORDER BY cl.year ASC, cl.MemberName ASC