DELETE FROM stocks WHERE product_id IN (
SELECT stock.product_id FROM stocks stock
JOIN products prod ON prod.product_id = stock.product_id
WHERE prod.category_name = 'Mountain'
)