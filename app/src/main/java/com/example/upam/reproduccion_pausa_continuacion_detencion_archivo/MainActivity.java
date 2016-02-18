package com.example.upam.reproduccion_pausa_continuacion_detencion_archivo;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    MediaPlayer mp;
    Button b4;
    int posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b4=(Button)findViewById(R.id.button4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void destruir() {
        if (mp != null)
            mp.release();
    }



    public void iniciar(View v) {
        destruir();
        mp = MediaPlayer.create(this, R.raw.motor);
        mp.start();
        String op = b4.getText().toString();
        if (op.equals("no reproducir en forma circular"))
            mp.setLooping(false);
        else
            mp.setLooping(true);


    }
    public void pausar(View v) {
        if (mp != null && mp.isPlaying()) {
            posicion = mp.getCurrentPosition();
            mp.pause();
        }
    }

    public void continuar(View v) {
        if (mp != null && mp.isPlaying() == false) {
            mp.seekTo(posicion);
            mp.start();
        }
    }

    public void detener(View v) {
        if (mp != null) {
            mp.stop();
            posicion = 0;
        }
    }

    public void noreproducir(View v) {
        detener(null);
        String op = b4.getText().toString();
        if (op.equals("no reproducir en forma circular"))
            b4.setText("reproducir en forma circular");
        else
            b4.setText("no reproducir en forma circular");
    }



}
