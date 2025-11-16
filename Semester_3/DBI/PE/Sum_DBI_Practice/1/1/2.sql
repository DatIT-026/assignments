SELECT ID, CustomerName, Country, City, State, PostalCode, Region 
FROM Customer
WHERE Segment = 'Consumer' AND City = 'Arlington'