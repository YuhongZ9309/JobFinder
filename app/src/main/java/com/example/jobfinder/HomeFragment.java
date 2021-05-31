package com.example.jobfinder;

    import android.content.Intent;
    import android.media.Image;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.google.android.material.floatingactionbutton.FloatingActionButton;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;

// import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class HomeFragment extends Fragment {

    RecyclerView listing;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Job> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        listing = v.findViewById(R.id.jobList);
        database = FirebaseDatabase.getInstance().getReference("Jobs");
        listing.setHasFixedSize(true);
        listing.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(getActivity(), list);
        listing.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Job job = dataSnapshot.getValue(Job.class);
                    list.add(job);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return v ;

        

    }

//    @Override
//    public void onStart()
//    {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<>
//
//
//    }

}

