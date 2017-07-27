package zhongjie3.com.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by zhongjie3 on 2017/7/27.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray viewArray;
    private View itemView;

    public ViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        this.viewArray = new SparseArray();
    }

    public View get(int id) {
        View childView = (View) this.viewArray.get(id);
        if (childView == null) {
            childView = this.itemView.findViewById(id);
            this.viewArray.put(id, childView);
        }
        return childView;
    }
}
