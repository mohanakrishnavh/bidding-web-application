<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>

 <div class="login-box">
<form action="success.jsp" method="post" class="email-signup">
            
                        <div class="u-form-group">
                        <input type="text" name="fname" placeholder="First Name" />
                        </div>
                      <div class="u-form-group">
                        <input type="text" name="lname" placeholder="Last Name" />
                  </div>
                  <div class="u-form-group">
                        <input type="text" name="email" placeholder="Email" />
                    </div>
                    <div class="u-form-group">
                        <input type="text" name="uname" placeholder="UserName" />
                    </div>
                    
                     <div class="u-form-group">
                        <input type="password" name="uname" placeholder="Password" />
                    </div>
                    <div class="u-form-group">
                        <input type="submit" value="Submit" />
                        </div>
                        <div class="u-form-group">
                        <input type="reset" value="Reset" />
                        </div>
                      <a href="login.jsp">Login Here</a>                    
        </form> 

</form></div>

</body>
</html>