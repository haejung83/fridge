package haejung.com.fridge.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import haejung.com.fridge.ui.base.FragmentInteractionListener;
import haejung.com.fridge.R;
import haejung.com.fridge.ui.addfood.AddFoodActivity;


public class ColdStorageFragment extends Fragment {
    private static String TAG = ColdStorageFragment.class.getSimpleName();

    private Button btnAddFood;

    private FragmentInteractionListener mListener;

    public ColdStorageFragment() {
        // Required empty public constructor
    }

    public static ColdStorageFragment newInstance(String param1, String param2) {
        ColdStorageFragment fragment = new ColdStorageFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cold_storage, container, false);

        btnAddFood = (Button) v.findViewById(R.id.btnAddFood);
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddFoodActivity.class);
                intent.putExtra(AddFoodActivity.TYPE_ADD, AddFoodActivity.TYPE_ADD_COLD_STORAGE);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        List<FoodItem> items = new ArrayList<>();

        for(int nLoop=0;nLoop<15;nLoop++)
            items.add(createDummyItem());

        FoodAdapter adapter = new FoodAdapter(items, getContext());
        recyclerView.setAdapter(adapter);

        return v;
    }

    private FoodItem createDummyItem() {
        FoodItem item = new FoodItem();
        item.quantity = 5;
        item.name = "당근";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date now = new Date();
        item.validation = simpleDateFormat.format(now);

        return item;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class FoodItem {
        public int imgId;
        public String name;
        public int quantity;
        public String validation;
    }

    class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {
        List<FoodItem> items;
        Context context;

        public FoodAdapter(List<FoodItem> items, Context context) {
            this.items = items;
            this.context = context;
        }

        @Override
        public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout = View.inflate(context, R.layout.layout_list_food_item, null);
            FoodViewHolder viewHolder = new FoodViewHolder(layout);
            viewHolder.imageView = (ImageView) layout.findViewById(R.id.imageView);
            viewHolder.textTitle = (TextView) layout.findViewById(R.id.textTitle);
            viewHolder.textQuantity = (TextView) layout.findViewById(R.id.textQuantity);
            viewHolder.textValidation = (TextView) layout.findViewById(R.id.textValidationDate);
            return viewHolder;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public void onBindViewHolder(FoodViewHolder holder, int position) {
            FoodItem item = items.get(position);

            if(item.imgId != 0)
                holder.imageView.setImageResource(item.imgId);

            if(!TextUtils.isEmpty(item.name))
                holder.textTitle.setText(item.name);

            holder.textQuantity.setText(String.valueOf(item.quantity));

            if(!TextUtils.isEmpty(item.validation))
                holder.textValidation.setText(item.validation);
        }
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textTitle;
        public TextView textQuantity;
        public TextView textValidation;

        public FoodViewHolder(View itemView) {
            super(itemView);
        }
    }

}
