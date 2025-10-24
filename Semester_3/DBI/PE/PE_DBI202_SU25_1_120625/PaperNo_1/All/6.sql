-- subquery
SELECT fname, lname, salary, dname FROM EMPLOYEE e
JOIN DEPARTMENT d ON e.Dno = d.Dnumber
WHERE e.Salary = (
	SELECT MIN(e1.Salary) FROM EMPLOYEE e1
	JOIN DEPARTMENT d1 ON e1.Dno = d1.Dnumber 
	WHERE e.Dno = d1.Dnumber
)

-- CTE
WITH MinSalaryCTE AS (
    SELECT MIN(Salary) AS MinSalary FROM EMPLOYEE
),
DeptWithMinSalaryCTE AS (
    SELECT e.Dno FROM EMPLOYEE e
    JOIN DEPARTMENT d ON e.Dno = d.Dnumber
    JOIN MinSalaryCTE ms ON e.Salary = ms.MinSalary
),
EmployeesInDeptCTE AS (
    SELECT e.Fname, e.Lname, e.Salary FROM EMPLOYEE e
    JOIN DEPARTMENT d ON e.Dno = d.Dnumber
    WHERE e.Dno = (SELECT Dno FROM DeptWithMinSalaryCTE)
)
SELECT * FROM EmployeesInDeptCTE ORDER BY Salary;