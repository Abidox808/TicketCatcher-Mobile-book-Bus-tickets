package com.said.supra;

public class ticket {
    public String car,depart,arriver,hour,date,price;
    public int id;
    public ticket(String car,String depart,String arriver,String hour,String date,String price,int id){
        this.car = car ;
        this.depart = depart;
        this.arriver = arriver;
        this.hour = hour;
        this.date = date;
        this.price = price;
        this.id = id ;
    }
}
