
INSERT INTO Reservations (
    ReservationID, 
    CustomerID, 
    ReservationTime, 
    GuestCount, 
    Status
)
VALUES (210, 7, '2025-11-26 19:15:00', 5, 'Confirmed');

INSERT INTO ReservationTables (
    ReservationID, 
    TableID
)
VALUES (210, 
    (
        SELECT TOP 1 TableID
        FROM Tables
        WHERE Capacity >= 5
        ORDER BY TableID ASC
    )
);
