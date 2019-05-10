package com.empty.ispy.Game;

import java.util.ArrayList;

class RouteItem {
    private String route_name;
    private String start_point;
    private String end_point;
    private String route_id;
    private  ArrayList<String> waypoints;
    private  ArrayList<String> waypoint_name;
    private String radioButtonState = "unSelected";

    RouteItem(String route_name, String route_id, String start_point, String end_point, ArrayList<String> waypoints, ArrayList<String> waypoint_name) {
        this.route_name = route_name;
        this.route_id = route_id;
        this.start_point = start_point;
        this.end_point = end_point;
        this.waypoints = waypoints;
        this.waypoint_name = waypoint_name;
    }

    String getRoute_name() {
        return route_name;
    }

    String getStart_point() {
        return start_point.split(";")[0];
    }

    String getEnd_point() {
        return end_point.split(";")[0];
    }

    String getEnd_point_coordinate() {
        return end_point.split(";")[1];
    }

    String getStart_point_coordinate() {
        return start_point.split(";")[1];
    }

    String getRoute_id() {
        return route_id;
    }

    ArrayList<String> getWaypoints() {
        return waypoints;
    }

    ArrayList<String> getWaypoint_name() {
        return waypoint_name;
    }

    public String getRadioButtonState() {
        return radioButtonState;
    }

    public void setRadioButtonState(String radioButtonState) {
        this.radioButtonState = radioButtonState;
    }

}
