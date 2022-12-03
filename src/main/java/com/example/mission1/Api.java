package com.example.mission1;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Api {
    public static int list_total_count;
    public static void getListTotalCount() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" + URLEncoder.encode("4d7365654b736b7436364e4d4a6947", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다   .

        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
        urlBuilder.append("/" + URLEncoder.encode("20220301", "UTF-8")); /* 서비스별 추가 요청인자들*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader br;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        conn.disconnect();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(sb.toString());
        JsonObject TbPublicWifiInfo = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();

        list_total_count = TbPublicWifiInfo.getAsJsonObject().get("list_total_count").getAsInt();

    }
    public static void wifiData() throws IOException {
        getListTotalCount();
        for (int i = 0; i < (list_total_count / 1000) + 1; i++) {
            int j = i * 1000 + 1;
            int k = (i * 1000) + 1000;
            if (i == list_total_count / 1000) {
                k = list_total_count;
            }
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" + URLEncoder.encode("4d7365654b736b7436364e4d4a6947", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode(Integer.toString(j), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode(Integer.toString(k), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다   .

            // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
            urlBuilder.append("/" + URLEncoder.encode("20220301", "UTF-8")); /* 서비스별 추가 요청인자들*/

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");
//            System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader br;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            conn.disconnect();
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(sb.toString());
            JsonObject TbPublicWifiInfo = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
            JsonArray row = (JsonArray) TbPublicWifiInfo.getAsJsonArray("row");

            for (int x = 0; x < row.size(); x++) {
                String X_SWIFI_MGR_NO = row.get(x).getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
                String X_SWIFI_WRDOFC = row.get(x).getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString();
                String X_SWIFI_MAIN_NM = row.get(x).getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString();
                String X_SWIFI_ADRES1 = row.get(x).getAsJsonObject().get("X_SWIFI_ADRES1").getAsString();
                String X_SWIFI_ADRES2 = row.get(x).getAsJsonObject().get("X_SWIFI_ADRES2").getAsString();
                String X_SWIFI_INSTL_FLOOR = row.get(x).getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString();
                String X_SWIFI_INSTL_TY = row.get(x).getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString();
                String X_SWIFI_INSTL_MBY = row.get(x).getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString();
                String X_SWIFI_SVC_SE = row.get(x).getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString();
                String X_SWIFI_CMCWR = row.get(x).getAsJsonObject().get("X_SWIFI_CMCWR").getAsString();
                String X_SWIFI_CNSTC_YEAR = row.get(x).getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString();
                String X_SWIFI_INOUT_DOOR = row.get(x).getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString();
                String X_SWIFI_REMARS3 = row.get(x).getAsJsonObject().get("X_SWIFI_REMARS3").getAsString();
                Double LAT = row.get(x).getAsJsonObject().get("LAT").getAsDouble();
                Double LNT = row.get(x).getAsJsonObject().get("LNT").getAsDouble();
                String WORK_DTTM = row.get(x).getAsJsonObject().get("WORK_DTTM").getAsString();


                WifiData wifiData = new WifiData();
                wifiData.setDISTANCE(0.0000);
                wifiData.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                wifiData.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                wifiData.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                wifiData.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                wifiData.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                wifiData.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                wifiData.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                wifiData.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                wifiData.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                wifiData.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                wifiData.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
                wifiData.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                wifiData.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                wifiData.setLAT(LAT);
                wifiData.setLNT(LNT);
                wifiData.setWORK_DTTM(WORK_DTTM);

                WifiService.dbInsert(wifiData);
            }
        }
    }
}

