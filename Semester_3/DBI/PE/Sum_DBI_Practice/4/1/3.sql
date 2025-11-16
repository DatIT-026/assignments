SELECT st.SupplierTransactionID, st.SupplierID,
	   st.TransactionType, st.TransactionDate, st.TransactionAmount
FROM SupplierTransactions st
WHERE st.TransactionDate >= '2013-05-20'
	AND st.TransactionDate <= '2013-05-31'
ORDER BY st.TransactionType, st.TransactionAmount DESC