package dev.uniayr.pricer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Scanner console;

    public static void main(String[] args) throws IOException {
        console = new Scanner(System.in);
        computation();
    }

    private static void computation() throws IOException {
        System.out.println("Файл с тарифами: ");
        String file1 = readStringConsole();
        List<Tarif> tarifs = FileUtils.readTarifs(file1);

        System.out.println("Файл с расходами: ");
        String file2 = readStringConsole();
        List<Consumption> consumption = FileUtils.readConsumption(file2);

        List<String> table1 = new ArrayList<>();
        List<String> table2 = new ArrayList<>();

        table1.add(String.format("\n%10.10s %12.10s %12.10s %10.10s",
                "Статья", "План", "Расход", "Процент"));
        table2.add(String.format("\n%10.20s %10.20s %12.12s", "Статья", "Продукт", "Расход"));


        for (Tarif tarif : tarifs) {

            List<Consumption> cons = consumption.stream()
                    .filter(p -> p.getItem().equalsIgnoreCase(tarif.getItem()))
                    .collect(Collectors.toList());

            double sum = cons.stream().mapToDouble(p -> p.getPrice()).sum();
            double rate = tarif.getPrice() - sum;
            double percent = 100 - rate / tarif.getPrice() * 100D;

            String str = String.format("%10.20s %12.2f %12.2f %10.2f", tarif.getItem(), tarif.getPrice(), sum, percent);
            table1.add(str);

            if (rate < 0) {
                cons = sort(cons);
                for (Consumption con : cons) {
                    str = String.format("%10.20s %10.20s %12.2f", con.getItem(), con.getProduct(), con.getPrice());
                    table2.add(str);
                }
            }

        }

        for (String i : table1)
            System.out.println(i);

        for (String i : table2)
            System.out.println(i);

        clearFile("out.txt");
        writeToFile("out.txt", table1, table2);
    }

    private static List<Consumption> sort(List<Consumption> list) {
        Comparator<Consumption> comparator = (a, b) -> a.getPrice() < b.getPrice() ? 1 : -1;
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }

    private static void clearFile(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                FileUtils.getFileFromResources(path).getPath()
        ));
        writer.write("");
        writer.close();
    }

    private static void writeToFile(String path, List<String>... tables) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                FileUtils.getFileFromResources(path).getPath()
        ));

        for (List<String> table : tables)
            for (String s : table)
                writer.append(s + "\n");
        writer.close();
    }

    private static String readStringConsole() {
        if (console.hasNext()) {
            return console.next();
        } else {
            System.out.println("Строка введена неккоректно.");
            return readStringConsole();
        }
    }
}
