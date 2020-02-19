using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.SqlClient;
using System.Data.Entity.Core;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Core.Common
{
    public class ExceptionHelper
    {
        public static string[] Parse(Exception ex)
        {
            // get inner exception
            Exception exception = ex;
            while (exception.InnerException != null)
            {
                exception = exception.InnerException;
            }

            if (exception is SqlException)
            {
                return ParseSQLException((SqlException)exception);
            }
            else if (exception is OptimisticConcurrencyException)
            {
                return new string[2] { "RowTS", "Timestamp(RowTS) not valid." };
            }

            return null;
        }

        private static string[] ParseSQLException(SqlException ex)
        {
            string errField, errMessage;

            switch (ex.Number)
            {
                case 2601:
                case 2627:
                    {
                        char[] splited = { '"', '“', '”' };
                        string[] msg = ex.Message.Split(splited);
                        if (msg.Length <= 2)
                        {
                            errField = null;
                            errMessage = ex.Message;
                        }
                        else
                        {
                            errField = msg[1];
                            errMessage = string.Format("Value of '{0}' exists, can not insert the same value.", errField);
                        }
                    }
                    break;
                default:
                    {
                        errField = null;
                        errMessage = ex.Message;
                    }
                    break;
            }

            return new string[2] { errField, errMessage };
        }
    }
}
