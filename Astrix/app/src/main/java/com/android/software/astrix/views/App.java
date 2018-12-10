package com.android.software.astrix.views;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.android.software.astrix.R;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class App extends AppCompatActivity {
    private CircleMenu circleMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista);
        menu();

    }
    private void menu(){
        final String[] array = new String []{"A","B","C","D","E"};
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(R.color.transparente,R.drawable.name2,R.drawable.name2)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.a)
                .addSubMenu(Color.parseColor("#30A400"),R.drawable.b)
                .addSubMenu(Color.parseColor("#FF4B32"),R.drawable.c)
                .addSubMenu(Color.parseColor("#8A39FF"),R.drawable.d)
                .addSubMenu(Color.parseColor("#FF6A00"),R.drawable.e)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Log.println(Log.DEBUG," Presionaste joquin: ",array[index]);

                    }

                });

    }

}
