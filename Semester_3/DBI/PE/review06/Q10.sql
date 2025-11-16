UPDATE Books 
SET GenreID = (SELECT GenreID FROM Genres WHERE GenreName = 'Horror')
WHERE Title = '1984';