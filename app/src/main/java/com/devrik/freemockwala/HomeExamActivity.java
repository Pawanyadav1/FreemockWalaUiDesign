package com.devrik.freemockwala;

import androidx.appcompat.app.AppCompatActivity;

public class HomeExamActivity extends AppCompatActivity {
    /*public Context context = HomeExamActivity.this;

    public SliderAdapter sliderAdapter;
    private SliderView showBanner;
    ArrayList<bannermodel> bannermodels;
    RecyclerView.LayoutManager layoutManager ;
    ArrayList<Exammodel> myModelArrayList = new ArrayList<>();

    ImageView  logout,img_navi;
    TextView txt_show;
    RecyclerView rv_showexam;
    ViewFlipper flipper;
    public Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_exam);

        int imgarray[]={R.drawable.freemockwala,R.drawable.freemockbanner,R.drawable.online_test};
        img_navi= findViewById(R.id.img_navi);
       // logout = findViewById(R.id.logout);
        txt_show = findViewById(R.id.txt_show);
        rv_showexam = findViewById(R.id.rv_showexam);



        BottomNavigationView bottomNavigationView= findViewById(R.id.nav_bottom);
        getSupportFragmentManager().beginTransaction().replace(R.id.fargment_cantener,new Homefragment()).commit();

       *//* showBanner = findViewById(R.id.showBanner);
        showBanner.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        showBanner.setIndicatorAnimation(IndicatorAnimationType.DROP);
        //sliderView.startAutoCycle();*//*
        flipper=findViewById(R.id.flipper);

        for (int i = 0; i <imgarray.length ; i++) {
            showimage(imgarray[i]);

        };
       // show();
        show_Exam();

        txt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeExamActivity.this, ExamCategoryScreen.class));
                finish();
            }
        });

        img_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(HomeExamActivity.this, img_navi);

                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(HomeExamActivity.this, "you clicked"+ menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        int itemid=menuItem.getItemId();
                        if(itemid==R.id.nev_changepwd)
                        {
                            startActivity(new Intent(HomeExamActivity.this,changepasswordActivity.class));
                        }
                        else if(itemid==R.id.nev_logout)
                        {
                            try {
                                    AlertDialog.Builder builder;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        builder = new AlertDialog.Builder(HomeExamActivity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                                    } else {
                                        builder = new AlertDialog.Builder(HomeExamActivity.this);
                                    }
                                    builder.setTitle(getResources().getString(R.string.app_name))
                                            .setMessage("Are you sure you want to logout in the app")
                                            .setPositiveButton(Html.fromHtml("<font color='#008037'>Ok</font>"), new DialogInterface.OnClickListener() {
                                                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                                public void onClick(final DialogInterface dialog, int which) {

                                                    Intent intent = new Intent(HomeExamActivity.this, SignInActivity.class);
                                                    if (Build.VERSION.SDK_INT >= 11) {
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    } else {
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    }
                                                    startActivity(intent);
                                                }
                                            })
                                            .setNegativeButton(Html.fromHtml("<font color='#08bd80'>Cancel</font>"), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .setIcon(R.drawable.ic_baseline_power_settings_new_24)
                                            .show();


                            } catch (Exception e) {
                                Toast.makeText(HomeExamActivity.this, "Error occured", Toast.LENGTH_SHORT).show();

                            }
                        }
                        return true;
                    }



                });
                popup.show();
            }
        });


               *//* logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(HomeExamActivity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(HomeExamActivity.this);
                        }
                        builder.setTitle(getResources().getString(R.string.app_name))
                                .setMessage("Are you sure you want to logout in the app")
                                .setPositiveButton(Html.fromHtml("<font color='#008037'>Ok</font>"), new DialogInterface.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                    public void onClick(final DialogInterface dialog, int which) {

                                        Intent intent = new Intent(HomeExamActivity.this, SignInActivity.class);
                                        if (Build.VERSION.SDK_INT >= 11) {
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        } else {
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        }
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton(Html.fromHtml("<font color='#08bd80'>Cancel</font>"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.ic_baseline_power_settings_new_24)
                                .show();
                    }
                });
*//*
            }



            private BottomNavigationView .OnNavigationItemReselectedListener navigationItemSelectedListner= new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {

                        case R.id.navigation_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fargment_cantener, new Homefragment()).commit();
                            break;

                        case R.id.navigation_profile:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fargment_cantener, new Profilefragment()).commit();
                            break;

                        case R.id.navigation_result:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fargment_cantener, new Resultfragment()).commit();
                            break;
                        case R.id.navigation_test:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fargment_cantener, new TypingTestfragment()).commit();
                            break;

                    }

                    return ;

                }
            };



    public void showimage(int img)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void show() {
        AndroidNetworking.post(API.showBanner)
                .setTag("showBanner")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("vbljldb", response.toString());
                        try {
                            if (response.getString("message").equals("successfull")) {

                                JSONArray jsonArray = new JSONArray(response.getString("data"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(i);

                                    bannermodel bannerModel = new bannermodel();
                                    bannerModel.setId(jsonObject.getString("id"));
                                    bannerModel.setImage(jsonObject.getString("image"));
                                    bannerModel.setPath(jsonObject.getString("path"));
                                    bannerModel.add(bannerModel);
                                    sliderAdapter = new SliderAdapter(getApplication(), bannermodels);
                                    showBanner.setSliderAdapter(sliderAdapter);

                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("cjhs", e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("dhkbh", anError.getMessage());
                    }
                });
    }


    public void show_Exam() {
        AndroidNetworking.post(API.showExam)
                .setTag("showexam")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("safdadfa", response.toString());
                        try {
                            JSONArray jsonArray = new JSONArray(response.getString("data"));
                            for (int i = 0; i < 6; i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.e("dfzfscc", response.toString());
                                Exammodel myModel = new Exammodel();
                                myModel.setExamLogo(jsonObject.getString("examLogo"));
                                myModel.setPath(jsonObject.getString("path"));
                                myModel.setExamName(jsonObject.getString("examName"));
                                myModel.setId(jsonObject.getString("id"));
                                myModelArrayList.add(myModel);
                            }
                            rv_showexam.setHasFixedSize(true);
                            layoutManager = new GridLayoutManager(HomeExamActivity.this,2);
                            rv_showexam.setLayoutManager(layoutManager);
                            showhome_examAdapter adapter = new showhome_examAdapter(context, myModelArrayList);
                            rv_showexam.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("dgfffdf", e.getMessage());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("fhsdds", anError.getMessage());

                    }
                });


    }*/
}
