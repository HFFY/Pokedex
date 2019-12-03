package bo.upb.programacion3.codelabpokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bo.upb.programacion3.codelabpokedex.model.Pokemon;
import bo.upb.programacion3.codelabpokedex.utils.PokemonUtils;

import static bo.upb.programacion3.codelabpokedex.PokemonListActivity.pokemonFavList;

public class MainActivity extends AppCompatActivity {

    // Elementos de la pantalla
    private Toolbar toolbar; // Usaremos un toolbar personalizado, para agregar el icono del Drawer a la izquierda
    private ActionBarDrawerToggle drawerToggle; // El objeto del botón del drawer
    private DrawerLayout drawerLayout; // Nuestro DrawerLayout
    ImageView imageViewUserIcon;
    TextView textView;
    String user;
    List<Pokemon> auxList = PokemonUtils.getPokemons();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            user = arguments.getString("User");
        }

        imageViewUserIcon = findViewById(R.id.image_user_image);
        textView = findViewById(R.id.text_user_name);

        if (this.toolbar != null) {
            // Aqui configuramos nuestro Toolbar, con el ícono del Drawer a la izquierda
            this.toolbar.setTitle(R.string.main_activity_title);
            this.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerToggle.setHomeAsUpIndicator(R.drawable.icon_hamburger);
            drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(GravityCompat.START); // Podemos abrir y cerrar nuestro drawer programáticamente!!
                }
            });
        }

        switch (user) {
            case "Ash":
                imageViewUserIcon.setImageResource(R.drawable.ash_ketchum);
                for(int i = 0; i < 5 ; i++){
                    pokemonFavList.add(auxList.get(i));
                }
                break;
            case "Misty":
                imageViewUserIcon.setImageResource(R.drawable.misty);
                for(int i = 5; i < 10 ; i++){
                    pokemonFavList.add(auxList.get(i));
                }
                break;
            case "Brook":
                imageViewUserIcon.setImageResource(R.drawable.brook);
                for(int i = 10; i < 15 ; i++){
                    pokemonFavList.add(auxList.get(i));
                }
                break;
            default:
                break;
        }
        textView.setText(user);
    }

    private void initViews() {
        this.toolbar = findViewById(R.id.toolbar);
        this.drawerLayout = findViewById(R.id.navigationDrawer);
    }

    public void goToMyPokemons(View view) {
        Intent intent = new Intent(this, PokemonRecyclerActivity.class);
        startActivity(intent);
    }

    public void goToMyFavoritePokemons(View view) {
        Intent intent = new Intent(this, PokemonListActivity.class);
        startActivity(intent);
    }

}
