package com.example.jason.mocksample;

/**
 * Created by jsson on 16/11/6.
 */

import android.os.AsyncTask;


/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
//public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//    private final String mEmail;
//    private final String mPassword;
//
//    /**
//     * A dummy authentication store containing known user names and passwords.
//     * TODO: remove after connecting to a real authentication system.
//     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo@example.com:hello", "bar@example.com:world"
//    };
//    UserLoginTask(String email, String password) {
//        mEmail = email;
//        mPassword = password;
//    }
//
//    @Override
//    protected Boolean doInBackground(Void... params) {
//        // TODO: attempt authentication against a network service.
//
//        try {
//            // Simulate network access.
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            return false;
//        }
//
//        for (String credential : DUMMY_CREDENTIALS) {
//            String[] pieces = credential.split(":");
//            if (pieces[0].equals(mEmail)) {
//                // Account exists, return true if the password matches.
//                return pieces[1].equals(mPassword);
//            }
//        }
//
//        // TODO: register the new account here.
//        return true;
//    }
//
//    @Override
//    protected void onPostExecute(final Boolean success) {
//    }
//
//    @Override
//    protected void onCancelled() {
//    }
//}
public class UserLoginTask {

    private final String email;
    private final String password;

    public UserLoginTask(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public void execute(String email, String password, NetworkCallBack networkCallBack) {
    }
}