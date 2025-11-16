SELECT 
    t.TableID, t.Capacity, t.Location, c.CustomerID, c.FullName
FROM Tables t
LEFT JOIN (
    SELECT rt.TableID, r.CustomerID, r.ReservationTime
    FROM ReservationTables rt
    JOIN Reservations r ON rt.ReservationID = r.ReservationID
    WHERE MONTH(r.ReservationTime) = 9 AND YEAR(r.ReservationTime) = 2025
) AS SeptReservations ON t.TableID = SeptReservations.TableID
LEFT JOIN Customers c ON SeptReservations.CustomerID = c.CustomerID
WHERE t.Location LIKE N'Khu A%' 
   OR t.Location LIKE N'Khu B%' 
   OR t.Location LIKE N'Khu C%'
ORDER BY t.Capacity DESC, t.Location