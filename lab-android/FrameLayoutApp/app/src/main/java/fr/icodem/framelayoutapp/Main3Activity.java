package fr.icodem.framelayoutapp;

import android.app.FragmentManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener, TwoFragment.OnFragmentInteractionListener {

    private OneFragment oneFragment;
    private TwoFragment twoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        FragmentManager fm = getFragmentManager();

        oneFragment = (OneFragment) fm.findFragmentById(R.id.fragment1);
        twoFragment = (TwoFragment) fm.findFragmentById(R.id.fragment2);

        oneFragment.setListener(this);
        twoFragment.setListener(this);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            if ("fragment1".equals(savedInstanceState.getString("fragment"))) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(oneFragment)
                        .hide(twoFragment)
                        .commit();
            }
            else if ("fragment2".equals(savedInstanceState.getString("fragment"))) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(oneFragment)
                        .show(twoFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (oneFragment.isHidden()) {
            outState.putString("fragment", "fragment2");
        }
        if (oneFragment.isVisible()) {
            outState.putString("fragment", "fragment1");
        }
    }

    @Override
    public void onFragmentInteraction1() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(oneFragment)
                .show(twoFragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction2() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(oneFragment)
                .hide(twoFragment)
                .commit();
    }

}
