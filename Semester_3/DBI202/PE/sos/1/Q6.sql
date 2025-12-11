SELECT store.store_id, store.store_name, SUM(stock.quantity) AS total_qty_in_stock FROM stores store
JOIN stocks stock ON stock.store_id = store.store_id
GROUP BY store.store_id, store.store_name
ORDER BY total_qty_in_stock DESC, store_id ASC