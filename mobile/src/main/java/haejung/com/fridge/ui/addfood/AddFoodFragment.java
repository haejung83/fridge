package haejung.com.fridge.ui.addfood;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import haejung.com.fridge.R;

public class AddFoodFragment extends Fragment {
    private static final String TAG = AddFoodFragment.class.getSimpleName();

    private AddFoodInteraction mInteraction;

    public AddFoodFragment() {
        mInteraction = null;
    }

    public static AddFoodFragment newInstance() {
        AddFoodFragment fragment = new AddFoodFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mInteraction == null) {
            if (context instanceof AddFoodInteraction)
                mInteraction = (AddFoodInteraction) context;
            else
                throw new RuntimeException("the context is not implemented AddFoodInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInteraction = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_food, container, false);
        v.findViewById(R.id.relativeValidation).setOnClickListener(mOnClickListener);
        v.findViewById(R.id.relativeStoragePeriod).setOnClickListener(mOnClickListener);
        return v;
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(TAG, "onClick: " + v);
            switch (v.getId()) {
                case R.id.relativeValidation:
                    mInteraction.onAddFoodInteraction(AddFoodInteraction.INTERACTION_MOVE_TO_SELECTION_VALIDATION_DATE);
                    break;
                case R.id.relativeStoragePeriod:
                    mInteraction.onAddFoodInteraction(AddFoodInteraction.INTERACTION_MOVE_TO_SELECTION_STORAGE_PERIOD);
                    break;
                default:
                    break;
            }
        }
    };

}
