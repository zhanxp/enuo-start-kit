using System.Web.Mvc;
using Autofac;
using Autofac.Integration.Mvc;
using System.Reflection;
using System.Web.Http;
using Com.EnuoCms.IService;
using Com.EnuoCms.Web.Core.Authentication;
using Com.EnuoCms.Web.Core.Dispatcher;
using Com.EnuoCms.Service;
using Autofac.Builder;
using Autofac.Features.Scanning;
using System.Web.Http.Controllers;

namespace Com.EnuoCms.Web
{
    public static class AutofacConfig
    {
      
        public static void RegisterDependencies()
        {
            var builder = new ContainerBuilder();
            builder.RegisterControllers(Assembly.GetExecutingAssembly());
            builder.RegisterType<DefaultCommandBus>().As<ICommandBus>().InstancePerRequest();
            builder.RegisterType<UnitOfWork>().As<IUnitOfWork>().InstancePerRequest();
            builder.RegisterType<DatabaseFactory>().As<IDatabaseFactory>().InstancePerRequest();

            builder.RegisterAssemblyTypes(typeof(AdminRepository).Assembly)
            .Where(t => t.Name.EndsWith("Repository"))
            .AsImplementedInterfaces().InstancePerRequest();

            var services = typeof(AdminCreateOrUpdateCommandHandler).Assembly;
            //var services = Assembly.Load("Com.EnuoCms.Domain");
            builder.RegisterAssemblyTypes(services).AsClosedTypesOf(typeof(ICommandHandler<>)).InstancePerRequest();
            builder.RegisterAssemblyTypes(services).AsClosedTypesOf(typeof(IValidationHandler<>)).InstancePerRequest();

            builder.RegisterApiControllers(Assembly.GetExecutingAssembly());

            builder.RegisterType<DefaultFormsAuthentication>().As<IFormsAuthentication>().InstancePerRequest();
            builder.RegisterFilterProvider();

            IContainer container = builder.Build();
            DependencyResolver.SetResolver(new AutofacDependencyResolver(container));

            //var resolver = new AutofacDependencyResolver(container);
            //GlobalConfiguration.Configuration.ServiceResolver.SetResolver(
            //    t => resolver.GetService(t),
            //    t => resolver.GetServices(t));
        }
    }
}