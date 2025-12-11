CREATE PROCEDURE proc_salesPerson_invoice
	@salesID decimal,
	@numberOfInvoices int OUTPUT
AS
BEGIN
	SELECT @numberOfInvoices = COUNT(DISTINCT si.invoiceID)
	FROM SalesInvoice si
	WHERE @salesID = si.salesID
END;