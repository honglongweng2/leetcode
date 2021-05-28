import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UndergroundSystem {
    private Map<Integer, Pair<String, Integer>> userData;
    private Map<String, List<Integer>> timeData;

    public UndergroundSystem() {
        userData = new HashMap<>();
        timeData = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        userData.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> temp = userData.get(id);
        List<Integer> entry;
        if (timeData.containsKey(temp.getKey() + '-' + stationName)) {
            entry = timeData.get(temp.getKey() + '-' + stationName);
        } else {
            entry = new ArrayList<>();
        }
        entry.add(t - temp.getValue());
        timeData.put(temp.getKey() + '-' + stationName, entry);
        userData.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        double result = 0;
        if (timeData.containsKey(startStation + '-' + endStation)) {
            result = timeData.get(startStation + '-' + endStation).stream().mapToDouble(val -> val).average().orElse(0);
        }
        return result;
    }

    public static void main(String[] args) {
        UndergroundSystem system = new UndergroundSystem();
        system.checkIn(10, "Leyton", 3);
        system.checkIn(5, "Leyton", 10);
        system.checkOut(10, "Paradise", 8);
        system.checkOut(5, "Paradise", 17);
        System.out.println(system.getAverageTime("Leyton", "Paradise"));
    }
}
