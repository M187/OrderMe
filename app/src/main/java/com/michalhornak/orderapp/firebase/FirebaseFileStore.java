package com.michalhornak.orderapp.firebase;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by michal.hornak on 2/6/2018.
 */

public class FirebaseFileStore {

    static FirebaseStorage storageRef = FirebaseStorage.getInstance();


    public static void uploadFile(final Context context, String pathToFile, String pathInFirebase, String firebaseItemName){
        StorageReference riversRef = storageRef.getReference().child(pathInFirebase.concat("/").concat(firebaseItemName));
        Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context, "Upload failure!", Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(context, "Upload success!", Toast.LENGTH_LONG).show();
            }
        });
    }


}
