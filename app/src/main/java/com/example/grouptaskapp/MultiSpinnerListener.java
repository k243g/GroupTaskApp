package com.example.grouptaskapp;

import java.util.List;

/**
 * Created by Kerrn on 11/12/2016.
 */
public interface MultiSpinnerListener {
   //void onItemsSelected(boolean[] selected);
   void onItemsSelected(List<KeyPairBoolData> items);
}
