SELECT bw.borrowId, bw.takenDate, bk.bookId, bk.name AS bookName, bk.typeId, t.name as typeName FROM borrows bw
JOIN books bk ON bk.bookId = bw.bookId
JOIN [types] t ON t.typeId = bk.typeId
WHERE (YEAR(bw.takenDate) = '2017' AND MONTH(bw.takenDate) = 03)
	AND (t.name LIKE 'Diaries' OR t.name LIKE 'Art')
ORDER BY typeName DESC, bw.borrowId