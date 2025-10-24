CREATE FUNCTION fn_LayThongTinPhieuXuat (@year INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongSoPhieu INT;
    
    SELECT @TongSoPhieu = COUNT(SoPX) FROM PHIEUXUAT
    WHERE YEAR(NgayXuat) = @year;
    
    RETURN ISNULL(@TongSoPhieu, 0);
END;