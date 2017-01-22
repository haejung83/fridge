package haejung.com.fridge.ui.addfood;

import android.support.annotation.IntDef;

/**
 * Created by ka on 2017. 1. 22..
 */

public interface AddFoodInteraction {
    int INTERACTION_MOVE_TO_SELECTION_VALIDATION_DATE = 0x100;
    int INTERACTION_MOVE_TO_SELECTION_STORAGE_PERIOD = 0x101;
    int INTERACTION_FINISH = 0x102;
    int INTERACTION_CANCEL = 0x103;

    @IntDef({INTERACTION_MOVE_TO_SELECTION_VALIDATION_DATE, INTERACTION_MOVE_TO_SELECTION_STORAGE_PERIOD, INTERACTION_FINISH, INTERACTION_CANCEL})
    @interface INTERACTION_TYPE {}

    void onAddFoodInteraction(@INTERACTION_TYPE int type);
}
