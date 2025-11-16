CREATE TRIGGER Tr2
ON books
FOR INSERT
AS
BEGIN
	SELECT i.bookId, i.name, i.pagecount, i.point, i.authorId, i.typeId
	FROM inserted i
	JOIN authors a ON a.authorId = i.authorId
END;

insert into books(bookId, name, pagecount, point, authorId, typeId) values
(500,'book 1', 250, 10, 1, 1),
(501,'book 2', 200, 15, 2, 3)
