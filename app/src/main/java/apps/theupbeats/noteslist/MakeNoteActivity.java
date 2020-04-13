package apps.theupbeats.noteslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MakeNoteActivity extends AppCompatActivity {
Button button;
EditText ti,co;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_note);



        button=findViewById(R.id.button);
        ti=findViewById(R.id.editText2);
        co=findViewById(R.id.editText3);


      if( getIntent().getBooleanExtra("show",false)) {

          TextView tt=findViewById(R.id.textView);
          tt.setText("Showing Note");
          String temp[] = getIntent().getStringExtra("data").split(""+(char)99999);
          ti.setText(temp[0]);
          co.setText(temp[1]);
          button.setVisibility(View.INVISIBLE);
          ti.setEnabled(false);
          co.setEnabled(false);
      }
      else {
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String t = ti.getText().toString();
                  String c = co.getText().toString();
                  boolean b = new DBHelper(getApplicationContext()).insertData(t, c);
                  if (b)
                      makeResult(t);
                  else
                      Toast.makeText(MakeNoteActivity.this, "Error", Toast.LENGTH_SHORT).show();
              }
          });

      }
    }
    void makeResult(String res)//call this to redirect the result to MAintActivity
    {
        Intent intent=new Intent();
        intent.putExtra("result",res);
        setResult(2,intent);
        finish();//finishing activity
         }
}
