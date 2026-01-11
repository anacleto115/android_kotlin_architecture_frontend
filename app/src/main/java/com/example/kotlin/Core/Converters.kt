
package com.example.kotlin.Core

import android.graphics.BitmapFactory
import android.widget.ImageView
import com.example.lib_language.Bundle
import com.example.lib_utilities.Utilities.EncodingHelper
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

public class Converters
{
    companion object
    {
        public fun ConverterBool(value: Boolean): String?
        {
            try
            {
                if (value == true)
                    return Bundle.Get("RsMessages", "lbActive");
                return Bundle.Get("RsMessages", "lbInactive");
            }
            catch (ex: Exception)
            {
                return Bundle.Get("RsMessages", "lbInactive");
            }
        }

        public fun ConverterDate(value: Date?): String
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

        public fun ConverterImage(value: String?, image: ImageView)
        {
            try
            {
                if (value == null || value == "")
                    return;
                var data = EncodingHelper.ToBytes(value);
                var bmp = BitmapFactory.decodeByteArray(data, 0, data.size);
                image.setImageBitmap(bmp);
            }
            catch (ex: Exception)
            {

            }
        }
    }
}