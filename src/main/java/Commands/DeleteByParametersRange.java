package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import java.util.List;
import java.util.Scanner;

public class DeleteByParametersRange extends Command{

    private ElectricalDevices devices;

    public DeleteByParametersRange(ElectricalDevices devices) {
        this.devices = devices;
    }

    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);

        // Пошук за роком
        System.out.println("Введіть мінімальний рік: ");
        int minYear = scanner.nextInt();
        System.out.println("Введіть максимальний рік: ");
        int maxYear = scanner.nextInt();
        List<ElectricalDevice> byYearRange = devices.findDevicesByYearRange(minYear, maxYear);
        printAndRemoveElements("видалені за діапазоном року", byYearRange);

        // Пошук за країною
        System.out.println("Введіть країну-виробника: ");
        scanner.nextLine();  // Очищення буфера
        String country = scanner.nextLine();
        List<ElectricalDevice> byCountry = devices.findDevicesByCountry(country);
        printAndRemoveElements("видалені за країною", byCountry);

        // Пошук за потужністю
        System.out.println("Введіть мінімальну потужність: ");
        double minPower = scanner.nextDouble();
        System.out.println("Введіть максимальну потужність: ");
        double maxPower = scanner.nextDouble();
        List<ElectricalDevice> byPowerRange = devices.findDevicesInPowerRange(minPower, maxPower);
        printAndRemoveElements("видалені за діапазоном потужності", byPowerRange);

        // Пошук за вагою
        System.out.println("Введіть мінімальну вагу: ");
        double minWeight = scanner.nextDouble();
        System.out.println("Введіть максимальну вагу: ");
        double maxWeight = scanner.nextDouble();
        List<ElectricalDevice> byWeightRange = devices.findDevicesByWeightRange(minWeight, maxWeight);
        printAndRemoveElements("видалені за діапазоном ваги", byWeightRange);

        // Пошук за ціною
        System.out.println("Введіть мінімальну ціну: ");
        double minPrice = scanner.nextDouble();
        System.out.println("Введіть максимальну ціну: ");
        double maxPrice = scanner.nextDouble();
        List<ElectricalDevice> byPriceRange = devices.findDevicesByPriceRange(minPrice, maxPrice);
        printAndRemoveElements("видалені за діапазоном ціни", byPriceRange);

        // Виведення остаточного результату
        System.out.println("Остаточний результат:");
        devices.getDevices().forEach(System.out::println);
    }

    private void printAndRemoveElements(String searchType, List<ElectricalDevice> list) {
        System.out.println("Елементи " + searchType + ":");
        if (list.isEmpty()) {
            System.out.println("Немає результатів.");
        } else {
            list.forEach(device -> {
                System.out.println(device);
                devices.getDevices().remove(device);
            });
        }
        System.out.println();
    }

    @Override
    public String commandName(){
        return "Видалити прилад(и) за діапазоном параметрів.";
    }
}
