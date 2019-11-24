package com.example.easyfit_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.easyfit_app.R;
import com.example.easyfit_app.dialogs.HelpDialogFragment;

/**
 * Creates a window containing text that explains how the application works.
 */

public class AboutActivity extends AppCompatActivity {

    private TextView tvAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar tbAbout = (Toolbar) findViewById(R.id.tb_about);
        setSupportActionBar(tbAbout);
        setTitle("About");
        tbAbout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvAbout = (TextView) findViewById(R.id.tv_about);
    }

    // Options menu methods

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tb_item_home:
                Intent intentOpenHome = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intentOpenHome);
                return true;
            case R.id.drop_item_help:
                DialogFragment helpDialog = new HelpDialogFragment();
                helpDialog.show(getSupportFragmentManager(), "help");
                return true;
            case R.id.drop_item_about:
                Intent intentOpenAbout = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(intentOpenAbout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
