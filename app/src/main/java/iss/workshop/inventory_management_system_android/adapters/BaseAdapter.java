package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.helper.Pageable;

public abstract class BaseAdapter extends RecyclerView.Adapter {

    private static final String TAG = BaseAdapter.class.getSimpleName();

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_RETRY = 1;
    private static final int VIEW_ITEM = 2;
    private static final int VIEW_HEADER = 3;
    private static final int VIEW_EMPTY = 4;

    private List<Pageable> itemsList;

    public BaseAdapter(List<Pageable> items) {
        this.itemsList = items == null ? new ArrayList<Pageable>() : items;
    }

    public BaseAdapter() {
        this(null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_LOADING) {
            return new ProgressHolder(prepareLoadingView(parent.getContext()));
        } else if (viewType == VIEW_RETRY) {
            RetryFooter retryFooter = (RetryFooter) itemsList.get(itemsList.size() - 1);
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(retryFooter.getRetryLayoutId(), parent, false);
            return new RetryHolder(view, retryFooter.getRetryActionTriggerViewId());

        } else if (viewType == VIEW_HEADER) {
            return onCreateCustomHeaderViewHolder(parent, viewType);
        } else if (viewType == VIEW_EMPTY) {
            EmptyView customView = (EmptyView) itemsList.get(itemsList.size() - 1);
            View view = LayoutInflater.from(parent.getContext()).inflate(customView.getCustomLayoutId(), parent, false);
            return new EmptyViewHolder(view);
        } else {
            return onCreateCustomViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == VIEW_LOADING) {
            ((ProgressHolder) holder).progressBar.setIndeterminate(true);
        } else if (viewType == VIEW_RETRY) {
            RetryFooter footer = (RetryFooter) itemsList.get(position);
            ((RetryHolder) holder).setOnRetryListener(footer.getOnRetryListener());
        } else if (viewType == VIEW_HEADER) {
            onBindCustomHeaderViewHolder(holder, position);
        } else if (viewType == VIEW_EMPTY) {
            Log.d(TAG, "onBindViewHolder: Skipping View Binding of EmptyView Item");
        } else {
            onBindCustomViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Pageable item = itemsList.get(position);
        if (item instanceof ProgressFooter) {
            return VIEW_LOADING;
        } else if (item instanceof RetryFooter) {
            return VIEW_RETRY;
        } else if (item instanceof RecyclerHeader) {
            return VIEW_HEADER;
        } else if (item instanceof EmptyView) {
            return VIEW_EMPTY;
        } else {
            return VIEW_ITEM;
        }
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    return type == BaseAdapter.VIEW_ITEM ? 1 : gridManager.getSpanCount();
                }
            });
        }
    }

    /**
     * Removes the last item if it is either ProgressFooter or RetryFooter
     */
    public final void clearFooter() {
        if (itemsList != null && !itemsList.isEmpty()) {
            Pageable pageable = itemsList.get(itemsList.size() - 1);
            if (pageable instanceof RetryFooter || pageable instanceof ProgressFooter || pageable instanceof EmptyView) {
                itemsList.remove(itemsList.size() - 1);
                notifyItemRemoved(itemsList.size());
            }
        }
    }

    /**
     * Add one pageable data to a specific position in adapter
     *
     * @param data     Item to be inserted
     * @param position Position where to insert
     */
    public final void add(Pageable data, int position) {
        if (0 <= position && position > itemsList.size()) {
            itemsList.add(position, data);
            notifyItemInserted(position);
        } else {
            throw new ArrayIndexOutOfBoundsException("Inserted position most greater than 0 and less than data size");
        }
    }

    /**
     * Add one pageable data to last position in adapter
     *
     * @param data Item to be inserted
     */
    public final void add(Pageable data) {
        itemsList.add(data);
        notifyItemInserted(itemsList.size());
    }

    public final void add(int position, Pageable data) {
        itemsList.add(position,data);
        notifyItemInserted(position);
    }

    /**
     * Add list of pageable items in adapter
     *
     * @param newItems List of items to be inserted
     */
    public final void add(List<Pageable> newItems) {
        itemsList.addAll(newItems);
        notifyItemRangeInserted(itemsList.size() - newItems.size(), newItems.size());
    }

    public final void update(Pageable newData,int position){
        if(0 <= position && position < itemsList.size()){
            itemsList.set(position,newData);
            notifyItemChanged(position);
        }else {
            throw new ArrayIndexOutOfBoundsException("Inserted position most greater than 0 and less than data size");
        }
    }

    public final void move(int fromPosition, int toPosition) {
        // update data array
        Pageable item = itemsList.get(fromPosition);
        itemsList.remove(fromPosition);
        itemsList.add(toPosition, item);
        // notify adapter
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * Remove one pagable data from a specific position in adapter
     *
     * @param position Position to be removed
     */
    public final void remove(int position) {
        if (0 <= position && position < itemsList.size()) {
            itemsList.remove(position);
            notifyItemRemoved(position);
        } else {
            throw new ArrayIndexOutOfBoundsException("Inserted position most greater than 0 and less than data size");
        }
    }

    /**
     * Remove a range of pagable data from a specific position to a specific position
     *
     * @param startPosition Start position to remove
     * @param endPosition   End position to remove
     */
    public final void remove(int startPosition, int endPosition) {
        boolean isValidStart = 0 <= startPosition && startPosition < itemsList.size();
        boolean isValidEnd = 0 <= endPosition && endPosition < itemsList.size();
        if (isValidStart && isValidEnd) {
            Log.i("BaseRCAdapter", "remove: Removed");

//            itemsList.subList(startPosition, endPosition).clear();
//            notifyItemRangeRemoved(startPosition, endPosition);

            int count = endPosition - startPosition + 1;
            itemsList.subList(startPosition, endPosition + 1).clear();
            notifyItemRangeRemoved(startPosition, count);
        }
    }

    /**
     * Add HeaderView to the Adapter
     *
     * @param headerData Data to be shown in HeaderView
     */
    public final <T> void addHeader(T headerData) {
        add(new RecyclerHeader<>(headerData));
    }

    /**
     * Add LoadingView to the Adapter
     */
    public final void showLoading() {
        add(new ProgressFooter());
    }

    /**
     * Add RetryView to the adapter
     *
     * @param retryLayoutId      Layout ID of the retryView
     * @param retryTriggerViewId Id of the retryView element to trigger retry action on click
     * @param retryListener      RetryListener
     */
    public final void showRetry(@LayoutRes int retryLayoutId, @IdRes int retryTriggerViewId, OnRetryListener retryListener) {
        add(new RetryFooter(retryListener, retryLayoutId, retryTriggerViewId));
    }

    /**
     * Add EmptyView to the Adapter
     */
    public final void showEmptyView(int customLayout) {
        add(new EmptyView(customLayout));
    }

    /**
     * Clear all items in the adapter
     */
    public final void clear() {
        itemsList.clear();
        notifyDataSetChanged();
    }

    public final int getHeaderPosition() {
        int position = -1;
        for (Pageable p : getItemsList()) {
            if (p instanceof RecyclerHeader) {
                position = getItemsList().indexOf(p);
                return position;
            }
        }
        return position;
    }

    /**
     * Get List of Items in the adapter
     *
     * @return List of Items in the adapter
     */
    public final List<Pageable> getItemsList() {
        return itemsList;
    }

    private View prepareLoadingView(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setPadding(5, 5, 5, 5);
        progressBar.setTag(123);
        relativeLayout.addView(progressBar, relativeLayoutParams);
        return relativeLayout;
    }

    /*------------------------------------Abstract Methods to be implemented by child classes------------------------------------*/

    /*To perform onCreateViewHolder tasks for custom item*/
    protected abstract RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType);

    /*To perform onBindViewHolder tasks for custom item*/
    protected abstract void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position);

    /*To perform onCreateViewHolder tasks for HeaderView*/
    protected abstract RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType);

    /*To perform onBindViewHolder tasks for Header*/
    protected abstract void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    /*------------------------------------Required Classes and Interface------------------------------------*/
    public interface OnRetryListener {
        void onRetry();
    }

    public static class ProgressFooter implements Pageable {
    }

    public static class EmptyView implements Pageable {

        @LayoutRes
        private int customLayout;

        public EmptyView(int customLayout) {
            this.customLayout = customLayout;
        }

        public int getCustomLayoutId() {
            return customLayout;
        }
    }

    public static class RetryFooter implements Pageable {
        private OnRetryListener onRetryListener;

        @LayoutRes
        private int retryLayoutId;

        @IdRes
        private int retryActionTriggerViewId;

        public RetryFooter(OnRetryListener onRetryListener, @LayoutRes int retryLayoutId, @IdRes int retryActionTriggerViewId) {
            this.onRetryListener = onRetryListener;
            this.retryLayoutId = retryLayoutId;
            this.retryActionTriggerViewId = retryActionTriggerViewId;
        }

        OnRetryListener getOnRetryListener() {
            return onRetryListener;
        }

        public int getRetryLayoutId() {
            return retryLayoutId;
        }

        public int getRetryActionTriggerViewId() {
            return retryActionTriggerViewId;
        }
    }

    public static class RecyclerHeader<T> implements Pageable {
        private T headerData;

        public RecyclerHeader(T headerData) {
            this.headerData = headerData;
        }

        public T getHeaderData() {
            return headerData;
        }
    }

    /*------------------------------------View Holders (Progress Holder and Retry Holder)------------------------------------*/

    private class ProgressHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        ProgressHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewWithTag(123);
        }
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class RetryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnRetryListener onRetryListener;

        RetryHolder(View itemView, @IdRes int retryTrigerViewId) {
            super(itemView);
            if (retryTrigerViewId == 0) {
                itemView.setOnClickListener(this);
            } else {
                itemView.findViewById(retryTrigerViewId).setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View v) {
            if (onRetryListener != null) {
                clearFooter();
                onRetryListener.onRetry();
            }
        }

        void setOnRetryListener(OnRetryListener onRetryListener) {
            this.onRetryListener = onRetryListener;
        }
    }

}
