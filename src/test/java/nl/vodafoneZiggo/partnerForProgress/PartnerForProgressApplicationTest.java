package nl.vodafoneZiggo.partnerForProgress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PartnerForProgressApplicationTest {
    @Test
    @DisplayName("application boots")
    public void applicationContextTest() {
        assertDoesNotThrow(() -> PartnerForProgressApplication.main(new String[] {}));
    }
}