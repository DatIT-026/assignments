create proc proc_SumQuantityProduct
	@Store_id int,
	@SumQuantity decimal(10,2) output
as
begin
	select @SumQuantity = sum(stk.quantity)
	from stores s
	join stocks stk on stk.store_id = s.store_id
	where s.store_id = @Store_id
	group by s.store_id, s.store_name
	return
end;
