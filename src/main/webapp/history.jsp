
<%@ page import="java.util.List" %>
<%@ page import="com.example.mission1.WifiService" %>
<%@ page import="com.example.mission1.History" %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #customers tr:hover {
            background-color: #ddd;
        }

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>

<h1>위치 히스토리 목록</h1>

<a href="http://localhost:8080" class>홈 |</a>
<a href="/history.jsp" class>위치 히스토리 목록 |</a>
<a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<br>
<br>

<table id="customers">
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        WifiService wifiService = new WifiService();
        List<History> historyList = wifiService.getHistoryList();
        for (History history : historyList) {
    %>
    <tr>
        <td>
            <%=history.getId()%>
        </td>
        <td>
            <%=history.getMyLat()%>
        </td>
        <td>
            <%=history.getMyLnt()%>
        </td>
        <td>
            <%=history.getInquiryTime()%>
        </td>
        <td>
            <div>
                <button type="button">삭제</button>
            </div>
        </td>
    </tr>
    <%
        }
    %>
    </tr>
    </tbody>
</table>

</body>
</html>


