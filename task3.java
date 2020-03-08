import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {


        File cashFile1 = new File(args[0], "Cash1.txt");
        File cashFile2 = new File(args[0], "Cash2.txt");
        File cashFile3 = new File(args[0], "Cash3.txt");
        File cashFile4 = new File(args[0], "Cash4.txt");
        File cashFile5 = new File(args[0], "Cash5.txt");


        ArrayList<Float> cash1 = new ArrayList<>();
        readFile(cash1, cashFile1);
        ArrayList<Float> cash2 = new ArrayList<>();
        readFile(cash2, cashFile2);
        ArrayList<Float> cash3 = new ArrayList<>();
        readFile(cash3, cashFile3);
        ArrayList<Float> cash4 = new ArrayList<>();
        readFile(cash4, cashFile4);
        ArrayList<Float> cash5 = new ArrayList<>();
        readFile(cash5, cashFile5);

        ArrayList<Float> summ = new ArrayList<>();
        int maxIndex = 0;
        float maxValue = 0f;

        for (int i = 0; i < cash1.size(); i++) {
            summ.add(cash1.get(i) + cash2.get(i) + cash3.get(i) + cash4.get(i) + cash5.get(i));
        }

        for (int i = 0; i < summ.size(); i++) {
            if (summ.get(i) > maxValue) {
                maxValue = summ.get(i);
                maxIndex = i;
            }
        }

        System.out.println(maxIndex + 1);

    }

    public static List readFile( ArrayList <Float> cash, File file) {

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                cash.add(Float.parseFloat(str));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return cash;
    }
}
