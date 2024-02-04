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

public class CalculatePowerConsumptionTest {

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
    public void testExecuteEmptyList() {
        when(electricalDevices.getDevices()).thenReturn(new ArrayList<>());

        CalculatePowerConsumption calculatePowerConsumption = new CalculatePowerConsumption(electricalDevices);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        calculatePowerConsumption.execute();

        assertEquals("На жаль, список порожній.\n", outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testExecuteNonEmptyList() {
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(electricalDevice1);
        devices.add(electricalDevice2);

        when(electricalDevice1.status()).thenReturn(true);
        when(electricalDevice2.status()).thenReturn(false);
        when(electricalDevice1.getPower()).thenReturn(100.0);
        when(electricalDevice2.getPower()).thenReturn(50.0);

        when(electricalDevices.getDevices()).thenReturn(devices);

        CalculatePowerConsumption calculatePowerConsumption = new CalculatePowerConsumption(electricalDevices);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        calculatePowerConsumption.execute();

        String expectedOutput = "Споживана потужність електроприладів, що ввімкнуті в розетку становить: 100.0\n";
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testExecuteNoDevicesSwitchedOn() {
        List<ElectricalDevice> devices = new ArrayList<>();
        devices.add(electricalDevice1);
        devices.add(electricalDevice2);

        when(electricalDevice1.status()).thenReturn(false);
        when(electricalDevice2.status()).thenReturn(false);

        when(electricalDevices.getDevices()).thenReturn(devices);

        CalculatePowerConsumption calculatePowerConsumption = new CalculatePowerConsumption(electricalDevices);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        calculatePowerConsumption.execute();

        assertEquals("Прилади не ввімкнуті в розетку.\n", outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testCommandName() {
        CalculatePowerConsumption calculatePowerConsumption = new CalculatePowerConsumption(electricalDevices);
        String result = calculatePowerConsumption.commandName();

        assertEquals("Розрахувати потужність ввімкнених приладів.", result);
    }
}

