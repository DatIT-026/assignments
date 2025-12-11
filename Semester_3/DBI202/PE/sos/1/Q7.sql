SELECT category_name, product_id, product_name, list_price FROM products product
WHERE list_price = (
	SELECT MAX(list_price) FROM products product2
	WHERE product.category_name = product2.category_name
)
ORDER BY category_name ASC, product_id ASC