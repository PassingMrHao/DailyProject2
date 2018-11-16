package mrhao.com.dailyproject2.utils.MySearchViewUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import mrhao.com.dailyproject2.R;
import mrhao.com.dailyproject2.adapter.RecyResultAd;
import mrhao.com.dailyproject2.bean.SearchDateBean;
import mrhao.com.dailyproject2.bean.TravelAllJingDianDateBean;

public class MySearchView extends LinearLayout {

    /**
     * 初始化成员变量
     */
    private Context context;

    //搜索结果展示
    RecyclerView recyResult;
    RecyResultAd Ad;
    String api = "http://chanyouji.com/api/destinations/attractions/14.json?page=";
    SearchDateBean searchbean = new SearchDateBean();
    SearchDateBean searchbean2 = new SearchDateBean();
    ArrayList<TravelAllJingDianDateBean> bean = new ArrayList<TravelAllJingDianDateBean>();
    Type littype = new TypeToken<List<TravelAllJingDianDateBean>>() {
    }.getType();
    Gson gs = new Gson();


    // 搜索框组件
    private EditText et_search; // 输入框搜索按键
    private TextView tv_clear;  // 删除搜索记录按键
    private LinearLayout search_block; // 搜索框布局
    private Button btn_sousuo; // 搜索按钮
    private LinearLayout linhis; // 搜索历史
    private RelativeLayout laynores;// 无搜索结果


    // ListView列表 & 适配器
    private SearchListView listView;
    private BaseAdapter adapter;

    // 数据库变量
    // 用于存放历史搜索记录
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;

    // 回调接口
    private ICallBack mCallBack;// 搜索按键回调接口


    // 自定义属性设置
    // 1. 搜索字体属性设置：大小、颜色 & 默认提示
    private Float textSizeSearch;
    private int textColorSearch;
    private String textHintSearch;

    // 2. 搜索框设置：高度 & 颜色
    private int searchBlockHeight;
    private int searchBlockColor;

    /**
     * 构造函数
     * 作用：对搜索框进行初始化
     */
    public MySearchView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(context, attrs); // ->>关注a
        init();// ->>关注b
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(context, attrs);
        init();
    }

    /**
     * 关注a
     * 作用：初始化自定义属性
     */
    private void initAttrs(Context context, AttributeSet attrs) {

        // 控件资源名称
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Search_View);

        // 搜索框字体大小（dp）
        textSizeSearch = typedArray.getDimension(R.styleable.Search_View_textSizeSearch, 20);

        // 搜索框字体颜色（使用十六进制代码，如#333、#8e8e8e）
        int defaultColor = context.getResources().getColor(R.color.colorText); // 默认颜色 = 灰色
        textColorSearch = typedArray.getColor(R.styleable.Search_View_textColorSearch, defaultColor);

        // 搜索框提示内容（String）
        textHintSearch = typedArray.getString(R.styleable.Search_View_textHintSearch);

        // 搜索框高度
        searchBlockHeight = typedArray.getInteger(R.styleable.Search_View_searchBlockHeight, 150);

        // 搜索框颜色
        int defaultColor2 = context.getResources().getColor(R.color.colorDefault); // 默认颜色 = 白色
        searchBlockColor = typedArray.getColor(R.styleable.Search_View_searchBlockColor, defaultColor2);

        // 释放资源
        typedArray.recycle();
    }


    /**
     * 关注b
     * 作用：初始化搜索框
     */
    private void init() {

        // 1. 添加数据进搜索库，初始化UI组件->>关注c
        AddDate();
        initView();

        // 2. 实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context);

        // 3. 第1次进入时查询所有的历史搜索记录
        queryData("");


        /**
         * "清空搜索历史"按钮
         */
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 清空数据库->>关注2
                deleteData();
                // 模糊搜索空字符 = 显示所有的搜索历史（此时是没有搜索记录的）
                queryData("");
            }
        });


        /**
         * 监听输入键盘更换后的搜索按键
         * 调用时刻：点击键盘上的搜索键时
         */
        btn_sousuo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 点击搜索按键后，根据输入的搜索字段进行查询
                // 注：由于此处需求会根据自身情况不同而不同，所以具体逻辑由开发者自己实现，此处仅留出接口

                searchbean2.getList().clear();
                searchbean2.getImglist().clear();
                searchbean2.getDesclist().clear();
                String sss = et_search.getText().toString().trim();
                boolean issame = false;
                for (int p = 0; p < searchbean.getList().size(); p++) {
                    if (searchbean.getList().get(p).equals(sss)) {
                        issame = true;
                        searchbean2.getList().add(searchbean.getList().get(p));
                        searchbean2.getImglist().add(searchbean.getImglist().get(p));
                        searchbean2.getDesclist().add(searchbean.getDesclist().get(p));
                    }
                }
                if (issame == false) {

                    char c;
                    for (int i = 0; i < sss.length(); i++) {
                        c = sss.charAt(i);
                        for (int j = 0; j < searchbean.getList().size(); j++) {
                            if (searchbean.getList().get(j).contains(c + "")) {
                                searchbean2.getList().add(searchbean.getList().get(j));
                                searchbean2.getImglist().add(searchbean.getImglist().get(j));
                                searchbean2.getDesclist().add(searchbean.getDesclist().get(j));
                            }
                        }
                    }

                    LinkedHashSet<String> set = new LinkedHashSet<>(searchbean2.getList().size());
                    LinkedHashSet<String> set2 = new LinkedHashSet<>(searchbean2.getImglist().size());
                    LinkedHashSet<String> set3 = new LinkedHashSet<>(searchbean2.getDesclist().size());
                    set.addAll(searchbean2.getList());
                    set2.addAll(searchbean2.getImglist());
                    set3.addAll(searchbean2.getDesclist());
                    searchbean2.getList().clear();
                    searchbean2.getImglist().clear();
                    searchbean2.getDesclist().clear();
                    searchbean2.getList().addAll(set);
                    searchbean2.getImglist().addAll(set2);
                    searchbean2.getDesclist().addAll(set3);
                    if (searchbean2.getList().size() == 0) {
                        laynores.setVisibility(VISIBLE);
                    } else {
                        laynores.setVisibility(GONE);
                    }
                    recyResult.setLayoutManager(new LinearLayoutManager(context));
                    Ad = new RecyResultAd(context, searchbean2);
                    recyResult.setAdapter(Ad);


                } else if (issame == true) {

                    if (searchbean2.getList().size() == 0) {
                        laynores.setVisibility(VISIBLE);
                    } else {
                        laynores.setVisibility(GONE);
                    }
                    recyResult.setLayoutManager(new LinearLayoutManager(context));
                    Ad = new RecyResultAd(context, searchbean2);
                    recyResult.setAdapter(Ad);
                }

                if (et_search.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "搜索内容为空，请输入要搜索的景点", Toast.LENGTH_SHORT).show();
                }
                if (!(mCallBack == null) && TextUtils.isEmpty(et_search.getText().toString()) && (!et_search.getText().toString().trim().equals(""))) {
                    //搜索事件监听
                    mCallBack.SearchAciton(et_search.getText().toString());

                }
//                    Toast.makeText(context, "需要搜索的是" + et_search.getText(), Toast.LENGTH_SHORT).show();

                // 2. 点击搜索键后，对该搜索字段在数据库是否存在进行检查（查询）->> 关注1

                boolean hasData = hasData(et_search.getText().toString().trim());
                // 3. 若存在，则不保存；若不存在，则将该搜索字段保存（插入）到数据库，并作为历史搜索记录
                if (!hasData) {
                    if (!et_search.getText().toString().trim().equals("")) {
                        insertData(et_search.getText().toString().trim());
                        queryData("");
                    }

                }


            }
        });


        /**
         * 监听输入键盘更换后的搜索按键
         * 调用时刻：点击键盘上的搜索键时
         */
        et_search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    searchbean2.getList().clear();
                    searchbean2.getImglist().clear();
                    searchbean2.getDesclist().clear();
                    String sss = et_search.getText().toString().trim();
                    boolean issame = false;
                    for (int p = 0; p < searchbean.getList().size(); p++) {
                        if (searchbean.getList().get(p).equals(sss)) {
                            issame = true;
                            searchbean2.getList().add(searchbean.getList().get(p));
                            searchbean2.getImglist().add(searchbean.getImglist().get(p));
                            searchbean2.getDesclist().add(searchbean.getDesclist().get(p));
                        }
                    }
                    if (issame == false) {

                        char c;
                        for (int i = 0; i < sss.length(); i++) {
                            c = sss.charAt(i);
                            for (int j = 0; j < searchbean.getList().size(); j++) {
                                if (searchbean.getList().get(j).contains(c + "")) {
                                    searchbean2.getList().add(searchbean.getList().get(j));
                                    searchbean2.getImglist().add(searchbean.getImglist().get(j));
                                    searchbean2.getDesclist().add(searchbean.getDesclist().get(j));
                                }
                            }
                        }

                        LinkedHashSet<String> set = new LinkedHashSet<>(searchbean2.getList().size());
                        LinkedHashSet<String> set2 = new LinkedHashSet<>(searchbean2.getImglist().size());
                        LinkedHashSet<String> set3 = new LinkedHashSet<>(searchbean2.getDesclist().size());
                        set.addAll(searchbean2.getList());
                        set2.addAll(searchbean2.getImglist());
                        set3.addAll(searchbean2.getDesclist());
                        searchbean2.getList().clear();
                        searchbean2.getImglist().clear();
                        searchbean2.getDesclist().clear();
                        searchbean2.getList().addAll(set);
                        searchbean2.getImglist().addAll(set2);
                        searchbean2.getDesclist().addAll(set3);

                        if (searchbean2.getList().size() == 0) {
                            laynores.setVisibility(VISIBLE);
                        } else {
                            laynores.setVisibility(GONE);
                        }
                        recyResult.setLayoutManager(new LinearLayoutManager(context));
                        Ad = new RecyResultAd(context, searchbean2);
                        recyResult.setAdapter(Ad);
                    } else if (issame == true) {

                        if (searchbean2.getList().size() == 0) {
                            laynores.setVisibility(VISIBLE);
                        } else {
                            laynores.setVisibility(GONE);
                        }
                        recyResult.setLayoutManager(new LinearLayoutManager(context));
                        Ad = new RecyResultAd(context, searchbean2);
                        recyResult.setAdapter(Ad);
                    }

                    if (et_search.getText().toString().trim().equals("")) {
                        Toast.makeText(context, "搜索内容为空，请输入要搜索的景点", Toast.LENGTH_SHORT).show();
                    }
                    if (!(mCallBack == null) && TextUtils.isEmpty(et_search.getText().toString()) && (!et_search.getText().toString().trim().equals(""))) {
                        //搜索事件监听
                        mCallBack.SearchAciton(et_search.getText().toString());

                    }
//                    Toast.makeText(context, "需要搜索的是" + et_search.getText(), Toast.LENGTH_SHORT).show();

                    // 2. 点击搜索键后，对该搜索字段在数据库是否存在进行检查（查询）->> 关注1

                    boolean hasData = hasData(et_search.getText().toString().trim());
                    // 3. 若存在，则不保存；若不存在，则将该搜索字段保存（插入）到数据库，并作为历史搜索记录
                    if (!hasData) {
                        if (!et_search.getText().toString().trim().equals("")) {
                            insertData(et_search.getText().toString().trim());
                            queryData("");
                        }

                    }

                }
                return false;
            }
        });


        /**
         * 搜索框的文本变化实时监听
         */
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(et_search.getText().toString().trim())) {
                    searchbean2.getList().clear();
                    searchbean2.getImglist().clear();
                    searchbean2.getDesclist().clear();
                    Ad.notifyDataSetChanged();
                } else {

                }
            }

            // 输入文本后调用该方法
            @Override
            public void afterTextChanged(Editable s) {
                // 每次输入后，模糊查询数据库 & 显示
                // 注：若搜索框为空,则模糊搜索空字符 = 显示所有的搜索历史
                String tempName = et_search.getText().toString();
                queryData(tempName); // ->>关注1

            }
        });

        /**
         * 搜索记录列表（ListView）监听
         * 即当用户点击搜索历史里的字段后,会直接将结果当作搜索字段进行搜索
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 获取用户点击列表里的文字,并自动填充到搜索框内
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                et_search.setText(name);

            }
        });
    }


    //添加数据进搜索库
    private void AddDate() {

        PostDate(1);
        PostDate(2);
        PostDate(3);
        PostDate(4);
        PostDate(5);
        PostDate(6);
        PostDate(7);
        PostDate(8);
        PostDate(9);
        PostDate(10);
        PostDate(11);
        PostDate(12);
        PostDate(13);
        PostDate(14);
        PostDate(15);
        PostDate(16);
        PostDate(17);

    }

    private void PostDate(int i) {

        Map<String, String> map = new HashMap<>();
        map.put("", "");
        OkHttpUtils.get().url(api + i).params(map).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        bean = gs.fromJson(response, littype);
                        for (int i = 0; i < bean.size(); i++) {
                            searchbean.getList().add(bean.get(i).getName());
                            searchbean.getImglist().add(bean.get(i).getImage_url());
                            searchbean.getDesclist().add(bean.get(i).getDescription());
                        }
                    }
                });
    }

    /**
     * 关注c：绑定搜索框xml视图
     */
    private void initView() {

        // 1. 绑定R.layout.search_layout作为搜索框的xml文件
        LayoutInflater.from(context).inflate(R.layout.mysearch_layout, this);

        // 2. 绑定搜索框EditText
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setTextSize(textSizeSearch);
        et_search.setTextColor(textColorSearch);
        et_search.setHint(textHintSearch);

        // 3. 搜索框背景颜色
        search_block = (LinearLayout) findViewById(R.id.search_block);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) search_block.getLayoutParams();
        params.height = searchBlockHeight;
        search_block.setBackgroundColor(searchBlockColor);
        search_block.setLayoutParams(params);

        // 4. 历史搜索记录 = ListView显示
        listView = (SearchListView) findViewById(R.id.listView);

        // 5. 删除历史搜索记录 按钮
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setVisibility(INVISIBLE);
        // 6. 搜索按钮
        btn_sousuo = (Button) findViewById(R.id.btn_sousuo);
        // 7. 搜索结果展示
        recyResult = (RecyclerView) findViewById(R.id.recy_result);
        recyResult.setNestedScrollingEnabled(false);
        // 8. 搜索历史
        linhis = (LinearLayout) findViewById(R.id.lin_history);
        linhis.setVisibility(INVISIBLE);
        // 9.无任何搜索结果
        laynores = (RelativeLayout) findViewById(R.id.relay_noresult);

    }

    /**
     * 关注1
     * 模糊查询数据 & 显示到ListView列表上
     */
    private void queryData(String tempName) {

        // 1. 模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 2. 创建adapter适配器对象 & 装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 3. 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        System.out.println(cursor.getCount());
        // 当输入框为空 & 数据库中有搜索记录时，显示 "删除搜索记录"按钮
        if (tempName.equals("") && cursor.getCount() != 0) {
            tv_clear.setVisibility(VISIBLE);
            linhis.setVisibility(VISIBLE);
        } else {
            tv_clear.setVisibility(INVISIBLE);
            linhis.setVisibility(INVISIBLE);
        }
        ;

    }

    /**
     * 关注2：清空数据库
     */
    private void deleteData() {

        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
        tv_clear.setVisibility(INVISIBLE);
        linhis.setVisibility(INVISIBLE);
    }

    /**
     * 关注3
     * 检查数据库中是否已经有该搜索记录
     */
    private boolean hasData(String tempName) {
        // 从数据库中Record表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //  判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 关注4
     * 插入数据到数据库，即写入搜索字段到历史搜索记录
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 点击键盘中搜索键后的操作，用于接口回调
     */
    public void setOnClickSearch(ICallBack mCallBack) {
        this.mCallBack = mCallBack;

    }

}
