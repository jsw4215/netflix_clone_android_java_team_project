package com.daniel.app.netfilx_clone.src.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.MainService;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.toptools.models.Contents;
import com.daniel.app.netfilx_clone.src.main.toptools.utils.RecyclerViewAdapter;
import com.daniel.app.netfilx_clone.src.main.utils.BottomNavigationViewHelper;
import com.daniel.app.netfilx_clone.src.search.interfaces.SearchActivityView;
import com.daniel.app.netfilx_clone.src.search.models.SearchPopularResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchPopularResult;
import com.daniel.app.netfilx_clone.src.search.models.SearchResponse;
import com.daniel.app.netfilx_clone.src.search.models.SearchResult;
import com.daniel.app.netfilx_clone.src.search.utils.SearchRecyclerViewAdapter;
import com.daniel.app.netfilx_clone.src.search.utils.SearchWordRecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class SearchActivity extends BaseActivity implements SearchActivityView {

    private static final String TAG = "SearchActivity";
    private static final int ACTIVITY_NUM = 1;

    Context mContext = SearchActivity.this;

    RecyclerView mRecyclerView;
    SearchWordRecyclerViewAdapter mSearchWordRecyclerViewAdapter;
    SearchRecyclerViewAdapter mRecyclerViewAdapter;
    BottomNavigationView mBottomNavigationView;

    EditText mSearchWord;
    TextView mTitleSearched;
    TextView mTitlePopular;

    List<SearchResult> mSearchList = new ArrayList<>();
    List<SearchPopularResult> mPopularList = new ArrayList<>();
    ArrayList<Contents> mContentsList = new ArrayList<>();

    String mSearchkeyWord;
    int mProfileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: started.");

        mBottomNavigationView = findViewById(R.id.nav_view);
        mRecyclerView = findViewById(R.id.search_rv_contents);
        mSearchWord = findViewById(R.id.search_et_search_word);
        mTitleSearched = findViewById(R.id.search_top_title_searched);
        mTitlePopular = findViewById(R.id.search_top_title_popular);

        mTitlePopular.setVisibility(View.VISIBLE);
        mTitleSearched.setVisibility(View.GONE);

        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

        mPopularList.clear();

        setupBottomNavigationView();

        tryGetPopularSearch();

        mSearchWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchWord = mSearchWord.getText().toString();

                if(!searchWord.equals("")){
                    mTitlePopular.setVisibility(View.GONE);
                    mTitleSearched.setVisibility(View.VISIBLE);
                    tryGetSearch(searchWord);
                }
                else if(searchWord.equals("")){
                    mTitlePopular.setVisibility(View.VISIBLE);
                    mTitleSearched.setVisibility(View.GONE);
                    setListToAdapter(mPopularList);
                }

            }
        });

    }

    public void tryGetSearchContents(){
        Log.d(TAG, "tryGetSearchContents: ");
        showProgressDialog();

        final SearchService searchService = new SearchService(this);
        searchService.getSearchContents(mSearchkeyWord);

    }

    private void tryGetSearch(String searchWord) {
        Log.d(TAG, "tryGetSearch: ");
        showProgressDialog();

        mSearchkeyWord = searchWord;

        final SearchService searchService = new SearchService(this);
        searchService.getSearch(searchWord);

    }

    private void setListToAdapter(List<SearchPopularResult> mPopularList) {

        //추가, 수정, 삭제 시에 해당 recyclerview의 크기 높이 변경 방지
        mRecyclerView.setHasFixedSize(true);
        //리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mRecyclerViewAdapter = new SearchRecyclerViewAdapter(mPopularList, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);


    }

    private void setWordListToAdapter(List<SearchResult> mSearchList) {

        //추가, 수정, 삭제 시에 해당 recyclerview의 크기 높이 변경 방지
        mRecyclerView.setHasFixedSize(true);
        //리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mSearchWordRecyclerViewAdapter = new SearchWordRecyclerViewAdapter(mSearchList, SearchActivity.this);
        mRecyclerView.setAdapter(mSearchWordRecyclerViewAdapter);


    }

    private void tryGetPopularSearch() {
        Log.d(TAG, "tryGetPopularSearch: ");
        showProgressDialog();

        final SearchService searchService = new SearchService(this);
        searchService.getPopularSearch();

    }

    private void setupBottomNavigationView(){
        Log.d(TAG,"setupBottomnavView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(mBottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext, this, mBottomNavigationView);
        Menu menu = mBottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();

    }

    @Override
    public void searchPopularSuccess(SearchPopularResponse searchPopularResponse) {
        hideProgressDialog();
        Log.d(TAG, "searchPopularSuccess: ");

        mPopularList = searchPopularResponse.getResult();

        setListToAdapter(mPopularList);

    }

    @Override
    public void searchSuccess(SearchResponse searchResponse) {
        hideProgressDialog();
        Log.d(TAG, "searchSuccess: ");

        if(searchResponse!=null) {
            Log.d(TAG, "searchSuccess: adapter started.");
            mSearchList.clear();
            mSearchList.addAll(searchResponse.getResult());

            setWordListToAdapter(mSearchList);
        }
    }

}
