package com.shadowtech.fullydbdemo.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.shadowtech.fullydbdemo.Data.MyDbHandler;
import com.shadowtech.fullydbdemo.Fragments.AddContactFragment;
import com.shadowtech.fullydbdemo.Model.Contact;
import com.shadowtech.fullydbdemo.R;

import java.util.List;


public class Custom_lisview_adapter extends RecyclerView.Adapter<Custom_lisview_adapter.viewHolder> {
    Context context;
    List<Contact> list;
    int flag = 1;
    MyDbHandler myDbHandler = new MyDbHandler(context);

    public Custom_lisview_adapter(Context context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontacts , parent , false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Contact contact = list.get(position);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        holder.time.setText(contact.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new AddContactFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer , fragment).commit();
                SharedPreferences spf = context.getSharedPreferences("UserContact" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.putString("id" , String.valueOf(contact.getId()));
                editor.putString("name" , contact.getName());
                editor.putString("phone" , contact.getPhone());
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView name , phone , time;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txName);
            phone = itemView.findViewById(R.id.txPhone);
            time = itemView.findViewById(R.id.txTime);
        }
    }
}
