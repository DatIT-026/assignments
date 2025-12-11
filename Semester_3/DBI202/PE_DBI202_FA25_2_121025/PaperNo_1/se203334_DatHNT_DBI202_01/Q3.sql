SELECT c.custName, c.phone, c.sex, COUNT(si.invoiceID) AS NumberOfInvoiceID
FROM Customer c
JOIN SalesInvoice si ON si.custID = c.custID
GROUP BY c.custName, c.phone, c.sex
HAVING COUNT(si.invoiceID) >= 3