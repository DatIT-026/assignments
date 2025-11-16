SELECT DISTINCT g.GenreID, g.GenreName FROM Genres g
JOIN Books b ON b.GenreID = g.GenreID
JOIN BookCopies bc ON bc.BookID = b.BookID
JOIN LoanDetails ld ON ld.CopyID = bc.CopyID
WHERE b.PublicationYear > 1980
ORDER BY g.GenreName