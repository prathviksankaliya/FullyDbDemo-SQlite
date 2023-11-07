package com.shadowtech.fullydbdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shadowtech.fullydbdemo.Data.MyDbHandler;
import com.shadowtech.fullydbdemo.Fragments.AddContactFragment;
import com.shadowtech.fullydbdemo.Fragments.ViewAllContactsFragment;
import com.shadowtech.fullydbdemo.Model.Contact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView showtext;
    Button Addcontact, ViewAllContacts;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showtext = findViewById(R.id.showtext);
        Addcontact = findViewById(R.id.AddContact);
//        ViewAllContacts = findViewById(R.id.ViewAllContacts);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentcontainer, ViewAllContactsFragment.class, null);
        ft.commit();

        Addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentcontainer, AddContactFragment.class, null);
                ft.commit();
            }
        });
//        ViewAllContacts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.fragmentcontainer , ViewAllContactsFragment.class , null);
//                ft.commit();
//            }
//        });


//        MyDbHandler myDbHandler = new MyDbHandler(this);
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
//        Date date = new Date();
////        Contact prathvik = new Contact("prathvik" , "9723816724" , ""+simpleDateFormat.format(date));
////        myDbHandler.addContact(prathvik);
//        Contact Aryan = new Contact("Aryan" , "7069500999" , ""+simpleDateFormat.format(date));
//        myDbHandler.addContact(Aryan);
//
//        List<Contact> allContact = myDbHandler.getAllContacts();
//        for (Contact contact: allContact)
//        {
//           showtext.setText("Id :"+contact.getId() + "\n" +
//                   "Name :"+contact.getName() + "\n" +"Phone :"+contact.getPhone() + "\n" + "Time :"+contact.getTime() +"\n");
//            Log.d("bhaidb" , "Id :"+contact.getId() + "\n" +
//                    "Name :"+contact.getName() + "\n" +"Phone :"+contact.getPhone() + "\n" + "Time :"+contact.getTime() +"\n");
//        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() == 1)
        {
            finish();
        }
        else {
            super.onBackPressed();
        }
    }
}
