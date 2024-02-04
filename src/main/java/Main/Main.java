package Main;

import Commands.*;
import Devices.ElectricalDevices;
import java.util.Scanner;

public class Main {
    public Main(ElectricalDevices devicesMock, Command[] commands) {
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ElectricalDevices devices = new ElectricalDevices();
        Command[] commands = new Command[9];
        commands[0] = new AddNewElectricalDevice(devices);
        commands[1] = new CalculatePowerConsumption(devices);
        commands[2] = new DeleteByParametersRange(devices);
        commands[3] = new SearchByParametersRange(devices);
        commands[4] = new ShowList(devices);
        commands[5] = new SortingByPower(devices, true);
        commands[6] = new SortingByPower(devices, false);
        commands[7] = new TurnOnOff(devices, false, "ввімкнено", "вимкнуто");
        commands[8] = new TurnOnOff(devices, true, "вимкнуто", "ввімкнено");;
        while(true){
            System.out.println("\n\n\t\tОберіть команду зі списку: \n\t(для завершення натисніть - 0)\n");
            for(int i = 0; i < commands.length; i++)
                System.out.println((i + 1) + "." + commands[i].commandName());
            int commandNumber = scan.nextInt();
            if(commandNumber <= commands.length && commandNumber > 0)
                commands[commandNumber - 1].execute();
            if(commandNumber == 0)
                break;
        }
    }
}