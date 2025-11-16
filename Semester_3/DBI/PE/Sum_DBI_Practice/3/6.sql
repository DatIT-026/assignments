SELECT t.typeId, t.name AS typeName, 
	   COUNT(bk.bookId) as NumberOfBooks
FROM [types] t
JOIN books bk ON bk.typeId = t.typeId
GROUP BY t.typeId, t.name
HAVING COUNT(bk.bookId) = ( 
	SELECT MAX(countBooks) FROM ( 
		SELECT t.typeId, COUNT(DISTINCT bk.bookId) AS countBooks
		FROM books bk
		JOIN [types] t ON t.typeId = bk.typeId
		GROUP BY t.typeId
	) AS meCountBooks
) 