CREATE PROCEDURE proc_serviceTicket_part
	@serviceTicketID int,
	@numberOfParts int OUTPUT
AS
BEGIN
	SELECT @numberOfParts = COUNT(DISTINCT pu.partID)
	FROM ServiceTicket st
	JOIN PartsUsed pu ON pu.serviceTicketID = st.serviceTicketID
	WHERE @serviceTicketID = st.serviceTicketID
END;