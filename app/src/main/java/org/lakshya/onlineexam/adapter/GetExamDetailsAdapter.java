package org.lakshya.onlineexam.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.lakshya.onlineexam.R;
import org.lakshya.onlineexam.model.GetExamDetails;
import org.lakshya.onlineexam.ui.onlineexam.GiveExamFragment;

import java.util.List;

public class GetExamDetailsAdapter extends RecyclerView.Adapter<GetExamDetailsAdapter.ViewHolder>
{
    LayoutInflater inflater;
    List<GetExamDetails> examDetails;
    Context ctx;
    public GetExamDetailsAdapter(Context ctx, List<GetExamDetails> examDetails)
    {
        this.ctx = ctx;
        this.inflater = LayoutInflater.from(ctx);
        this.examDetails = examDetails;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_exam_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.examTitle.setText(examDetails.get(position).getExamTitle());
        holder.examdate.setText(examDetails.get(position).getExamDate());
        Picasso.get().load(examDetails.get(position).getExamImage()).into(holder.examImage);
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)ctx).getSupportFragmentManager();
                fragmentManager.popBackStack();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                GiveExamFragment giveExamFragment = new GiveExamFragment();
                Bundle bundle=new Bundle();
                bundle.putString("title",examDetails.get(position).getExamTitle());
                giveExamFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment, giveExamFragment);
               /* fragmentTransaction.addToBackStack(null);*/
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return examDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView examTitle,examdate;
        ImageView examImage;
        Button btnStart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            examTitle = itemView.findViewById(R.id.tvExamTitle);
            examdate = itemView.findViewById(R.id.tvExamDate);
            examImage = itemView.findViewById(R.id.ivExamImage);
            btnStart = itemView.findViewById(R.id.btnStart);
        }
    }
}
