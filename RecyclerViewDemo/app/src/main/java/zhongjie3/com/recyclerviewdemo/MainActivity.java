package zhongjie3.com.recyclerviewdemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private List<MainBean> list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData()
    {
        list = new ArrayList<>();

        for (int i = 0; i < 10; i ++)
        {
            MainBean bean = new MainBean();
            bean.setIconId(R.drawable.icon);
            bean.setTitle("测试"+i);
            bean.setType(i % 2);
            list.add(bean);
        }
    }

    private void initView()
    {
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        recyclerView = (RecyclerView)findViewById(R.id.grid_recycler);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
    }


    class MyAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener
    {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType)
            {
                case 0:
                {
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recycler_main, parent, false);
                }
                    break;
                case 1:
                {
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recycler_other, parent, false);
                }
                    break;
                default:
                {
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recycler_main, parent, false);
                }
                    break;
            }

            ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MainBean bean = list.get(position);

            if (bean == null)
            {
                return;
            }

            switch (bean.getType())
            {
                case 0:
                {
                    ((ImageView)holder.get(R.id.iconView)).setImageResource(bean.getIconId());
                    ((TextView)holder.get(R.id.titleView)).setText(bean.getTitle());
                }
                    break;
                case 1:
                {
                    ((TextView)holder.get(R.id.titleView1)).setText(bean.getTitle());
                }
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public int getItemViewType(int position) {
            MainBean bean = list.get(position);

            return bean.getType();
        }

        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v);
            Log.d("MainActivity", "position = "+position);
        }
    }
}
