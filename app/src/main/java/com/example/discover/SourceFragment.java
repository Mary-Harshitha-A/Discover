package com.example.discover;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.SourceDetails;
import com.example.SourceMain;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SourceFragment extends Fragment {

    List<SourceDetails> sourceList = new ArrayList<>();
    SourceAdapter sourceAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSources();
    }

    private void getSources() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SourceService service = retrofit.create(SourceService.class);

        String apikey = "6adcfec6f402411685f2094dfd1b169c";
        String language = "en";
        Call<SourceMain> sources = service.getSource(language,apikey);
        sources.enqueue(new Callback<SourceMain>() {
            @Override
            public void onResponse(Call<SourceMain> call, Response<SourceMain> response) {
                SourceMain data = response.body();
                sourceList = data.getSources();
                sourceAdapter.setData(sourceList);

                ((SourcesActivity) getActivity()).setActionBarTitle("Sources");
            }

            @Override
            public void onFailure(Call<SourceMain> call, Throwable t) {
                Toast.makeText(getActivity(), "Service failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_source_list, container, false);
        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            sourceAdapter = new SourceAdapter(sourceList);
            recyclerView.setAdapter(sourceAdapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}
