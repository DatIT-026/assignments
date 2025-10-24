SELECT Dname, Lname, Fname, Pname FROM EMPLOYEE e
JOIN DEPARTMENT d ON e.Dno = d.Dnumber
JOIN WORKS_ON wo ON e.Ssn = wo.Essn
JOIN PROJECT p ON wo.Pno = p.Pnumber