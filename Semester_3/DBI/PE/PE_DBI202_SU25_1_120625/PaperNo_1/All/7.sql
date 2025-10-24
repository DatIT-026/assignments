USE PE_DBI202

-- subquery
SELECT Ssn, Fname, Lname, COUNT(w.Essn) AS DoneProjects FROM EMPLOYEE e
JOIN WORKS_ON w ON w.Essn = e.Ssn
GROUP BY e.Ssn, e.Fname, e.Lname
HAVING COUNT(w.Essn) = (
	SELECT MAX(ProjectCount) FROM (
		SELECT COUNT(w2.Essn) AS ProjectCount FROM WORKS_ON w2 
		GROUP BY w2.Essn
	) AS Counts
);

-- CTE
WITH ProjectCount AS (
    SELECT w.Essn, COUNT(w.Pno) AS DoneProjects FROM Works_On w
    GROUP BY w.Essn
),
MaxCount AS (
    SELECT MAX(DoneProjects) AS MaxProjects FROM ProjectCount
)
SELECT e.Ssn, e.Fname, e.Lname, p.DoneProjects FROM Employee e
JOIN ProjectCount p ON e.Ssn = p.Essn
JOIN MaxCount m ON p.DoneProjects = m.MaxProjects;