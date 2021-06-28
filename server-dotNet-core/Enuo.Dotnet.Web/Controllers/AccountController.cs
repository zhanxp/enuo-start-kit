using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using System.Security.Claims;
using Enuo.Dotnet.Service;
using Enuo.Dotnet.Web.Models;

namespace Enuo.Dotnet.Web.Controllers
{
  public class AccountController : Controller
  {
    private readonly AccountService _accountService;

    public AccountController(AccountService accountService)
    {
      this._accountService = accountService;
    }


    [HttpGet]
    public IActionResult Login(string returnUrl = null)
    {
      ViewData["ReturnUrl"] = returnUrl;
      return View();
    }

    //private bool ValidateLogin(string userName, string password)
    //{
    //  // For this sample, all logins are successful.
    //  return true;
    //}

    [HttpPost]
    public async Task<IActionResult> Login(LoginModel ent) //string userName, string password, string returnUrl = null)
    {
      ViewData["ReturnUrl"] = ent.returnUrl;

      // Normally Identity handles sign in, but you can do it directly
      if (_accountService.ValidateLogin(ent.userName, ent.password))
      {
        var claims = new List<Claim>
                {
                    new Claim("user", ent.userName),
                    new Claim("role", "Member")
                };

        await HttpContext.SignInAsync(new ClaimsPrincipal(new ClaimsIdentity(claims, "Cookies", "user", "role")));

        if (Url.IsLocalUrl(ent.returnUrl))
        {
          return Redirect(ent.returnUrl);
        }
        else
        {
          return Redirect("/");
        }
      }
      else
      {
        ModelState.AddModelError("password", "登录失败，用户名密码错误！");
        // ViewBag["Error"] = "登录失败，用户名密码错误！";
      }

      return View();
    }

    public IActionResult AccessDenied(string returnUrl = null)
    {
      return View();
    }

    public async Task<IActionResult> Logout()
    {
      await HttpContext.SignOutAsync();
      return Redirect("/");
    }
  }
}
