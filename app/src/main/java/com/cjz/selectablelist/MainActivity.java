package com.cjz.selectablelist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cjz.selectablelist.adapter.MyAdapter;
import com.cjz.selectablelist.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    private ListView listView;

    private List<DataBean> mDatas;

    private MyAdapter mAdapter;
    /**
     * 全选
     */
    private Button mAllSelect;
    /**
     * 全不选
     */
    private Button mAllNotSelect;
    /**
     * 反选
     */
    private Button mBackSelect;
    /**
     * 操作
     */
    private Button mGoDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        mAllSelect = (Button) findViewById(R.id.all_select);
        mAllSelect.setOnClickListener(this);
        mAllNotSelect = (Button) findViewById(R.id.all_not_select);
        mAllNotSelect.setOnClickListener(this);
        mBackSelect = (Button) findViewById(R.id.back_select);
        mBackSelect.setOnClickListener(this);
        mGoDo = (Button) findViewById(R.id.go_do);
        mGoDo.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            DataBean dataBean = new DataBean("" + i, "上邪", "山无棱，天地合，乃敢与君绝");
            mDatas.add(dataBean);
        }

        mAdapter = new MyAdapter(this, mDatas);
        listView.setAdapter(mAdapter);
        button.setOnClickListener(this);
        mAllSelect.setOnClickListener(this);
        mAllNotSelect.setOnClickListener(this);
        mBackSelect.setOnClickListener(this);
        mGoDo.setOnClickListener(this);
    }

    /**
     * 编辑、取消编辑
     *
     * @param view
     */
    public void btnEditList(View view) {

        mAdapter.flage = !mAdapter.flage;

        if (mAdapter.flage) {
            button.setText("取消");
        } else {
            button.setText("编辑");
        }

        mAdapter.notifyDataSetChanged();
    }

    /**
     * 全选
     *
     * @param view
     */
    public void btnSelectAllList(View view) {
        if (mAdapter.flage) {
            for (int i = 0; i < mDatas.size(); i++) {
                mDatas.get(i).isCheck = true;
            }

            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 全不选
     *
     * @param view
     */
    public void btnNoList(View view) {

        if (mAdapter.flage) {
            for (int i = 0; i < mDatas.size(); i++) {
                mDatas.get(i).isCheck = false;
            }

            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 反选
     *
     * @param view
     */
    public void btnfanxuanList(View view) {
        if (mAdapter.flage) {
            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i).isCheck) {
                    mDatas.get(i).isCheck = false;
                } else {
                    mDatas.get(i).isCheck = true;
                }
            }

            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取选中数据
     *
     * @param view
     */
    public void btnOperateList(View view) {

        List<String> ids = new ArrayList<>();

        if (mAdapter.flage) {

            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i).isCheck) {
                    ids.add(mDatas.get(i).id);
                }
            }

            Toast.makeText(MainActivity.this, ids.toString(), Toast.LENGTH_SHORT).show();
            Log.e("TAG", ids.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                btnEditList(view);
                break;
            case R.id.all_select:
                btnSelectAllList(view);
                break;
            case R.id.all_not_select:
                btnNoList(view);
                break;
            case R.id.back_select:
                btnfanxuanList(view);
                break;
            case R.id.go_do:
                btnOperateList(view);
                break;
        }

    }

}
