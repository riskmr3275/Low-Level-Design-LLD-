 
import models.*;

import java.util.*;
public class Restaurant {
    private int nextRestaurantId=0;
    private int restaurantId;
    private String name;
    private String location;
    private List<MenuItem>menu=new ArrayList<>();

    public Restaurant(String name,String location){
        this.name=name;
        this.location=location;
        this.restaurantId=++nextRestaurantId;;
        System.out.println(restaurantId);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String loc){
        this.location=loc;
    }
    public void addMenuItem(MenuItem item){
        menu.add(item);
    }
}
