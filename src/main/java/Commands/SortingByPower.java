package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import java.util.List;

public class SortingByPower extends Command{

    private ElectricalDevices devices;
    private boolean ascending;

    public SortingByPower(ElectricalDevices devices, boolean ascending) {
        this.devices = devices;
        this.ascending = ascending;
    }

    @Override
    public void execute(){
        devices.sortDevicesByPower(ascending);
        List<ElectricalDevice> sortedDevices = devices.getDevices();
        System.out.println("\n\t\tПрилади відсортовано за потужністю:\n");
        for (int i = 0; i < sortedDevices.size(); i++){
            System.out.println((i + 1) + ". " + sortedDevices.get(i).toString());
            System.out.println("------------------------------------------\n");
        }
    }

    @Override
    public String commandName(){
        if(ascending)
            return "Відсортувати прилади в порядку зростання потужності.";
        else
            return "Відсортувати прилади в порядку спадання потужності.";
    }
}
