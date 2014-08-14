package com.tripadvisor.seekbar.sample;

import org.joda.time.DateTime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tripadvisor.seekbar.ClockView;
import com.tripadvisor.seekbar.ClockView.ClockTimeUpdateListener;
import com.tripadvisor.seekbar.util.annotations.VisibleForTesting;

public class MainActivity extends ActionBarActivity {

    private static ClockView sMinDepartTimeClockView;
    private static ClockView sMaxDepartTimeClockView;
    private static PlaceholderFragment sPlaceholderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            sPlaceholderFragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, sPlaceholderFragment)
                    .commit();
        }
    }

    public static ClockView getMinDepartTimeClockView() {
        return sMinDepartTimeClockView;
    }

    public static ClockView getMaxDepartTimeClockView() {
        return sMaxDepartTimeClockView;
    }

    public static PlaceholderFragment getPlaceholderFragment() {
        return sPlaceholderFragment;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements ClockTimeUpdateListener {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            final DateTime minTime = new DateTime(2014, 4, 25, 7, 0);
            final DateTime maxTime = new DateTime(2014, 4, 26, 5, 0);

            sMinDepartTimeClockView = (ClockView) rootView.findViewById(R.id.min_depart_time_clock_view);
            sMinDepartTimeClockView.setBounds(minTime, maxTime, false);

            sMaxDepartTimeClockView = (ClockView) rootView.findViewById(R.id.max_depart_time_clock_view);
            sMaxDepartTimeClockView.setBounds(minTime, maxTime, true);

            ClockView mMinArrivalTimeClockView = (ClockView) rootView.findViewById(R.id
                    .min_arrive_time_clock_view);
            mMinArrivalTimeClockView.setBounds(minTime, maxTime, false);
            mMinArrivalTimeClockView.setNewCurrentTime(new DateTime(2014, 4, 25, 10, 0));
            ClockView mMaxArrivalTimeClockView = (ClockView) rootView.findViewById(R.id
                    .max_arrive_time_clock_view);
            mMaxArrivalTimeClockView.setBounds(minTime, maxTime, true);
            mMaxArrivalTimeClockView.setNewCurrentTime(new DateTime(2014, 4, 25, 10, 0));

            final ClockView minRandomTime = (ClockView) rootView.findViewById(R.id.min_random_time_clock_view);
            minRandomTime.setBounds(minTime, maxTime, false);
            minRandomTime.setNewCurrentTime(new DateTime(2014, 4, 25, 10, 0));

            final ClockView maxRandomTime = (ClockView) rootView.findViewById(R.id.max_random_time_clock_view);
            maxRandomTime.setBounds(minTime, maxTime, false);
            maxRandomTime.setNewCurrentTime(new DateTime(2014, 4, 25, 10, 0));

            return rootView;
        }

        @VisibleForTesting
        public void changeClockTimeForTests(DateTime dateTime, boolean isMaxTime) {
            if (isMaxTime) {
                sMaxDepartTimeClockView.setClockTimeUpdateListener(this);
                sMaxDepartTimeClockView.setNewCurrentTime(dateTime);
            } else {
                sMinDepartTimeClockView.setClockTimeUpdateListener(this);
                sMinDepartTimeClockView.setNewCurrentTime(dateTime);
            }
        }

        @Override
        public void onClockTimeUpdate(ClockView clockView, DateTime currentTime) {
            if (clockView.equals(sMinDepartTimeClockView)) {
                if (currentTime.compareTo(sMaxDepartTimeClockView.getNewCurrentTime()) >= 0) {
                    sMinDepartTimeClockView.setNewCurrentTime(sMinDepartTimeClockView.getNewCurrentTime().minusHours(1));
                }
            } else if (clockView.equals(sMaxDepartTimeClockView)) {
                if (currentTime.compareTo(sMinDepartTimeClockView.getNewCurrentTime()) <= 0) {
                    sMaxDepartTimeClockView.setNewCurrentTime(sMaxDepartTimeClockView.getNewCurrentTime().plusHours(1));
                }
            }
        }
    }
}
