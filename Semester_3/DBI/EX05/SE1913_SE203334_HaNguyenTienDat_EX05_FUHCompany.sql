-- 1. Cho biết ai đang quản lý phòng ban có tên: Phòng Nghiên cứu và phát triển.
SELECT E.empSSN AS N'Mã số', E.empName AS N'Họ tên',
       D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban'
FROM tblDepartment D
JOIN tblEmployee E ON D.mgrSSN = E.empSSN
WHERE D.depName = N'Phòng Nghiên cứu và phát triển';

SELECT * FROM tblEmployee ORDER BY empSSN;

-- 2. Cho phòng ban có tên: Phòng Nghiên cứu và phát triển hiện đang quản lý dự án nào.
SELECT P.proNum AS N'Mã số dự án', P.proName AS N'Tên dự án',
	   D.depName AS N'Tên phòng ban'
FROM tblProject P
JOIN tblDepartment D ON P.depNum = D.depNum
WHERE D.depName = N'Phòng Nghiên cứu và phát triển';

-- 3. Dự án "ProjectB" do phòng ban nào quản lý?
SELECT P.proNum AS N'Mã số dự án', P.proName AS N'Tên dự án',
	   D.depName AS N'Tên phòng ban'
FROM tblProject P
JOIN tblDepartment D ON P.depNum = D.depNum
WHERE P.proName = 'ProjectB';

-- 4. Nhân viên nào bị giám sát bởi "Mai Duy An"?
SELECT E.empSSN AS N'Mã số nhân viên', E.empName AS N'Họ tên'
FROM tblEmployee E
JOIN tblEmployee Supervisor ON E.supervisorSSN = Supervisor.empSSN
WHERE Supervisor.empName = N'Mai Duy An';

-- 5. Ai đang giám sát nhân viên "Mai Duy An"?
SELECT Supervisor.empSSN AS N'Mã số người giám sát', Supervisor.empName AS N'Họ tên người giám sát'
FROM tblEmployee E
JOIN tblEmployee Supervisor ON E.supervisorSSN = Supervisor.empSSN
WHERE E.empName = N'Mai Duy An';

-- 6. Dự án có tên `ProjectA` hiện đang làm việc ở đâu?
SELECT L.locNum AS N'Mã số', L.locName AS N'Tên vị trí' FROM tblProject p
JOIN tblLocation l ON p.locNum = l.locNum
WHERE p.proName = 'ProjectA'

-- 7. Vị trí làm việc có tên Tp. HCM hiện đang là chỗ làm việc của những dự án nào?
SELECT p.proNum AS N'Mã số', p.proName AS N'Tên dự án' FROM tblProject p
JOIN tblLocation l ON  p.locNum = l.locNum
WHERE l.locName = N'TP Hồ Chí Minh'

-- 8. Cho biết những người phụ thuộc trên 18 tuổi
SELECT D.depName AS N'Tên người phụ thuộc', D.depBirthdate AS N'Ngày sinh',
       E.empName AS N'Tên nhân viên'
FROM tblDependent d
JOIN tblEmployee e ON d.empSSN = e.empSSN
WHERE DATEDIFF(YEAR, d.depBirthdate, GETDATE()) > 18

-- 9. Cho biết những người phụ thuộc là nam giới
SELECT D.depName AS N'Tên người phụ thuộc', D.depBirthdate AS N'Ngày sinh',
       E.empName AS N'Tên nhân viên'
FROM tblDependent d
JOIN tblEmployee e ON d.empSSN = e.empSSN
WHERE D.depSex = 'M'

-- 10. Nơi làm việc của "Phòng Nghiên cứu và phát triển"
SELECT D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
       L.locName AS N'Tên nơi làm việc'
FROM tblDepartment d
JOIN tblDepLocation dl ON dl.depNum = d.depNum
JOIN tblLocation l ON l.locNum = dl.locNum
WHERE d.depName = N'Phòng Nghiên cứu và phát triển'

-- 11. Các dự án làm việc tại "Tp. HCM"
SELECT p.proNum AS N'Mã dự án', p.proName AS N'Tên dự án',
	   d.depName AS N'Tên phòng ban'
FROM tblProject p
JOIN tblLocation l ON l.locNum = p.locNum
JOIN tblDepartment d ON d.depNum = p.depNum
WHERE l.locName = N'TP Hồ Chí Minh'

-- 12. Người phụ thuộc là nữ của nhân viên "Phòng Nghiên cứu và phát triển"
SELECT e.empName AS N'Tên nhân viên',
	   depent.depName AS N'Tên người phụ thuộc', depent.depRelationship AS N'Mối liên hệ'
FROM tblDependent depent
JOIN tblEmployee e ON e.empSSN = depent.empSSN
JOIN tblDepartment depart ON depart.depNum = e.depNum
WHERE depSex = 'F' AND depart.depName = N'Phòng Nghiên cứu và phát triển'

-- 13. Người phụ thuộc trên 18 tuổi của nhân viên "Phòng Nghiên cứu và phát triển"
SELECT e.empName AS N'Tên nhân viên',
	   depent.depName AS N'Tên người phụ thuộc', depent.depRelationship AS N'Mối liên hệ'
FROM tblDependent depent
JOIN tblEmployee e ON e.empSSN = depent.empSSN
JOIN tblDepartment depart ON depart.depNum = e.depNum
WHERE depart.depName = N'Phòng Nghiên cứu và phát triển'
    AND DATEDIFF(YEAR, depent.depBirthdate, GETDATE()) > 18

-- 14. Số lượng người phụ thuộc theo giới tính
SELECT depSex AS N'Giới tính', COUNT(*) AS N'Số lượng' FROM tblDependent
GROUP BY depSex

-- 15. Số lượng người phụ thuộc theo mối liên hệ
SELECT depRelationship AS N'Mối liên hệ', COUNT(*) AS N'Số lượng' FROM tblDependent
GROUP BY depRelationship

-- 16. Số lượng người phụ thuộc theo mỗi phòng ban
SELECT D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
	   COUNT(DEP.depName) AS N'Số lượng người phụ thuộc'
FROM tblDepartment D
LEFT JOIN tblEmployee E ON D.depNum = E.depNum
LEFT JOIN tblDependent DEP ON E.empSSN = DEP.empSSN
GROUP BY D.depNum, D.depName ORDER BY D.depNum

-- 17. Phòng ban có số lượng người phụ thuộc ít nhất
SELECT TOP 1 WITH TIES
    depNum AS N'Mã phòng ban', depName AS N'Tên phòng ban',
    NumDependents AS N'Số lượng người phụ thuộc'
FROM (
    SELECT D.depNum, D.depName,
		   COUNT(DEP.depName) AS NumDependents
    FROM tblDepartment D
    LEFT JOIN tblEmployee E ON D.depNum = E.depNum
    LEFT JOIN tblDependent DEP ON E.empSSN = DEP.empSSN
    GROUP BY D.depNum, D.depName
) AS DependentCounts
ORDER BY NumDependents ASC;

-- 18. Phòng ban có số lượng người phụ thuộc nhiều nhất
SELECT TOP 1 WITH TIES
    depNum AS N'Mã phòng ban',
    depName AS N'Tên phòng ban',
    NumDependents AS N'Số lượng người phụ thuộc'
FROM (
    SELECT D.depNum, D.depName,
		   COUNT(DEP.depName) AS NumDependents
    FROM tblDepartment D
    LEFT JOIN tblEmployee E ON D.depNum = E.depNum
    LEFT JOIN tblDependent DEP ON E.empSSN = DEP.empSSN
    GROUP BY D.depNum, D.depName
) AS DependentCounts
ORDER BY NumDependents DESC;

-- 19. Tổng số giờ tham gia dự án của mỗi nhân viên
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Tên nhân viên',
    D.depName AS N'Tên phòng ban',
    ISNULL(SUM(W.workHours), 0) AS N'Tổng số giờ'
FROM tblEmployee E
LEFT JOIN tblWorksOn W ON E.empSSN = W.empSSN
JOIN tblDepartment D ON E.depNum = D.depNum
GROUP BY E.empSSN, E.empName, D.depName ORDER BY E.empSSN;

-- 20. Tổng số giờ làm dự án của mỗi phòng ban
SELECT D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
    ISNULL(SUM(W.workHours), 0) AS N'Tổng số giờ'
FROM tblDepartment D
LEFT JOIN tblEmployee E ON D.depNum = E.depNum
LEFT JOIN tblWorksOn W ON E.empSSN = W.empSSN
GROUP BY D.depNum, D.depName
ORDER BY D.depNum;

-- 21. Cho biết nhân viên nào có số giờ tham gia dự án là ít nhất
SELECT TOP 1 WITH TIES
    E.empSSN AS N'Mã nhân viên', E.empName AS N'Tên nhân viên',
    SUM(W.workHours) AS N'Tổng số giờ'
FROM tblEmployee E
JOIN tblWorksOn W ON E.empSSN = W.empSSN
GROUP BY E.empSSN, E.empName
ORDER BY N'Tổng số giờ' ASC;

-- 22. Cho biết nhân viên nào có số giờ tham gia dự án là nhiều nhất.
SELECT TOP 1 WITH TIES
    E.empSSN AS N'Mã nhân viên',
    E.empName AS N'Tên nhân viên',
    SUM(W.workHours) AS N'Tổng số giờ'
FROM tblEmployee E
JOIN tblWorksOn W ON E.empSSN = W.empSSN
GROUP BY E.empSSN, E.empName
ORDER BY N'Tổng số giờ' DESC;

-- 23. Cho biết những nhân viên nào lần đầu tiên tham gia dụ án.
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Tên nhân viên',
	   D.depName AS N'Tên phòng ban'
FROM tblEmployee E
JOIN tblWorksOn W ON E.empSSN = W.empSSN
JOIN tblDepartment D ON E.depNum = D.depNum
GROUP BY E.empSSN, E.empName, D.depName
HAVING COUNT(W.proNum) = 1;

-- 24. Cho biết những nhân viên nào lần thứ hai tham gia dụ án.
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Tên nhân viên',
       D.depName AS N'Tên phòng ban'
FROM tblEmployee E
JOIN tblWorksOn W ON E.empSSN = W.empSSN
JOIN tblDepartment D ON E.depNum = D.depNum
GROUP BY E.empSSN, E.empName, D.depName
HAVING COUNT(W.proNum) = 2;

-- 25. Cho biết những nhân viên nào tham gia tối thiểu hai dụ án.
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Tên nhân viên',
       D.depName AS N'Tên phòng ban'
FROM tblEmployee E
JOIN tblWorksOn W ON E.empSSN = W.empSSN
JOIN tblDepartment D ON E.depNum = D.depNum
GROUP BY E.empSSN, E.empName, D.depName
HAVING COUNT(W.proNum) >= 2;

-- 26. Cho biết số lượng thành viên của mỗi dự án.
SELECT P.proNum AS N'Mã dự án', P.proName AS N'Tên dự án',
    COUNT(W.empSSN) AS N'Số lượng thành viên'
FROM tblProject P
LEFT JOIN tblWorksOn W ON P.proNum = W.proNum
GROUP BY P.proNum, P.proName ORDER BY P.proNum;

-- 27. Cho biết tổng số giờ làm của mỗi dự án.
SELECT P.proNum AS N'Mã dự án', P.proName AS N'Tên dự án',
	   ISNULL(SUM(W.workHours), 0) AS N'Tổng số giờ'
FROM tblProject P
LEFT JOIN tblWorksOn W ON P.proNum = W.proNum
GROUP BY P.proNum, P.proName ORDER BY P.proNum;

-- 28. Cho biết dự án nào có số lượng thành viên là ít nhất.
SELECT TOP 1 WITH TIES
    P.proNum AS N'Mã dự án', P.proName AS N'Tên dự án',
    COUNT(W.empSSN) AS N'Số lượng thành viên'
FROM tblProject P
JOIN tblWorksOn W ON P.proNum = W.proNum
GROUP BY P.proNum, P.proName ORDER BY N'Số lượng thành viên' ASC;

-- 29. Cho biết dự án nào có số lượng thành viên là nhiều nhất.
SELECT TOP 1 WITH TIES
    P.proNum AS N'Mã dự án',
    P.proName AS N'Tên dự án',
    COUNT(W.empSSN) AS N'Số lượng thành viên'
FROM tblProject P
JOIN tblWorksOn W ON P.proNum = W.proNum
GROUP BY P.proNum, P.proName ORDER BY N'Số lượng thành viên' DESC;

-- 30. Cho biết dự án nào có tổng số giờ làm là ít nhất.
SELECT TOP 1 WITH TIES
    P.proNum AS N'Mã dự án',
    P.proName AS N'Tên dự án',
    SUM(W.workHours) AS N'Tổng số giờ'
FROM tblProject P
JOIN tblWorksOn W ON P.proNum = W.proNum
GROUP BY P.proNum, P.proName ORDER BY N'Tổng số giờ' ASC;

-- 31. Cho biết dự án nào có tổng số giờ làm là nhiều nhất.
SELECT TOP 1 WITH TIES
    P.proNum AS N'Mã dự án',
    P.proName AS N'Tên dự án',
    SUM(W.workHours) AS N'Tổng số giờ'
FROM tblProject P
JOIN tblWorksOn W ON P.proNum = W.proNum
GROUP BY P.proNum, P.proName ORDER BY N'Tổng số giờ' DESC;

-- 32. Cho biết số lượng phòng ban làm việc theo mỗi nơi làm việc.
SELECT L.locName AS N'Tên nơi làm việc',
    COUNT(DL.depNum) AS N'Số lượng phòng ban'
FROM tblLocation L
LEFT JOIN tblDepLocation DL ON L.locNum = DL.locNum
GROUP BY L.locName ORDER BY L.locName;

-- 33. Cho biết số lượng chỗ làm việc theo mỗi phòng ban.
SELECT D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
	   COUNT(DL.locNum) AS N'Số lượng chỗ làm việc'
FROM tblDepartment D
LEFT JOIN tblDepLocation DL ON D.depNum = DL.depNum
GROUP BY D.depNum, D.depName ORDER BY D.depNum;

-- 34. Cho biết phòng ban nào có nhiều chỗ làm việc nhất.
SELECT TOP 1 WITH TIES
    D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
    COUNT(DL.locNum) AS N'Số lượng chỗ làm việc'
FROM tblDepartment D
LEFT JOIN tblDepLocation DL ON D.depNum = DL.depNum
GROUP BY D.depNum, D.depName ORDER BY N'Số lượng chỗ làm việc' DESC;

-- 35. Cho biết phòng ban nào có it chỗ làm việc nhất.
SELECT TOP 1 WITH TIES
    D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
    COUNT(DL.locNum) AS N'Số lượng chỗ làm việc'
FROM tblDepartment D
JOIN tblDepLocation DL ON D.depNum = DL.depNum
GROUP BY D.depNum, D.depName ORDER BY N'Số lượng chỗ làm việc' ASC;

-- 36. Cho biết địa điểm nào có nhiều phòng ban làm việc nhất.
SELECT TOP 1 WITH TIES
    L.locName AS N'Tên nơi làm việc',
    COUNT(DL.depNum) AS N'Số lượng phòng ban'
FROM tblLocation L
JOIN tblDepLocation DL ON L.locNum = DL.locNum
GROUP BY L.locName ORDER BY N'Số lượng phòng ban' DESC;

-- 37. Cho biết địa điểm nào có ít phòng ban làm việc nhất.
SELECT TOP 1 WITH TIES
    L.locName AS N'Tên nơi làm việc',
    COUNT(DL.depNum) AS N'Số lượng phòng ban'
FROM tblLocation L
LEFT JOIN tblDepLocation DL ON L.locNum = DL.locNum
GROUP BY L.locName ORDER BY N'Số lượng phòng ban' ASC;

-- 38. Cho biết nhân viên nào có nhiều người phụ thuộc nhất.
SELECT TOP 1 WITH TIES
    E.empSSN AS N'Mã số', E.empName AS N'Họ tên',
    COUNT(D.depName) AS N'Số lượng người phụ thuộc'
FROM tblEmployee E
JOIN tblDependent D ON E.empSSN = D.empSSN
GROUP BY E.empSSN, E.empName ORDER BY N'Số lượng người phụ thuộc' DESC;

-- 39. Cho biết nhân viên nào có ít người phụ thuộc nhất.
SELECT TOP 1 WITH TIES
    E.empSSN AS N'Mã số', E.empName AS N'Họ tên',
    COUNT(D.depName) AS N'Số lượng người phụ thuộc'
FROM tblEmployee E
JOIN tblDependent D ON E.empSSN = D.empSSN
GROUP BY E.empSSN, E.empName
ORDER BY N'Số lượng người phụ thuộc' ASC;

-- 40. Cho biết nhân viên nào không có người phụ thuộc.
SELECT E.empSSN AS N'Mã số', E.empName AS N'Họ tên',
       D.depName AS N'Tên phòng ban'
FROM tblEmployee E
JOIN tblDepartment D ON E.depNum = D.depNum
WHERE E.empSSN NOT IN (SELECT empSSN FROM tblDependent);

-- 41. Cho biết phòng ban nào không có người phụ thuộc.
SELECT depNum AS N'Mã phòng ban', depName AS N'Tên phòng ban'
FROM tblDepartment
WHERE depNum NOT IN (
    SELECT DISTINCT E.depNum FROM tblEmployee E
    JOIN tblDependent D ON E.empSSN = D.empSSN
);
 
-- 42. Cho biết những nhân viên nào chưa hề tham gia vào bất kỳ dự án nào.
SELECT E.empSSN AS N'Mã số', E.empName AS N'Tên nhân viên',
       D.depName AS N'Tên phòng ban'
FROM tblEmployee E
JOIN tblDepartment D ON E.depNum = D.depNum
WHERE E.empSSN NOT IN (SELECT empSSN FROM tblWorksOn);

-- 43. Cho biết phòng ban không có nhân viên nào tham gia (bất kỳ) dự án.
SELECT depNum AS N'Mã phòng ban', depName AS N'Tên phòng ban'
FROM tblDepartment
WHERE depNum NOT IN (
    SELECT DISTINCT E.depNum FROM tblEmployee E
    JOIN tblWorksOn W ON E.empSSN = W.empSSN
);

-- 44. Cho biết phòng ban không có nhân viên nào tham gia vào dự án có tên là ProjectA.
SELECT D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban'
FROM tblDepartment D
WHERE D.depNum NOT IN (
    SELECT DISTINCT E.depNum FROM tblEmployee E
    JOIN tblWorksOn W ON E.empSSN = W.empSSN
    JOIN tblProject P ON W.proNum = P.proNum
    WHERE P.proName = 'ProjectA'
);

-- 45. Cho biết số lượng dự án được quản lý theo mỗi phòng ban.
SELECT D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
       COUNT(P.proNum) AS N'Số lượng dự án'
FROM tblDepartment D
LEFT JOIN tblProject P ON D.depNum = P.depNum
GROUP BY D.depNum, D.depName ORDER BY D.depNum;

-- 46. Cho biết phòng ban nào quản lý ít dự án nhất.
SELECT TOP 1 WITH TIES
    D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
    COUNT(P.proNum) AS N'Số lượng dự án'
FROM tblDepartment D
LEFT JOIN tblProject P ON D.depNum = P.depNum
GROUP BY D.depNum, D.depName ORDER BY N'Số lượng dự án' ASC;

-- 47. Cho biết phòng ban nào quản lý nhiều dự án nhất.
SELECT TOP 1 WITH TIES
    D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
    COUNT(P.proNum) AS N'Số lượng dự án'
FROM tblDepartment D
LEFT JOIN tblProject P ON D.depNum = P.depNum
GROUP BY D.depNum, D.depName ORDER BY N'Số lượng dự án' DESC;

-- 48. Cho biết những phòng ban nào có nhiều hơn 5 nhân viên đang quản lý dự án gì.
SELECT D.depNum AS N'Mã phòng ban', D.depName AS N'Tên phòng ban',
    COUNT(E.empSSN) AS N'Số lượng nhân viên',
    P.proName AS N'Tên dự án'
FROM tblDepartment D
JOIN tblEmployee E ON D.depNum = E.depNum
JOIN tblProject P ON D.depNum = P.depNum
GROUP BY D.depNum, D.depName, P.proName
HAVING COUNT(E.empSSN) > 5;

-- 49. Cho biết những nhân viên thuộc phòng có tên là Phòng nghiên cứu, và không có người phụ thuộc.
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Họ tên'
FROM tblEmployee E
JOIN tblDepartment D ON E.depNum = D.depNum
WHERE D.depName = N'Phòng Nghiên cứu và phát triển' AND E.empSSN NOT IN (SELECT empSSN FROM tblDependent);

-- 50. Cho biết tổng số giờ làm của các nhân viên, mà các nhân viên này không có người phụ thuộc.
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Họ tên',
	   ISNULL(SUM(W.workHours), 0) AS N'Tổng số giờ làm'
FROM tblEmployee E
LEFT JOIN tblWorksOn W ON E.empSSN = W.empSSN
WHERE E.empSSN NOT IN (SELECT empSSN FROM tblDependent)
GROUP BY E.empSSN, E.empName;

-- 51. Cho biết tổng số giờ làm của các nhân viên, mà các nhân viên này có nhiều hơn 3 người phụ thuộc
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Họ tên',
    COUNT(D.depName) AS N'Số người phụ thuộc',
    ISNULL(SUM(W.workHours), 0) AS N'Tổng số giờ làm'
FROM tblEmployee E
JOIN tblDependent D ON E.empSSN = D.empSSN
LEFT JOIN tblWorksOn W ON E.empSSN = W.empSSN
GROUP BY E.empSSN, E.empName
HAVING COUNT(D.depName) > 3;

-- 52. Cho biết tổng số giờ làm việc của các nhân viên hiện đang dưới quyền giám sát (bị quản lý bởi) của nhân viên Mai Duy An.
SELECT E.empSSN AS N'Mã nhân viên', E.empName AS N'Họ tên',
       ISNULL(SUM(W.workHours), 0) AS N'Tổng số giờ làm'
FROM tblEmployee E
LEFT JOIN tblWorksOn W ON E.empSSN = W.empSSN
WHERE E.supervisorSSN = (SELECT empSSN FROM tblEmployee WHERE empName = N'Mai Duy An')
GROUP BY E.empSSN, E.empName;