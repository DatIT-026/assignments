SELECT DISTINCT g.GenreID, g.GenreName
FROM Genres g
JOIN Books b ON g.GenreID = b.GenreID
JOIN BookCopies bc ON b.BookID = bc.BookID
JOIN LoanDetails ld ON bc.CopyID = ld.CopyID
JOIN Loans l ON ld.LoanID = l.LoanID
WHERE b.PublicationYear > 1980
ORDER BY g.GenreName ASC;