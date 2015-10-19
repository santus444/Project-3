package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilies;
import barqsoft.footballscores.scoresAdapter;

/**
 * Created by santosh on 10/18/15.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)

public class TodaysScoreRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ScoresRemoteViewsFactory(this, intent);
    }

    class ScoresRemoteViewsFactory implements RemoteViewsFactory {
        private final String LOG_TAG = ScoresRemoteViewsFactory.class.getSimpleName();
        private Context mContext;
        private Cursor mCursor;
        private int mAppWidgetId;
        private String[] mFragmentdate = new String[1];

        public ScoresRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            Log.v(LOG_TAG, "10 days app widget id: " + mAppWidgetId);
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            if (mCursor != null) {
                mCursor.close();
            }
            Date fragmentdate = new Date(System.currentTimeMillis());//+((i-2)*86400000));
            SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
            mFragmentdate[0] = mformat.format(fragmentdate);

            //String sortOrder = WeatherContract.WeatherEntry.COLUMN_DATE + " ASC";

            Uri baseUri;
            baseUri = DatabaseContract.scores_table.buildScoreWithDate();
            final long token = Binder.clearCallingIdentity();
            try {
                mCursor = mContext.getContentResolver().query(baseUri, null, null, mFragmentdate, null);
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }

        @Override
        public void onDestroy() {
            if (mCursor != null) {
                mCursor.close();
            }
        }

        @Override
        public int getCount() {
            return mCursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_scores_list_item);
            if (mCursor.moveToPosition(position)) {
//                String highTemp = Utility.formatTemperature(mContext, mCursor.getFloat(ForecastFragment.COL_WEATHER_MAX_TEMP));
//                String lowTemp = Utility.formatTemperature(mContext, mCursor.getDouble(ForecastFragment.COL_WEATHER_MIN_TEMP));
//                remoteViews.setTextViewText(R.id.list_item_high_textview, highTemp);
                remoteViews.setTextViewText(R.id.home_name, mCursor.getString(scoresAdapter.COL_HOME));
                remoteViews.setTextViewText(R.id.away_name, mCursor.getString(scoresAdapter.COL_AWAY));
                remoteViews.setTextViewText(R.id.data_textview, mCursor.getString(scoresAdapter.COL_MATCHTIME));
                remoteViews.setTextViewText(R.id.score_textview, Utilies.getScores(mCursor.getInt(scoresAdapter.COL_HOME_GOALS), mCursor.getInt(scoresAdapter.COL_AWAY_GOALS), mContext));

//                mHolder.home_name.setText(cursor.getString(COL_HOME));
//                mHolder.away_name.setText(cursor.getString(COL_AWAY));
//                mHolder.date.setText(cursor.getString(COL_MATCHTIME));
//                mHolder.score.setText(Utilies.getScores(cursor.getInt(COL_HOME_GOALS), cursor.getInt(COL_AWAY_GOALS), context));
//                mHolder.match_id = cursor.getDouble(COL_ID);
//                mHolder.home_crest.setImageResource(Utilies.getTeamCrestByTeamName(
//                        cursor.getString(COL_HOME)));
//                mHolder.away_crest.setImageResource(Utilies.getTeamCrestByTeamName(
//                        cursor.getString(COL_AWAY)
            }
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
