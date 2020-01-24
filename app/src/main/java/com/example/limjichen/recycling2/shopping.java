package com.example.limjichen.recycling2;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.example.limjichen.recycling2.shopping_library.Album;
import com.example.limjichen.recycling2.shopping_library.AlbumsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIMJICHEN on 23/4/2018.
 */

public class shopping extends AppCompatActivity{
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

    }
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.shop_1,
                R.drawable.shop_2,
                R.drawable.shop_3,
                R.drawable.shop_4,
                R.drawable.shop_5,
                R.drawable.shop_6,
                R.drawable.shop_7,
                R.drawable.shop_8,};

        Album a = new Album("RM30 Petronas Voucher", "450 Gru Coins", covers[0]);
        albumList.add(a);

        a = new Album("RM10 AEON Voucher", "200 Gru Coins", covers[1]);
        albumList.add(a);

        a = new Album("RM10 ChinaBuffet Voucher", "220 Gru Coins", covers[2]);
        albumList.add(a);

        a = new Album("RM10 Secret Recipe Voucher", "250 Gru Coins", covers[3]);
        albumList.add(a);

        a = new Album("RM15 SushiTeh Voucher", "200 Gru Coins", covers[4]);
        albumList.add(a);

        a = new Album("RM15 Zoo Melaka Voucher", "150 Gru Coins", covers[5]);
        albumList.add(a);

        a = new Album("RM10 MBO Cinemas Voucher", "180 Gru Coins", covers[6]);
        albumList.add(a);

        a = new Album("RM10 TambunLostWorld Voucher", "220 Gru Coins", covers[7]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
