package com.example.firstexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstexample.data.FakeApi;
import com.example.firstexample.data.adapters.StudentAdapter;
import com.example.firstexample.data.models.Student;
import com.example.firstexample.data.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        StudentAdapter adapter =  new StudentAdapter(FakeApi.getInstance().getStudents());

        adapter.setStudentClickListener(new StudentAdapter.StudentClickListener() {
            @Override
            public void onStudentClicked(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STUDENT_ARG_KEY, position);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext());
        builder.setTitle("Add New Student");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_student_dialog, null);

        builder.setView(view);

        final EditText etIndex, etFirstName, etLastName, etDetails;
        etIndex = view.findViewById(R.id.etIndex);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etSecondName);
        etDetails = view.findViewById(R.id.etDetails);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(etIndex.getText().toString().isEmpty() ||
                    etFirstName.getText().toString().isEmpty()||
                    etDetails.getText().toString().isEmpty()||
                    etLastName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please fill the fields", Toast.LENGTH_LONG).show();
                    return;
                }

                Student newStudent = new Student(
                        etIndex.getText().toString(),
                        etFirstName.getText().toString(),
                        etLastName.getText().toString(),
                        etDetails.getText().toString()
                );

                FakeApi.getInstance().addNewStudent(newStudent);
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}