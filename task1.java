import java.io.*;
import java.util.*;


public class Task1 {
    public static void main(String[] args) throws IOException {
        List<Double> numbers = new ArrayList<Double>();
        File file = new File(args[0]);

        readFile(numbers, file);
        Collections.sort(numbers);

        System.out.println(String.format("%.2f", percentile(numbers, 90)).replace(",", "."));
        System.out.println(String.format("%.2f", median(numbers)).replace(",", "."));
        System.out.println(String.format("%.2f", maxValue(numbers)).replace(",", "."));
        System.out.println(String.format("%.2f", minValue(numbers)).replace(",", "."));
        System.out.println(String.format("%.2f", avarage(numbers)).replace(",", "."));
    }

    public static List readFile(List<Double> list, File file) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Double.parseDouble(text));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        return list;
    }

    public static double percentile(List<Double> list, double percentile) {
        if (percentile / 100 == 1) {
            return list.get(list.size() - 1);
        } else if (percentile / 100 == 0) {
            return list.get(0);
        } else {
            double x = (percentile / 100 * (list.size() - 1)) + 1;
            int n = (int) x;
            return list.get(n - 1) + ((x - n) * (list.get(n) - list.get(n - 1)));
        }
    }


        public static double median (List < Double > list) {
            Collections.sort(list);
            if (list.size() % 2 == 0) {
                return (list.get(list.size() / 2) + list.get((list.size() / 2) - 1)) / 2;
            } else {
                return list.get(list.size() / 2);
            }
        }

        public static double maxValue (List < Double > list) {
            return list.get(list.size() - 1);
        }

        public static double minValue (List < Double > list) {
            return list.get(0);
        }

        public static double avarage (List < Double > list) {
            double sum = 0;
            for (double value : list) {
                sum += value;
            }
            return sum / list.size();
        }


    }

