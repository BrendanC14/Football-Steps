package com.cutlerdevelopment.footballsteps.Mocks;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.service.autofill.Dataset;

import com.cutlerdevelopment.footballsteps.Constants.Numbers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GoogleFITAPIMock {


    private static GoogleFITAPIMock instance = null;
    public static GoogleFITAPIMock getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new GoogleFITAPIMock();
        return instance;
    }

    public GoogleFITAPIMock() {

        instance = this;
    }

    public HashMap<Date, Integer> getStepsForDates(List<Date> allDates) {
        HashMap<Date, Integer> map = new HashMap<>();
        Random r = new Random();
        for (Date date : allDates) {
            map.put(date, r.nextInt(Numbers.MOCK_MAX_STEPS - Numbers.MOCK_MIN_STEPS) + Numbers.MOCK_MIN_STEPS);
        }

        return map;
    }


}
