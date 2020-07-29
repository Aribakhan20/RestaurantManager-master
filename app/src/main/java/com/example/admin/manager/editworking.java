package com.example.admin.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class editworking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworking);



        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Workinglist")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null;
                        ArrayList<String> pos= new ArrayList<>();
                        ArrayList<String> names= new ArrayList<>();

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot x : task.getResult())
                            {
                                x1 = x.getString("name");
                                x2 = x.getString("position");


                                System.out.println("Name" + x2);
                                names.add(x1);
                                pos.add(x2);


                            }

                            position_adapter adapter = new position_adapter( editworking.this,names,pos,2);
                            RecyclerView recyclerView=findViewById(R.id.recyclerView2);

                            if(adapter != null || recyclerView !=null)
                            {
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(editworking.this));
                            }

                            else
                            {
                                System.out.println("Eorrrosr");
                            }
                        }
                        else
                        {
                            System.out.println("Eorrrosr");
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this,managermenu.class);
        startActivity(setIntent);
    }
}
