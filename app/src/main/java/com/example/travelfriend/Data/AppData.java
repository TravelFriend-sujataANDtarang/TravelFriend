package com.example.travelfriend.Data;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.travelfriend.Constants.MyConstants;
import com.example.travelfriend.Database.RoomDB;
import com.example.travelfriend.Dao.ItemsDao;
import com.example.travelfriend.Models.Items;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {
    RoomDB database;
    String category;
    Context context;

    public static final String LAST_VERSION="LAST_VERSION";
    public static final int NEW_VERSION=1;


    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

    public List<Items> getBasicData(){
        category ="Basic Needs";
        List<Items> basicItem=new ArrayList<>();

        basicItem.add(new Items("Visa",category,false));
        basicItem.add(new Items("Passport",category,false));
        basicItem.add(new Items("House Keys",category,false));
        basicItem.add(new Items("Medical Insurance",category,false));
        basicItem.add(new Items("Books",category,false));
        basicItem.add(new Items("Umbrella",category,false));
        basicItem.add(new Items("Journal",category,false));
        basicItem.add(new Items("Money",category,false));
        basicItem.add(new Items("Wallet",category,false));
        basicItem.add(new Items("Medicines",category,false));

        return basicItem;
    }

    public List<Items> getPersonalCareData(){
        String[] data={"Tooth-brush","Tooth-paste","Floss","Mouthwash","Shaving cream","COntact lens","Tweezers","Soap","Makeup Kit","Perfume","Moisturizer","Lip Balm","Menstrual needs"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE,data);
    }

    public List<Items> getClothingData(){
        String[] data={"Trousers","Tshirts","Sneakers","Cap","Socks","Towels","Gloves","Scarf","Inner Wear","SwimSuit","Coat","Belt","Coat"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE,data);
    }

    public List<Items> getTechnologyData(){
        String[] data={"Phone","Tablet","camera","phone charger","laptop charger","camera battery","speakers","Portable charger"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE,data);
    }
    public List<Items> getFoodData(){
        String[] data={"Nuts","Chips","Crackers","Candy","Water","Soft Drinks","Juice","Thermos","Coffee"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE,data);
    }
    public List<Items> getBeachSuppliesData(){
        String[] data={"Sunscreen","FLoaters","Beach umbrella","Cooler","Water","Beach towel","Beach bag","Sunbed","Waterproof watch","Waterproof phone cover"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE,data);
    }
    public List<Items> getCarSupplies(){
        String[] data={"Pump","Car jack","Spare car key","car charger","window sun shades"};
        return prepareItemsList(MyConstants.CAR_SUPPLIES_CAMEL_CASE,data);
    }
    public List<Items> prepareItemsList(String category,String[] data){
        List<String> list= Arrays.asList(data);
        List<Items> dataList=new ArrayList<>();
        dataList.clear();

        for (int i=0;i<list.size();i++){
            dataList.add(new Items(list.get(i),category,false));
        }
        return dataList;
    }

    public List<List<Items>> getAllData(){
        List<List<Items>> listofAllItems=new ArrayList<>();
        listofAllItems.clear();

        listofAllItems.add(getBasicData());
        listofAllItems.add(getBeachSuppliesData());
        listofAllItems.add(getFoodData());
        listofAllItems.add(getClothingData());
        listofAllItems.add(getCarSupplies());
        listofAllItems.add(getTechnologyData());
        listofAllItems.add(getPersonalCareData());

        return listofAllItems;
    }

    public void persistAllData(){
        List<List<Items>> listOfAllItems=getAllData();
        for (List<Items> list:listOfAllItems){
            for (Items items:list){
                database.mainDao().saveItem(items);
            }
        }
        System.out.println("Data added");
    }

    public void persistDataByCategory(String category,Boolean onlyDelete){
        try{
            List<Items> list=deleteAndGetListbyCategory(category,onlyDelete){
            if(!onlyDelete){
               for(Items item:list){
                   database.mainDao().saveItem(item);
               }
                Toast.makeText(this, category+"Reset Successfully.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,category+"Reset Successfully.",Toast.LENGTH_SHORT).show();
            }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();


        }
    }

    private List<Items> deleteAndGetListbyCategory(String category,Boolean onlyDelete){
        if(onlyDelete){
            database.mainDao().deleteAllByCategoryAndAddedBy(category,MyConstants.SYSTEM_SMALL);

        }else{
            database.mainDao().deleteAllByCategory(category);

        }
        switch(category){
            case MyConstants.BASIC_NEEDS_CAMEL_CASE:
                return getBasicData();
            case MyConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();
            case MyConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();
            case MyConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();
            case MyConstants.HEALTH_CAMEL_CASE:
                return getHealthData();
            case MyConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnologyData();
            case MyConstants.FOOD_CAMEL_CASE:
                return getFoodData();
            case MyConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliesData();
            case MyConstants.CAR_SUPPLIES_CAMEL_CASE:
                return getCarSupplies();
            case MyConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();
            default:
                return new ArrayList<>();

        }
    }


}
