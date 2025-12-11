delete stocks
from stocks s 
join products p on p.product_id = s.product_id
where p.category_name = 'Mountain'