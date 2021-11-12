package com.fsoft.igos.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class DateTimeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final SimpleDateFormat simpleDateFormat_YYYY_MM_DD = new SimpleDateFormat(FORMAT_YYYY_MM_DD);

    public static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String TIME_FORMAT_DD_MMM_YYYY_HH_MM_SS = "dd-MMM-yyyy HH:mm:ss";
    public static final String TIME_FORMAT_DD_MMM_YYYY_HH_MM_A = "dd MMM yyyy, hh:mm a";
    public static final String TIME_FORMAT_LOG = "yyyyMMdd";
    public static final String FORMAT_DD_MM_YYYY_HH_MM_SS_NO_SPACE = "ddMMyyyyHHmmss";
    public static final String FORMAT_HH_MM_A = "hh:mma";
    public static final String FORMAT_HH_MM_A_DD_HH = "hh:mma dd MMM";
    public static final String FORMAT_DD_MM_HH_MM_A = "dd MMM";
    public static final String FORMAT_DD_MM_YYYY_HYPHEN = "dd-MM-yyyy";
    public static final String FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_ISO_8601_PE = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static Date toDateMillis(String strInput) {
        Date date = null;
        if (ValidateUtil.isNotNullOrEmpty(strInput)) {
            DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTime();
            DateTime jodaTime = dateFormatter.parseDateTime(strInput);
            if (jodaTime != null) {
                date = jodaTime.toDate();
            }
        }
        return date;
    }

    /**
     * Reset hours, minutes, seconds to 0
     *
     * @param dateInput date input
     * @return DateTime excluded time
     */
    public static Date excludeTime(Date dateInput) {
        return DateUtils.truncate(dateInput, Calendar.DATE);
    }

    /**
     * Adds the day.
     *
     * @param dateInput the date input
     * @param amount    the amount
     * @return the date
     */
    public static Date addDays(Date dateInput, int amount) {
        return DateUtils.addDays(dateInput, amount);
    }

    /**
     * @param input
     * @return
     * @throws ParseException
     */
    public static String convertDate(String input) throws ParseException {
        if (ValidateUtil.isNotNullOrEmpty(input)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = sdf.parse(input);
            sdf = new SimpleDateFormat("MM/dd/yyyy");
            return sdf.format(date);
        }
        return null;
    }

    public static String convertDateFollowPattern(String input, String pattern) throws ParseException {
        if (ValidateUtil.isNotNullOrEmpty(input)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(input);
            return sdf.format(date);
        }
        return null;
    }

    /**
     * Convert and validate string to date
     *
     * @param datetime
     * @param pattern
     * @return
     */
    public static Date convertAndValidateStringToDate(String datetime, String pattern) {
        try {
            if (ValidateUtil.isNotNullOrEmpty(datetime)) {
                Date date = convertStringToDate(datetime, pattern);
                if (datetime.indexOf("+") != -1) {
                    String timezone = datetime.substring(datetime.indexOf("+") + 1, datetime.length());
                    Pattern TIMEZONE_PATTERN = Pattern.compile("^\\d{2}:\\d{2}$");
                    if (TIMEZONE_PATTERN.matcher(timezone).matches()) {
                        timezone = "GMT+" + timezone;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        sdf.setTimeZone(TimeZone.getTimeZone(timezone));
                        String testConvertDate = sdf.format(date);
                        if (!testConvertDate.equals(datetime))
                            return null;
                    } else
                        return null;
                }
                return date;
            }
        } catch (Exception ex) {
        }

        return null;
    }

    public static String convertDateToString(Date input, String pattern) {
        if (ValidateUtil.isNotNullOrEmpty(input)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(input);
        }
        return null;
    }

    // Get Start Time of Day
    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        return calendar.getTime();
    }

    // Get Last Time of Day
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    // Get Start Time of Day
    public static Date getStartOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        return calendar.getTime();
    }

    // Get Start Time of Day
    public static Date getFristTimeOfMonth(Date date, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        return calendar.getTime();
    }

    // Get Start Time of Day
    public static Date getLastTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    // Get Start Time of Day
    public static Calendar addHourMinuteSecond(Date date) {
        DateTime dt = new DateTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, dt.getHourOfDay());
        calendar.set(Calendar.MINUTE, dt.getMinuteOfHour());
        calendar.set(Calendar.SECOND, dt.getSecondOfMinute());
        return calendar;
    }

    // Get current Datetime of a Different Time Zone
    public static Date getCurrentDatetimeWithTimeZone(String timezone) {
        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        return date;
    }

    // Get Date of a Different Time Zone
    public static Date getCurrentDateWithTimeZone(String timezone) {
        Date date = getCurrentDatetimeWithTimeZone(timezone);
        return excludeTime(date);
    }

    // Generate default start time
    public static Date setDefaultStartTimeTransactionsReport(String endTime) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(convertStringToDate(endTime, "yyyy-MM-dd"));
        c.add(Calendar.MONTH, -3);
        Date outOfDate = c.getTime();
        return outOfDate;
    }

    /**
     * Parse string with format "yyyy-MM-dd'T'HH:mm:ssXXX" to date
     *
     * @param strInput date time in string need to parse
     * @return the java.util.Date
     */

    public static Date toDate(String strInput) {
        Date date = null;
        if (ValidateUtil.isNotNullOrEmpty(strInput)) {
            // DateTimeFormatter dateFormatter = DateTimeFormat.forPattern(datePattern);
            DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis();
            DateTime jodaTime = dateFormatter.parseDateTime(strInput);
            if (jodaTime != null) {
                date = jodaTime.toDate();
            }
        }
        return date;
    }

    /**
     * Parse date to string with format "yyyy-MM-dd'T'HH:mm:ssXXX"
     *
     * @param dateInput date input
     * @return String in format "yyyy-MM-dd'T'HH:mm:ssXXX"
     */
    public static String toString(Date dateInput) {
        String dateStr = null;
        if (ValidateUtil.isNotNullOrEmpty(dateInput)) {
            DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis();
            DateTime jodaTime = new DateTime(dateInput);
            if (jodaTime != null) {
                dateStr = dateFormatter.print(jodaTime);
            }
        }
        return dateStr;
    }

    public static Date convertStringToDate(String input, String pattern) throws ParseException {
        if (ValidateUtil.isNotNullOrEmpty(input)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(input);
        }
        return null;
    }

    /**
     * Calculate Hold Time in the queue in minute unit
     *
     * @param file current file in the queue
     * @return holdTimeInQueue in minute unit
     */
    public static long calculateHoldTimeInQueue(File file) {

        long currentTimeStemp = System.currentTimeMillis();
        long holdTimeInQueue = currentTimeStemp - file.lastModified();

        // 1 minute = 1000 * 60 miliseconds
        holdTimeInQueue = (long) (holdTimeInQueue / (1000 * 60));
        return holdTimeInQueue;
    }

    /**
     * Building start date is 3 last month.
     *
     * @param month - minus number
     * @return - Date in string format
     */
    public static String buildStartDate(int month) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        // get data from recently 3 months ago
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date recent3Months = c.getTime();
        String strStartDate = sdf.format(recent3Months).toString();
        /*
         * try { strStartDate = URLEncoder.encode(strStartDate, "UTF-8"); } catch
         * (UnsupportedEncodingException e) {
         * mResponseResultDTO.setStatus(MobileBackendSvcConst.ERROR);
         * mResponseResultDTO.getErrorMsgs().add(e.getMessage());
         * logger.error(MessageUtil.formatMessage(CommonMessageConst._HAS_ERROR_MSG,
         * "processGetPendingReceipt(...)"), e); }
         */
        return strStartDate;
    }

    /**
     * Getting start of inputted date.
     *
     * @param date - input date.
     * @return
     */
    public static Date getStartOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // Getting start of inputted date.
        return cal.getTime();
    }

    /**
     * Building date with format: MM/dd/yyyy HH:mm:ss.
     *
     * @param date - used date
     * @return - Date in string format
     */
    public static String buildUsedDate(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        String usedDate = sdf.format(date).toString();
        return usedDate;
    }

    /**
     * Building start date is last month nDates.
     *
     * @param nDate - minus number
     * @return - Date in string format
     */
    public static String buildStartDateBaseOnDate(int nDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // get data from recently n dates ago
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, nDate);
        Date recentDates = c.getTime();
        String strStartDate = sdf.format(recentDates).toString();
        return strStartDate;
    }

    /**
     * Building start date of current date.
     *
     * @return - Date in string format yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static String buildStartDateInCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        // get data from recently n dates ago
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date startDate = c.getTime();
        String strStartDate = sdf.format(startDate).toString();
        return strStartDate;
    }

    /**
     * Building end date of current date. yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     *
     * @return - Date in string format
     */
    public static String buildEndDateInCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        // get data from recently n dates ago
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23); // anything 0 - 23
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date startDate = c.getTime();
        String strStartDate = sdf.format(startDate).toString();
        return strStartDate;
    }

    /**
     * convert format Date from yyyy-MM-dd'T'HH:mm:ssXXX to MM/dd/yyyy HH:mm:ss
     *
     * @param inputDate - date in format yyyy-MM-dd'T'HH:mm:ss.SSS
     * @return
     */
    public static String changeFormatDate(String inputDate) {
        try {
            Date usedDate = toDate(inputDate);
            return buildUsedDate(usedDate);
        } catch (Exception ex) {
            LOGGER.error(
                    "Has exception while converting format of input date from yyyy-MM-dd'T'HH:mm:ssXXX to MM/dd/yyyy HH:mm:ss",
                    ex);
            LOGGER.error("Input date: {}", inputDate);
            return null;
        }
    }

    /**
     * Parse date to string with format "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
     *
     * @param dateInput date input
     * @return String in format "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
     */
    public static String formattedDate(String dateInput) {
        String dateStr = null;
        if (ValidateUtil.isNotNullOrEmpty(dateInput)) {
            DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Singapore")));
            System.out.println("dateFormatter: " + dateFormatter);
            DateTime jodaTime = new DateTime(dateInput);
            System.out.println("jodaTime: " + jodaTime);
            if (jodaTime != null) {
                dateStr = dateFormatter.print(jodaTime);
            }
        }
        return dateStr;
    }

    /**
     * Parse date to string with format "yyyy-MM-dd'T'HH:mm:ssXXX"
     *
     * @param dateInput date input
     * @return String in format "yyyy-MM-dd'T'HH:mm:ssXXX"
     */
    public static String convertDateToString(Date dateInput) {
        String dateStr = null;
        if (dateInput != null) {
            DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis();
            DateTime jodaTime = new DateTime(dateInput);
            if (jodaTime != null) {
                dateStr = dateFormatter.print(jodaTime);
            }
        }
        return dateStr;
    }

    /**
     * @param pattern
     * @return current datetime
     */
    public static String getCurrentDateTimeWithPattern(String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * convertDateToStringWithPattern: convert date to string with pattern
     *
     * @param input:   input data
     * @param pattern: pattern
     * @return: the string date by pattern
     */
    public static String convertDateToStringWithPattern(Date input, String pattern) {
        if (ValidateUtil.isNullOrEmpty(input)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(input);
        } catch (Exception e) {
            LOGGER.error("Having exception in convertDateToStringWithPattern method.", e);
        }
        return null;
    }

    /**
     * Convert UTC date to Local Datetime
     *
     * @param utcDateStr
     * @return
     */
    public static Date convertUTCDateTimeToLocalDateTime(String utcDateStr) {
        try {
            DateTimeFormatter dtf = ISODateTimeFormat.dateTime();
            Date date = dtf.parseDateTime(utcDateStr).toDate();

            Instant utcInstant = new Date(date.getTime()).toInstant();
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(utcInstant, ZoneId.of("UTC"));

            LocalDateTime here = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            return Timestamp.valueOf(here);
        } catch (Exception e) {
            LOGGER.error("Having exception in convertUTCDateTimeToLocalDateTime", e);
        }
        return null;
    }
}
