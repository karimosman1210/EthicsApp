package com.elmostafa.ethicstask.login_page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.elmostafa.ethicstask.R;
import com.elmostafa.ethicstask.Validator;
import com.elmostafa.ethicstask.base_classes.BasePresenter;
import com.elmostafa.ethicstask.utels.StaticMethods;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static com.elmostafa.ethicstask.utels.AppKey.RC_SIGN_IN;

public class LoginPresenter extends BasePresenter<LoginView> {
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private Activity context;
    // use constructor to init firebase auth to use get Credential
    public LoginPresenter(Activity context) {
        this.context=context;
    }
    // Validation with data get from view
    void loginData(String email, String password) {
        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(context);

            return;
        } else if (Validator.isTextEmpty(email)) {
            view.showErrorMessageBase(context, context.getString(R.string.empty_email));
            return;
        } else if (!Validator.isValidMail(email)) {
            view.showErrorMessageBase(context, context.getString(R.string.invaildphone));
            return;
        } else if (Validator.isTextEmpty(password)) {
            view.showErrorMessageBase(context, context.getString(R.string.emptyPassword));

            return;
        } else if (!Validator.isValid(password)) {
            view.showErrorMessageBase(context, context.getString(R.string.invaildPasswordLenght));
            return;
        }
        // when validation correct do this interface action in view
        view.openNewActivity(email);
    }
    // action from google auth
    void loginGoogle() {
        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(context);
            return;
        }
        signIn();
    }
    // init google auth
    private void signIn() {
       mAuth=FirebaseAuth.getInstance();
        initGoogleAuth();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        context.startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void initGoogleAuth() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(context, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        view.openNewActivity(user.getEmail());
                    } else {
                        // If sign in fails, display a message to the user.
                    }

                });
    }
}
