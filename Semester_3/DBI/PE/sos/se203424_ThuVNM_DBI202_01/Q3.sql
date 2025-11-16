select
	p.product_id,
	p.product_name,
	p.list_price
from products p
where p.product_name like '%City%'
order by p.product_name;