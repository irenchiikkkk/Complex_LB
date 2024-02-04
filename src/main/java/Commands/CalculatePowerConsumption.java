package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import java.util.List;

public class CalculatePowerConsumption extends Command{

    private List<ElectricalDevice> devices;

    public CalculatePowerConsumption(ElectricalDevices devices) {
        this.devices = devices.getDevices();
    }

    @Override
    public void execute(){
        double totalPowerConsumption = 0;
        if(devices.isEmpty()){
            System.out.println("На жаль, список порожній.");
        }
        else{
            for (ElectricalDevice device: devices) {
                if(device.status())
                    totalPowerConsumption += device.getPower();
            }
        }
        if(totalPowerConsumption == 0.0)
            System.out.println("Прилади не ввімкнуті в розетку.");
        else
             System.out.println("Споживана потужність електроприладів, що ввімкнуті в розетку становить: " + totalPowerConsumption + "\n");
    }

    @Override
    public String commandName(){
        return "Розрахувати потужність ввімкнених приладів.";
    }
}
