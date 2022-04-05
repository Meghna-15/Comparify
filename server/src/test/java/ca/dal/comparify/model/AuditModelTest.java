package ca.dal.comparify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class AuditModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        AuditModel actualAuditModel = new AuditModel();
        actualAuditModel.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualAuditModel.setCreatedOn(fromResult);
        actualAuditModel.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualAuditModel.setUpdatedOn(fromResult1);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualAuditModel.getCreatedBy());
        assertSame(fromResult, actualAuditModel.getCreatedOn());
        assertEquals("2020-03-01", actualAuditModel.getUpdatedBy());
        assertSame(fromResult1, actualAuditModel.getUpdatedOn());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createdOn = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        AuditModel actualAuditModel = new AuditModel("Jan 1, 2020 8:00am GMT+0100", "2020-03-01", createdOn,
            Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        actualAuditModel.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        actualAuditModel.setCreatedOn(fromResult);
        actualAuditModel.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        actualAuditModel.setUpdatedOn(fromResult1);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualAuditModel.getCreatedBy());
        assertSame(fromResult, actualAuditModel.getCreatedOn());
        assertEquals("2020-03-01", actualAuditModel.getUpdatedBy());
        assertSame(fromResult1, actualAuditModel.getUpdatedOn());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate() {
        assertEquals("Jan 1, 2020 8:00am GMT+0100", AuditModel.create("Jan 1, 2020 8:00am GMT+0100").getCreatedBy());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate2() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        AuditModel actualCreateResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100",
            Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreateResult.getCreatedBy());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(actualCreateResult.getCreatedOn()));
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testUpdate() {
        assertEquals("2020-03-01", AuditModel.update("2020-03-01").getUpdatedBy());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testUpdate2() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        AuditModel actualUpdateResult = AuditModel.update("2020-03-01",
            Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(actualUpdateResult.getUpdatedOn()));
        assertEquals("2020-03-01", actualUpdateResult.getUpdatedBy());
    }
}

