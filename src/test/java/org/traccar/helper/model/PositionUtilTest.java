package org.traccar.helper.model;

import org.junit.jupiter.api.Test;
import org.traccar.model.Device;
import org.traccar.model.Position;
import org.traccar.storage.Storage;
import org.traccar.storage.query.Request;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PositionUtilTest {

    @Test
    void getLatestPositionsForPublicUserReturnsAllPositions() throws Exception {
        Storage storage = mock(Storage.class);
        Position position1 = new Position();
        Position position2 = new Position();
        position1.setId(1L);
        position2.setId(2L);

        when(storage.getObjects(eq(Position.class), any(Request.class))).thenReturn(List.of(position1, position2));

        List<Position> positions = PositionUtil.getLatestPositions(storage, 0L);

        assertEquals(List.of(position1, position2), positions);
        verify(storage, never()).getObjects(eq(Device.class), any(Request.class));
    }
}
