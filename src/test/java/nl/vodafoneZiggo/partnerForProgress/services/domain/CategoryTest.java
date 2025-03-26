package nl.vodafoneZiggo.partnerForProgress.services.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {
    @Test
    @DisplayName("filter search")
    void filterSearch() {
        Category category = new Category("name", "empty", List.of(
                new Service("1a", "", "", List.of()),
                new Service("1b", "", "", List.of()),
                new Service("1c", "", "", List.of()),
                new Service("1d", "", "", List.of()),
                new Service("2a", "", "", List.of())
        ));

        category.filterSearch("1");

        assertEquals(4, category.getServices().size());
    }
}