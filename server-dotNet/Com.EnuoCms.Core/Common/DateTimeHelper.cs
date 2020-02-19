using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Core.Common
{
    public static class DateTimeHelper
    {
        public static double ToSecondsFrom1970(DateTime datetime)
        {
            return (datetime - new DateTime(1970, 1, 1, 0, 0, 0, 0, datetime.Kind)).TotalSeconds;
        }

        public static DateTime ToDateTimeFrom1970(double seconds, DateTimeKind kind)
        {
            return new DateTime(1970, 1, 1, 0, 0, 0, 0, kind).AddSeconds(seconds);
        }

        public static DateTime UTC2TimeZone(DateTime datetime, int timezone)
        {
            return DateTime.SpecifyKind(datetime + new TimeSpan(0, timezone, 0, 0, 0), DateTimeKind.Unspecified);
        }

        public static DateTime TimeZone2UTC(DateTime datetime, int timezone)
        {
            return DateTime.SpecifyKind(datetime - new TimeSpan(0, timezone, 0, 0, 0), DateTimeKind.Unspecified);
        }
    }
}
