import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {
    private Series series;
    @BeforeEach
    protected void setUp() {
        series = new Series();
        series.seriesList.add(new SeriesModel("101", "Test 1", "10", "20"));
        series.seriesList.add(new SeriesModel("33", "Test 2", "18", "50"));
    }
    @Test
    public void testSearchSeries() {
        SeriesModel found = series.searchSeriesById("33");
        assertNotNull(found);
        assertEquals("Test 2", found.seriesName);
        assertEquals("18", found.seriesAge);
        assertEquals("50", found.seriesNumberOfEpisode);
    }
    @Test
    public void testSearchSeries_SeriesNotFound() {
        SeriesModel notFound = series.searchSeriesById("102");
        assertNull(notFound);
    }
    @Test
    public void testUpdateSeries() {
        boolean updated = series.seriesUpdateById("101", "Update 1", "15", "10");
        assertTrue(updated);
        SeriesModel updatedSeries = series.searchSeriesById("101");
        assertEquals("Update 1", updatedSeries.seriesName);
        assertEquals("15", updatedSeries.seriesAge);
        assertEquals("10", updatedSeries.seriesNumberOfEpisode);
    }
    @Test
    public void testDeleteSeries() {
        int initialSize = series.seriesList.size();
        boolean deleted = series.seriesDeleteById("33");
        assertTrue(deleted);
        assertEquals(initialSize - 1, series.seriesList.size());
        assertNull(series.searchSeriesById("33"));
    }
    @Test
    public void testDeleteSeries_SeriesNotFound() {
        boolean notDeleted = series.seriesDeleteById("22");
        assertFalse(notDeleted);
    }
    @Test
    public void testSeriesAgeRestriction_AgeValid() {
        assertTrue(series.isAgeValid("2"));
        assertTrue(series.isAgeValid("12"));
        assertTrue(series.isAgeValid("18"));
    }
    @Test
    public void testSeriesAgeRestriction_AgeInvalid() {
        assertFalse(series.isAgeValid("1"));
        assertFalse(series.isAgeValid("19"));
        assertFalse(series.isAgeValid("abc"));
        assertFalse(series.isAgeValid(""));
    }
}
