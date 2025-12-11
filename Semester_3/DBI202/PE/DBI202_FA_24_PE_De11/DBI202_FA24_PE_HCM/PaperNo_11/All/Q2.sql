SELECT EmployeeID, FullName, Role, HireDate, Salary FROM Employees
WHERE (Role = 'Chef' OR Role = 'Manager') AND YEAR(HireDate) = 2021