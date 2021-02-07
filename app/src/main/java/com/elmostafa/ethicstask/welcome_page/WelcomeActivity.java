package com.elmostafa.ethicstask.welcome_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.elmostafa.ethicstask.R;
import com.elmostafa.ethicstask.login_page.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.elmostafa.ethicstask.utels.AppKey.EMAIL_USER;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.btLogin)
    Button btLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initVrible();
    }

    private void initVrible() {
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        if (intent != null) {
            tvEmail.setText(intent.getStringExtra(EMAIL_USER));
        }
    }

    // click view in page with ButternKnife
    @OnClick({R.id.btLogin})
    void submit(View view) {
        switch (view.getId()) {
            // logout from firebase or normal
            case R.id.btLogin:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;

        }
    }
}