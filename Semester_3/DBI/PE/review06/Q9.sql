CREATE TRIGGER deleteGenres
ON Genres
INSTEAD OF DELETE
AS
BEGIN
    
    UPDATE Books 
    SET GenreID = NULL 
    WHERE GenreID IN (SELECT GenreID FROM deleted);
    
    DELETE FROM Genres 
    WHERE GenreID IN (SELECT GenreID FROM deleted);
END;

