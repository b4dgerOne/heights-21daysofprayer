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
//    private final String TV_TITLE_ID = "tv_day_title_card";
//    private final String TV_FOCUS_ID = "tv_day_focus_card";
//    private final String IV_BANNER_ID = "iv_day_banner_card";
    private Context mContext;
    private int numberOfDays;
    private PrayerDay[] mPrayerDays;

    public PrayerDayAdapter(Context context, PrayerDay[] prayerDays){
        mContext = context;
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

    class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView cardBannerImage;
        TextView cardTitleText;
        TextView cardFocusText;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardTitleText = (TextView) itemView.findViewById(R.id.tv_day_title_card);
            cardFocusText = (TextView) itemView.findViewById(R.id.tv_day_focus_card);
            cardBannerImage = (ImageView) itemView.findViewById(R.id.iv_day_banner_card);
        }

        void bind(PrayerDay prayerDay) {
            int imageID = mContext.getResources().getIdentifier(prayerDay.getDayImageRef(),"drawable",mContext.getPackageName());
            cardBannerImage.setImageResource(imageID);
            cardTitleText.setText(prayerDay.getDayTitle().toUpperCase());
            cardFocusText.setText(prayerDay.getDayFocus());
        }
    }
}
