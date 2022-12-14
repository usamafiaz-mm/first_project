package com.example.first_project.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.first_project.R;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.model.RegData;
import com.example.first_project.ui.FormActivity;
import com.example.first_project.ui.FragmentDemo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskara.widget.MaskEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;


public class FormFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Dialog customDialog;
    TextInputEditText email, name, university, company, age, userDOBView, firstNumber, secondNumber, result;
    TextInputLayout compLay, uniLay, userDOBViewLay;
    EditText line1, line2, line3, line4;
    Button addBtn, removeBtn, submitBtn;
    MaskEditText cnic;
    RadioGroup radioGroup;
    AutoCompleteTextView autoCompleteTextView;
    ImageView profilePicture, coverPicture;

    String nameData, emailData, designation, profilePicturePath = "", coverPicturePath = "";

    Spinner spinner;
    int id;

    boolean isDataValid = true;


    static int currentLines = 1;

    Designation designationEnum;
    Gender genderEnum;

    boolean shouldUpdate = false;


    public FormFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.form_email);
        university = view.findViewById(R.id.form_uni);
        company = view.findViewById(R.id.form_comap);
        compLay = view.findViewById(R.id.companyLay);
        uniLay = view.findViewById(R.id.uni_lay);
        firstNumber = view.findViewById(R.id.fnum);
//        firstNumber.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "99999")});
        secondNumber = view.findViewById(R.id.snum);
//        secondNumber.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "99999")});
        result = view.findViewById(R.id.rnum);
        name = view.findViewById(R.id.form_name);
        age = view.findViewById(R.id.form_age);

//        age.setFilters(new InputFilter[]{ new InputFilterMinMax("18", "99")});


        line1 = view.findViewById(R.id.line1);
        line2 = view.findViewById(R.id.line2);
        line3 = view.findViewById(R.id.line3);
        line4 = view.findViewById(R.id.line4);

        coverPicture = view.findViewById(R.id.cover_picture);
        profilePicture = view.findViewById(R.id.profile_picture);
        addBtn = view.findViewById(R.id.add_btn);
        removeBtn = view.findViewById(R.id.rm_btn);
        submitBtn = view.findViewById(R.id.submit_btn);
        cnic = view.findViewById(R.id.form_cnic);
//        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        spinner = view.findViewById(R.id.form_dsn);
        EditText[] texts = {line1, line2, line3, line4};

        ArrayList dsn = new ArrayList();
        dsn.add("Select Designation");
        dsn.add("Student");
        dsn.add("Employee");


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, dsn);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int id = bundle.getInt("id", 1);
            Log.d("TAG", "onViewCreated: " + id);

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    RegData data = DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase().regUserDao().SearchById(id);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            shouldUpdate = true;
                            submitBtn.setText("Update");
                            email.setText(data.getEmail());
                            name.setText(data.getName());
                            age.setText(String.valueOf(data.getAge()));
                            cnic.setText(data.getCnic());
                            System.out.println(data.getDesignation());
                            System.out.println(data.getDesignation() == "Student" ? 1 : 2);

                            spinner.setSelection(adapter.getPosition(data.getDesignation()));
                            if (data.getDob() != null) {
                                String pattern = "dd-MM-yyyy";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                                String date = simpleDateFormat.format(data.getDob());
                                System.out.println(date);

                                userDOBView.setText(date.toString());

                            }

                            if (!data.getCompany().equals(""))
                                company.setText(data.getCompany());
                            if (!data.getUniversity().equals(""))
                                university.setText(data.getUniversity());


                            if (data.getGender().toString().equals("Male")) {
                                RadioButton rb = view.findViewById(R.id.male);
                                rb.setChecked(true);
                            } else {
                                RadioButton rb = view.findViewById(R.id.female);
                                rb.setChecked(true);
                            }

                            if (data.getCoverImage() != null) {
                                coverPicture.setImageURI(Uri.parse(data.getCoverImage()));
                            }

                            if (data.getProfileImage() != null) {
                                profilePicture.setImageURI(Uri.parse(data.getProfileImage()));
                            }

                        }
                    });
                }
            });
        }


        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog = new Dialog(getActivity());
                customDialog.setContentView(R.layout.custom_dialog);
                customDialog.show();
                Button cameraBtn = (Button) customDialog.findViewById(R.id.camera_btn);
                cameraBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(camera_intent, 100);
                    }
                });

                Button galleryButton = customDialog.findViewById(R.id.gallery_btn);
                galleryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 101);
                    }
                });


                customDialog.show();
            }
        });

        coverPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog = new Dialog(getActivity());
                customDialog.setContentView(R.layout.custom_dialog);
                customDialog.show();
                Button cameraBtn = (Button) customDialog.findViewById(R.id.camera_btn);
                cameraBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(camera_intent, 103);
                    }
                });

                Button galleryButton = customDialog.findViewById(R.id.gallery_btn);
                galleryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 104);
                    }
                });


                customDialog.show();
            }
        });


        radioGroup = view.findViewById(R.id.rgGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male:

                        genderEnum = Gender.Male;

                        break;
                    case R.id.female:
                        genderEnum = Gender.Female;
                        break;
                }
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDataValid = true;
                RegData regData = new RegData();

                if (getName() != null)
                    regData.setName(getName());

                if (getCnic() != null)
                    regData.setCnic(getCnic());
                if (emailData != null) {
                    regData.setEmail(emailData);
                } else {
                    email.setError("Invalid Email");
                    isDataValid = false;
                    emailData = null;
                }

                if (getAge() != null)
                    regData.setAge(getAge());


                if (designationEnum != null) {
                    if (designationEnum == Designation.Student) {
                        if (getUniversity() != null) {
                            regData.setUniversity(getUniversity());
                            regData.setCompany("");
                            regData.setDesignation("Student");
                        }

                    }
                    if (designationEnum == Designation.Employee) {
                        if (getCompany() != null) {
                            regData.setCompany(getCompany());
                            regData.setUniversity("");
                            regData.setDesignation("Employee");

                        }

                    }
                } else {
                    ((TextView) spinner.getSelectedView()).setError("Error message");
                    isDataValid = false;
                }
                if (genderEnum == null) {
                    RadioButton radioButton = view.findViewById(R.id.female);
                    radioButton.setError("Please select Gender");
                    isDataValid = false;

                } else {
                    regData.setGender(genderEnum.toString());
                    RadioButton radioButton = view.findViewById(R.id.female);
                    radioButton.setError(null);
                }

                System.out.println(userDOBView.getText());
                String a = String.valueOf(userDOBView.getText());

                if (String.valueOf(userDOBView.getText()).equals("")) {
                    regData.setDob(null);
                } else {
                    String tempDate = userDOBView.getText().toString();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    userDOBView.setError(null);

                    try {
                        Date date = simpleDateFormat.parse(tempDate);
                        regData.setDob(date);
                        System.out.println(date.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

                String address = "";

                for (int i = 0; i <= currentLines; i++) {
                    String temp = texts[i].getText().toString();


                    address = address + " " + texts[i].getText().toString();
                }

                if (profilePicturePath.equals("")) {
                    Log.e("TAG", "onClick: " + regData);
                    new AlertDialog.Builder(getActivity().getApplicationContext())
                            .setTitle("Profile Picture is Requires")
                            .show();
                    isDataValid = false;
                } else {
                    regData.setProfileImage(profilePicturePath);
                }
                if (!coverPicturePath.equals(""))
                    regData.setCoverImage(coverPicturePath);
                regData.setAddress(address.trim());
                System.out.println(address);
                if (isDataValid && shouldUpdate) {
                    regData.setId(id);
                    Toast.makeText(getActivity(), "UPDATED", Toast.LENGTH_LONG).show();
                    System.out.println(regData.toString());
                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase().regUserDao().updateRegData(regData);
                        }
                    });
                } else if (isDataValid) {
                    Toast.makeText(getActivity(), "Added to DB", Toast.LENGTH_LONG).show();
                    System.out.println(regData.toString());
                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            Long id = DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase().regUserDao().insert(regData);
                            FragmentManager fragmentManager2 = getParentFragmentManager();
                            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                            DetailFragment detailFragment = new DetailFragment();
                            Bundle args = new Bundle();
                            args.putInt("id", Math.toIntExact(id));
                            detailFragment.setArguments(args);
                            fragmentTransaction2.hide(FormFragment.this);
                            fragmentTransaction2.add(R.id.frameLayout, detailFragment);
                            fragmentTransaction2.commit();
                        }
                    });
                }
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLines >= 1 && currentLines < 3) {
                    currentLines += 1;
                    texts[currentLines].setVisibility(View.VISIBLE);
                    System.out.println(currentLines);
                    removeBtn.setVisibility(View.VISIBLE);
                    handleColor();

                }
            }
        });


        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLines >= 2 && currentLines <= 3) {
                    texts[currentLines].setVisibility(View.GONE);
                    currentLines -= 1;

                    System.out.println(currentLines);
                    handleColor();

                }
            }
        });


        userDOBView = view.findViewById(R.id.etDOB);

        userDOBView.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               // calender class's instance and get current date , month and year from calender
                                               final Calendar c = Calendar.getInstance();
                                               int mYear = c.get(Calendar.YEAR); // current year
                                               int mMonth = c.get(Calendar.MONTH); // current month
                                               int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                                               // date picker dialog
                                               DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                                       new DatePickerDialog.OnDateSetListener() {

                                                           @Override
                                                           public void onDateSet(DatePicker view, int year,
                                                                                 int monthOfYear, int dayOfMonth) {
                                                               // set day of month , month and year value in the edit text
                                                               userDOBView.setText(dayOfMonth + "-"
                                                                       + (monthOfYear + 1) + "-" + year);

                                                           }
                                                       }, mYear, mMonth, mDay);
                                               datePickerDialog.show();
                                           }
                                       }
        );

        firstNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                handleCalc();
            }
        });
        secondNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                handleCalc();
            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable target) {

                if (TextUtils.isEmpty(target) || !Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
                    email.setError("Invalid Email");
                    isDataValid = false;
                    emailData = null;

                }
                emailData = target.toString();
            }
        });
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                System.out.println("Inside on Change");

                System.out.println("Student");
                designation = "student";

                uniLay.setVisibility(View.VISIBLE);
                compLay.setVisibility(View.GONE);
                university.setVisibility(View.VISIBLE);
                company.setVisibility(View.GONE);
                designationEnum = Designation.Student;

                break;
            case 2:
                System.out.println("Inside on Change");
                company.setVisibility(View.VISIBLE);
                compLay.setVisibility(View.VISIBLE);
                uniLay.setVisibility(View.GONE);
                university.setVisibility(View.GONE);
                designation = "employee";
                System.out.println("Employee");
                designationEnum = Designation.Employee;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void handleCalc() {

        String fnum = firstNumber.getText().toString();
        String snum = secondNumber.getText().toString();


        if (fnum.equals("") || snum.equals("")) {
            result.setText("");
        } else {

            Double res = Double.valueOf(fnum) + Double.valueOf(snum);

            result.setText(String.valueOf(res));
        }

    }


    private void handleColor() {


        if (currentLines >= 3) {
            addBtn.setBackgroundColor(Color.parseColor("#808080"));

        } else {
            addBtn.setBackgroundColor(Color.parseColor("#17871C"));

        }


        if (currentLines <= 1) {
            removeBtn.setBackgroundColor(Color.parseColor("#808080"));

        } else {
            removeBtn.setBackgroundColor(Color.parseColor("#EC0A0A"));

        }
    }


    private String getName() {

        if (name.getText().toString().equals("")) {
            isDataValid = false;
            name.setError("Invalid Name");
            return null;
        } else {
            return name.getText().toString();
        }
    }


    String getCnic() {
        String temp = cnic.getText().toString();
        System.out.println(temp.length());
        if (temp.length() == 16) {
            return temp;
        } else {
            cnic.setError("Invalid CNIC");
            isDataValid = false;
            return null;
        }
    }


    String getUniversity() {
        String temp = university.getText().toString();

        return temp;
//        if (temp.equals("")) {
//            isDataValid = false;
//            university.setError("Invalid university");
//            return null;
//        } else {
//            return temp.toString();
//        }
    }


    String getCompany() {
        String temp = company.getText().toString();

        if (temp.equals("")) {
            isDataValid = false;
            company.setError("Invalid company");
            return null;
        } else {
            return temp.toString();
        }
    }


    Integer getAge() {
        String temp = age.getText().toString();

        if (!temp.equals("")) {
            if (Integer.valueOf(temp) > 99) {
                age.setError("Age should be less than 100");
                isDataValid = false;
                return null;
            }
            return Integer.valueOf(temp);
        } else {
            age.setError("Invalid age");
            isDataValid = false;
            return null;
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == 100) {

            String filename = UUID.randomUUID().toString() + ".png";
            String path = getActivity().getFilesDir().getPath() + File.separator + "imageDir";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            File dest = new File(path, filename);
            Log.e("PATH", "onActivityResult: " + dest);

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            try {
                FileOutputStream out = new FileOutputStream(dest);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                profilePicture.setImageURI(Uri.fromFile(dest));
                profilePicturePath = dest.getAbsolutePath();
                customDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == 101) {
            Uri selectedImageUri = data.getData();
            profilePicture.setImageURI(selectedImageUri);
            profilePicturePath = getPath(getActivity().getApplicationContext(), selectedImageUri);
            customDialog.dismiss();
        } else if (requestCode == 103) {

            String filename = UUID.randomUUID().toString() + ".png";
            String path = getActivity().getFilesDir().getPath() + File.separator + "imageDir";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            File dest = new File(path, filename);
            Log.e("PATH", "onActivityResult: " + dest);

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            try {
                FileOutputStream out = new FileOutputStream(dest);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                coverPicture.setImageURI(Uri.fromFile(dest));
                coverPicturePath = dest.getAbsolutePath();
                customDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == 104) {
            Uri selectedImageUri = data.getData();


            coverPicture.setImageURI(selectedImageUri);
            coverPicturePath = getPath(getActivity().getApplicationContext(), selectedImageUri);

            customDialog.dismiss();
        }
    }

    public String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    @Override
    public void onResume() {
        super.onResume();


        ((FragmentDemo) getActivity())
                .setActionBarTitle("Form Fragment");
    }

}

enum Designation {
    Student, Employee
}

enum Gender {
    Male, Female
}