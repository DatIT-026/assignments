<%-- 
    Document   : ViewCart
    Created on : Feb 7, 2026, 7:35:58 AM
    Author     : datto
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="Models.Entities.CartItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <%
            double totalAmount = 0;
            int numberOfBooks = 0;
            List<CartItem> itemsInCart = (List<CartItem>) request.getAttribute("Cart");
        %>
        <%
            if (itemsInCart == null || itemsInCart.size() == 0) {
        %>

        <h3>Cart is empty!!!</h3>

        <%
        } else if (itemsInCart != null) {
        %>
        <table border='1' style="width:300px" >
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>SubTotal</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (CartItem item : itemsInCart) {
                        totalAmount += item.getSubTotal();
                        numberOfBooks += item.getQuantity();
                %>
            <form action="CartController" method="post">
                <tr>
                    <td><%=(++count)%></td>
                    <td><%=item.getItemId()%>
                        <input type="hidden" value='<%=item.getItemId()%>' name="ItemId"/>
                    </td>
                    <td><%=item.getItemName()%></td>
                    <td><%=item.getUnitPrice()%></td>
                    <td>
                        <input type="number" min="1" name="quantity" value="<%= item.getQuantity()%>"/>
                    </td>
                    <td><%= String.format("%.2f", item.getSubTotal())%></td>
                    <td style="text-align: center">
                        <input type="submit" value="Remove" name="action" />
                    </td>
                    <td style="text-align: center">
                        <input type="submit" value="Update" name="action" />
                    </td>
                </tr>
            </form>
            <%
            }
            %>
            <tr>
                <td colspan="5" style="text-align: right"><b>Total Amount</b></td>
                <td><%=String.format("%.2f", totalAmount)%></td>
            </tr>
            </tbody>
        </table>
        <h3>Number of books in cart: <%=numberOfBooks%></h3>
        <% 
        }
        %>
        <form method = "Post"> 
            <input type="submit" formaction="BookController?action=ViewBookList" value="Continue" />
            <input type="submit" formaction="CartController?action=Save" value  = "Save Cart"/>
        </form>

        <%if (request.getAttribute("Message") != null) {%>
            <%=request.getAttribute("Message")%>
        <% }%>
    </body>
</html>
