
using System.Web;
using System.Web.Security;
using System;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Core.Authentication
{
    public class DefaultFormsAuthentication : IFormsAuthentication
    {
        public void Signout()
        {
            FormsAuthentication.SignOut();
        }

        public void SetAuthCookie(HttpContextBase httpContext, string appName, string serverAddress, bool rememberMe)
        {
            string encryptedTicket = Encrypt(CreateAuthenticationTicket(appName, serverAddress));

            if (rememberMe)
            {
                httpContext.Response.Cookies.Add(new HttpCookie(FormsAuthentication.FormsCookieName, encryptedTicket) { Expires = CalculateCookieExpirationDate() });
            }
            else
            {
                httpContext.Response.Cookies.Add(new HttpCookie(FormsAuthentication.FormsCookieName, encryptedTicket));
            }
        }

        public string Encrypt(FormsAuthenticationTicket authenticationTicket)
        {
            return FormsAuthentication.Encrypt(authenticationTicket);
        }

        public FormsAuthenticationTicket Decrypt(string encryptedTicket)
        {
            return FormsAuthentication.Decrypt(encryptedTicket);
        }

        private DateTime CalculateCookieExpirationDate()
        {
            return DateTime.Now.Add(FormsAuthentication.Timeout);
        }

        private FormsAuthenticationTicket CreateAuthenticationTicket(
            string appName, string serverAddress)
        {
            var ticket = new FormsAuthenticationTicket(
                1,
                appName,
                DateTime.Now,
                DateTime.Now.Add(FormsAuthentication.Timeout),
                false,
                serverAddress);

            return ticket;
        }
    }
}