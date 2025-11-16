SELECT m.MemberID, m.MemberName, m.Sex,
    COUNT(DISTINCT ld.CopyID) AS NumberOfBookCopies
FROM Members m
LEFT JOIN Loans l ON m.MemberID = l.MemberID AND YEAR(l.LoanDate) = 2020
LEFT JOIN LoanDetails ld ON l.LoanID = ld.LoanID
WHERE m.Sex = 'Female'
GROUP BY m.MemberID, m.MemberName, m.Sex
ORDER BY NumberOfBookCopies DESC, m.MemberName;