package com.example.mission1;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiData implements Comparable<WifiData> {
    Double DISTANCE;
    String X_SWIFI_MGR_NO;
    String X_SWIFI_WRDOFC;
    String X_SWIFI_MAIN_NM;
    String X_SWIFI_ADRES1;
    String X_SWIFI_ADRES2;
    String X_SWIFI_INSTL_FLOOR;
    String X_SWIFI_INSTL_TY;
    String X_SWIFI_INSTL_MBY;
    String X_SWIFI_SVC_SE;
    String X_SWIFI_CMCWR;
    String X_SWIFI_CNSTC_YEAR;
    String X_SWIFI_INOUT_DOOR;
    String X_SWIFI_REMARS3;
    Double LAT;
    Double LNT;
    String WORK_DTTM;

    @Override
    public int compareTo(WifiData o) {
        if (this.DISTANCE > o.DISTANCE) {
            return 1;
        } else if (this.DISTANCE < o.DISTANCE) {
            return -1;
        } else {
            return 0;
        }
    }
}


