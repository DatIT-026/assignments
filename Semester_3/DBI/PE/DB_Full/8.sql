CREATE PROCEDURE proc_university_year 
	@year int, 
	@pct_international_students int, 
	@nbUniversity int OUTPUT
AS
BEGIN
    SELECT @nbUniversity = COUNT(university_id) 
	FROM university_year
    WHERE pct_international_students > @pct_international_students AND year = @year
	RETURN
END

DECLARE @out int
exec proc_university_year 2011, 30, @out output
select @out as NumberOfUniversities