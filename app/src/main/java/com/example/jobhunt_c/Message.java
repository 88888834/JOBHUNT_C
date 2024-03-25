package com.example.jobhunt;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Message extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        String ville = getIntent().getStringExtra("villementionee");
        afficherNombreAnnonces(ville);
    }


    private void afficherNombreAnnonces(String ville) {
        // Vérifier si la ville est non nulle
        if (ville != null) {

            DatabaseHelper db = new DatabaseHelper(this);
            int nombreAnnonces = db.compterAnnoncesPourVille(ville);
            TextView textViewAnnounceCount = findViewById(R.id.textView_announce_count);
            textViewAnnounceCount.setText("Nombre d'annonces pour " + ville + ": " + nombreAnnonces);
        } else {

            TextView textViewAnnounceCount = findViewById(R.id.textView_announce_count);
            textViewAnnounceCount.setText("Erreur: Aucune ville spécifiée");
        }
    }
}