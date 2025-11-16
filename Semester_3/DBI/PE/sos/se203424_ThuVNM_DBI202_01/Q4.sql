select 
	o.order_id,
	o.customer_id,
	o.order_date,
	o.store_id,
	o.staff_id
from orders o
where year (o.order_date) = 2025 and
	month (o.order_date) = 8 and
	(day (o.order_date) between 5 and 15)
order by o.order_date;