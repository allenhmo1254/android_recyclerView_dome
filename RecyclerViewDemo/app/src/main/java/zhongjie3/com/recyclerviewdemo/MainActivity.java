package zhongjie3.com.recyclerviewdemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
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


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener
    {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recycler_main, parent, false);

            MyViewHolder holder = new MyViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            MainBean bean = list.get(position);

            if (bean == null)
            {
                return;
            }

            holder.iconView.setImageResource(bean.getIconId());
            holder.titleView.setText(bean.getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v);
            Log.d("MainActivity", "position = "+position);
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            private ImageView iconView;
            private TextView titleView;

            public MyViewHolder(View itemView) {
                super(itemView);

                iconView = (ImageView)itemView.findViewById(R.id.iconView);
                titleView = (TextView)itemView.findViewById(R.id.titleView);
            }
        }
    }
}
