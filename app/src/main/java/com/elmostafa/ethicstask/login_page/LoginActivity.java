package com.elmostafa.ethicstask.login_page;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.elmostafa.ethicstask.R;
import com.elmostafa.ethicstask.base_classes.BaseActivity;
import com.elmostafa.ethicstask.utels.ToastUtil;
import com.elmostafa.ethicstask.welcome_page.WelcomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.elmostafa.ethicstask.utels.AppKey.EMAIL_USER;
import static com.elmostafa.ethicstask.utels.AppKey.RC_SIGN_IN;

public class LoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btGmail)
    Button btGmail;
    @BindView(R.id.coorId)
    CoordinatorLayout coorId;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initVaribles();
    }

    private void initVaribles() {
        // init ButternKnife and presenter of page
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        presenter.attachView(this);
    }

    // click view in page with ButternKnife
    @OnClick({R.id.btLogin, R.id.btGmail})
    void submit(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                presenter.loginData(etEmail.getText().toString().trim(), etPassword.getText().toString().trim());
                break;
            case R.id.btGmail:
                presenter.loginGoogle( );
                break;
        }
    }
    // when result success in presenter
    @Override
    public void openNewActivity(String email) {
        Intent intent=new Intent(this, WelcomeActivity.class);
        intent.putExtra(EMAIL_USER,email);
        startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                ToastUtil.showErrorToast(this, e.getMessage());
            }
        }
    }
}