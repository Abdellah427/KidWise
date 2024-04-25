import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonRegister;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialisez FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Initialisez les vues
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Configurez le clic sur le bouton d'inscription
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérez l'e-mail et le mot de passe saisis par l'utilisateur
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Vérifiez si l'e-mail et le mot de passe sont vides
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Veuillez saisir votre e-mail et votre mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Appelez la méthode d'inscription avec l'e-mail et le mot de passe
                registerUser(email, password);
            }
        });
    }

    // Méthode pour inscrire l'utilisateur avec l'e-mail et le mot de passe
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inscription réussie, affichez un message à l'utilisateur
                            Toast.makeText(RegisterActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                            // Vous pouvez rediriger l'utilisateur vers une autre activité après l'inscription réussie
                        } else {
                            // L'inscription a échoué, affichez un message d'erreur à l'utilisateur
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this, "Erreur lors de l'inscription : " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
