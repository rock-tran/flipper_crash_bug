package co.utilities

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * @author TUNGDX
 */

/**
 * All utils for [Date]
 */
object DateUtils {

    /**
     * Compare day between 2 [Date]. This is difference with
     * [Date.after] or [Date.before]. Because it only
     * compare by day (not compare hour, minutes, seconds).

     * @param dateRoot      This is root
     * *
     * @param dateToCompare Date to compare with root.
     * *
     * @return true if dateToCompare > date root, false otherwise.
     */
    fun checkLargerByDay(dateRoot: Date, dateToCompare: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = dateRoot

        val calendarToCompare = Calendar.getInstance()
        calendarToCompare.time = dateToCompare

        val lastYear = calendar.get(Calendar.YEAR)
        val newYear = calendarToCompare.get(Calendar.YEAR)
        if (lastYear == newYear) {
            val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
            val dayOfNewYearToCompare = calendarToCompare
                    .get(Calendar.DAY_OF_YEAR)
            if (dayOfYear < dayOfNewYearToCompare)
                return true
        } else if (lastYear < newYear) {
            return true
        }
        return false
    }

    /**
     * Compare day between 2 [Date]. This is difference with
     * [Date.after] or [Date.before]. Because it only
     * compare by day (not compare hour, minutes, seconds).

     * @param dateRoot    This is rootDate
     * *
     * @param dateCompare Date compare to rootDate.
     * *
     * @return -1 if dateCompare < dateRoot, 0 if equal, 1 if dateCompare = dateRoot.
     */
    fun compareDate(dateRoot: Date, dateCompare: Date): Int {
        val calendarRoot = Calendar.getInstance()
        calendarRoot.time = dateRoot

        val calenderCompare = Calendar.getInstance()
        calenderCompare.time = dateCompare
        val rootYear = calendarRoot.get(Calendar.YEAR)
        val compareYear = calenderCompare.get(Calendar.YEAR)
        if (compareYear < rootYear)
            return -1
        else if (compareYear > rootYear)
            return 1
        else {
            val rootDayOfYear = calendarRoot.get(Calendar.DAY_OF_YEAR)
            val compareDayOfYear = calenderCompare.get(Calendar.DAY_OF_YEAR)
            if (compareDayOfYear < rootDayOfYear)
                return -1
            else if (compareDayOfYear > rootDayOfYear)
                return 1
            else
                return 0
        }
    }

    /**
     * Convert time string to other format

     * @param time       in string
     * *
     * @param fromFormat
     * *
     * @param toFormat
     * *
     * @return
     */
    fun convertFormatDateTime(time: String, fromFormat: String,
                              toFormat: String): String {
        val from = SimpleDateFormat(fromFormat,
                Locale.getDefault())
        val to = SimpleDateFormat(toFormat,
                Locale.getDefault())
        try {
            val date = from.parse(time)
            return to.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }

    }

    /**
     * Get time in GMT as template

     * @param template date in GMT. Example: yyyyMMddHHmmssSSS
     * *
     * @return
     */
    fun getTimeInGMT(template: String): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        val date = calendar.time
        val dateFormat = SimpleDateFormat(template,
                Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormat.format(date)
    }

    /**
     * Convert time in GMT to Locale.

     * @param time Time to converted.
     * *
     * @return Time in Locale.
     */
    fun convertGMTtoLocale(time: String): String? {
        var dateFormat = SimpleDateFormat("yyyyMMddHHmmssSSS",
                Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        try {
            val date = dateFormat.parse(time)
            dateFormat = SimpleDateFormat("yyyyMMddHHmmssSSS",
                    Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * Convert time in locale to GMT.

     * @param time Time to converted.
     * *
     * @return Time in GMT.
     */
    fun convertLocaleToGMT(time: String): String {

        val dateFormat = SimpleDateFormat("yyyyMMddHHmmssSSS",
                Locale.getDefault())
        val date: Date
        try {
            date = dateFormat.parse(time)
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            return dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            return time
        }

    }

    /**
     * Get [Date] in GMT.

     * @return date in GMT.
     */
    // if exception -> get time local
    val dateTimeInGMT: Date?
        get() {
            val dateFormat = SimpleDateFormat("yyyyMMddHHmmssSSS",
                    Locale.getDefault())
            val date = Calendar.getInstance().time
            try {
                dateFormat.timeZone = TimeZone.getTimeZone("GMT")
                val d = dateFormat.format(date)

                val simpleDateFormat = SimpleDateFormat(
                        "yyyyMMddHHmmssSSS", Locale.getDefault())
                return simpleDateFormat.parse(d)

            } catch (e: ParseException) {
                e.printStackTrace()
                return null
            }

        }
}
