<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name = "viewport" content = "width=device-width,initial-scale=1">
	<title>Status</title>
</head>
<body>
   <div class="container">
	<form action = "Userinfo" method = "get">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Stock status</button>
      </form>

	<form action = "Userinfo" method = "post">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Delay status</button>
      </form>

	<form action = "BorrowUmbrella" method = "get">
       <button class="btn btn-lg btn-primary btn-block" type="submit">Borrow Umbrella</button>
      </form>
	<form action = "ReturnUmbrella" method = "get">
       <button class="btn btn-lg btn-primary btn-block" type="submit">Return Umbrella</button>
      </form>
      
   	<form action = "BrokenBox" method = "get">
       <button class="btn btn-lg btn-primary btn-block" type="submit">Supply Umbrella</button>
      </form>  
    
    <form action = "IssuedChange" method = "get">
      <input type="text" name = "nfctagid">
      <br>
      <input type="text" name = "customerid">
      <br>
      <button type = "submit">unIssued</button>
      </form>    
      
    <form action = "UnIssuedChange" method = "get">
      <input type="text" name = "nfctagid">
      <br>
       <input type="text" name = "customerid">
      <br>
            <button type = "submit">Issued</button>
     </form>    
     
    <form action = "RentTime" method = "get">
      <input type="text" name = "customerid">
      <br>
            <button type = "submit">time DB Update</button>
     </form>     
    </div> <!-- /container -->

</body>
</html>