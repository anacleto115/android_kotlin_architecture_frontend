
package com.example.lib_utilities.Utilities

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

public class Convert
{
    companion object
    {
        public fun StringToDate(value: String?): Date?
        {
            var date = StringToDate(value, "yyyy-MM-dd HH:mm:ss");
            if (date != null)
                return date;
            date = StringToDate(value, "yyyy-MM-dd'T'HH:mm:ss");
            if (date != null)
                return date;
            date = StringToDate(value, "yyyy-MM-dd");
            return date;
        }

        public fun StringToDate(value: String?, format: String?): Date?
        {
            var date: Date? = null;
            try
            {
                var income = SimpleDateFormat(format);
                date = income.parse(value);
                return date;
            }
            catch (ex: Exception)
            {
                return date;
            }
        }

        public fun DateToString(value: Date?): String
        {
            var result = "";
            try
            {
                var dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                result = dateFormat.format(value);
                return result;
            }
            catch (ex: Exception)
            {
                return result;
            }
        }
    }
}
