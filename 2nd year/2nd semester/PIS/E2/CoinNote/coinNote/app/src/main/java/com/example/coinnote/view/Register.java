package com.example.coinnote.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.coinnote.R;
import com.example.coinnote.model.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button login, signup;
    EditText txtUsername, txtEmail, txtPassword;
    Usuario usuario;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    // Inicio sesión Google
    private ImageButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUsername = (EditText) findViewById(R.id.Username);
        txtEmail = (EditText) findViewById(R.id.Email);
        txtPassword = (EditText) findViewById(R.id.Password);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        login = (Button) findViewById(R.id.signin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        signup = (Button) findViewById(R.id.register_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String username = txtUsername.getText().toString().trim();
                usuario = new Usuario(username, email, password);

                if (TextUtils.isEmpty(email)) {
                    txtEmail.setError("Es necesario introducir un email.");
                }

                // Mirar si email tiene formato de email
                Pattern patronEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$");
                Matcher mEmail = patronEmail.matcher(email.toLowerCase());
                if (mEmail.find() == false) {
                    txtEmail.setError("Email no válido.");
                }

                if (TextUtils.isEmpty(password)) {
                    txtPassword.setError("Es necesario introducir un password.");
                }

                // Comprovación contraseña tiene mayúscula, minñuscula y número
                char clave;
                byte  contNumero = 0, contLetraMay = 0, contLetraMin=0;
                for (byte i = 0; i < password.length(); i++) {
                    clave = password.charAt(i);
                    String passValue = String.valueOf(clave);
                    if (passValue.matches("[A-Z]")) {
                        contLetraMay++;
                    } else if (passValue.matches("[a-z]")) {
                        contLetraMin++;
                    } else if (passValue.matches("[0-9]")) {
                        contNumero++;
                    }
                }

                if (password.length() < 8) {
                    txtPassword.setError("Mínimo 8 caracteres.");
                }

                if (contLetraMay < 1 || contLetraMin < 1 || contNumero < 1) {
                    txtPassword.setError("Debe contener una mayúscula, minúscula y número.");
                }
                else {

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("Usuario").document(userID);
                                Map<String, Object> usuario = new HashMap<>();
                                usuario.put("Username", username);
                                usuario.put("Email", email);
                                usuario.put("Password", password);
                                documentReference.set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user profile is created for " + userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure "+ e.toString());
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(), PaginaPrin.class));
                            } else {
                                Toast.makeText(Register.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        // Google
        signInButton = findViewById(R.id.google_boton);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

        }

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode != RESULT_CANCELED) {
                if (requestCode == RC_SIGN_IN) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    handleSignInResult(task);
                }
        }
        }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(getApplicationContext(), "Registro exitoso!", Toast.LENGTH_SHORT).show();
            firebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(getApplicationContext(), "Error en registro", Toast.LENGTH_SHORT).show();
            //firebaseGoogleAuth(null);
        }
    }

    private void firebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Éxito!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Toast.makeText(getApplicationContext(), "Fallo!", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String usernameG = account.getDisplayName();
            String emailG = account.getEmail();
            Toast.makeText(getApplicationContext(), emailG, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), PaginaPrin.class));

        }
    }
}