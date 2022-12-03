package com.example.mission1;


public class DistanceCalculator {
    static Double getDistance(Double lat, Double lnt, Double lat2, Double lnt2) {
        double theta = lnt - lnt2;
        double dist = Math.sin(deg2rad(lat))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist / 1000;
    }

    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }

    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
