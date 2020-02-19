using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Com.EnuoCms.Model.Entities;
using Com.EnuoCms.IService;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Controllers
{
    [RoutePrefix("")]
    public class HomeController : Controller
    {
		[Route]
        public ActionResult Index()
        {
            return View();
        }

        [Route("about")]
        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }

    }
}