SELECT cust.custName, c.model, st.serviceTicketID, st.dateReturned
FROM Customer cust
JOIN ServiceTicket st ON st.custID = cust.custID
JOIN Cars c ON c.carID = st.carID
WHERE YEAR(st.dateReturned) = 2021
AND MONTH(st.dateReturned) BETWEEN 01 AND 04