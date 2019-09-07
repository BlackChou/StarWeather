package com.example.starweather.util;

import android.text.TextUtils;

import com.example.starweather.db.City;
import com.example.starweather.db.County;
import com.example.starweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// 这是一个解析服务器下载数据的工具类，处理JSON
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i = 0; i < allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    // 数据入库
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for(int i = 0; i < allCities.length(); i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    // 数据入库
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);  // 得到服务器下载数据
                for(int i = 0; i < allCounties.length(); i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    // 数据入库
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}