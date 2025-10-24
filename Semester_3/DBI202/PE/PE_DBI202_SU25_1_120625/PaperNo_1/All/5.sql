-- subquery
SELECT Fname, Lname, Salary FROM EMPLOYEE e
JOIN DEPARTMENT d ON e.Dno = d.Dnumber
WHERE e.Dno = (
	SELECT e1.Dno FROM EMPLOYEE e1
	JOIN DEPARTMENT d1 ON e1.Dno = d1.Dnumber
	WHERE e1.Salary = (
		SELECT MAX(e2.Salary) FROM EMPLOYEE e2
	)
) ORDER BY Salary

-- CTE
-- tìm thằng có lương bự nhất
WITH MaxSalaryCTE AS (
    SELECT MAX(Salary) AS MaxSalary FROM EMPLOYEE
),
-- tìm nơi thằng lương bự đang ở
DeptWithMaxSalaryCTE AS (
    SELECT e.Dno FROM EMPLOYEE e
    JOIN DEPARTMENT d ON e.Dno = d.Dnumber
    JOIN MaxSalaryCTE ms ON e.Salary = ms.MaxSalary
),
-- list tất cả người đang ở cùng nơi với thằng lương bự
EmployeesInDeptCTE AS (
    SELECT e.Fname, e.Lname, e.Salary FROM EMPLOYEE e
    JOIN DEPARTMENT d ON e.Dno = d.Dnumber
    WHERE e.Dno = (SELECT Dno FROM DeptWithMaxSalaryCTE)
)
SELECT * FROM EmployeesInDeptCTE ORDER BY Salary;