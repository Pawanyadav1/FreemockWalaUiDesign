package com.devrik.freemockwala.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Activity.changepasswordActivity;
import com.devrik.freemockwala.ExamCategoryScreen;
import com.devrik.freemockwala.Model.Exammodel;
import com.devrik.freemockwala.Model.bannermodel;
import com.devrik.freemockwala.R;
import com.devrik.freemockwala.SignInActivity;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.devrik.freemockwala.others.SliderAdapter;
import com.devrik.freemockwala.others.showhome_examAdapter;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Homefragment extends Fragment {

    public SliderAdapter sliderAdapter;
    private SliderView showBanner;
    ArrayList<bannermodel> bannermodels;
    RecyclerView.LayoutManager layoutManager ;
    ArrayList<Exammodel> myModelArrayList = new ArrayList<>();

    ImageView logout,img_navi;
    TextView txt_show;
    RecyclerView rv_showexam;
    ViewFlipper flipper;
    public Object view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);

        int imgarray[]={R.drawable.freemockwala,R.drawable.freemockbanner,R.drawable.online_test};
        img_navi= view. findViewById(R.id.img_navi);
        // logout = findViewById(R.id.logout);
        txt_show = view. findViewById(R.id.txt_show);
        rv_showexam =view. findViewById(R.id.rv_showexam);

        flipper=view. findViewById(R.id.flipper);



        for (int i = 0; i <imgarray.length ; i++) {
            showimage(imgarray[i]);

        };
        // show();


        txt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ExamCategoryScreen.class));
            }
        });

        img_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), img_navi);

                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getContext(), "you clicked"+ menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        int itemid=menuItem.getItemId();
                        if(itemid==R.id.nev_changepwd)
                        {

                            startActivity(new Intent(getContext(), changepasswordActivity.class));
                        }
                        else if(itemid==R.id.nev_logout)
                        {
                            try {
                                AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
                                } else {
                                    builder = new AlertDialog.Builder(getContext());
                                }
                                builder.setTitle(getResources().getString(R.string.app_name))
                                        .setMessage("Are you sure you want to logout in the app")
                                        .setPositiveButton(Html.fromHtml("<font color='#008037'>Ok</font>"), new DialogInterface.OnClickListener() {
                                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                            public void onClick(final DialogInterface dialog, int which) {
                                                ShareHelper.putkey(getContext(), APPCONSTANT.USERID,"");
                                                Intent intent = new Intent(getContext(), SignInActivity.class);
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
                                Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();

                            }
                        }
                        return true;
                    }



                });
                popup.show();
            }
        });


               /* logout.setOnClickListener(new View.OnClickListener() {
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
*/
        show_Exam();
        onBack(view);
        return view;
    }

    public void onBack(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.popup_home_fragment);
                    dialog.setCancelable(true);
                    Button btn_yes = dialog.findViewById(R.id.btn_yes);
                    Button btn_no = dialog.findViewById(R.id.btn_no);

                    btn_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getActivity().finishAffinity();

                            // startActivity(new Intent(getActivity(), SplashScreenActivity.class));
               /* SharedHelper.putKey(getApplicationContext(), Appconstant.USERID, "");
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                finish();*/
                        }
                    });

                    btn_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });


                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                }     return true;
            }
        });

    }

    public void showimage(int img)
    {
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
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
                                Exammodel myModel = new Exammodel();
                                myModel.setExamLogo(jsonObject.getString("examLogo"));
                                myModel.setPath(jsonObject.getString("path"));
                                myModel.setExamName(jsonObject.getString("examName"));
                                myModel.setId(jsonObject.getString("id"));
                                myModelArrayList.add(myModel);

                                Log.e("dfzfscc", jsonObject.getString("examLogo"));

                            }
                            rv_showexam.setHasFixedSize(true);
                            layoutManager = new GridLayoutManager(getContext(),2);
                            rv_showexam.setLayoutManager(layoutManager);
                            showhome_examAdapter adapter = new showhome_examAdapter(getContext(), myModelArrayList);
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
    }
}
