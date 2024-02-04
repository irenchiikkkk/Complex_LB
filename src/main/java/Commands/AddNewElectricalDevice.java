package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import java.util.Scanner;

public class AddNewElectricalDevice extends Command{

    private ElectricalDevices devices;

    public AddNewElectricalDevice(ElectricalDevices devices) {
        this.devices = devices;
    }

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("\t\tДодавання нового приладу\n(для завершення введіть в назві - 0)\n");
            System.out.print("Введіть назву приладу: ");
            String name = scan.nextLine();
            if (name.equals("0"))
                break;
            System.out.print("Введіть ціну: ");
            double price = scan.nextDouble();
            System.out.print("Введіть вагу: ");
            double weight = scan.nextDouble();
            System.out.print("Введіть рік випуску: ");
            int year = scan.nextInt();
            scan.nextLine();
            System.out.print("Введіть назву країни: ");
            String country = scan.nextLine();
            System.out.print("Введіть потужність: ");
            double power = scan.nextDouble();
            scan.nextLine();

            ElectricalDevice newDevice = new ElectricalDevice(name, year, country, power, weight, false, price);
            devices.addDevice(newDevice);
            System.out.println("\nПрилад " + newDevice.getName() + " успішно додано до списку.\n");
        }
    }

    @Override
    public String commandName(){
        return "Додати прилад(и).";
    }
}
