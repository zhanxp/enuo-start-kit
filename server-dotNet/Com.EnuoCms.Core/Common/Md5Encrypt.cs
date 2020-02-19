using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Security.Cryptography;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Core.Common
{
    public static class Md5Encrypt
    {
        public static string Md5EncryptPassword(string data)
        {
            MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider();
            byte[] bytes = md5.ComputeHash(Encoding.UTF8.GetBytes(data));
            return BitConverter.ToString(bytes);
        }
    }
}
