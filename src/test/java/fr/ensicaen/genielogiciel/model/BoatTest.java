package fr.ensicaen.genielogiciel.model;

import fr.ensicaen.genielogiciel.mvp.model.Boat;
import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoatTest {
    Boat _boat;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_get_x_y_direction_when_give_x_y_direction() {
        assertEquals(100, _boat.getX());
        assertEquals(100, _boat.getY());
        assertEquals(10, _boat.getDirection());
    }
}
