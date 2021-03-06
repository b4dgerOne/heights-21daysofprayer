package com.theorytwelve.heights.a21daysofprayer.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.theorytwelve.heights.a21daysofprayer.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrayerDayAdapter extends RecyclerView.Adapter<PrayerDayAdapter.CardViewHolder> {
    private static final String TAG = PrayerDayAdapter.class.getSimpleName();
    private final PrayerDayClickListener mPrayerDayClickListener;
//    private final String TV_TITLE_ID = "tv_day_title_card";
//    private final String TV_FOCUS_ID = "tv_day_focus_card";
//    private final String IV_BANNER_ID = "iv_day_banner_card";
    private Context mContext;
    private int numberOfDays;
    private PrayerDay[] mPrayerDays;

    public interface PrayerDayClickListener {
        void onPrayerDayClick(PrayerDay prayerDay);
    }

    public PrayerDayAdapter(Context context, PrayerDay[] prayerDays, PrayerDayClickListener listener){
        mContext = context;
        mPrayerDayClickListener = listener;
        mPrayerDays = prayerDays;
        numberOfDays = prayerDays.length;
    }

    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.day_scroll_display;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId,parent,false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(mPrayerDays[position]);
    }

    @Override
    public int getItemCount() {
        return numberOfDays;
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cardBannerImage;
        TextView cardTitleText;
        TextView cardFocusText;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            cardTitleText = (TextView) itemView.findViewById(R.id.tv_day_title_card); // title will show date
            cardFocusText = (TextView) itemView.findViewById(R.id.tv_day_focus_card);
            cardBannerImage = (ImageView) itemView.findViewById(R.id.iv_day_banner_card);
        }

        void bind(PrayerDay prayerDay) {
            int imageID = mContext.getResources().getIdentifier(prayerDay.getDayImageRef(),"drawable",mContext.getPackageName());
            cardBannerImage.setImageResource(imageID);
            cardTitleText.setText(prayerDay.getFormattedDate()); // setting new "date" field to show as the day title
            cardFocusText.setText(prayerDay.getDayFocus());
        }

        @Override
        public void onClick(View view) {
            int clickedDay = getAdapterPosition();
            mPrayerDayClickListener.onPrayerDayClick(mPrayerDays[clickedDay]);
        }
    }
}
