package bo.upb.programacion3.codelabpokedex;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    EditText editTextUser, editTextPass;
    Button buttonSignin;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editTextUser = findViewById(R.id.edit_user_log_in);
        editTextPass = findViewById(R.id.password_log_in);
        buttonSignin = findViewById(R.id.button_sign_in);

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUser.getText().toString();
                String pssw = editTextPass.getText().toString();
                if (!user.equals(pssw)) {
                    Toast.makeText(activity, "La contraseña o el usuario son incorrectos",
                            Toast.LENGTH_LONG).show();
                } else {
                    switch (user) {
                        case "Ash":
                        case "Misty":
                        case "Brook":
                            logIn(user);
                            break;
                        default:
                            Toast.makeText(activity, "La contraseña o el usuario son incorrectos",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        });

    }

    public void logIn(String user){

        Intent intent = new Intent(activity , MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("User",user);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
