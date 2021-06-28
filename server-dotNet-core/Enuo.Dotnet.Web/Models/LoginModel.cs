using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Enuo.Dotnet.Web.Models
{
  public class LoginModel
  {
    [MaxLength(10)]
    [Display(Name = "用户名")]
    [Required(ErrorMessage = "用户名不能为空")]
    [Column("user_name")]
    public string userName { get; set; }

    [MaxLength(16)]
    [Display(Name = "密码")]
    [Required(ErrorMessage = "密码不能为空")]
    public string password { get; set; }

    public string returnUrl { get; set; }
  }
}
