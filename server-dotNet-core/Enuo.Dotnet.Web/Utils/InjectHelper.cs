using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace Enuo.Dotnet.Web.Utils
{

  /// <summary>
  /// InjectHelper
  /// Startup: services.AddScoped(Assembly.Load("xxxx.IService"), Assembly.Load("xxxx.Service"));
  /// </summary>
  /// <param name="services"></param>
  /// <param name="implementAssembly"></param>
  /// <param name="nameSpaceRegStr"></param>
  public static class InjectHelper
  {
    public static void AddScoped(this IServiceCollection services, Assembly implementAssembly, String nameSpaceRegStr)
    {
      var implements = implementAssembly.GetTypes();
      foreach (var item in implements)
      {
        var isMatch = Regex.IsMatch(item.FullName, nameSpaceRegStr);
        if (isMatch && !item.IsAbstract)
        {
          services.AddScoped(item);
        }
      }
    }

    /// <summary>
    /// Add Scoped from InterfaceAssembly and ImplementAssembly to the specified Microsoft.Extensions.DependencyInjection.IServiceCollection.
    /// </summary>
    /// <param name="services"></param>
    /// <param name="interfaceAssembly"></param>
    /// <param name="implementAssembly"></param>
    public static void AddScoped(this IServiceCollection services, Assembly interfaceAssembly, Assembly implementAssembly)
    {
      var interfaces = interfaceAssembly.GetTypes().Where(t => t.IsInterface);
      var implements = implementAssembly.GetTypes();
      foreach (var item in interfaces)
      {
        var type = implements.FirstOrDefault(x => item.IsAssignableFrom(x));
        if (type != null)
        {
          services.AddScoped(item, type);
        }
      }
    }

    /// <summary>
    /// Add AddSingleton from InterfaceAssembly and ImplementAssembly to the specified Microsoft.Extensions.DependencyInjection.IServiceCollection.
    /// </summary>
    /// <param name="services"></param>
    /// <param name="interfaceAssembly"></param>
    /// <param name="implementAssembly"></param>
    public static void AddSingleton(this IServiceCollection services, Assembly interfaceAssembly, Assembly implementAssembly)
    {
      var interfaces = interfaceAssembly.GetTypes().Where(t => t.IsInterface);
      var implements = implementAssembly.GetTypes();
      foreach (var item in interfaces)
      {
        var type = implements.FirstOrDefault(x => item.IsAssignableFrom(x));
        if (type != null)
        {
          services.AddSingleton(item, type);
        }
      }
    }

    /// <summary>
    /// Add AddTransient from InterfaceAssembly and ImplementAssembly to the specified Microsoft.Extensions.DependencyInjection.IServiceCollection.
    /// </summary>
    /// <param name="services"></param>
    /// <param name="interfaceAssembly"></param>
    /// <param name="implementAssembly"></param>
    public static void AddTransient(this IServiceCollection services, Assembly interfaceAssembly, Assembly implementAssembly)
    {
      var interfaces = interfaceAssembly.GetTypes().Where(t => t.IsInterface);
      var implements = implementAssembly.GetTypes();
      foreach (var item in interfaces)
      {
        var type = implements.FirstOrDefault(x => item.IsAssignableFrom(x));
        if (type != null)
        {
          services.AddTransient(item, type);
        }
      }
    }
  }
}
