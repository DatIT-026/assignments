SELECT d.DoctorID, d.FullName, d.Specialty,
	   COUNT(ISNULL(a.AppointmentID, 0)) AS NumberOfAppointments, 
 	   SUM(ISNULL(b.TotalAmount, 0)) AS SumOfBillingTotalAmount
FROM Doctors d
LEFT JOIN Appointments a ON a.DoctorID = d.DoctorID
	AND YEAR(a.AppointmentDate) IN (2021, 2022)
LEFT JOIN Billing b ON b.AppointmentID = a.AppointmentID
	AND YEAR(a.AppointmentDate) IN (2021, 2022)
WHERE Specialty = 'Neurology'
GROUP BY d.DoctorID, d.FullName, d.Specialty
