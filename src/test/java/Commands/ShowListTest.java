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

public class ShowListTest {

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
    public void testExecuteEmptyList() {
        // Configure electricalDevices mock to return an empty list of devices
        when(electricalDevices.getDevices()).thenReturn(new ArrayList<>());

        // Create ShowList instance
        ShowList showList = new ShowList(electricalDevices);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the method
        showList.execute();

        // Verify console output
        assertEquals("\nСписок приладів порожній.\n", outContent.toString());

        // Reset System.out to its original state
        System.setOut(System.out);
    }

    @Test
    public void testExecuteNonEmptyList() {
        // Set up mock devices
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);

        // Configure mock devices
        when(device1.toString()).thenReturn("Device1");
        when(device2.toString()).thenReturn("Device2");

        // Configure electricalDevices mock to return the list of mock devices
        when(electricalDevices.getDevices()).thenReturn(devices);

        // Create ShowList instance
        ShowList showList = new ShowList(electricalDevices);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the method
        showList.execute();

        // Verify console output
        String expectedOutput = "\t\tСписок всіх приладів:\n" +
                "1. Device1\n" +
                "2. Device2\n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out to its original state
        System.setOut(System.out);
    }

    @Test
    public void testCommandName() {
        ShowList showList = new ShowList(electricalDevices);
        String result = showList.commandName();

        assertEquals("Список приладів.", result);
    }
}

