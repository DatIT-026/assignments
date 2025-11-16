select
	p.product_id,
	p.product_name,
	p.list_price
from products p
where p.list_price = (
	select MIN(p1.list_price)
	from products p1
)
