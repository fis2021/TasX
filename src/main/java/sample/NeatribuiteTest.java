package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeatribuiteTest {

    @Test
    public void testMail()
    {
        Neatribuite obj = new Neatribuite();
        assertTrue(obj.haveMail(""));
    }

}