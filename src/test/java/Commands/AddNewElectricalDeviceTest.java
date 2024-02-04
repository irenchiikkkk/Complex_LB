package Commands;
import Devices.ElectricalDevices;
import Devices.ElectricalDevice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AddNewElectricalDeviceTest {

    @Mock
    private ElectricalDevices electricalDevices;

    @InjectMocks
    private AddNewElectricalDevice addNewElectricalDevice;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        // Set up simulated user input
        String simulatedInput = "Device1\n100.0\n1.0\n2022\nCountry1\n50.0\n0\n";
        InputStream savedSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Mock the addDevice method
        doNothing().when(electricalDevices).addDevice(any(ElectricalDevice.class));

        // Execute the method
        addNewElectricalDevice.execute();

        // Verify that the addDevice method was called
        verify(electricalDevices, times(1)).addDevice(any(ElectricalDevice.class));

        // Reset System.in to its original state
        System.setIn(savedSystemIn);
    }

    @Test
    public void testCommandName() {
        String result = addNewElectricalDevice.commandName();

        assertEquals("Додати прилад(и).", result);
    }
}
