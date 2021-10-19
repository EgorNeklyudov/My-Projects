package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.BiPredicate;

public class JsonUtils {

    public static boolean isJson(String string) {
        try {
            new JSONObject(string);
        } catch (JSONException e1) {
            try {
                new JSONArray(string);
            } catch (JSONException e2) {
                return false;
            }
        }
        return true;
    }

    public static boolean isJson(Object response) {
        try {
            new JSONObject(response);
        } catch (JSONException e1) {
            try {
                new JSONArray(response);
            } catch (JSONException e2) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSortedAscendingOrder(JSONArray jsonArray, BiPredicate<JSONObject, JSONObject> predicate) {
        boolean isSorted = true;
        for (int i = 0; i < jsonArray.length() - 1; i++) {
            if (!predicate.test(jsonArray.getJSONObject(i), jsonArray.getJSONObject(i + 1))) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public static boolean isArraySorted(JSONArray jsonArray, BiPredicate<JSONObject, JSONObject> negativePredicate) {
        return isSortedAscendingOrder(jsonArray, negativePredicate);
    }
}
