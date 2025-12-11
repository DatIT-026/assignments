CREATE FUNCTION F1(@diagnosis nvarchar(255), @year int) RETURNS INT
BEGIN
	DECLARE @NumberOfPatients INT
	SELECT @NumberOfPatients = COUNT(DISTINCT PatientID) FROM MedicalRecords
	WHERE Diagnosis = @diagnosis AND YEAR(RecordDate) = @year
	RETURN @NumberOfPatients
END

-- test
-- SELECT DISTINCT Diagnosis, YEAR(RecordDate) as YEAR, dbo.F1(Diagnosis, YEAR(RecordDate)) as NumberOfPatients FROM MedicalRecords
-- WHERE RecordDate < '2022-01-01'