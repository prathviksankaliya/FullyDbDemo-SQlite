package com.shadowtech.fullydbdemo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.shadowtech.fullydbdemo.Adapter.Custom_lisview_adapter;
import com.shadowtech.fullydbdemo.Data.MyDbHandler;
import com.shadowtech.fullydbdemo.Model.Contact;
import com.shadowtech.fullydbdemo.R;

import java.util.ArrayList;
import java.util.List;


public class ViewAllContactsFragment extends Fragment {

RecyclerView listContacts;
Custom_lisview_adapter adapter;
    public ViewAllContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_view_all_contacts, container, false);
        listContacts = view.findViewById(R.id.listcontacts);

        MyDbHandler myDbHandler = new MyDbHandler(getContext());


        List<Contact> allContact = myDbHandler.getAllContacts();

        adapter = new Custom_lisview_adapter(getContext() , allContact);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listContacts.setLayoutManager(linearLayoutManager);
        listContacts.setAdapter(adapter);


        return view;
    }
}
