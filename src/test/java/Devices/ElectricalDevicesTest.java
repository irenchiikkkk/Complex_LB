package Devices;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ElectricalDevicesTest {


    @Test
    public void testFindDevicesByWeightRange() {
        // Create mock ElectricalDevices
        ElectricalDevice device1 = Mockito.mock(ElectricalDevice.class);
        when(device1.getWeight()).thenReturn(50.0);

        ElectricalDevice device2 = Mockito.mock(ElectricalDevice.class);
        when(device2.getWeight()).thenReturn(100.0);

        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        ElectricalDevices electricalDevices = new ElectricalDevices(devices);

        // Test findDevicesByWeightRange method
        List<ElectricalDevice> result = electricalDevices.findDevicesByWeightRange(75.0, 150.0);

        // Verify that the correct device is returned
        assertEquals(1, result.size());
        assertEquals(device2, result.get(0));

        // Verify that getWeight was called on the mocked devices
        verify(device1, times(1)).getWeight();
        verify(device2, times(1)).getWeight();
    }

    @Test
    public void testFindDevicesByPriceRange() {
        // Create mock ElectricalDevices
        ElectricalDevice device1 = Mockito.mock(ElectricalDevice.class);
        when(device1.getPrice()).thenReturn(500.0);

        ElectricalDevice device2 = Mockito.mock(ElectricalDevice.class);
        when(device2.getPrice()).thenReturn(1000.0);

        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        ElectricalDevices electricalDevices = new ElectricalDevices(devices);

        // Test findDevicesByPriceRange method
        List<ElectricalDevice> result = electricalDevices.findDevicesByPriceRange(700.0, 1500.0);

        // Verify that the correct device is returned
        assertEquals(1, result.size());
        assertEquals(device2, result.get(0));

        // Verify that getPrice was called on the mocked devices
        verify(device1, times(1)).getPrice();
        verify(device2, times(1)).getPrice();
    }

    @Test
    public void testAddDevice() {
        // Create a mock ElectricalDevice
        ElectricalDevice device = Mockito.mock(ElectricalDevice.class);

        // Create ElectricalDevices instance
        ElectricalDevices electricalDevices = new ElectricalDevices();

        // Add the mock device
        electricalDevices.addDevice(device);

        // Verify that the device was added
        assertEquals(1, electricalDevices.getDevices().size());
        assertEquals(device, electricalDevices.getDevices().get(0));
    }

    @Test
    public void testFindDevicesInPowerRange() {
        // Create mock ElectricalDevices
        ElectricalDevice device1 = Mockito.mock(ElectricalDevice.class);
        when(device1.getPower()).thenReturn(50.0);

        ElectricalDevice device2 = Mockito.mock(ElectricalDevice.class);
        when(device2.getPower()).thenReturn(100.0);

        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        ElectricalDevices electricalDevices = new ElectricalDevices(devices);

        // Test findDevicesInPowerRange method
        List<ElectricalDevice> result = electricalDevices.findDevicesInPowerRange(75.0, 150.0);

        // Verify that the correct device is returned
        assertEquals(1, result.size());
        assertEquals(device2, result.get(0));

        // Verify that getPower was called on the mocked devices
        verify(device1, times(1)).getPower();
        verify(device2, times(1)).getPower();
    }

    @Test
    public void testFindDevicesByYearRange() {
        // Create mock ElectricalDevices
        ElectricalDevice device1 = Mockito.mock(ElectricalDevice.class);
        when(device1.getYear()).thenReturn(2010);

        ElectricalDevice device2 = Mockito.mock(ElectricalDevice.class);
        when(device2.getYear()).thenReturn(2020);

        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        ElectricalDevices electricalDevices = new ElectricalDevices(devices);

        // Test findDevicesByYearRange method
        List<ElectricalDevice> result = electricalDevices.findDevicesByYearRange(2015, 2025);

        // Verify that the correct device is returned
        assertEquals(1, result.size());
        assertEquals(device2, result.get(0));

        // Verify that getYear was called on the mocked devices
        verify(device1, times(1)).getYear();
        verify(device2, times(1)).getYear();
    }

    // Add similar tests for other methods like findDevicesByCountry, findDevicesByWeightRange, findDevicesByPriceRange.

    @Test
    public void testSortDevicesByPower() {
        // Create mock ElectricalDevices
        ElectricalDevice device1 = Mockito.mock(ElectricalDevice.class);
        when(device1.getPower()).thenReturn(100.0);

        ElectricalDevice device2 = Mockito.mock(ElectricalDevice.class);
        when(device2.getPower()).thenReturn(50.0);

        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        ElectricalDevices electricalDevices = new ElectricalDevices(devices);

        // Test sortDevicesByPower method
        electricalDevices.sortDevicesByPower(true);

        // Verify that the devices list is sorted in ascending order based on power
        verify(device1, times(1)).getPower();
        verify(device2, times(1)).getPower();
        assertEquals(device2, electricalDevices.getDevices().get(0));
        assertEquals(device1, electricalDevices.getDevices().get(1));
    }

    @Test
    public void testFindDevicesByCountry() {
        // Create mock ElectricalDevices
        ElectricalDevice device1 = Mockito.mock(ElectricalDevice.class);
        when(device1.getCountry()).thenReturn("USA");

        ElectricalDevice device2 = Mockito.mock(ElectricalDevice.class);
        when(device2.getCountry()).thenReturn("Canada");

        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        ElectricalDevices electricalDevices = new ElectricalDevices(devices);

        // Test findDevicesByCountry method
        List<ElectricalDevice> result = electricalDevices.findDevicesByCountry("Canada");

        // Verify that the correct device is returned
        assertEquals(1, result.size());
        assertEquals(device2, result.get(0));

        // Verify that getCountry was called on the mocked devices
        verify(device1, times(1)).getCountry();
        verify(device2, times(1)).getCountry();
    }
}
