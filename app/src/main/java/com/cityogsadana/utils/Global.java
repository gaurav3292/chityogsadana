package com.cityogsadana.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.provider.AlarmClock;
import android.support.design.widget.TabLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cityogsadana.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;


public class Global {


    public static Typeface regular,italic,bold;
    public static AlertDialog dialog;
    public static String token;


    public static void setFont(ViewGroup group, Typeface font) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof EditText
                    || v instanceof Button) {
                ((TextView) v).setTypeface(font);
            } else if (v instanceof ViewGroup)
                setFont((ViewGroup) v, font);
        }
    }

    public static void setText(ViewGroup group, String text) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof EditText
                    || v instanceof Button) {
                ((TextView) v).setText(text);
            } else if (v instanceof ViewGroup)
                setText((ViewGroup) v, text);
        }
    }

    public static void setCustomFont(Typeface font, View... group) {
        int count = group.length;
        View v;
        for (int i = 0; i < count; i++) {
            v = group[i];
            if (v instanceof TextView || v instanceof EditText
                    || v instanceof Button) {
                ((TextView) v).setTypeface(font);
            } else if (v instanceof ViewGroup)
                setFont((ViewGroup) v, font);
        }
    }

    public static void initializeFont(Context context) {

        regular = Typeface.createFromAsset(context.getAssets(),
                Fonts.REGULAR);
        bold = Typeface.createFromAsset(context.getAssets(),
                Fonts.BOLD);
        italic = Typeface.createFromAsset(context.getAssets(),
                Fonts.ITALIC);



    }

    public static void showProgress(Context context) {
        try {
            dialog = new SpotsDialog(context, R.style.Custom);
            Global.overrideFont(context, "MONOSPACE", Fonts.REGULAR);
            dialog.show();
        }catch (Exception e)
        {e.printStackTrace();}
    }

    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
        try {
            final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

            final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
        } catch (Exception e) {
        }
    }


    public static void hideSoftKeyboard(Activity activity) throws Exception {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void setupUI(View view, final Activity activity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    try {
                        hideSoftKeyboard(activity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }

    }

    public static void disableClick(ViewGroup group){

        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof EditText
                    || v instanceof Button || v instanceof ImageButton || v instanceof ImageView
                    || v instanceof Spinner ||v instanceof RelativeLayout || v instanceof LinearLayout) {
                v.setClickable(false);
            } else if (v instanceof ViewGroup)
                disableClick((ViewGroup) v);
        }

    }

    public static void enableClick(ViewGroup group){

        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView || v instanceof EditText
                    || v instanceof Button || v instanceof ImageButton || v instanceof ImageView ||
                    v instanceof Spinner ||v instanceof RelativeLayout || v instanceof LinearLayout) {
                v.setClickable(true);
            } else if (v instanceof ViewGroup)
                enableClick((ViewGroup) v);
        }

    }


//    public static SpannableString typeface(Typeface typeface, CharSequence string) {
//        SpannableString s = new SpannableString(string);
//        s.setSpan(new CustomTypefaceSpan(typeface), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return s;
//    }


    public static String trimString(String s) {

        Pattern trimmer = Pattern.compile("^\\s+|\\s+$");
        Matcher m = trimmer.matcher(s);
        StringBuffer out = new StringBuffer();
        while (m.find())
            m.appendReplacement(out, "");
        m.appendTail(out);
        System.out.println(out + "!");

        return out.toString();

    }

//    public static void showProgress(Context context){
//
//        try {
//
//            LayoutInflater layoutInflater = LayoutInflater
//                    .from(context);
//            View promptsView = layoutInflater.inflate(
//                    R.layout.progress_dialog, null);
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                    context);
//            alertDialogBuilder.setView(promptsView);
//            alertDialogBuilder.setCancelable(false);
//            dialog = alertDialogBuilder.create();
//            TextView loadingText = (TextView) promptsView.findViewById(R.id.text_loading);
//            loadingText.setTypeface(Global.medium);
//
//            dialog.show();
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static SpannableString typeface(Typeface typeface, CharSequence string) {
        SpannableString s = new SpannableString(string);
        s.setSpan(new CustomTypeFace(typeface), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }


    public static void changeTabsFont(TabLayout tabLayout) {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Global.bold);
                }
            }
        }
    }

    public static Map<String, String> getAddressComponents(JSONArray addressArray) throws JSONException {
        Map<String, String> map = new HashMap<>();
        if (addressArray != null) {
            for (int i = 0; i < addressArray.length(); i++) {
                JSONObject address_components = addressArray.getJSONObject(i);
                JSONArray types_array = address_components.getJSONArray("types");
                for (int k = 0; k < types_array.length(); k++) {
                    if (types_array.get(k).toString().contains("sublocality_level_1")) {
                        map.put("area", address_components.getString("long_name"));
                    } else if (types_array.get(k).toString().contains("sublocality_level_2")) {
                        map.put("locality", address_components.getString("long_name"));
                    } else if (types_array.get(k).toString().contains("locality")) {
                        map.put("city", address_components.getString("long_name"));
                    } else if (types_array.get(k).toString().contains("administrative_area_level_1")) {
                        map.put("state", address_components.getString("long_name"));
                    } else if (types_array.get(k).toString().contains("postal_code")) {
                        map.put("postal_code", address_components.getString("long_name"));
                    }
                }


            }
            return map;
        }


        return null;
    }

    public static void animateTextView(int initialValue, int finalValue, final TextView textview) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        int start = Math.min(initialValue, finalValue);
        int end = Math.max(initialValue, finalValue);
        int difference = Math.abs(finalValue - initialValue);
        Handler handler = new Handler();
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 100) * count;
            final int finalCount = ((initialValue > finalValue) ? initialValue - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textview.setText(String.valueOf(finalCount));
                }
            }, time);
        }
    }

    public static void animateItemView(View view, int position) {
        long animationDelay = 600;

        animationDelay += position * 60;

        view.setScaleY(0);
        view.setScaleX(0);
        view.animate()
                .scaleY(1)
                .scaleX(1)
                .setDuration(200)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(null)
                .setStartDelay(animationDelay)
                .start();
    }


    public static List<String> getCountries(Context context){

        String [] countries= context.getResources().getStringArray(R.array.country);
        int soze = countries.length;


        List<String> stringList = Arrays.asList(countries);
        return stringList;
    }

    public static void setAlarm(Activity activity) {
        ArrayList<Integer> alarmDays = new ArrayList<Integer>();
        alarmDays.add(android.icu.util.Calendar.SATURDAY);
        alarmDays.add(android.icu.util.Calendar.SUNDAY);
        alarmDays.add(android.icu.util.Calendar.MONDAY);
        alarmDays.add(android.icu.util.Calendar.TUESDAY);
        alarmDays.add(android.icu.util.Calendar.WEDNESDAY);
        alarmDays.add(android.icu.util.Calendar.THURSDAY);
        alarmDays.add(android.icu.util.Calendar.FRIDAY);


        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, "Time to submit your progress");
        i.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
        i.putExtra(AlarmClock.EXTRA_HOUR, 21);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 45);
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        activity.startActivity(i);


    }

}
