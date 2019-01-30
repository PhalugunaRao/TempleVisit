package com.temples.dashboard.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.shimmer.ShimmerRecyclerView;
import com.temples.R;
import com.temples.holders.BaseViewHolder;
import com.temples.holders.PlaceViewHolder;
import com.temples.holders.ViewHolderNoDataAction;
import com.temples.model.ParkModel;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.NetworkCaller;
import com.temples.utils.NoDataFoundCommonModel;
import com.temples.utils.PreferenceHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentPlaces extends Fragment implements NetworkHandlerController.ResultListener {

    RecyclerViewAdapter adapter;
    ShimmerRecyclerView recyclerView;
    String url;
    boolean isLoaded = false, isVisible;
    private View view;
    private ParkModel mParkModel;
    private PreferenceHelper prefs;
    private List<Object> dataList;
    private boolean isNoInternetViewDisplaing = false;
    String hidebutton = "";
    Context context;
    public FragmentPlaces() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new PreferenceHelper(getActivity());
        dataList = new ArrayList<>();
        isVisible = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isVisible) {
            if (isLoaded) {
                Thread thread = new Thread(new GetEventDataThread());
                thread.start();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        isLoaded = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.common_recycle_view_with_out_refresh, container, false);
        initializeView();
        setUpRecyclerView();
        setHasOptionsMenu(false);
        if (!isVisible) {
            if (!isLoaded) {
                Thread thread = new Thread(new GetEventDataThread());
                thread.start();
            }
        }
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisibleToUser) {
            if (!isLoaded) {
                Thread thread = new Thread(new GetEventDataThread());
                thread.start();
            }
        } else {
        }
    }

    private void initializeView() {
        recyclerView = view.findViewById(R.id.recyclerview_shimmer);
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewAdapter(dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.showShimmerAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(0);

    }


    public void refreshTabIfNoInternet() {
        if (isNoInternetViewDisplaing) {
            refreshTab();
        }
    }

    public void registerEventDetails() {
        isLoaded = true;
        if (NetworkHandlerController.isInternetOncheck(getContext())) {
            backgoundgthreadStoreData();
            isNoInternetViewDisplaing = false;
        } else {
            if (context == null)
                return;

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    adapter.clearData();
                    isNoInternetViewDisplaing = true;
                    adapter.add(adapter.getItemCount(), new NoDataFoundCommonModel(getString(R.string.no_internet_title), getString(R.string.no_net_message), R.drawable.ic_bad_internet, "",""));
                    recyclerView.hideShimmerAdapter();
                }
            });
        }
    }


    private void displayView() {
        isLoaded = true;
        if (mParkModel != null && mParkModel.getObjTempleEntityList() != null) {
            adapter.clearData();
            try {
                if (mParkModel.getObjTempleEntityList().size() == 0) {
                    isNoInternetViewDisplaing = false;
                    adapter.add(adapter.getItemCount(), new NoDataFoundCommonModel("No Registered Events", getString(R.string.no_my_event_title_empty), R.drawable.ic_bad_internet, "",""));
                    recyclerView.hideShimmerAdapter();
                } else {
                    for (int i = 0; i < mParkModel.getObjTempleEntityList().size(); i++) {
                        adapter.add(adapter.getItemCount(), mParkModel.getObjTempleEntityList().get(i));
                    }
                    isNoInternetViewDisplaing = false;
                    recyclerView.hideShimmerAdapter();
                }

            } catch (Exception e) {
                adapter.clearData();
                isNoInternetViewDisplaing = false;
                adapter.add(adapter.getItemCount(), new NoDataFoundCommonModel("No Registered Events", getString(R.string.no_my_event_title_empty), R.drawable.ic_bad_internet, "",""));
                recyclerView.hideShimmerAdapter();
            }
        } else {
            adapter.clearData();
            isNoInternetViewDisplaing = false;
            adapter.add(adapter.getItemCount(), new NoDataFoundCommonModel("No Registered Events", getString(R.string.no_my_event_title_empty), R.drawable.ic_bad_internet, "",""));
            recyclerView.hideShimmerAdapter();
        }
    }

    private void backgoundgthreadStoreData() {
        url = "http://www.json-generator.com/api/json/get/bVshQhYhcO?indent=2";
        /*HashMap<String, String> customHeaders = NetworkHandlerController.getInstance().getCustomHeaders(false,
                context, prefs, customerManager, "");*/
        NetworkHandlerController.getInstance().volleyGetRequestT(context, url,
                 this, null);
    }

    private void showImmunizationsData(JSONObject result) {
        mParkModel = new Gson().fromJson(result.toString(), ParkModel.class);
        try {
            if (context == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayView();
                }
            });
        } catch (Exception e) {
        }
    }

    public void refreshTab() {
        isLoaded = false;
        if (adapter != null) {
            adapter.clearData();
            recyclerView.showShimmerAdapter();
            if (isVisible) {
                if (!isLoaded) {
                    Thread thread = new Thread(new GetEventDataThread());
                    thread.start();
                }
            }
        }
    }

    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {
        if (isSuccess) {
            if (resultObject != null) {
                showImmunizationsData(resultObject);
            } else {
                if (context == null)
                    return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clearData();
                        isNoInternetViewDisplaing = false;
                        adapter.add(adapter.getItemCount(), new NoDataFoundCommonModel("No Registered Events", getString(R.string.no_my_event_title_empty), R.drawable.ic_bad_internet, "",""));
                        recyclerView.hideShimmerAdapter();
                        isLoaded = true;
                    }
                });
            }

        } else {
            NetworkResponse networkResponse = volleyError.networkResponse;

        }
    }

    private class GetEventDataThread implements Runnable {
        @Override
        public void run() {
            registerEventDetails();
        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
        private final int DATA = 0, NO_DATA = 1;
        private List<Object> items;

        public RecyclerViewAdapter(List<Object> items) {
            this.items = items;
        }

        public void add(int location, Object object) {
            try {
                items.add(location, object);
                notifyItemInserted(location);
                notifyItemRangeChanged(location, items.size());
            } catch (Exception e) {
            }
        }

        public void clearData() {
            int size = this.items.size();
            this.items.clear();
            notifyItemRangeRemoved(0, size);
        }

        @Override
        public int getItemViewType(int position) {
            if (items.get(position) instanceof ParkModel.TempleListData) {
                return DATA;
            } else if (items.get(position) instanceof NoDataFoundCommonModel) {
                return NO_DATA;
            }

            return 1;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            BaseViewHolder viewHolder = null;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            switch (viewType) {
                case DATA:
                    View v1 = inflater.inflate(R.layout.row_place_data, viewGroup, false);
                    viewHolder = new PlaceViewHolder(v1);
                    break;
                case NO_DATA:
                    View v2 = inflater.inflate(R.layout.common_empty_state_full_screen, viewGroup, false);
                    viewHolder = new ViewHolderNoDataAction(v2);
                    break;
                default:
                    break;
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder viewHolder, final int position) {
            viewHolder.bind(items.get(position), context, prefs, position);

        }
    }
}