package com.droid.solver.admin2020.addplaces;

public class AddPlacesModel {
    String cityName;
    String description;
    String imageUrl;

     AddPlacesModel(String cityName,String description,String imageUrl){
         this.cityName=cityName;
         this.description=description;
         this.imageUrl=imageUrl;
     }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
