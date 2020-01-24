package com.example.limjichen.recycling2.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reale on 23/11/2016.
 */

public class TitleCreator {
    static TitleCreator _titleCreator;
    List<TitleParent> _titleParents;

    public TitleCreator(Context context) {
        _titleParents = new ArrayList<>();
        String[] myStringArray = {"Recycle with 3 Different Classes","Recycle with Minimum 10 Units","Recycle 5 Days Consecutively","Like our Facebook Page"};
        for(int i=0;i<=3;i++)
        {
            TitleParent title = new TitleParent(myStringArray[i]);
            //TitleParent title = new TitleParent(String.format("Challenges #%d",i));
            _titleParents.add(title);
        }
    }

    public static TitleCreator get(Context context)
    {
        if(_titleCreator == null)
            _titleCreator = new TitleCreator(context);
        return _titleCreator;
    }

    public List<TitleParent> getAll() {
        return _titleParents;
    }
}
