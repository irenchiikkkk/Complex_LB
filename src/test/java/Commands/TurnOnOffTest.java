package Commands;
import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TurnOnOffTest {

    @Mock
    private ElectricalDevices electricalDevices;

    @Mock
    private ElectricalDevice electricalDevice1;

    @Mock
    private ElectricalDevice electricalDevice2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testKstElementsForDoAction() {
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(electricalDevice1);
        devices.add(electricalDevice2);

        when(electricalDevice1.status()).thenReturn(true);
        when(electricalDevice2.status()).thenReturn(false);

        TurnOnOff turnOnOff = new TurnOnOff(electricalDevices, true, "start", "result");
        int result = turnOnOff.kstElementsForDoAction(devices);

        assertEquals(1, result);
    }

    @Test
    public void testExecuteWithNoDevices() {
        List<ElectricalDevice> devices = new ArrayList<>();
        when(electricalDevices.getDevices()).thenReturn(devices);

        TurnOnOff turnOnOff = new TurnOnOff(electricalDevices, true, "start", "result");
        turnOnOff.execute();

        // Add assertions based on the expected behavior
    }

    @Test
    public void testExecuteWithAllDevicesAlreadyInDesiredState() {
        // Set up mock devices
        ElectricalDevice mockDevice1 = mock(ElectricalDevice.class);
        ElectricalDevice mockDevice2 = mock(ElectricalDevice.class);
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(mockDevice1);
        devices.add(mockDevice2);

        // Configure mock devices to be in the desired state
        when(mockDevice1.status()).thenReturn(true);
        when(mockDevice2.status()).thenReturn(true);

        // Configure electricalDevices mock to return the list of mock devices
        when(electricalDevices.getDevices()).thenReturn(devices);

        // Create TurnOnOff instance
        TurnOnOff turnOnOff = new TurnOnOff(electricalDevices, true, "start", "result");

        // Execute the method
        turnOnOff.execute();

        // Add assertions based on the expected behavior
    }

    @Test
    public void testExecuteWithUserInput() {
        // Set up mock devices
        ElectricalDevice mockDevice1 = mock(ElectricalDevice.class);
        ElectricalDevice mockDevice2 = mock(ElectricalDevice.class);
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(mockDevice1);
        devices.add(mockDevice2);

        // Configure mock devices to be in the desired state
        when(mockDevice1.status()).thenReturn(false);
        when(mockDevice2.status()).thenReturn(true);

        // Configure electricalDevices mock to return the list of mock devices
        when(electricalDevices.getDevices()).thenReturn(devices);

        // Set up a mock for System.in
        ByteArrayInputStream in = new ByteArrayInputStream("1\n0\n".getBytes());
        System.setIn(in);

        // Create TurnOnOff instance
        TurnOnOff turnOnOff = new TurnOnOff(electricalDevices, true, "start", "result");

        // Execute the method
        turnOnOff.execute();

        // Add assertions based on the expected behavior

        // Reset System.in to its original state
        System.setIn(System.in);
    }

    @Test
    public void testCommandNameTurnOn() {
        TurnOnOff turnOnOff = new TurnOnOff(electricalDevices, true, "start", "result");
        String result = turnOnOff.commandName();

        assertEquals("Ввімкнути прилад(и).", result);
    }

    @Test
    public void testCommandNameTurnOff() {
        TurnOnOff turnOnOff = new TurnOnOff(electricalDevices, false, "start", "result");
        String result = turnOnOff.commandName();

        assertEquals("Ввимкнути прилад(и).", result);
    }
}


