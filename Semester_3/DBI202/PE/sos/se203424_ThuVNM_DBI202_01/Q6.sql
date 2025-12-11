select
	s.store_id,
	s.store_name,
	sum(stk.quantity) as total_qty_in_stock
from stores s
join stocks stk on stk.store_id = s.store_id
group by s.store_id, s.store_name
order by sum(stk.quantity) desc, s.store_id;