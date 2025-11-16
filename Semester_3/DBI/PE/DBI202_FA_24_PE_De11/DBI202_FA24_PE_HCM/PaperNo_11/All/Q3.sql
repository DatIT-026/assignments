SELECT c.CustomerID, c.FullName, r.ReservationTime, rt.TableID, t.Location FROM Customers c
JOIN Reservations r ON r.CustomerID = c.CustomerID
JOIN ReservationTables rt ON rt.ReservationID = r.ReservationID
JOIN Tables t ON t.TableID = rt.TableID
WHERE YEAR(ReservationTime) = '2025' AND Location LIKE 'Khu A%'