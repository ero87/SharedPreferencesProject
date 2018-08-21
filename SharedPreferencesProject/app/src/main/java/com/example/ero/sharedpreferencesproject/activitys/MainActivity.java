package com.example.ero.sharedpreferencesproject.activitys;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ero.sharedpreferencesproject.fragment.AddDialogFragment;
import com.example.ero.sharedpreferencesproject.models.Model;
import com.example.ero.sharedpreferencesproject.R;
import com.example.ero.sharedpreferencesproject.adapters.SaredPreferancesAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Model> list = new ArrayList<>();
    private static final String JSONELIST = "JsoneList";
    private RecyclerView recyclerView;
    private SaredPreferancesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setList();
        recycler();
        openDialog();
        final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.
                SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT | direction == ItemTouchHelper.RIGHT) {
                    remove(position);
                }
            }
        };
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void remove(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.delete));

        builder.setPositiveButton(getString(R.string.remove), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.notifyItemRemoved(position);
                list.remove(position);
            }
        }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.notifyItemRemoved(position + 1);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }
        }).show();
    }

    private void recycler() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SaredPreferancesAdapter(MainActivity.this, list);
        recyclerView.setAdapter(adapter);
    }

    private void openDialog() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDialogFragment dialogFragment = new AddDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "tag");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveArrayList((ArrayList<Model>) list);
    }

    private void setList() {
        if (getArrayList() != null) {
            list = getArrayList();
        } else {
            list = new ArrayList<>();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Model> getList() {
        return list;
    }

    private void saveArrayList(ArrayList<Model> list) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final Gson gson = new Gson();
        final String json = gson.toJson(list);
        editor.putString(JSONELIST, json);
        editor.apply();
    }

    private ArrayList<Model> getArrayList() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Gson gson = new Gson();
        final String json = prefs.getString(JSONELIST, null);
        final Type type = new TypeToken<ArrayList<Model>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
