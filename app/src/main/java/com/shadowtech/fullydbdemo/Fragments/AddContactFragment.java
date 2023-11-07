package com.shadowtech.fullydbdemo.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shadowtech.fullydbdemo.Data.MyDbHandler;
import com.shadowtech.fullydbdemo.Model.Contact;
import com.shadowtech.fullydbdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AddContactFragment extends Fragment {

   Button btnAdd , btnDelete;
   EditText edName , edPhone;
   MyDbHandler myDbHandler;

    public AddContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences spf = requireContext().getSharedPreferences("UserContact" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.clear().apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_contact, container, false);
         myDbHandler = new MyDbHandler(getContext());
        edName = view.findViewById(R.id.edName);
        edPhone = view.findViewById(R.id.edPhone);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnDelete = view.findViewById(R.id.btndelete);
        SharedPreferences spf = getContext().getSharedPreferences("UserContact" , Context.MODE_PRIVATE);
        String pre_id = spf.getString("id" , null);
        String pre_name = spf.getString("name" , null);
        String pre_phone = spf.getString("phone" , null);

        if(pre_name == null)
        {
           btnAdd.setText("Add");
        }
        else
        {
            edName.setText(pre_name);
            edPhone.setText(pre_phone);
            btnAdd.setText("Update");
            btnDelete.setVisibility(View.VISIBLE);
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pre_name == null)
                {
                    String Name = edName.getText().toString();
                    String Phone = edPhone.getText().toString();
                    if(Name.isEmpty())
                    {
                        edName.setError("Fillup The Name");
                        edName.requestFocus();
                    }
                    else if(Phone.length() != 10)
                    {
                        edPhone.setError("Fillup The Correct Phone Number");
                        edPhone.requestFocus();
                    }
                    else {
                        MyDbHandler myDbHandler = new MyDbHandler(getContext() );
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
                        Date date = new Date();
                        Contact contactname = new Contact(Name , Phone ,""+simpleDateFormat.format(date));
                        myDbHandler.addContact(contactname);
                        Toast.makeText(getContext(), "Added Contact "+Name, Toast.LENGTH_SHORT).show();

                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentcontainer, ViewAllContactsFragment.class, null);
                        ft.commit();
                    }
                }
                else {

                    String update_Name = edName.getText().toString();
                    String update_Phone = edPhone.getText().toString();
                    if(update_Name.isEmpty())
                    {
                        edName.setError("Fillup The Name");
                        edName.requestFocus();
                    }
                    else if(update_Phone.length() != 10)
                    {
                        edPhone.setError("Fillup The Correct Phone Number");
                        edPhone.requestFocus();
                    }
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
                        Date date = new Date();
                        Contact updatecontact = new Contact(Integer.parseInt(pre_id) , update_Name , update_Phone , ""+simpleDateFormat.format(date));
                          int affectedRows = myDbHandler.updateContact(updatecontact);


                    if (affectedRows == 0) {
                        Toast.makeText(getContext(), "Error  with Update", Toast.LENGTH_SHORT).show();
                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                                ft.replace(R.id.fragmentcontainer , ViewAllContactsFragment.class ,null).commit();

                    } else {
                        Toast.makeText(getContext(), "Success  with Update", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact deletecontact = new Contact(pre_name , pre_phone);
                myDbHandler.deleteContact(deletecontact);
                Toast.makeText(getContext(), pre_name+" Deleted", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentcontainer , ViewAllContactsFragment.class ,null).commit();
            }
        });
        return view;
    }
}