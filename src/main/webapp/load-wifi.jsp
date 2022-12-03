
<%@ page import="com.example.mission1.WifiService" %>
<%@ page import="com.example.mission1.Api" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8");%>


<html>
<style>
  h1 {
    font-size: 40px;
    text-align: center;
  }

  h2 {
    font-size: 18px;
    text-align: center;
  }
</style>
<body>
<h1>

  <%WifiService.wifiListClear();%>
  <%Api.wifiData();%>
  <%=Api.list_total_count%>개의 WIFI 정보를 정상적으로 저장하였습니다.

</h1>

<h2>
  <a href="http://localhost:8080"> 홈 으로 가기 </a>
</h2>

</body>
</html>
