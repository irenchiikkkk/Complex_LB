package Commands;

import Devices.ElectricalDevice;
import Devices.ElectricalDevices;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteByParametersRangeTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("2020\n2022\nCountry\n50.0\n75.0\n10.0\n20.0\n30.0\n40.0\n".getBytes());

    @Mock
    private ElectricalDevices electricalDevicesMock;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        System.setIn(inContent);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    public void testExecute() {
        // Set up mock devices
        ElectricalDevices electricalDevices = mock(ElectricalDevices.class);
        when(electricalDevices.findDevicesByYearRange(2020, 2022)).thenReturn(createMockDeviceList());
        when(electricalDevices.findDevicesByCountry("Country")).thenReturn(createMockDeviceList());
        when(electricalDevices.findDevicesInPowerRange(10.0, 20.0)).thenReturn(createMockDeviceList());
        when(electricalDevices.findDevicesByWeightRange(30.0, 40.0)).thenReturn(createMockDeviceList());
        when(electricalDevices.findDevicesByPriceRange(50.0, 75.0)).thenReturn(createMockDeviceList());
        when(electricalDevices.getDevices()).thenReturn(createMockDeviceList());

        DeleteByParametersRange deleteByParametersRange = new DeleteByParametersRange(electricalDevices);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the method
        deleteByParametersRange.execute();

        // Verify that findDevices methods and getDevices were called
        verify(electricalDevices, times(1)).findDevicesByYearRange(2020, 2022);
        verify(electricalDevices, times(1)).findDevicesByCountry("Country");
        verify(electricalDevices, times(1)).findDevicesInPowerRange(10.0, 20.0);
        verify(electricalDevices, times(1)).findDevicesByWeightRange(30.0, 40.0);
        verify(electricalDevices, times(1)).findDevicesByPriceRange(50.0, 75.0);
        verify(electricalDevices, times(1)).getDevices();

        // Reset System.out to its original state
        System.setOut(originalOut);
    }

    private List<ElectricalDevice> createMockDeviceList() {
        ElectricalDevice device1 = mock(ElectricalDevice.class);
        when(device1.toString()).thenReturn("Device1");

        ElectricalDevice device2 = mock(ElectricalDevice.class);
        when(device2.toString()).thenReturn("Device2");

        return Arrays.asList(device1, device2);
    }

    @Test
    public void testCommandName() {
        DeleteByParametersRange deleteByParametersRange = new DeleteByParametersRange(electricalDevicesMock);
        String result = deleteByParametersRange.commandName();

        assertEquals("Видалити прилад(и) за діапазоном параметрів.", result);
    }
}
