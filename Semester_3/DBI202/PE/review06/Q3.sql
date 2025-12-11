SELECT 
	b.BookID,
	b.Title,
	b.PublicationYear,
	g.GenreID,
	g.GenreName
FROM Books b 
JOIN Genres g ON g.GenreID = b.GenreID
WHERE b.PublicationYear <= 1900 AND g.GenreName = 'Adventure' 