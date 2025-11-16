SELECT order_id, customer_id, order_date, store_id, staff_id FROM orders
WHERE YEAR(order_date) = 2025
AND MONTH(order_date) = 8
AND DAY(order_date) BETWEEN 05 AND 15
ORDER BY order_date ASC