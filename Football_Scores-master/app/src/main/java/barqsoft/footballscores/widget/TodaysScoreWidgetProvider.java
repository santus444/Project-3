package barqsoft.footballscores.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import barqsoft.footballscores.R;

/**
 * Created by santosh on 10/18/15.
 */
public class TodaysScoreWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_today);
            Intent serviceIntent = new Intent(context, TodaysScoreRemoteViewService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            remoteViews.setRemoteAdapter(appWidgetId, R.id.widget_todays_listview, serviceIntent);
            remoteViews.setEmptyView(R.id.widget_todays_listview, R.id.widget_listview_scores_empty);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }
}
