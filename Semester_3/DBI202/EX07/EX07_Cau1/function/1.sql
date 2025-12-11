CREATE FUNCTION fn_ThongKeSLDatTheoNhaCungCap()
RETURNS TABLE
AS
RETURN
(
    SELECT dh.MaNCC, 
        SUM(ct.SLDat) AS TongSoLuongDat
    FROM DONDATHANG dh
    JOIN CTDATHANG ct ON dh.SoDH = ct.SoDH
    GROUP BY dh.MaNCC
);