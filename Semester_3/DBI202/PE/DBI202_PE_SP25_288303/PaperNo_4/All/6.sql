SELECT Specialty, DoctorID, FullName, SumOfTotalAmount
FROM (
	SELECT d.Specialty, d.DoctorID, d.FullName,
		   SUM(b.TotalAmount) AS SumOfTotalAmount,
		   RANK() OVER(
				PARTITION BY d.Specialty
				ORDER BY SUM(b.totalAmount) DESC
		   ) AS RankNum
	FROM Doctors d
	JOIN Appointments a ON d.DoctorID = a.DoctorID
	JOIN Billing b ON b.AppointmentID = a.AppointmentID
	GROUP BY d.Specialty, d.DoctorID, d.FullName
) AS RankedDoctors
WHERE RankNum = 1 ORDER BY Specialty, DoctorID