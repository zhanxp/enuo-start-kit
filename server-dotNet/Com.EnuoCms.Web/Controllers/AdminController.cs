using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Com.EnuoCms.IService;
using Com.EnuoCms.Model.Entities;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Controllers
{
	[RoutePrefix("admin")]
    public class AdminController : Controller
    {
		private readonly IAdminRepository adminRepository;

		public AdminController(
			IAdminRepository adminRepository
			)
		{
			this.adminRepository = adminRepository;
		}

        [Route]
        public ActionResult Index()
        {
            var userName = User.Identity.Name;
            Admin user = adminRepository.Get(sim => sim.Name == userName);
            return View("Profile", user);
        }
    }
}
