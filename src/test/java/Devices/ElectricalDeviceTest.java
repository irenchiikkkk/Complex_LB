package Devices;

import org.junit.Test;
import static org.junit.Assert.*;

public class ElectricalDeviceTest {

    @Test
    public void testGetters() {
        ElectricalDevice device = new ElectricalDevice("Laptop", 2022, "USA", 100.0, 2.5, true, 1200.0);

        assertEquals("Laptop", device.getName());
        assertEquals(2022, device.getYear());
        assertEquals("USA", device.getCountry());
        assertEquals(100.0, device.getPower(), 0.01);
        assertTrue(device.status());
        assertEquals(1200.0, device.getPrice(), 0.01);
    }

    @Test
    public void testSetStatus() {
        ElectricalDevice device = new ElectricalDevice();

        assertFalse(device.status()); // By default, it should be turned off

        device.setStatus(true);
        assertTrue(device.status());

        device.setStatus(false);
        assertFalse(device.status());
    }

    @Test
    public void testToString() {
        ElectricalDevice device = new ElectricalDevice("Refrigerator", 2020, "Canada", 200.0, 50.0, false, 800.0);

        String expected = "Ім'я електричного приладу - Refrigerator:" +
                "\n\t1)рік випуску: 2020" +
                "\n\t2)назва країни: Canada" +
                "\n\t3)потужність: 200.0" +
                "\n\t4)вага: 50.0" +
                "\n\t5)статус: turned off" +
                "\n\t6)ціна: 800.0";

        assertEquals(expected, device.toString());
    }
}
