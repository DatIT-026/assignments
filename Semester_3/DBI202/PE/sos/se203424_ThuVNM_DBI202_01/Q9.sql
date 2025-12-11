select 
	s.staff_id,
	s.first_name,
	s.last_name
from customers c
join orders o on o.staff_id = c.customer_id
join staffs s on o.customer_id = s.staff_id
where c.state = 'CA'