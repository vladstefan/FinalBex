package com.db.bexlibrary.BexLibrary.entities;

import lombok.Data;

@Data
public class GlobalVariables {
    public String email;
    private static GlobalVariables globalVariables = null;


    public static GlobalVariables getInstance(){
        if(globalVariables!=null){
            return globalVariables;
        }else{
            globalVariables = new GlobalVariables();
        }
        return globalVariables;
    }

}
