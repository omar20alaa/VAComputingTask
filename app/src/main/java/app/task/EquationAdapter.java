package app.task;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.task.databinding.EquationItemBinding;

public class EquationAdapter extends RecyclerView.Adapter<EquationAdapter.viewHolder> {

    private Context context;
    private List<Equation> equations;
    EquationItemBinding binding;

    public EquationAdapter(Context context, List<Equation> equations) {
        this.context = context;
        this.equations = equations;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private EquationItemBinding binding;

        public viewHolder(@NonNull EquationItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    @NonNull
    @Override
    public EquationAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        binding = EquationItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new EquationAdapter.viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EquationAdapter.viewHolder viewHolder, final int position) {

        if (equations.get(position).isCompleted()) {
            binding.btnStatus.setText("Completed");
            binding.btnStatus.setTextColor(Color.parseColor("#00ff00"));
            binding.equal.setVisibility(View.VISIBLE);
            binding.tvResult.setVisibility(View.VISIBLE);
        } else {
            binding.btnStatus.setText("Pending");
            binding.btnStatus.setTextColor(Color.parseColor("#ff0000"));
            binding.equal.setVisibility(View.GONE);
            binding.tvResult.setVisibility(View.GONE);
        }

        binding.tvFirstNum.setText(equations.get(position).getFirst_num() + "");
        binding.tvSecNum.setText(equations.get(position).getSec_num() + "");
        binding.tvSign.setText(equations.get(position).getSign() + "");
        binding.tvResult.setText(equations.get(position).getResult() + "");

    }


    @Override
    public int getItemCount() {
        return (equations == null) ? 0 : equations.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}




