package com.example.jobfinder;

    import android.media.Image;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.RecyclerView;

    import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class HomeFragment extends Fragment {

    private RecyclerView listing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

        //listing = getView().findViewById(R.id.jobList);

    }

    @Override
    public void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<>


    }
}

