package misespacios.MisEspaciosApi.db;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;

public class DBManager {

    private Firestore db;

    public DBManager() {
        /* TO DO:
            Set up SqlServer database connection and crud
        * */
    }

    public static void main(String[] args) {
        DBManager db = new DBManager();

    }
}
