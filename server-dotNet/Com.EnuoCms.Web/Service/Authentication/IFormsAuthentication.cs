
using System.Web;
using System.Web.Security;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Core.Authentication
{
    public interface IFormsAuthentication
    {
        void Signout();
        void SetAuthCookie(HttpContextBase httpContext, string appName, string serverAddress, bool rememberMe);
        string Encrypt(FormsAuthenticationTicket authenticationTicket);
        FormsAuthenticationTicket Decrypt(string encryptedTicket);
    }
}