package fr.icodem.framelayoutapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TwoFragment extends Fragment {

    private int count;

    private FrameLayout contentPanel;
    private TextView textView;
    private Button button;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            count = args.getInt("count2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        textView = (TextView) view.findViewById(R.id.textView);
        if (count > 0) {
            textView.setText("Started : " + count);
        }

        contentPanel = (FrameLayout) view.findViewById(R.id.contentPanel);
        contentPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView.setText("Started : " + count);
            }
        });

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction2();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count2", count);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count2");
            textView.setText("Started : " + count);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction2();
    }

    public void setListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }
}
