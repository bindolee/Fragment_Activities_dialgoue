package sbin.com.fragmentdialog;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sbin on 10/18/2016.
 */

public class DataEntryDialog extends DialogFragment {

    public static final String PERSON_KEY = "PERSON_KEY";
    EditText etFirstName, etLastName, etAge;
    private DataEntryListener mListener;

    //newInstance method is needed to pass parcelable object data
    public static DataEntryDialog newInstance(Person person) {

        Bundle args = new Bundle();
        args.putParcelable(PERSON_KEY, person);
        DataEntryDialog fragment = new DataEntryDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DataEntryListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.data_entry_dialog, container, false);
        etFirstName = (EditText) rootview.findViewById(R.id.textFirstName);
        etLastName = (EditText) rootview.findViewById(R.id.textLastName);
        etAge = (EditText) rootview.findViewById(R.id.textAge);

        Person person = getArguments().getParcelable(PERSON_KEY);
        etFirstName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());
        etAge.setText(String.valueOf(person.getAge()));

        Button btnOk = (Button) rootview.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        Button btnCancel = (Button) rootview.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootview;
    }

    private void saveData() {
        Person person = new Person();
        person.setFirstName(etFirstName.getText().toString());
        person.setLastName(etLastName.getText().toString());
        person.setAge(Integer.valueOf(etAge.getText().toString()));

        mListener.onDataEntryCOmplete(person);
        dismiss(); // Close dialog when I am done;
    }

    public interface DataEntryListener{
        void onDataEntryCOmplete(Person person);
    }
}
