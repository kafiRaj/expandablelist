package com.example.class3;

import android.annotation.SuppressLint;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;

// This class is not used anywhere


public class BottomNavHelper {

    @SuppressLint("RestrictedApi")

    public static void disabledShiftMode(BottomNavigationView view) {

        BottomNavigationMenuView navigationView =(BottomNavigationMenuView) view.getChildAt(0);

        try {
            Field shiftMode = navigationView.getClass().getDeclaredField("ShiftMode");
            shiftMode.setAccessible(true);
            shiftMode.setBoolean(navigationView, false);
            shiftMode.setAccessible(false);

            for(int i = 0; i<navigationView.getChildCount(); i++){

                BottomNavigationItemView item = (BottomNavigationItemView) navigationView.getChildAt(i);
                item.setShifting(false);
                item.setChecked(item.getItemData().isChecked());

            }



        } catch (NoSuchFieldException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }
    }
}
