package Devices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElectricalDevices implements Serializable {
    private List<ElectricalDevice> devices = new ArrayList<>();

    public ElectricalDevices() {}
    public ElectricalDevices(List<ElectricalDevice> devices) {
        this.devices = devices;
    }

    public List<ElectricalDevice> getDevices() {
        return devices;
    }

    public void addDevice(ElectricalDevice device) {
        devices.add(device);
    }

    public List<ElectricalDevice> findDevicesInPowerRange(double minPower, double maxPower) {
        List<ElectricalDevice> result = new ArrayList<>();
        for (ElectricalDevice device : devices) {
            double devicePower = device.getPower();
            if (devicePower >= minPower && devicePower <= maxPower)
                result.add(device);
        }
        return result;
    }

    public List<ElectricalDevice> findDevicesByYearRange(int minYear, int maxYear) {
        List<ElectricalDevice> matchingDevices = new ArrayList<>();
        for (ElectricalDevice device : devices) {
            int deviceYear = device.getYear();
            if (deviceYear >= minYear && deviceYear <= maxYear)
                matchingDevices.add(device);
        }
        return matchingDevices;
    }

    public List<ElectricalDevice> findDevicesByCountry(String country) {
        List<ElectricalDevice> matchingDevices = new ArrayList<>();
        for (ElectricalDevice device : devices) {
            if (country.equals(device.getCountry()))
                matchingDevices.add(device);
        }
        return matchingDevices;
    }

    public List<ElectricalDevice> findDevicesByWeightRange(double minWeight, double maxWeight) {
        List<ElectricalDevice> matchingDevices = new ArrayList<>();
        for (ElectricalDevice device : devices) {
            int deviceWeight = device.getYear();
            if (deviceWeight >= minWeight && deviceWeight <= maxWeight)
                matchingDevices.add(device);
        }
        return matchingDevices;
    }

    public List<ElectricalDevice> findDevicesByPriceRange(double minPrice, double maxPrice) {
        List<ElectricalDevice> matchingDevices = new ArrayList<>();
        for (ElectricalDevice device : devices) {
            double devicePrice = device.getPrice();
            if (devicePrice >= minPrice && devicePrice <= maxPrice)
                matchingDevices.add(device);
        }
        return matchingDevices;
    }

    public void sortDevicesByPower(boolean ascending) {
        devices.sort(new Comparator<ElectricalDevice>() {
            @Override
            public int compare(ElectricalDevice o1, ElectricalDevice o2) {
                double powerConsumption1 = o1.getPower();
                double powerConsumption2 = o2.getPower();

                // Вибрати порядок сортування відповідно до параметра
                int result;
                if (ascending) {
                    result = Double.compare(powerConsumption1, powerConsumption2);
                } else {
                    result = Double.compare(powerConsumption2, powerConsumption1);
                }
                return result;
            }
        });
    }
}