<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mission1.WifiService" %>
<%@ page import="com.example.mission1.WifiData" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8");%>

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

        form {
            display: inline;
        }
    </style>
</head>
<h1>와이파이 정보 구하기</h1>
<body>

<a href="http://localhost:8080" class> 홈 </a>|
<a href="/history.jsp" class> 위치 히스토리 목록 </a>|
<a href="/load-wifi.jsp"> Open API 와이파이 정보 가져오기</a>
<br>
<form method="get" action="http://localhost:8080">
    LAT: <input type="text" id="lat" name="lat" value="0.0">,
    LNT: <input type="text" id="lnt" name="lnt" value="0.0">
    <input type="button" onclick="getLocation()" value="내 위치 가져오기">
    <input type="submit" value="근처 WIFI 정보 보기">
</form>
<br>
<br>
<table id="customers">
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <%
        String myLat = request.getParameter("lat");
        String myLnt = request.getParameter("lnt");
        System.out.println(myLat);
        System.out.println(myLnt);
        if(myLat==null && myLnt==null) {%>
    <tbody>
    <tr>
        <td style="text-align: center;" colspan="17">위치 정보를 입력한 후에 조회해
            주세요.</td>

    </tr>
    <%}else{
        WifiService wifiService = new WifiService();
        WifiService.historyInsert(myLat, myLnt);
        List<WifiData> wifiDataList = wifiService.getWifiDataList(myLat, myLnt);

        String result2 = "";
        for (WifiData wifiData : wifiDataList) {

            result2 = String.format("%.4f", wifiData.getDISTANCE());
    %>
    <tr>
        <td>
            <%= result2 %>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_MGR_NO()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_WRDOFC()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_MAIN_NM()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_ADRES1()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_ADRES2()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INSTL_FLOOR()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INSTL_TY()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INSTL_MBY()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_SVC_SE()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_CMCWR()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_CNSTC_YEAR()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_INOUT_DOOR()%>
        </td>
        <td>
            <%=wifiData.getX_SWIFI_REMARS3()%>
        </td>
        <td>
            <%=wifiData.getLAT()%>
        </td>
        <td>
            <%=wifiData.getLNT()%>
        </td>
        <td>
            <%=wifiData.getWORK_DTTM()%>
        </td>
    </tr>

    <%}
    }%>
    </tbody>
</table>

</body>
<script>
    let latitude = 0
    let longitude = 0

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                latitude = position.coords.latitude;
                longitude = position.coords.longitude;
                document.getElementById('lat').value = latitude;
                document.getElementById('lnt').value = longitude;
            });
        } else {
            alert("Browser not support Geolocation");
        }
    }
</script>
</html>


