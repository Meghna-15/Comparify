package ca.dal.comparify.utils;

/**
 * @author Harsh Shah
 */
public class StringUtils {

    /**
     * @author Harsh Shah
     */
    private StringUtils(){}

    /**
     * @param str
     * @return
     *
     * @author Harsh Shah
     */
    public static boolean isNotEmpty(String str){
        return org.springframework.util.StringUtils.hasLength(str);
    }

    /**
     * @param str
     * @return
     *
     * @author Harsh Shah
     */
    public static boolean isEmpty(String str){
        return !isNotEmpty(str);
    }

    /**
     * @param strs
     * @return
     *
     * @author Harsh Shah
     */
    public static boolean isAnyEmpty(String... strs) {
        for(String str: strs){
            if(isEmpty(str)){
                return true;
            }
        }

        return false;
    }
}
