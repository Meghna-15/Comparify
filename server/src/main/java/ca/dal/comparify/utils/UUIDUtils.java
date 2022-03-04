package ca.dal.comparify.utils;

import java.util.UUID;

/**
 * @author Harsh Shah
 */
public class UUIDUtils {

    /**
     * @return
     *
     * @author Harsh Shah
     */
    public static String generate(){
        return UUID.randomUUID().toString();
    }
}
