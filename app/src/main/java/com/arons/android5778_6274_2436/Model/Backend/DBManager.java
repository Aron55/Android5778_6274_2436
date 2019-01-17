package com.arons.android5778_6274_2436.Model.Backend;

import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;

public interface DBManager {
    void addNewClientRequestToDataBase(Ride ride, Action action);

    interface Action {
        void onSuccess();
        void onFailure();
    }
}

