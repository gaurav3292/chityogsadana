package com.cityogsadana.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;

/**
 * Created by aa on 2/10/2018.
 */

public class PaypalHelper {


    private static PaypalHelper payPalHelper;

    public static final String TAG = "paymentResponse";

    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_PRODUCTION;


    // note that these credentials will differ between live & sandbox environments.
    // PAYPAL LIVE CLIENT ID
    // private static final String CONFIG_CLIENT_ID = "AaIhdTxZxeizNzogJ0lQ-Bhs5EQRAF7Yw1AFacEAeGW_CdEzDyRUHO6O6-6G-SZHtcQn6VY_ZPGfhWUv";
    // private static final String CONFIG_CLIENT_ID = "AWM9MALNFWtEYEKEW45okiQwC7iIp577cv-9FHV5H9x_ykh5-5jptCtUWAPppTjG4eztPeF3KEtCHepN";
    private static final String CONFIG_CLIENT_ID = "AaXqm7QWn0c7Dr71NRRt20KWRPFmTlKzXqLEbnidvIdBNTyvMGkAyRwO0-hskHdsd88Xz7u2dOFq1Yvw";   // live

    public static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("http://www.chityogsadhana.com/privacy-policy/"))
            .merchantUserAgreementUri(Uri.parse("http://www.chityogsadhana.com/privacy-policy/"));

    public PaypalHelper() {

    }

    public static PaypalHelper getInstance(){
        if(payPalHelper==null){
            synchronized (PaypalHelper.class){
                payPalHelper = new PaypalHelper();
            }
        }

        return payPalHelper;
    }

    public static void startPayPalService(Context context, Activity activity,String amount){

        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE,amount);

        /*
         * See getStuffToBuy(..) for examples of some available payment options.
         */

        Intent intent = new Intent(context, PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        activity.startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    public static PayPalPayment getThingToBuy(String paymentIntent,String amount) {
        return new PayPalPayment(new BigDecimal(amount), "USD", "Amount:",
                paymentIntent);
    }

    public static void stopPayPalService(Context context,Activity activity){
        activity.stopService(new Intent(context, PayPalService.class));
    }
}
