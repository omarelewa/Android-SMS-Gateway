package com.example.sms_gateway;

import java.util.ArrayList;
import android.telephony.SmsManager;
import android.util.Log;


public class SendSMS {
    static String TAG = "SendSMS";
    SmsManager mSMSManager = null;

    /* The list of message parts our message
     * gets broken up into by SmsManager */
    ArrayList<String> mFragmentList = null;

    /* Service Center - not used */
    String mServiceCentreAddr = null;

    SendSMS() {
        mSMSManager = SmsManager.getDefault();
    }

    /* Called from the GUI to send one message to one destination */
    public boolean sendSMSMessage( String aDestinationAddress,
                                   String aMessageText) {
        if (mSMSManager == null) {
            return (false);
        }

        mFragmentList = mSMSManager.divideMessage(aMessageText);
        int fragmentCount = mFragmentList.size();

        if (fragmentCount > 1) {
            Log.d(TAG, "Sending " + fragmentCount + " parts");
            mSMSManager.sendMultipartTextMessage(aDestinationAddress,
                    mServiceCentreAddr, mFragmentList, null, null);
        }
        else{
            Log.d(TAG, "Sending one part"); mSMSManager.sendTextMessage(aDestinationAddress,
                    mServiceCentreAddr, aMessageText, null, null);
        }
        return true;
    }
}
