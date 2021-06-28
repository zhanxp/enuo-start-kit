using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Enuo.Dotnet.Core.Utils
{
  public static class Md5Encrypt
  {
    public static string Encrypt(string data)
    {
      MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider();
      byte[] bytes = md5.ComputeHash(Encoding.UTF8.GetBytes(data));
      return BitConverter.ToString(bytes);
    }
  }
}
