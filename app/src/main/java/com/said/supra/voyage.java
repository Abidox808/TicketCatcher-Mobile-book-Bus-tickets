package com.said.supra;

public class voyage {
    public String car,depart,arriver,departHour,arriverHour,date;
    public int price;
    public voyage(String car,String depart,String arriver,String departHour,String arriverHour,int price){
        this.car = car ;
        this.depart = depart;
        this.arriver = arriver;
        this.departHour = departHour;
        this.arriverHour = arriverHour;
        this.price = price;
    }

    public void addDate(String date){
        this.date = date ;
    }
}
