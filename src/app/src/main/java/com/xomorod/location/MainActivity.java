package com.xomorod.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.xomorod.location.binders.LocationRecyclerBinder;
import com.xomorod.location.business.BinderSection;
import com.xomorod.location.business.BinderViewType;
import com.xomorod.location.db.Location;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter;

public class MainActivity extends AppCompatActivity {
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    int requestCodeAdd = 1001;
    private final RecyclerBinderAdapter<BinderSection, BinderViewType> adapter
            = new RecyclerBinderAdapter<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
        
        setSupportActionBar(toolbar);
        fillGrid();


    }

    private void fillGrid() {


        List<Location> locations = DaoAPP.getLocationDao().queryBuilder().list();
        for (Location l : locations) {
            adapter.add(BinderSection.BASE_SECTION, new LocationRecyclerBinder(this, l));
        }
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, requestCodeAdd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_export_database:
                Project.exportDatabase(this, DaoAPP.dbName, DaoAPP.dbName);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCodeAdd) {
            fillGrid();
        }
    }
}
