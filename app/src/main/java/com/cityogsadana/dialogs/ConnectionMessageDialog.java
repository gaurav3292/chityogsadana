package com.cityogsadana.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.activity.LevelActivity_;
import com.cityogsadana.activity.MainActivity_;
import com.cityogsadana.activity.ResultActivity;
import com.cityogsadana.activity.SelfTestActivity_;
import com.cityogsadana.interfaces.ActivitySet;
import com.cityogsadana.utils.Global;

/**
 * Created by pc15 on 10/23/2017.
 */

public class ConnectionMessageDialog extends DialogFragment {

    private ViewGroup viewGroup;
    private LinearLayout okButton;
    private LinearLayout cancelButton, cancelLayout;
    private TextView headingTxt, messageTxt, buttonTxt;
    private Dialog dialog;

    public ConnectionMessageDialog() {
        // Required empty public constructor
    }


    public void successShow(Activity activity, String title, String message, String button, Boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            dialog.setCancelable(true);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void successBack(final Activity activity, String title, String message, String button, Boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            dialog.setCancelable(true);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    activity.finish();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void successShowHome(final Activity activity, String title, String message, String button, Boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            dialog.setCancelable(false);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                  /*  Intent intent = new Intent(activity, MainActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);*/
                    activity.finish();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                  /*  Intent intent = new Intent(activity, MainActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);*/
                    activity.finish();
                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void successShowMain(final Activity activity, String title, String message, String button, Boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            dialog.setCancelable(false);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(activity, MainActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(activity, MainActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void successShowLevelMenu(final Activity activity, String title, String message, String button, Boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            dialog.setCancelable(false);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(activity, LevelActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(activity, LevelActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void successShowActivityDismiss(final Activity activity, String title, String message, String button, Boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            dialog.setCancelable(false);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    activity.finish();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    activity.finish();
                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showSelfTest(final Activity activity, String title, String message, String button, Boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            cancelLayout = (LinearLayout) dialog.findViewById(R.id.cancel);
            cancelLayout.setVisibility(View.VISIBLE);
            dialog.setCancelable(false);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(activity, SelfTestActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);

                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            cancelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showResult(final ResultActivity activity, String title, String message, String button, boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            cancelLayout = (LinearLayout) dialog.findViewById(R.id.cancel);
            cancelLayout.setVisibility(View.VISIBLE);
            dialog.setCancelable(false);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText(button);
            if (!b) {
                cancelButton.setVisibility(View.GONE);
            }


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(activity, LevelActivity_.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                    activity.finish();

                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            cancelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showStartRoutine(final Activity activity, final ActivitySet activitySet, String title, String message, boolean b) {

        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.FILL_PARENT;
            viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_alert);
            Global.setFont(viewGroup, Global.regular);

            okButton = (LinearLayout) dialog.findViewById(R.id.ok_button);
            cancelButton = (LinearLayout) dialog.findViewById(R.id.cancel_button);
            headingTxt = (TextView) dialog.findViewById(R.id.heading);
            messageTxt = (TextView) dialog.findViewById(R.id.message);
            buttonTxt = (TextView) dialog.findViewById(R.id.button_text);
            cancelLayout = (LinearLayout) dialog.findViewById(R.id.cancel);
            cancelLayout.setVisibility(View.VISIBLE);
            dialog.setCancelable(false);

            headingTxt.setText(title);
            messageTxt.setText(message);
            buttonTxt.setText("Start");


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    activitySet.startRoutineTest();

                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            cancelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });
            // Showing Alert Message
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
