SELECT a.authorId, a.name AS authorName, a.surname AS authorsurname,
		COUNT(DISTINCT bk.bookId) AS NumberOfBooks, 
		COUNT(DISTINCT bw.borrowId) AS NumberOfBorrow
FROM authors a
JOIN books bk ON bk.authorId = a.authorId
JOIN borrows bw ON bw.bookId = bk.bookId
GROUP BY a.authorId, a.name, a.surname
ORDER BY a.authorId