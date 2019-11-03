package com.example.student.dialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_id, et_title, et_author;
    Button bt_save, bt_select, bt_exit, bt_update, bt_delete;
    GridView gv_display;
    DBHelper dbHelper;
    private ArrayAdapter<String> adapter;
    //    Dialog dialog = new Dialog(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingView();
        //  eventClick();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_book:
                showdialog();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showdialog(){
        Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_Light_NoTitleBar);
        dialog.setTitle("Thong Tin Sach");
        dialog.setContentView(R.layout.dialog_main);
        et_id = (EditText) dialog.findViewById(R.id.editTextID);
        et_title = (EditText) dialog.findViewById(R.id.editTextTitle);
        et_author = (EditText)dialog.findViewById(R.id.editTextName);

        //GridView
        gv_display = (GridView) dialog.findViewById(R.id.gridView_listItem);

        //DBHelper
        dbHelper = new DBHelper(this);

        //Button
        bt_save = (Button) dialog.findViewById(R.id.buttonSave);
        bt_select = (Button) dialog.findViewById(R.id.buttonSelect);
        bt_exit = (Button) dialog.findViewById(R.id.buttonExit);
        bt_delete = (Button) dialog.findViewById(R.id.buttonDelete);
        bt_update = (Button) dialog.findViewById(R.id.buttonUpdate);
        eventClick();
        dialog.show();
    }
    private void eventClick() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setId(Integer.parseInt(et_id.getText().toString()));
                book.setTitle(et_title.getText().toString());
                book.setAuthor(et_author.getText().toString());
                if (dbHelper.insertBook(book)) {
                    Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
                    clear();
                } else {
                    Toast.makeText(getApplicationContext(), "Luu khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> booklist = new ArrayList<>();
                String id = et_id.getText().toString();
                if (!id.isEmpty()) {
                    int idkq = Integer.parseInt(id);
                    Book book = dbHelper.getBook(idkq);
                    list.add(book.getId() + "");
                    list.add(book.getTitle());
                    list.add(book.getAuthor());
                } else {
                    booklist = dbHelper.getAllBook();
                    for (Book b : booklist) {
                        list.add(b.getId() + "");
                        list.add(b.getTitle());
                        list.add(b.getAuthor());
                    }
                }
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gv_display.setAdapter(adapter);
            }
        });

        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_id.getText().toString();
                if (!id.isEmpty()) {
                    int idkq = Integer.parseInt(id);
                    dbHelper.deleteBook(idkq);
                    adapter.notifyDataSetChanged();
                    clear();
                    Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void clear() {
        et_id.setText("");
        et_title.setText("");
        et_author.setText("");
        et_id.requestFocus();
    }

    private void mappingView() {
        //EditText
        et_id = (EditText) findViewById(R.id.editTextID);
        et_title = (EditText) findViewById(R.id.editTextTitle);
        et_author = (EditText)findViewById(R.id.editTextName);

        //GridView
        gv_display = (GridView) findViewById(R.id.gridView_listItem);

        //DBHelper
        dbHelper = new DBHelper(this);

        //Button
        bt_save = (Button) findViewById(R.id.buttonSave);
        bt_select = (Button) findViewById(R.id.buttonSelect);
        bt_exit = (Button) findViewById(R.id.buttonExit);
        bt_delete = (Button) findViewById(R.id.buttonDelete);
        bt_update = (Button) findViewById(R.id.buttonUpdate);
    }
}
