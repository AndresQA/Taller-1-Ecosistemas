package com.example.talleres.ecosistemas.controltaller;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import Comunicacion.ComunicacionTCP;
import Comunicacion.Observador;

public class MainActivity extends AppCompatActivity implements OnTouchListener, Observador {

    private ImageButton btn_accion;
    private ImageButton btn_up;
    private ImageButton btn_down;
    private ImageButton btn_left;
    private ImageButton btn_right;

    private LinearLayout ly_registro;

    private EditText et_ip;
    private Button btn_entrar;

    private ComunicacionTCP comunicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btn_accion = findViewById(R.id.btn_accion);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_up = findViewById(R.id.btn_up);
        btn_down = findViewById(R.id.btn_down);

        btn_accion.setOnTouchListener(this);
        btn_left.setOnTouchListener(this);
        btn_right.setOnTouchListener(this);
        btn_up.setOnTouchListener(this);
        btn_down.setOnTouchListener(this);

        ly_registro = findViewById(R.id.ly_registro);
        et_ip = findViewById(R.id.et_ip);
        btn_entrar = findViewById(R.id.btn_conectar);

        this.btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = et_ip.getText().toString().trim();
                Toast.makeText(MainActivity.this, "Comunicacion creada: " + ip, Toast.LENGTH_SHORT).show();
                comunicacion = new ComunicacionTCP(ip, 5000);

                ly_registro.setVisibility(LinearLayout.GONE);
            }
        });


       // this.comunicacion = new ComunicacionTCP()

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            keyPressed(v);
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            keyReleased(v);
        }
        return true;
    }

    public void keyPressed(View v){
        switch (v.getId()){
            case R.id.btn_accion:
               // Toast.makeText(this, "Press: accion", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("pressaccion");
                break;
            case R.id.btn_up:
              //  Toast.makeText(this, "Press: up", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("pressup");
                break;

            case R.id.btn_down:
              //  Toast.makeText(this, "Press: down", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("pressdown");
                break;

            case R.id.btn_left:
                // Toast.makeText(this, "Press: left", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("pressleft");
                break;

            case R.id.btn_right:
                Toast.makeText(this, "Press: right", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("pressright");
                break;
        }

    }

    public void keyReleased(View v){
        switch (v.getId()){
            case R.id.btn_accion:
                // Toast.makeText(this, "Released: accion", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("releasedaccion");
                break;
            case R.id.btn_up:
                // Toast.makeText(this, "Released: up", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("releasedup");
                break;

            case R.id.btn_down:
                //Toast.makeText(this, "Released: down", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("releasedown");
                break;

            case R.id.btn_left:
                //Toast.makeText(this, "Released: left", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("releasedleft");
                break;

            case R.id.btn_right:
                //Toast.makeText(this, "Released: right", Toast.LENGTH_SHORT).show();
                comunicacion.enviar("releasedright");
                break;
        }

    }


    @Override
    public void mensajeRecibido(String datos) {

    }
}


