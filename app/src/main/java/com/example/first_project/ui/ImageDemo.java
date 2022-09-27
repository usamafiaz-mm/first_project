package com.example.first_project.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import com.example.first_project.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageDemo extends AppCompatActivity {

    Button selectImageGallery, selectImageCamera;
    ImageView imageView;
    ActivityResultLauncher pickMedia =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), uri -> {
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: " + uri);
                } else {
                    Log.d("PhotoPicker", "No media selected");
                }
            });

    ActivityResultLauncher<Uri> someActivityResultLauncher
            = registerForActivityResult(new ActivityResultContracts.TakePicture(),

            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    // stuff here

                }

            });



    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK
                        && result.getData() != null) {
                    Uri photoUri = result.getData().getData();
                    Glide
                            .with(ImageDemo.this)
                            .load(photoUri)
                            .centerCrop()

                            .into(imageView);
                    System.out.println(photoUri);
                    //use photoUri here
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_demo);

        selectImageGallery = findViewById(R.id.btnSelectImageGallery);
        selectImageCamera = findViewById(R.id.btnSelectImageCamera);


        imageView = findViewById(R.id.putImage);


        selectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);
            }
        });

        selectImageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                    // Start the activity with camera_intent, and request pic id
                startActivityForResult(camera_intent, 101);

            }
        });


//selectImageGallery.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
//        galleryIntent.setType("image/*");
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        Intent chooser = Intent.createChooser(galleryIntent, "Some text here");
//        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { cameraIntent });
//        startActivityForResult(chooser, 100);
//    }
//});


//        selectImage.setOnClickListener(v -> {
//            new DialogImageChooser(ImageDemo.this, selectedOption -> {
//                if (selectedOption.equalsIgnoreCase("camera")) {
//                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                    // Start the activity with camera_intent, and request pic id
//                    startActivityForResult(camera_intent, 111);
//                } else if (selectedOption.equalsIgnoreCase("gallery")) {
//                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(i, 112);
//                }
//            }).show(getSupportFragmentManager(), "add_photo_dialog");
//        });

//        selectImage.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
////                ActivityCompat.requestPermissions(ImageDemo.this, new String[] {Manifest.permission.CAMERA}, 100);
////
////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                launcher.launch(intent);
//
//
//                if(!checkCameraPermission()){
//                    requestCameraPermission();
//                }else{
//                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    // Start the activity with camera_intent, and request pic id
//                    startActivityForResult(camera_intent, 111);
//                }
//
//
//
//
////                boolean pick = true;
////                if (pick) {
////                    if (!checkCameraPermission()) {
////                        requestCameraPermission();
////                    } else PickImage();
////
////                } else {
////                    if (!checkStoragePermission()) {
////                        requestStoragePermission();
////                    } else PickImage();
////                }
//
//
//                // Registers a photo picker activity launcher in single-select mode.
//
//
//// Include only one of the following calls to launch(), depending on the types
//// of media that you want to allow the user to choose from.
//
//// Launch the photo picker and allow the user to choose images and videos.
//
//
//            }
//        });
    }


//    private void PickImage() {
//        CropImage.activity()
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .start(this);
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void requestStoragePermission() {
//        requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    //
    private boolean checkStoragePermission() {
        boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return res2;
    }

    private boolean checkCameraPermission() {
        boolean res1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        return res1;
    }


    //    private void imageChooser()
//    {
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//
//        launchSomeActivity.launch(i);
//    }
//
//    ActivityResultLauncher<Intent> launchSomeActivity
//            = registerForActivityResult(
//            new ActivityResultContracts
//                    .StartActivityForResult(),
//            result -> {
//                if (result.getResultCode()
//                        == Activity.RESULT_OK) {
//                    Intent data = result.getData();
//                    // do your operation from here....
//                    if (data != null
//                            && data.getData() != null) {
//                        Uri selectedImageUri = data.getData();
//                        Bitmap selectedImageBitmap;
//                        try {
//                            selectedImageBitmap
//                                    = MediaStore.Images.Media.getBitmap(
//                                    this.getContentResolver(),
//                                    selectedImageUri);
//                            imageView.setImageBitmap(
//                                    selectedImageBitmap);
//                        }
//                        catch (IOException e) {
//                            e.printStackTrace();
//                        }
////                        imageView.setImageBitmap(
////                                selectedImageBitmap);
//                    }
//                }
//            });
//
//
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == 100) {
            // BitMap is data structure of image file which store the image in memory
            Uri selectedImageUri = data.getData();
//        System.out.println(data.getExtras());

            // Set the image in imageview for display
            imageView.setImageURI(selectedImageUri);
        }
        else if (requestCode==101){
            ContextWrapper cw = new ContextWrapper(getApplicationContext());

            String filename = UUID.randomUUID().toString()+".png";
String path =  ImageDemo.this.getFilesDir().getPath() + File.separator +  "imageDir" ;
File f = new File(path);
if(!f.exists()){
    f.mkdirs();
}
//            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File dest = new File(path, filename);
            Log.e("PATH", "onActivityResult: "+dest);

            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            try {
                FileOutputStream out = new FileOutputStream(dest);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                imageView.setImageURI(Uri.fromFile(dest));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}