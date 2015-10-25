package barqsoft.footballscores;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies {
    public static final int SERIE_A = 357;
    public static final int PREMIER_LEGAUE = 354;
    public static final int CHAMPIONS_LEAGUE = 362;
    public static final int PRIMERA_DIVISION = 358;
    public static final int BUNDESLIGA = 351;

    public static String getLeague(int league_num, Context context) {
        switch (league_num) {
            case SERIE_A:
                return context.getString(R.string.seriaa);
            case PREMIER_LEGAUE:
                return context.getString(R.string.premierleague);
            case CHAMPIONS_LEAGUE:
                return context.getString(R.string.champions_league);
            case PRIMERA_DIVISION:
                return context.getString(R.string.primeradivison);
            case BUNDESLIGA:
                return context.getString(R.string.bundesliga);
            default:
                return context.getString(R.string.unknown_league);
        }
    }

    public static String getMatchDay(int match_day, int league_num, Context context) {
        if (league_num == CHAMPIONS_LEAGUE) {
            if (match_day <= 6) {
                return context.getString(R.string.group_stage_text) + ", " + context.getString(R.string.matchday_text) + " : 6";
            } else if (match_day == 7 || match_day == 8) {
                return context.getString(R.string.first_knockout_round);
            } else if (match_day == 9 || match_day == 10) {
                return context.getString(R.string.quarter_final);
            } else if (match_day == 11 || match_day == 12) {
                return context.getString(R.string.semi_final);
            } else {
                return context.getString(R.string.final_text);
            }
        } else {
            return context.getString(R.string.matchday_text) + " : " + String.valueOf(match_day);
        }
    }

    public static String getScores(int home_goals, int awaygoals, Context context) {
        if (home_goals < 0 || awaygoals < 0) {
            return " - ";
        } else {
            Configuration config = context.getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                return context.getString(R.string.format_score, awaygoals, home_goals);
            }
            return context.getString(R.string.format_score, home_goals, awaygoals);
        }
    }

    public static String getGoalsContentDescription(String teamName, int goals, Context context) {
        if (goals == 0) {
            return teamName + " " + context.getString(R.string.scored_no_goal);
        } else if (goals == 1) {
            return teamName + " " + context.getString(R.string.scored_one_goal);
        } else if (goals < 0) {
            return "\u00A0";
        } else {
            return teamName + context.getString(R.string.scored) + goals + context.getString(R.string.goals);
        }
    }

    public static int getTeamCrestByTeamName(String teamname) {
        if (teamname == null) {
            return R.drawable.no_icon;
        }
        switch (teamname) { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC":
                return R.drawable.arsenal;
            case "Manchester United FC":
                return R.drawable.manchester_united;
            case "Swansea City":
                return R.drawable.swansea_city_afc;
            case "Leicester City":
                return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC":
                return R.drawable.everton_fc_logo1;
            case "West Ham United FC":
                return R.drawable.west_ham;
            case "Tottenham Hotspur FC":
                return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion":
                return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC":
                return R.drawable.sunderland;
            case "Stoke City FC":
                return R.drawable.stoke_city;
            default:
                return R.drawable.no_icon;
        }
    }

    public static CharSequence getMatchStartTime(int homeGoals, int awayGoals, String matchTime, Context context) {
        if (homeGoals > -1 && awayGoals > -1) {
            return context.getString(R.string.match_started_at_text) + " " + matchTime;
        } else {
            return context.getString(R.string.match_not_yet_started_text) + " " + matchTime;
        }
    }
}
