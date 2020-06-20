package com.example.customadapterdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class UserListAdapter extends BaseAdapter {
    Context ctx; ArrayList<User> users;
    LayoutInflater layoutInflater;
    // TODO: реализовать сортировку по каждому из полей
    // класса: sex, name, phoneNumber

    public void resetList(String option) {
        switch (option) {
            case "По имени":
                Comparator<User> compareByName = new Comparator<User>() {
                    @Override
                    public int compare(User name1, User name2) {
                        return name1.getName().compareTo(name2.getName());
                    }
                };

                Collections.sort(users, compareByName);
                break;
            case "По номеру телефона":
                Comparator<User> compareByPhone = new Comparator<User>() {
                    @Override
                    public int compare(User name1, User name2) {
                        return name1.getNumber().compareTo(name2.getNumber());
                    }
                };

                Collections.sort(users, compareByPhone);
                break;
            case "По полу":
                Comparator<User> compareBySex = new Comparator<User>() {
                    @Override
                    public int compare(User name1, User name2) {
                        return name1.getSex().compareTo(name2.getSex());
                    }
                };

                Collections.sort(users, compareBySex);
                break;
        }
    }


    public UserListAdapter(Context ctx, ArrayList<User> users) {
        this.ctx = ctx;
        this.users = users;
        layoutInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // получаем данные из коллекции
        Date begin = new Date();
        if (convertView == null) {
            convertView = layoutInflater
                    .inflate(R.layout.useritem, parent, false);
        }
        ImageView ivUserpic = convertView.findViewById(R.id.userpic);
        User u = users.get(position);

        // создаём разметку (контейнер)
        convertView = LayoutInflater.from(ctx).
                inflate(R.layout.useritem, parent, false);
        // получаем ссылки на элементы интерфейса
        ivUserpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
            }
        });
        TextView tvName = convertView.findViewById(R.id.name);
        TextView tvPhone = convertView.findViewById(R.id.phoneNumber);

        // задаём содержание

        tvName.setText(u.name);
        tvPhone.setText(u.phoneNumber);
        tvName.setTextSize(18);
        tvPhone.setTextSize(18);
        switch (u.sex) {
            case MAN: ivUserpic.setImageResource(R.drawable.user_man); break;
            case WOMAN: ivUserpic.setImageResource(R.drawable.user_woman); break;
            case UNKNOWN: ivUserpic.setImageResource(R.drawable.user_unknown); break;
        }
        Date finish = new Date();
        Log.d("mytag", "getView time: "+(finish.getTime()-begin.getTime()));
        return convertView;
    }
}
