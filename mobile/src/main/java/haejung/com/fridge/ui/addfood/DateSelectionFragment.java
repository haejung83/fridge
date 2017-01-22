package haejung.com.fridge.ui.addfood;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import haejung.com.fridge.R;

public class DateSelectionFragment extends Fragment {

    private static final String ARG_TYPE = "arg_type";

    public static final int DATE_TYPE_VALIDATION = 0;
    public static final int DATE_TYPE_STORAGE_PERIOD = 1;

    @IntDef({DATE_TYPE_VALIDATION, DATE_TYPE_STORAGE_PERIOD})
    public @interface DATE_TYPE {
    }


    public DateSelectionFragment() {
        // Required empty public constructor
    }

    private
    @DATE_TYPE
    int mDateSelectionType;
    private DatePicker mDatePicker;
    private RadioGroup mRadioGroupDate;

    public static DateSelectionFragment newInstance(@DATE_TYPE int type) {
        DateSelectionFragment fragment = new DateSelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            switch (args.getInt(ARG_TYPE)) {
                case 0:
                    mDateSelectionType = DATE_TYPE_VALIDATION;
                    break;
                case 1:
                    mDateSelectionType = DATE_TYPE_STORAGE_PERIOD;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_date_selection, container, false);

        bindingViews(v);
        setupDateViewGroupBySelectionType();

        return v;
    }

    private void bindingViews(View root) {
        mDatePicker = (DatePicker) root.findViewById(R.id.datePicker);
        mRadioGroupDate = (RadioGroup) root.findViewById(R.id.radioGroupDate);
    }

    private void setupDateViewGroupBySelectionType() {
        switch (mDateSelectionType) {
            case DATE_TYPE_STORAGE_PERIOD:
                mDatePicker.setVisibility(View.GONE);
                mRadioGroupDate.setVisibility(View.VISIBLE);
                break;
            default:
            case DATE_TYPE_VALIDATION:
                mDatePicker.setVisibility(View.VISIBLE);
                mRadioGroupDate.setVisibility(View.GONE);
                break;
        }

    }

}
