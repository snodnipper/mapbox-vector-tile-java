package com.wdtinc.mapbox_vector_tile;

import com.vividsolutions.jts.geom.*;
import com.wdtinc.mapbox_vector_tile.adapt.jts.MvtReader;
import com.wdtinc.mapbox_vector_tile.adapt.jts.TagKeyValueMapConverter;
import com.wdtinc.mapbox_vector_tile.util.JtsGeomStats;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test reading MVTs.
 */
public final class MvtReaderTest {

    @Test
    public void simpleTest() {
        try {

            // Load multipolygon z0 tile
            final List<Geometry> geoms = MvtReader.loadMvt(
                    Paths.get("src/test/resources/vec_tile_test/0/0/0.mvt"),
                    new GeometryFactory(),
                    new TagKeyValueMapConverter());

            // Debug stats of multipolygon
            final JtsGeomStats stats = JtsGeomStats.getStats(geoms);
            LoggerFactory.getLogger(MvtReaderTest.class).info("Stats: {}", stats);

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testMultiPoint() {
        try {
            final List<Geometry> geoms = getGeometries("src/test/resources/mapbox/multi-point.mvt");

            int expected = 1;
            int actual = geoms.size();
            assertEquals(expected, actual);

            assertTrue(geoms.get(0) instanceof MultiPoint);

            MultiPoint multiPoint = (MultiPoint) geoms.get(0);
            verifyCoordinates(multiPoint,
                    new Coordinate(2059, 2025),
                    new Coordinate(2082, 2002)
            );

            // Debug stats of multipoint
            final JtsGeomStats stats = JtsGeomStats.getStats(geoms);
            LoggerFactory.getLogger(MvtReaderTest.class).info("Stats: {}", stats);

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testMultiPolygon() {
        try {
            // two triangles
            final List<Geometry> geoms = getGeometries("src/test/resources/mapbox/multi-polygon.mvt");

            // one multipolygon feature
            int expected = 1;
            int actual = geoms.size();
            assertEquals(expected, actual);

            assertTrue(geoms.get(0) instanceof MultiPolygon);

            // Debug stats of multipoint
            final JtsGeomStats stats = JtsGeomStats.getStats(geoms);
            LoggerFactory.getLogger(MvtReaderTest.class).info("Stats: {}", stats);

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    private List<Geometry> getGeometries(String path) throws IOException {
        return MvtReader.loadMvt(
                Paths.get(path),
                new GeometryFactory(),
                new TagKeyValueMapConverter());
    }

    private void verifyCoordinates(MultiPoint multiPoint, Coordinate... coordinates) {
        int actualCount = multiPoint.getCoordinates().length;
        int expectedCount = coordinates.length;
        assertEquals(expectedCount, actualCount);

        Coordinate[] actualCoordinates = multiPoint.getCoordinates();

        for (int i = 0; i < expectedCount; i++) {
            Coordinate expected = coordinates[i];
            Coordinate actual = actualCoordinates[i];
            assertEquals(expected, actual);
        }
    }
}
