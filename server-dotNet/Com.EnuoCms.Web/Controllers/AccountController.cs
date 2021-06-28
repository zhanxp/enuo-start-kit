using Com.EnuoCms.IService;

using Com.EnuoCms.Model.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Controllers
{
  [RoutePrefix("account")]
  public class AccountController : Controller
  {
    private readonly ICommandBus commandBus;
    private readonly IAdminRepository adminRepository;
    public AccountController(ICommandBus commandBus, IAdminRepository adminRepository)
    {
      this.commandBus = commandBus;
      this.adminRepository = adminRepository;
    }

    [Route]
    public ActionResult Index()
    {
      return View();
    }

    [Route("login")]
    public ActionResult Login(string returnUrl)
    {
      //var site = this.getSite();
      //return Content(site.Title);

      ViewBag.returnUrl = returnUrl;
      return View();
    }

    [HttpPost]
    [Route("login")]
    public ActionResult Login(FormCollection form, string UserName, string Password, string returnUrl)
    {
      string url = Url.Action("Index", "Home");
      if (!string.IsNullOrEmpty(returnUrl))
      {
        url = returnUrl;
      }
      Admin admin = adminRepository.Get(s => s.Name == UserName && s.Password == Password);
      if (admin != null)
      {
        string[] roles = { };
        string rolestr = string.Join(",", roles);

        //FormsAuthentication.SetAuthCookie(uname,true);
        FormsAuthenticationTicket ticket = new FormsAuthenticationTicket
           (1,
               UserName,
               DateTime.Now,
               DateTime.Now.AddMinutes(20),
               true,
               rolestr,
               "/"
           );
        var cookie = new HttpCookie(FormsAuthentication.FormsCookieName, FormsAuthentication.Encrypt(ticket));
        cookie.HttpOnly = true;
        HttpContext.Response.Cookies.Add(cookie);

        return Redirect(url);
      }
      else
      {
        ModelState.AddModelError("", "登录失败，用户名密码错误！");
        return View();
      }
    }

    [Route("logout")]
    public ActionResult LogOut()
    {
      FormsAuthentication.SignOut();
      return RedirectToAction("Login");
    }

    //ViewData["user"]=User.Identity.Name;
    //var cookie = Request.Cookies[FormsAuthentication.FormsCookieName];
    //var ticket = FormsAuthentication.Decrypt(cookie.Value);
    //string role = ticket.UserData;
    //ViewData["role"] = role;
  }
}