import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P_12100_printer_queue {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        try{
            int numberCases = Integer.parseInt(reader.readLine());

            while (numberCases > 0){
                String[] firstLine = reader.readLine().split("\\s+");
                int jobs = Integer.parseInt(firstLine[0]);
                int position = Integer.parseInt(firstLine[1]);

                String[] secondLine = reader.readLine().split("\\s+");
                Map<Integer, Integer> map = new HashMap<>();

                for(int i=0; i<jobs; i++){
                    map.put(i, Integer.parseInt(secondLine[i]));
                }

                Map<Integer, Integer> ansMap = sortMapBYValue(map);

                int[] keyValues = ansMap.keySet().stream()
                        .mapToInt(Integer::intValue)
                        .toArray();

                int currentPosition = 1;

                for(int i = 0; i<jobs; i++){
                    if(keyValues[i] == position){
                        sb.append(currentPosition+"\n");
                        break;
                    }
                    currentPosition++;
                }


                numberCases--;
            }

            System.out.println(sb);

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static Map<Integer, Integer> sortMapBYValue(Map<Integer, Integer> map){
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());

        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return entryList.stream()
                .collect(Collectors.toMap(
                   Map.Entry::getKey,
                   Map.Entry::getValue,
                   (e1, e2) -> e1,
                   LinkedHashMap::new
                ));
    }
}