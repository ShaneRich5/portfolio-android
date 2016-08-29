package com.shane.portfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab_email) FloatingActionButton emailFab;
    @BindView(R.id.recycler_projects)
    RecyclerView projectRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        emailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEmailIntent();
            }
        });

        List<Project> projects = generateProjects();

        ProjectAdapter projectAdapter = new ProjectAdapter(this, projects);
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        projectRecyclerView.setAdapter(projectAdapter);
    }

    private List<Project> generateProjects() {
        return new ArrayList<>(Arrays
                .asList(new Project("Popular Movies"),
                        new Project("Stock Market"),
                        new Project("Build It Bigger"),
                        new Project("Make You App Material"),
                        new Project("Go Ubiquitous"),
                        new Project("Capstone")));
    }

    private void createEmailIntent() {
        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "shane.richards121@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Portfolio App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}