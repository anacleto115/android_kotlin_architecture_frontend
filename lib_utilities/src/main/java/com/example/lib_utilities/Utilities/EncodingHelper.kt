
package com.example.lib_utilities.Utilities

import android.os.Build
import java.nio.charset.StandardCharsets
import java.util.Base64

public class EncodingHelper
{
    companion object
    {
        public fun ToString(data: ByteArray?): String
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                return Base64.getEncoder().encodeToString(data);
            return String(data!!, StandardCharsets.US_ASCII);
        }

        public fun ToBytes(data: String): ByteArray
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                return Base64.getDecoder().decode(data);
            return data.toByteArray(StandardCharsets.US_ASCII);
        }
    }
}