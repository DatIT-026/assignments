SELECT e.name AS EventName, l.locationID AS LocationName, s.name AS StaffName FROM Events e
JOIN Locations l ON l.locationID = e.locationID
JOIN workFor w ON w.eventID = e.eventID
JOIN Staffs s ON s.staffID = w.staffID