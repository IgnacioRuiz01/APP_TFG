package com.vedruna.tfg.Network;

import android.content.Context;

import com.vedruna.tfg.Interfaces.AuthInterface;
import com.vedruna.tfg.Interfaces.ClassAdInterface;
import com.vedruna.tfg.Interfaces.UserInterface;

public class RetrofitClient {

    private static AuthInterface authInterface;
    private static ClassAdInterface classAdInterface;
    private static UserInterface userInterface;

    public static AuthInterface getAuthInterface(Context context) {
        if (authInterface == null) {
            authInterface = ApiClient.getClient(context).create(AuthInterface.class);
        }
        return authInterface;
    }

    public static ClassAdInterface getClassAdInterface(Context context) {
        if (classAdInterface == null) {
            classAdInterface = ApiClient.getClient(context).create(ClassAdInterface.class);
        }
        return classAdInterface;
    }

    public static UserInterface getUserInterface(Context context) {
        if (userInterface == null) {
            userInterface = ApiClient.getClient(context).create(UserInterface.class);
        }
        return userInterface;
    }
}
