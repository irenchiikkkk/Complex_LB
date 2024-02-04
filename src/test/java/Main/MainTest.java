package Main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import Commands.*;
import Devices.ElectricalDevices;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.*;

public class MainTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void setUpInput() {
        testIn = new ByteArrayInputStream("1\n2\n3\n4\n5\n6\n7\n8\n9\n0\n".getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemIn() {
        System.setIn(systemIn);
    }

    @Test
    public void testMain() {
        ElectricalDevices devicesMock = Mockito.mock(ElectricalDevices.class);
        Command[] commands = new Command[9];

        // Mock окремо для кожної команди
        for (int i = 0; i < commands.length; i++) {
            commands[i] = Mockito.mock(Command.class);
        }

        Main main = new Main(devicesMock, commands);

        main.main(null);

        // перевірка виконання кожної команди хоча б раз
        for (Command command : commands) {
            verify(command, atLeastOnce()).execute();
        }

        // сканер читає вхідні дані кілька разів
        verify(devicesMock, atLeastOnce()).getDevices();
    }
}
