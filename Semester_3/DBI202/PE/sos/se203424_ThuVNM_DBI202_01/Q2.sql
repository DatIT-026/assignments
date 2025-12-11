select
	c.customer_id,
	c.first_name,
	c.last_name,
	c.email
from customers c
where c.city = 'New York';