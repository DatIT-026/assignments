SELECT 
	d.DoctorID, d.FullName AS DoctorFullName,
	pr.PrescriptionID, pr.Medicine, pr.Dosage, pr.PrescriptionDate,
	p.PatientID, p.FirstName + ' ' + p.LastName AS PatientFullName
FROM Doctors d
LEFT JOIN Prescriptions pr ON pr.DoctorID = d.DoctorID
		AND pr.PrescriptionDate BETWEEN '2020-05-15' AND '2020-09-15'
LEFT JOIN Patients p ON p.PatientID = pr.PatientID
ORDER BY DoctorFullName, PrescriptionDate