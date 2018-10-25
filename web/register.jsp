<%@ page import="src.util.StringUtils" %>

<%--
  Created by IntelliJ IDEA.
  User: z
  Date: 2018/8/30
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>web test</title>

    <script type="text/javascript" src="/jslib/jquery.min.js"></script>
    <script type="text/javascript" src="/jslib/jquery.validate.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#cde').attr('src','code?s='+Math.random());
            $('#cde').click(function () {
                $('#cde').attr('src','code?p='+Math.random());
            });

            $('#frm').validate({
                rules:{
                    username:{ required: true ,rangelength:[6,20]},
                    password:{ required: true ,rangelength:[6,20]},
                    repassword:{ required: true ,rangelength:[6,20]},
                    code:{ required: true ,rangelength:[6,6]}
                },
                messages:{
                    username:{required:'needed!',rangelength:'need 6-20 char'},
                    password:{required:'needed!',rangelength:'need 6-20 char'},
                    repassword:{required:'needed!',rangelength:'need 6-20 char'},
                    code:{required:'needed!',rangelength:'need 6 char'}
                }
            });

        });
    </script>

    <style>
      .error{color: darkorange}
    </style>
  </head>
  <body>
    <form action="register" method="get" id="frm">
      <table >
        <caption>
          <span class="error"><%=request.getAttribute("msg") != null ?request.getAttribute("msg"):"" %></span>
        </caption>
        <tr>
          <td>name</td>
          <td><input type="text" name="username" value="<%=StringUtils.getName(request,"username")%>"/>
            <span ><%=StringUtils.getInfo(request,"username") %></span>
          </td>
        </tr>
        <tr>
          <td>password</td>
          <td><input type="password" name="password"/>
            <span ><%=StringUtils.getInfo(request,"password") %></span>
          </td>
        </tr>
        <tr>
          <td>confirm password</td>
          <td><input type="password" name="repassword"/>
            <span ><%=StringUtils.getInfo(request,"repassword") %></span>
          </td>
        </tr>
        <tr>
          <td>role</td>
          <td>
            <select name="roleId" ></select>
          </td>
        </tr>
        <tr>
          <td>check code</td>
          <td><input type="text" name="code"/>
            <span ><%=StringUtils.getInfo(request,"code") %></span>
          </td>
        </tr>
        <tr>
          <td><img id="cde"></td>
          <td>click pic to refresh</td>
        </tr>
        <tr>
          <td><input type="submit" value="submit"/> </td>
          <td><input type="reset" value="reset"/> </td>
          <td><input id="login" type="button" value="login"/></td>
        </tr>
      </table>
    </form>



  </body>
</html>
