package com.example.firstexample.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstexample.R;
import com.example.firstexample.data.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private StudentClickListener studentClickListener;
    private List<Student> studentList;

    public StudentAdapter(List<Student> students){
        this.studentList = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bindHolder(studentList.get(position), studentClickListener);
    }

    @Override
    public int getItemCount() {
        return this.studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView tvIndex, tvName, tvSurname;
        private View view;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvIndex = itemView.findViewById(R.id.tvIndex);
            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);
        }

        public void bindHolder(final Student student, final StudentClickListener listener){
            tvIndex.setText(student.index);
            tvName.setText(student.firstName);
            tvSurname.setText(student.lastName);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onStudentClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface StudentClickListener{
        void onStudentClicked(int position);
    }

    public void setStudentClickListener(StudentClickListener studentClickListener) {
        this.studentClickListener = studentClickListener;
    }
}
