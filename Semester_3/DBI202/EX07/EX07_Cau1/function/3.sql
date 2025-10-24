CREATE FUNCTION fn_LayThongTinPhieuNhap (@SoPN VARCHAR(5))
RETURNS FLOAT
AS
BEGIN
    DECLARE @TongTien FLOAT;
    
	-- tinh tong bang BIGINT trc de tranh tran so, sau do CAST sang FLOAT
    SELECT @TongTien = CAST(SUM(CAST(SLNhap AS BIGINT) * DGNhap) AS FLOAT)
    FROM CTPHIEUNHAP
    WHERE SoPN = @SoPN;
    
    RETURN ISNULL(@TongTien, 0);
END;