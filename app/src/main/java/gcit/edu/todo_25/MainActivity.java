package gcit.edu.todo_25;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private EditText fName,surname,Marks,Id;
    private Button add,view,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DatabaseHelper(this);
        Id=findViewById(R.id.id);
        fName=findViewById(R.id.firstname);
        surname=findViewById(R.id.lastname);
        Marks=findViewById(R.id.marks);


        add=findViewById(R.id.button_add);
        view=findViewById(R.id.button_view);
        update=findViewById(R.id.button_update);
        delete=findViewById(R.id.button_delete);
    }

    public void AddData(View view) {
                boolean isInserted= myDb.insertData(Id.getText().toString(),
                fName.getText().toString(),
                surname.getText().toString(),
                Marks.getText().toString());
                if(isInserted==true){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            }


    public void viewData(View view) {
        Cursor res = myDb.getAllData();
        if(res.getCount()==0){
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer =new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Student ID : "+res.getString(0)+"\n");
            buffer.append("First Name : "+res.getString(1)+"\n");
            buffer.append("Last Name : "+res.getString(2)+"\n");
            buffer.append("ITW202 Marks : "+res.getString(3)+"\n\n");

            showMessage("List of Students",buffer.toString());
        }
    }

    private void showMessage(String title, String Message) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void update(View view) {
        boolean isUpdate=myDb.updateData(Id.getText().toString(),fName.getText().toString(),surname.getText().toString(),Marks.getText().toString());
        if(isUpdate == true){
            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
        }

    }

    public void delete(View view) {
        Integer isDeleted=myDb.deleteData(Id.getText().toString());
        if(isDeleted > 0){
            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();

        }

    }
}

