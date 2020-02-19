using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;
using Autofac;
using Autofac.Builder;
using Autofac.Features.Scanning;
using System.Web.Http.Controllers;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web
{
	public static class ApiControllerExtension
	{
		public static IRegistrationBuilder<object, ScanningActivatorData, DynamicRegistrationStyle> RegisterApiControllers(this ContainerBuilder builder, params Assembly[] controllerAssemblies)
		{
			return
				from t in builder.RegisterAssemblyTypes(controllerAssemblies)
				where typeof(IHttpController).IsAssignableFrom(t) && t.Name.EndsWith("Controller")
				select t;
		}
	}
}