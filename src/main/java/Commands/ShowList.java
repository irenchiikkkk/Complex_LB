package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import java.util.List;

public class ShowList extends Command{

    private ElectricalDevices devices;

    public ShowList(ElectricalDevices devices) {
        this.devices = devices;
    }

    @Override
    public void execute(){
        List<ElectricalDevice> devicesList = devices.getDevices();
        if(devicesList.isEmpty())
            System.out.println("\nСписок приладів порожній.");
        else {
            System.out.println("\t\tСписок всіх приладів:\n");
            for (int i = 0; i < devicesList.size(); i++) {
                System.out.println((i + 1) + ". " + devicesList.get(i).toString());
            }
        }
    }

    @Override
    public String commandName(){
        return "Список приладів.";
    }
}
