package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import java.util.List;
import java.util.Scanner;

public class SearchByParametersRange extends Command{

    private ElectricalDevices devices;

    public SearchByParametersRange(ElectricalDevices devices) {
        this.devices = devices;
    }

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        List<ElectricalDevice> devicesList = devices.getDevices();
        // Пошук за роком
        System.out.println("Введіть мінімальний рік: ");
        int minYear = scanner.nextInt();
        System.out.println("Введіть максимальний рік: ");
        int maxYear = scanner.nextInt();
        List<ElectricalDevice> byYearRange = devices.findDevicesByYearRange(minYear, maxYear);
        printResults("Пошук за роком", byYearRange);

        // Пошук за країною
        System.out.println("Введіть країну-виробника: ");
        scanner.nextLine();  // Очищення буфера
        String country = scanner.nextLine();
        List<ElectricalDevice> byCountry = devices.findDevicesByCountry(country);
        printResults("Пошук за країною", byCountry);

        // Пошук за потужністю
        System.out.println("Введіть мінімальну потужність: ");
        double minPower = scanner.nextDouble();
        System.out.println("Введіть максимальну потужність: ");
        double maxPower = scanner.nextDouble();
        List<ElectricalDevice> byPowerRange = devices.findDevicesInPowerRange(minPower, maxPower);
        printResults("Пошук за потужністю", byPowerRange);

        // Пошук за вагою
        System.out.println("Введіть мінімальну вагу: ");
        double minWeight = scanner.nextDouble();
        System.out.println("Введіть максимальну вагу: ");
        double maxWeight = scanner.nextDouble();
        List<ElectricalDevice> byWeightRange = devices.findDevicesByWeightRange(minWeight, maxWeight);
        printResults("Пошук за вагою", byWeightRange);

        // Пошук за ціною
        System.out.println("Введіть мінімальну ціну: ");
        double minPrice = scanner.nextDouble();
        System.out.println("Введіть максимальну ціну: ");
        double maxPrice = scanner.nextDouble();
        List<ElectricalDevice> byPriceRange = devices.findDevicesByPriceRange(minPrice, maxPrice);
        printResults("пошуку за діапазоном ціни від " + minPrice + " до " + maxPrice, byPriceRange);
        // Перевірка наявності спільних елементів
        checkAndPrintCommonElements(byYearRange, byCountry, byPowerRange, byWeightRange, byPriceRange);
    }

    private void printResults(String searchType, List<ElectricalDevice> results) {
        System.out.println("Результати " + searchType + ":");
        if (results.isEmpty()) {
            System.out.println("Немає результатів.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i+1) + "." + results.get(i));
            }
        }
        System.out.println();
    }

    private void checkAndPrintCommonElements(List<ElectricalDevice> list, List<ElectricalDevice>... resultLists) {
        boolean commonElementsExist = true;
        for (List<ElectricalDevice> resultList : resultLists) {
            if (resultList.isEmpty()) {
                commonElementsExist = false;
                break;
            }
        }

        if (commonElementsExist) {
            System.out.println("Є спільні елементи для всіх пошуків:");
            for (ElectricalDevice device : list) {
                boolean isCommonElement = true;
                for (List<ElectricalDevice> resultList : resultLists) {
                    if (!resultList.contains(device)) {
                        isCommonElement = false;
                        break;
                    }
                }
                if (isCommonElement) {
                    System.out.println(device);
                }
            }
        } else {
            System.out.println("Немає спільних елементів для всіх пошуків.");
        }
    }

    @Override
    public String commandName(){
        return "Пошук за діапазоном параметрів.";
    }
}
