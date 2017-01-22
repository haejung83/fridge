package haejung.com.fridge.ui.addfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import haejung.com.fridge.R;

public class AddFoodActivity extends AppCompatActivity implements AddFoodInteraction {
    private static String TAG = AddFoodActivity.class.getSimpleName();

    public static String TYPE_ADD = "type_add";
    public static String TYPE_ADD_COLD_STORAGE = "cold";
    public static String TYPE_ADD_FROZEN_STORAGE = "frozen";

    private TextView mTextNavigationTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        bindingViews();
        setupWithIntent();

    }

    private void bindingViews() {
        mTextNavigationTitle = (TextView) findViewById(R.id.textNavigationTitle);
    }

    private void setupWithIntent() {
        Intent intent = getIntent();
        if (intent == null) return;

        if (intent.hasExtra(TYPE_ADD)) {
            String typeAdd = intent.getStringExtra(TYPE_ADD);
            if (typeAdd.equals(TYPE_ADD_COLD_STORAGE)) {
                setupForColdStorage();
            } else if (typeAdd.equals(TYPE_ADD_FROZEN_STORAGE)) {
                setupForFrozenStorage();
            }
        }
    }


    private void setupForColdStorage() {
        Log.v(TAG, "setupForColdStorage");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, AddFoodFragment.newInstance());
        transaction.commit();

        mTextNavigationTitle.setText(R.string.title_navigation_cold);
    }

    private void setupForFrozenStorage() {
        Log.v(TAG, "setupForFrozenStorage");

        mTextNavigationTitle.setText(R.string.title_navigation_frozen);
    }

    public void onPressBackButton(View v) {
        Log.v(TAG, "onPressBackButton");
        super.onBackPressed();
    }

    public void onPressSaveButton(View v) {
        Log.v(TAG, "onPressSaveButton");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onAddFoodInteraction(@INTERACTION_TYPE int type) {
        Log.v(TAG, "onAddFoodInteraction: " + type);
        switch (type) {
            case AddFoodInteraction.INTERACTION_MOVE_TO_SELECTION_VALIDATION_DATE: {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,
                        DateSelectionFragment.newInstance(DateSelectionFragment.DATE_TYPE_VALIDATION));
                transaction.addToBackStack(null);
                transaction.commit();
            }
            break;
            case AddFoodInteraction.INTERACTION_MOVE_TO_SELECTION_STORAGE_PERIOD: {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,
                        DateSelectionFragment.newInstance(DateSelectionFragment.DATE_TYPE_STORAGE_PERIOD));
                transaction.addToBackStack(null);
                transaction.commit();
            }
            break;
            case AddFoodInteraction.INTERACTION_FINISH:
                break;
            default:
            case AddFoodInteraction.INTERACTION_CANCEL:
                break;
        }

    }
}
