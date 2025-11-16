SELECT staff.staff_id, staff.first_name, staff.last_name FROM staffs staff
JOIN orders ord ON ord.staff_id = staff.staff_id
JOIN customers customer ON customer.customer_id = ord.customer_id
WHERE customer.state = 'CA'