SELECT Lname, Fname FROM EMPLOYEE e
JOIN DEPARTMENT d ON e.Dno = d.Dnumber
WHERE e.Salary > ALL (
	SELECT e1.Salary FROM EMPLOYEE e1
	JOIN DEPARTMENT d1 ON e1.Dno = d1.Dnumber 
	WHERE e1.Dno = 5
)