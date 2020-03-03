package com.example.class3;

public class MenuModel {

    public String menuName;
    public boolean hasChild, isGroup;


    public MenuModel(String menuName, boolean hasChild, boolean isGroup) {

        this.menuName = menuName;
        this.hasChild = hasChild;
        this.isGroup = isGroup;

    }


}
