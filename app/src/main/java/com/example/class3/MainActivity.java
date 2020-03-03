package com.example.class3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private HomeFragment homeFragment;
    private InboxFragment inboxFragment;
    private LocationFragment locationFragment;
    private FavoriteFragment favoriteFragment;
    private ExpandListAddapter expandListAddapter;
    private ExpandableListView expandableListView;
    List<MenuModel> menuModelList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> chidlist = new HashMap<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayId);
        toolbar = findViewById(R.id.mToolbar);
        frameLayout = findViewById(R.id.frameId);
        navigationView = findViewById(R.id.navViewId);

        expandableListView = findViewById(R.id.expaneded_menuId);
        prePareMenu();
        populateExpandableList();

        // tool bar set up
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        toolbar.setTitleTextColor(Color.WHITE);


        //Creating object of Fragment classes
        homeFragment = new HomeFragment();
        inboxFragment = new InboxFragment();
        locationFragment = new LocationFragment();
        favoriteFragment = new FavoriteFragment();


        //left Navigation set up

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        actionBarDrawerToggle.syncState();

        // Bottom Navigation setup
        bottomNavigationView = findViewById(R.id.bottomNavId);

        setFragment(homeFragment);

        bottomNavigationView.setSelectedItemId(R.id.homeId);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.homeId:
                        setFragment(homeFragment);
                        return true;

                    case R.id.messageId:
                        setFragment(inboxFragment);
                        return true;

                    case R.id.locationId:
                        setFragment(locationFragment);
                        return true;

                    case R.id.favoriteId:
                        setFragment(favoriteFragment);
                        return true;


                    default:
                        return false;
                }


            }
        });


        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.settingsId) {

                            Toast.makeText(MainActivity.this, "Settings is clicked", Toast.LENGTH_SHORT).show();
                        } else if (id == R.id.shareId) {

//                    Toast.makeText(MainActivity.this, "Share is clicked", Toast.LENGTH_SHORT).show();

                            Snackbar snackbar = Snackbar.make(drawerLayout, "Share is clicked", Snackbar.LENGTH_LONG);
                            snackbar.show();
                            snackbar.setAction("Okay", new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "Nothing happen", Toast.LENGTH_SHORT).show();


                                }
                            });
                        } else if (id == R.id.logoutId) {

                            Toast.makeText(MainActivity.this, "Logout is clicked", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    }
                });
    }



    private void populateExpandableList() {

        // menumodellist childlist

        expandListAddapter = new ExpandListAddapter(this, menuModelList, chidlist);
        expandableListView.setAdapter(expandListAddapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (menuModelList.get(groupPosition).isGroup){

                    if (!menuModelList.get(groupPosition).hasChild){


                    }
                }

                return false;
            }
        });

    }

    private void prePareMenu() {

        MenuModel menuModel = new MenuModel("test", true,true);

        menuModelList.add(menuModel);

        List<MenuModel> childModelList = new ArrayList<>();
        MenuModel childItem = new MenuModel("Item1",false,false);
        childModelList.add(childItem);

        childItem = new MenuModel("Item2",false,false);
        childModelList.add(childItem);

        if (menuModel.hasChild){
            chidlist.put(menuModel, childModelList);
        }
    }


    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.frameId, fragment);
        fragmentTransaction.commit();

    }

}
