import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String, HashMap<String, int[]>> origin_destination_map = new HashMap<>();
        String origin, destination;
        int distance;

        String[][] tableEntrySet = {
                {"a", "b", "10"},
                {"b", "a", "15"},
                {"a", "b", "22"},
                {"c", "d", "10"},
                {"c", "d", "22"},
                {"d", "c", "30"},
                {"d", "c", "34"},
                {"e", "f", "10"},
                {"f", "e", "20"}
        };

        for (String[] e : tableEntrySet) {
            origin = e[0];
            destination = e[1];
            distance = Integer.parseInt(e[2]);

            if (origin_destination_map.containsKey(origin)) {
                HashMap<String, int[]> destination_distance_map = origin_destination_map.get(origin);

                if (destination_distance_map.containsKey(destination)) {
                    int[] dist_count_array = destination_distance_map.get(destination);
                    dist_count_array[0] += distance;     // dist_count_array = [sumOfDistance, count]
                    dist_count_array[1] += 1;            // increment count
                } else {
                    int[] dist_count_array = {distance, 1};        //
                    destination_distance_map.put(destination, dist_count_array);
                }
            } else if (origin_destination_map.containsKey(destination)) {
                HashMap<String, int[]> destination_distance_map = origin_destination_map.get(destination);

                if (destination_distance_map.containsKey(origin)) {
                    int[] dist_count_array = destination_distance_map.get(origin);
                    dist_count_array[0] += distance;     // dist_count_array = [sumOfDistance, count]
                    dist_count_array[1] += 1;            // increment count
                } else {
                    int[] dist_count_array = {distance, 1};        //
                    destination_distance_map.put(origin, dist_count_array);
                }
            } else {
                HashMap<String, int[]> destination_distance_map = new HashMap<>();
                int[] dist_count_array = {distance, 1};
                destination_distance_map.put(destination, dist_count_array);
                origin_destination_map.put(origin, destination_distance_map);
            }
//            System.out.println("data = " + origin_destination_map);
        }

        LinkedList<String[]> output = new LinkedList<>();

        for (String ori : origin_destination_map.keySet()) {
            HashMap<String, int[]> destination_distance_map = origin_destination_map.get(ori);

            String[] e = new String[3];
            e[0] = ori;
            for (String dest : destination_distance_map.keySet()) {
                int[] dist_count_array = destination_distance_map.get(dest);
                e[1] = dest;
                e[2] = "" + dist_count_array[0]/(float)dist_count_array[1];
            }
            output.add(e);
        }

        for (String[] L : output) {
            System.out.println(Arrays.toString(L));
        }
    }
}