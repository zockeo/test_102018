<%@ page import="src.entity.Page" %>
<%@ page import="src.entity.User" %>
<%@ page import="java.util.List" %>

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
    <script>
      $(function () {
          $(".delete").click(function () {
              $this = $(this);
              var name = $this.next().val();
              var b = confirm("are you sure to delete "+name+" user ?");
              if(b){
                  location.href = this.href;
              }
              return false;
          })
      });
    </script>

    <style>
    </style>

  </head>
  <body>
  <%
    Object obj = request.getAttribute("userlist");
      if(obj != null && ((List)obj).size() > 0 ) {
      List<User> ulist = (List<User>)obj;
  %>
    <table border="1">
      <thead>
        <tr>
          <th>id</th>
          <th>username</th>
          <th>role</th>
          <th>description</th>
          <th>action</th>
        </tr>
      </thead>
      <tbody>
      <%for(User uList:ulist){%>
        <tr>
          <td><%=uList.getId()%></td>
          <td><%=uList.getUsername()%></td>
          <td><%=uList.getRole().getName()%></td>
          <td><%=uList.getRole().getDesc()%></td>
          <td>
            <a>details</a>|
            <a>modify</a>|
            <a class="delete" href="user?action=delete&id=<%=uList.getId()%>">delete</a><input type="hidden" value="<%=uList.getUsername()%>"></td>
        </tr>
      <%}%>
      </tbody>
      <tfoot>
        <%
          Object pag = request.getAttribute("pageList");
          if(pag != null && pag instanceof Page){
              Page pageList = (Page)pag;
              if(pageList.getMaxPage()>1) {
        %>
        <tr><td colspan="6">
          <a href="user?action=show&page=1">head</a>
          <%
            if (pageList.getLastPage() > 0) {
          %>
            <a href="user?action=show&page=<%=pageList.getLastPage()%>">last</a>
          <%}%>
          <%
            if(pageList.getNextPage()> 0 ) {
          %>
            <a href="user?action=show&page=<%=pageList.getNextPage()%>">next</a>
          <%}%>
          <a href="user?action=show&page=<%=pageList.getMaxPage()%>">end</a>
        </td></tr>
      </tfoot>
      <%}}%>
    </table>
  <%
    } else {
  %>
  <h2>no any information!</h2>
  <%}%>
  </body>
</html>
