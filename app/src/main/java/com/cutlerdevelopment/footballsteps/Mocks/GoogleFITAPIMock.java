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

    public HashMap<Date, HashMap<Integer, Integer>> getStepsForDates(List<Date> allDates) {
        HashMap<Date, HashMap<Integer, Integer>> finalMap = new HashMap<>();
        Random r = new Random();
        for (Date date : allDates) {
            HashMap<Integer, Integer> numbersMap = new HashMap<>();
            numbersMap.put(r.nextInt(Numbers.MOCK_MAX_STEPS - Numbers.MOCK_MIN_STEPS) + Numbers.MOCK_MIN_STEPS,
                    r.nextInt(Numbers.MOCK_MAX_AMINUTES - Numbers.MOCK_MIN_AMINUTES) + Numbers.MOCK_MIN_AMINUTES);
            finalMap.put(date, numbersMap);
        }

        return finalMap;
    }


}
