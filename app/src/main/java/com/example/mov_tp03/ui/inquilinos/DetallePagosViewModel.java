package com.example.mov_tp03.ui.inquilinos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class DetallePagosViewModel extends AndroidViewModel {
    private Context context;
    public DetallePagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
}