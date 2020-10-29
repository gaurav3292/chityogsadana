package com.cityogsadana.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import androidx.fragment.app.DialogFragment;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import com.cityogsadana.R;
import com.cityogsadana.handler.DateTimePickerHandler;

import java.util.ArrayList;
import java.util.Calendar;


public class DialogDateTmePicker extends DialogFragment {


    private TextView textView;
    private String title;
    private String pickerValue;
    private String format;
    private DatePickerDialog dpd;
    public static DateTimePickerHandler dtHandler;


    public static DialogDateTmePicker getInstance(String picker, String title, DateTimePickerHandler dtHandler1) {
        DialogDateTmePicker dialog = new DialogDateTmePicker();
        dtHandler = dtHandler1;
        Bundle b = new Bundle();
        b.putString("picker", picker);
        b.putString("text_view", title);
        dialog.setArguments(b);
        return dialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        pickerValue = getArguments().getString("picker");
        title = getArguments().getString("text_view");

        final java.util.Calendar c = java.util.Calendar.getInstance();


        if (pickerValue.equalsIgnoreCase("date_pick")) {
            dpd = new DatePickerDialog(new ContextThemeWrapper(getActivity(), R.style.DateTimePicker),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
// Show selected date
                            String monthValue = "", dayValue = "";
//                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                            String strDate = format.format(c.getTime());

                            if (month + 1 < 10) {
                                monthValue = "0" + (month + 1);
                            } else {

                                monthValue = String.valueOf(month);
                            }

                            if (dayOfMonth < 10) {
                                dayValue = "0" + dayOfMonth;
                            } else {
                                dayValue = String.valueOf(dayOfMonth);
                            }

                            textView.setText(new StringBuilder().append(year)
                                    .append("-").append(monthValue)
                                    .append("-").append(dayValue)
                                    .append(" "));


                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));


            dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return dpd;
        } else {
            TimePickerDialog tpd = new TimePickerDialog(new ContextThemeWrapper(getActivity(), R.style.timePicker),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                    setAlarm(title,hourOfDay,minute);
                        }
                    }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true);
            return tpd;
        }


    }
    private void setAlarm(String title, int hourOfDay, int minute) {
        ArrayList<Integer> alarmDays= new ArrayList<Integer>();
        alarmDays.add(android.icu.util.Calendar.SATURDAY);
        alarmDays.add(android.icu.util.Calendar.SUNDAY);
        alarmDays.add(android.icu.util.Calendar.MONDAY);
        alarmDays.add(android.icu.util.Calendar.TUESDAY);
        alarmDays.add(android.icu.util.Calendar.WEDNESDAY);
        alarmDays.add(android.icu.util.Calendar.THURSDAY);
        alarmDays.add(android.icu.util.Calendar.FRIDAY);


        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, title);
        i.putExtra(AlarmClock.EXTRA_DAYS,alarmDays);
        i.putExtra(AlarmClock.EXTRA_HOUR, hourOfDay);
        i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
        i.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        startActivity(i);


    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        dtHandler.setActivity();

    }
}