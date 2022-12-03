package com.example.mission1;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WifiService {
    static int cnt = 0;
    public static void dbInsert(WifiData wifiData) {
        cnt++;

        String dbUrl = "jdbc:sqlite:C:/Users/sktjs/Desktop/practice/WifiData.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(dbUrl);
            String sql = " insert into WifiData( " +
                    " X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, " +
                    " X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, " +
                    " X_SWIFI_CNSTC_YEAR,  X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM ) " +
                    " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifiData.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2, wifiData.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3, wifiData.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4, wifiData.getX_SWIFI_ADRES1());
            preparedStatement.setString(5, wifiData.getX_SWIFI_ADRES2());
            preparedStatement.setString(6, wifiData.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7, wifiData.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8, wifiData.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9, wifiData.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10, wifiData.getX_SWIFI_CMCWR());
            preparedStatement.setString(11, wifiData.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12, wifiData.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13, wifiData.getX_SWIFI_REMARS3());
            preparedStatement.setDouble(14, wifiData.getLAT());
            preparedStatement.setDouble(15, wifiData.getLNT());
            preparedStatement.setString(16, wifiData.getWORK_DTTM());
            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(cnt + "번 저장 성공");
            } else {
                System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<WifiData> getWifiDataList(String x, String y) {
        List<WifiData> wifiDataList = new ArrayList<>();


        String dbUrl = "jdbc:sqlite:C:/Users/sktjs/Desktop/practice/WifiData.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(dbUrl);
            String sql = " select X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
                    " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, " +
                    " X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, " +
                    " X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM " +
                    " from WifiData ";
            preparedstatement = connection.prepareStatement(sql);
            rs = preparedstatement.executeQuery();

            while (rs.next()) {
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                Double LAT = rs.getDouble("LAT");
                Double LNT = rs.getDouble("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");

                WifiData wifiData = new WifiData();
                DistanceCalculator distanceCalculator = new DistanceCalculator();
                Double myLat = Double.parseDouble(x);
                Double myLnt = Double.parseDouble(y);

                wifiData.setDISTANCE(distanceCalculator.getDistance(myLat, myLnt, LAT, LNT));
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

                wifiDataList.add(wifiData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedstatement != null && !preparedstatement.isClosed()) {
                    preparedstatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(wifiDataList);
        return wifiDataList.subList(0,20);
    }
    public static void wifiListClear() {

        String dbUrl = "jdbc:sqlite:C:/Users/sktjs/Desktop/practice/WifiData.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection(dbUrl);
            String sql = "delete from WifiData ";

            preparedStatement = connection.prepareStatement(sql);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void historyInsert(String myLat, String myLnt) {

        String dbUrl = "jdbc:sqlite:C:/Users/sktjs/Desktop/practice/WifiData.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(dbUrl);
            String sql = " INSERT INTO history (myLat, myLnt, inquiryTime) "
                    + " VALUES (?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, myLat);
            preparedStatement.setString(2, myLnt);
            preparedStatement.setString(3, String.valueOf(LocalDateTime.now()).substring(0,19));

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("History 저장 성공");
            } else {
                System.out.println("History 저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<History> getHistoryList() {
        List<History> historyList = new ArrayList<>();

        String dbUrl = "jdbc:sqlite:C:/Users/sktjs/Desktop/practice/WifiData.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(dbUrl);
            String sql = " select * from history ORDER BY id DESC ";
            preparedstatement = connection.prepareStatement(sql);
            rs = preparedstatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String myLat = rs.getString("myLat");
                String myLnt = rs.getString("myLnt");
                String inquiryTime = rs.getString("inquiryTime");

                History history = new History();
                history.setId(id);
                history.setMyLat(myLat);
                history.setMyLnt(myLnt);
                history.setInquiryTime(inquiryTime);

                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedstatement != null && !preparedstatement.isClosed()) {
                    preparedstatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return historyList;
    }
}


