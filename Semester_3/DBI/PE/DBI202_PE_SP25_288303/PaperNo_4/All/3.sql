SELECT 
	a.AppointmentID, a.AppointmentDate,
	d.DoctorID,	d.FullName AS DoctorFullName, d.Specialty,
	p.PatientID, p.FirstName + ' ' + p.LastName AS PatientFullName
FROM Appointments a
JOIN Doctors d ON d.DoctorID = a.DoctorID 
JOIN Patients p ON a.PatientID = p.PatientID
WHERE Specialty = 'Orthopedics'