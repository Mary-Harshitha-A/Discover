package com.example.discover;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.Article;
import com.example.FeedsMain;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFeed extends Fragment {

    List<Article> feedList = new ArrayList<>();
    NewsfeedAdapter newsfeedAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFeeds();
    }

    private void getFeeds() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeedsService service = retrofit.create(FeedsService.class);

        String apiKey = "6adcfec6f402411685f2094dfd1b169c";
        String q = "india";
        String from = "2020-01-15";
        String to = "2020-01-15";
        String pageSize = "1";
        Call<FeedsMain> feeds = service.getFeeds(q,apiKey);

        feeds.enqueue(new Callback<FeedsMain>() {
            @Override
            public void onResponse(Call<FeedsMain> call, Response<FeedsMain> response) {
                FeedsMain data = response.body();
                assert data != null;
                feedList = data.getArticles();
                newsfeedAdapter.setData(feedList);
            }

            @Override
            public void onFailure(Call<FeedsMain> call, Throwable t) {
                Toast.makeText(getActivity(), "Service failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsfeed_list, container, false);

        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            newsfeedAdapter = new NewsfeedAdapter(feedList);
            recyclerView.setAdapter(newsfeedAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
