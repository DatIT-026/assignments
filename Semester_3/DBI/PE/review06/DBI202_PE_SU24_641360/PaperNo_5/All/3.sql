SELECT b.BookID, b.Title, b.PublicationYear, g.GenreID, g.GenreName 
FROM Books b
JOIN Genres g ON g.GenreID = b.GenreID
WHERE PublicationYear <= 1900 AND GenreName = 'Adventure'