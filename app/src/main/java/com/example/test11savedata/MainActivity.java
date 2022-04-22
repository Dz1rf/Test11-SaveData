package com.example.test11savedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText entText;
    Button but1, but2;
    SharedPreferences sPref;                        //переменная, обозначающая поле, для хранения данных
    String savedText = "Saved Text";                //константа, содержащее имя переменной, по которой будут сохранятья данные в поле sPref

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entText = findViewById(R.id.EntText);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);

        but1.setOnClickListener(this);              //присваивамем обработчик нажатий кнопке
        but2.setOnClickListener(this);              //присваивамем обработчик нажатий кнопке

        loadText();                                 //загрузка данных при запуске программы
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.but1:                         //при нажатии на кнопку 1, запускается метод saveText
                saveText();
                break;
            case R.id.but2:                         //при нажатии на кнопку 2, запускается метод loadText
                loadText();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();                                 //сохранение данных, при закрытии приложения
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);                   //создаётся объект sPref (поле, для записи данных), с константой MODE_PRIVATE означающей,
                                                                // что данные будут доступны только этому приложению
        SharedPreferences.Editor ed = sPref.edit();             //объект ed позволяет изменять данные в поле sPref
        ed.putString(savedText, entText.getText().toString());  //записываем в поле данные (entText.getText().toString()) под именем savedText
        ed.commit();                                            //завершение сохранения данных в поле
        Toast.makeText(MainActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String loadText = sPref.getString(savedText, "");
        entText.setText(loadText);
        Toast.makeText(MainActivity.this, "Data Loaded!", Toast.LENGTH_SHORT).show();
    }

}