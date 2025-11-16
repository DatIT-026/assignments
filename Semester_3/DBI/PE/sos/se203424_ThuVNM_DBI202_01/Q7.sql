with CTE_new_table as (
	select 
		p1.category_name,
		max (p1.product_id) as product_id,
		max(p1.list_price) as list_price
	from products p1
	group by p1.category_name
)

select
	p.category_name,
	cte.product_id,
	p.product_name,
	p.list_price
from CTE_new_table cte 
join products p on p.product_id = cte.product_id
order by p.category_name, cte.product_id