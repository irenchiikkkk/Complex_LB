package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import java.util.List;
import java.util.Scanner;

public class TurnOnOff extends Command{

    private String beforeAction;
    private String result;
    private ElectricalDevices devices;
    private boolean doAction;

    public TurnOnOff(ElectricalDevices devices, boolean value, String start, String result) {
        this.devices = devices;
        this.doAction = value;
        this.beforeAction = start;
        this.result = result;
    }

    public int kstElementsForDoAction(List<ElectricalDevice> Devices) {
        int numberTurnOn = 0;
        for(ElectricalDevice device: Devices) {
            if (device.status() == !doAction)
                numberTurnOn++;
        }
        return numberTurnOn;
    }

    @Override
    public void execute() {
        int number, deviceNumber;
        Scanner scan = new Scanner(System.in);
        List<ElectricalDevice> Devices = devices.getDevices();

        if (Devices.isEmpty()) {
            System.out.println("Список приладів порожній.");
            return;
        }

        if(kstElementsForDoAction(Devices) == 0){
            System.out.println("Всі прилади " + result + ".");
            return;
        }

        do {
            number = 0;
            System.out.println("\n\t\tСписок приладів, які " + beforeAction + ":\n");
            for (int i = 0; i < Devices.size(); i++) {
                if (!doAction == Devices.get(i).status())
                    System.out.println(++number + ". " + Devices.get(i).getName());
            }

            System.out.println("Введіть номер приладу, щоб завершити введіть 0: ");
            deviceNumber = scan.nextInt();

            if (deviceNumber > 0 && deviceNumber <= number) {
                int index = 0;
                for (int i = 0; i < Devices.size(); i++) {
                    if (Devices.get(i).status() == !doAction) {
                        if (index == (deviceNumber - 1)) {
                            Devices.get(i).setStatus(doAction);
                            System.out.println("Прилад №" + deviceNumber + " " + result + ".");
                            --number;
                            break;
                        }
                        else
                            index++;
                    }
                }
            }
            else {
                if (deviceNumber != 0)
                    System.out.println("Введено некоректний номер.");
            }
        } while (deviceNumber != 0 && number != 0);
    }

    @Override
    public String commandName(){
        if(doAction)
            return "Ввімкнути прилад(и).";
        else
            return "Ввимкнути прилад(и).";
    }
}