using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Enuo.Dotnet.Service;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System.Reflection;
using Enuo.Dotnet.Web.Utils;

namespace Enuo.Dotnet.Web
{
  public class Startup
  {
    public const string CookieScheme = "CookieScheme";
    public Startup(IConfiguration configuration)
    {
      Configuration = configuration;
    }

    public IConfiguration Configuration { get; }

    // This method gets called by the runtime. Use this method to add services to the container.
    public void ConfigureServices(IServiceCollection services)
    {
      services.AddControllersWithViews();
      services.AddDbContext<MySqlDbContext>(options => options.UseMySQL(Configuration.GetConnectionString("MySqlDbContext"), b => b.MigrationsAssembly("Enuo.Dotnet.Web")));
      //services.AddScoped<ArticleService>();
      services.AddScoped(Assembly.GetAssembly(typeof(ArticleService)), "^Enuo.Dotnet.Service.*Service$");

      services.AddAuthentication(CookieScheme) // Sets the default scheme to cookies
               .AddCookie(CookieScheme, options =>
               {
                 options.AccessDeniedPath = "/Account/Denied";
                 options.LoginPath = "/Account/Login";
               });
    }

    // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
    public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
    {
      if (env.IsDevelopment())
      {
        app.UseDeveloperExceptionPage();
      }
      else
      {
        app.UseExceptionHandler("/Home/Error");
        // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
        app.UseHsts();
      }
      app.UseHttpsRedirection();
      app.UseStaticFiles();

      app.UseRouting();

      app.UseAuthentication();
      app.UseAuthorization();

      app.UseEndpoints(endpoints =>
      {
        endpoints.MapControllerRoute(
                  name: "default",
                  pattern: "{controller=Home}/{action=Index}/{id?}");
      });
    }
  }
}
