-- Khứa này là để tính số lần bọn này lặp lại
WITH DiagnosisCounts AS (
	SELECT DoctorID, Diagnosis,
		   COUNT(RecordID) AS DiagnosisCount
	FROM MedicalRecords 
	GROUP BY DoctorID, Diagnosis
),

-- Thằng này tìm max của khứa ở trên
MaxCountPerDoc AS (
	SELECT DoctorID, MAX(DiagnosisCount) AS MaxDiagnosisCount
	FROM DiagnosisCounts
	GROUP BY DoctorID
),

-- Lọc những thằng Max
MostCommonDiagnosisFiltered AS (
	SELECT dc.DoctorID, dc.Diagnosis FROM DiagnosisCounts dc
	JOIN MaxCountPerDoc mc ON mc.DoctorID = dc.DoctorID
	-- điều kiện lọc
	AND dc.DiagnosisCount = mc.MaxDiagnosisCount
)

SELECT DISTINCT d.DoctorID, d.FullName AS DoctorFullName,
	   mcdf.Diagnosis, 
	   p.PatientID, p.FirstName + ' ' + p.LastName AS PatientFullName
FROM MostCommonDiagnosisFiltered mcdf
JOIN Doctors d ON d.DoctorID = mcdf.DoctorID
JOIN MedicalRecords m ON m.DoctorID = d.DoctorID
	 AND m.Diagnosis = mcdf.Diagnosis
JOIN Patients p ON p.PatientID = m.PatientID
ORDER BY DoctorFullName, mcdf.Diagnosis, PatientID