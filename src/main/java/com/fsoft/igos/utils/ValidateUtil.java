package com.fsoft.igos.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateUtil.class);

    // CAPITASTAR-18153: [AMS] Unable to send gift eCV with email have special characters
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    // CAPITASTAR-18153: [AMS] Unable to send gift eCV with email have special characters

    private ValidateUtil() {
        // Don't allow to create instance of this class.
    }

    public static boolean isNullOrEmpty(Object object) {
        boolean result = false;
        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            if (((String) object).equalsIgnoreCase("")) {
                result = true;
            }
        }

        if (object instanceof List<?>) {
            if (((List<?>) object).size() == 0) {
                result = true;
            }
        }

        return result;
    }

    public static boolean isNotNullOrEmpty(Object object) {
        return !isNullOrEmpty(object);
    }

    /**
     * Normalizing Date
     *
     * @param inputDate
     * @return
     */
    private static Date normalizeDate(Date inputDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * Validate email format
     *
     * @param email
     * @return email is in correct format
     */
    public static boolean isValidEmailAddress(String email) {
        try {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            if (email == null || email.isEmpty()) {
                return false;
            }

            return matcher.matches();
        } catch (Exception ex) {
            LOGGER.error("Validate format for email failed, value: " + email, ex);
            return false;
        }
    }

}
