package com.example.tamer.vucutindexapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Math.pow;


public class MainActivity extends AppCompatActivity {

    private EditText txt_boy;
    private TextView lbl_durum,lbl_idealKilo,lbl_kilo;
    private Button btn_hesapla;
    private RadioButton rb_erkek,rb_bayan;
    private Double kilo,boy;
    private SeekBar sb_kilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_boy = (EditText)findViewById(R.id.txt_boy);
        lbl_durum = (TextView)findViewById(R.id.lbl_durum);
        lbl_idealKilo = (TextView)findViewById(R.id.lbl_idealKilo);
        rb_erkek = (RadioButton)findViewById(R.id.rb_erkek);
        rb_bayan = (RadioButton)findViewById(R.id.rb_bayan);
        btn_hesapla = (Button)findViewById(R.id.btn_hesapla);
        sb_kilo = (SeekBar)findViewById(R.id.sb_kilo);
        lbl_kilo = (TextView)findViewById(R.id.lbl_kilo);

        try{
            sb_kilo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    lbl_kilo.setText(String.valueOf(i).toString()); //seekbar degistiginden degerini yanindaki textview e aktariyor.
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            btn_hesapla.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kilo = Double.valueOf(lbl_kilo.getText().toString()).doubleValue();
                    boy = (Double.valueOf(txt_boy.getText().toString()).doubleValue()/100);
                    if (rb_erkek.isChecked()){
                        //Erkek Seciliyse Burasi
                        lbl_durum.setText("Erkek: " + bmi_durum(boy,kilo)); //bmi_durum fonksiyonundan deger cekiyor.
                        lbl_idealKilo.setText(String.valueOf(bmi(boy,kilo)).toString()); //bmi fonksiyondan deger cekiyor.
                    }else{
                        //Bayan Seciliyse Burasi
                        lbl_durum.setText("Bayan : " + bmi_durum(boy,kilo));
                        lbl_idealKilo.setText(String.valueOf(bmi(boy,kilo)).toString());
                    }
                }
            });
        }catch (Exception e){
            Log.e("Hata", e.getMessage());
        }
    }

    protected double bmi(double boy, double kilo){
        return kilo / (pow(boy,2)); // pow karesini aliyor.
    }


    protected String bmi_durum(double boy, double kilo){
        double bmi = bmi(boy,kilo);
        if (bmi <= 18.5){
            return "Zayif";
        }else if(bmi > 18.5 || bmi <= 24.9){
            return "Normal Kilo";
        }else if(bmi > 25 || bmi <= 29.9){
            return "Fazla Kilolu";
        }else if(bmi > 30 || bmi <= 34.9){
            return "1. Derece Obez";
        }else if(bmi > 35 || bmi <= 39.9){
            return "2. Derece Obez";
        }else{
            return "3. Dereceden Morbit Obez";
        }
    }
}
