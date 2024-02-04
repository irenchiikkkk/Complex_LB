package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SortingByPowerTest {

    @Mock
    private ElectricalDevices electricalDevices;

    @Mock
    private ElectricalDevice device1;

    @Mock
    private ElectricalDevice device2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteAscending() {
        // Set up mock devices
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        // Configure mock devices
        when(device1.toString()).thenReturn("Device1");
        when(device2.toString()).thenReturn("Device2");

        // Configure electricalDevices mock to return the list of mock devices
        when(electricalDevices.getDevices()).thenReturn(devices);

        // Create SortingByPower instance
        SortingByPower sortingByPower = new SortingByPower(electricalDevices, true);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the method
        sortingByPower.execute();

        // Verify that sortDevicesByPower and getDevices were called
        verify(electricalDevices, times(1)).sortDevicesByPower(true);
        verify(electricalDevices, times(1)).getDevices();

        // Verify console output
        String expectedOutput = "\n\t\tПрилади відсортовано за потужністю:\n" +
                "1. Device1\n" +
                "------------------------------------------\n" +
                "2. Device2\n" +
                "------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out to its original state
        System.setOut(System.out);
    }

    @Test
    public void testCommandNameAscending() {
        SortingByPower sortingByPower = new SortingByPower(electricalDevices, true);
        String result = sortingByPower.commandName();

        assertEquals("Відсортувати прилади в порядку зростання потужності.", result);
    }

    @Test
    public void testExecuteDescending() {
        // Set up mock devices
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        // Configure mock devices
        when(device1.toString()).thenReturn("Device1");
        when(device2.toString()).thenReturn("Device2");

        // Configure electricalDevices mock to return the list of mock devices
        when(electricalDevices.getDevices()).thenReturn(devices);

        // Create SortingByPower instance for descending order
        SortingByPower sortingByPower = new SortingByPower(electricalDevices, false);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the method
        sortingByPower.execute();

        // Verify that sortDevicesByPower and getDevices were called with descending order
        verify(electricalDevices, times(1)).sortDevicesByPower(false);
        verify(electricalDevices, times(1)).getDevices();

        // Verify console output
        String expectedOutput = "\n\t\tПрилади відсортовано за потужністю:\n" +
                "1. Device2\n" +
                "------------------------------------------\n" +
                "2. Device1\n" +
                "------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out to its original state
        System.setOut(System.out);
    }

    @Test
    public void testCommandNameDescending() {
        SortingByPower sortingByPower = new SortingByPower(electricalDevices, false);
        String result = sortingByPower.commandName();

        assertEquals("Відсортувати прилади в порядку спадання потужності.", result);
    }

}
