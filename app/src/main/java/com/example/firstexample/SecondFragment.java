package com.example.firstexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstexample.data.FakeApi;
import com.example.firstexample.data.models.Student;
import com.example.firstexample.data.util.Constants;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvIndex, tvName, tvSurname, tvDetails;

        tvIndex = view.findViewById(R.id.tvIndex);
        tvName = view.findViewById(R.id.tvName);
        tvSurname = view.findViewById(R.id.tvSurname);
        tvDetails = view.findViewById(R.id.tvDetails);

        int studentPosition = getArguments().getInt(Constants.STUDENT_ARG_KEY);
        Student currentStudent = FakeApi.getInstance().getStudents().get(studentPosition);

        tvIndex.setText(currentStudent.index);
        tvName.setText(currentStudent.firstName);
        tvSurname.setText(currentStudent.lastName);
        tvDetails.setText(currentStudent.details);
    }
}