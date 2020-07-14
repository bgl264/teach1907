package com.teach.frame.utils;

import java.util.HashMap;

/**
 * Created by 任小龙 on 2020/5/27.
 */
public class ParamHashMap extends HashMap<String,Object> {

   /* private static ParamHashMap mMap;

    private ParamHashMap(){}

    public static ParamHashMap create(){
        mMap = new ParamHashMap();
        return mMap;
    }

    public ParamHashMap add(String key,Object value){
        mMap.put(key,value);
        return mMap;
    }*/
   public ParamHashMap add(String key,Object value){
       this.put(key,value);
       return this;
   }
}
