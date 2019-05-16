package dev.uniayr.pricer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    public static List<Tarif> readTarifs(String path) {
        List<Tarif> tarifs = new ArrayList<>();

        for (String data : readFile(path)) {
            Scanner scan = new Scanner(data);

            String item;
            double price;

            if (scan.hasNext()) {
                 item = scan.next();
            } else {
                System.out.printf("Неудалось прочесть название статьи: \n%s\n", data);
                continue;
            }

            if (scan.hasNextDouble()) {
                price = scan.nextDouble();
            } else {
                System.out.printf("Неудалось прочесть цену: \n%s\n", data);
                continue;
            }

            tarifs.add(new Tarif(item, price));
        }
        return tarifs;
    }

    public static List<Consumption> readConsumption(String path) {
        List<Consumption> consumptions = new ArrayList<>();

        for (String data : readFile(path)) {
            Scanner scan = new Scanner(data);

            String item;
            String product;
            double price;

            if (scan.hasNext()) {
                item = scan.next();
            } else {
                System.out.printf("Неудалось прочесть название статьи: \n%s\n", data);
                continue;
            }

            if (scan.hasNext()) {
                product = scan.next();
            } else {
                System.out.printf("Неудалось прочесть название продукта: \n%s\n", data);
                continue;
            }

            if (scan.hasNextDouble()) {
                price = scan.nextDouble();
            } else {
                System.out.printf("Неудалось прочесть цену: \n%s\n", data);
                continue;
            }

            consumptions.add(new Consumption(item, product, price));
        }
        return consumptions;
    }

    public static File getFileFromResources(String fileName) {

        ClassLoader classLoader = Main.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    private static List<String> readFile(String path) {
        List<String> list = new ArrayList<>();
        File file = getFileFromResources(path);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                list.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
