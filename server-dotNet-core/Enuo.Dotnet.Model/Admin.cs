using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Enuo.Dotnet.Core.Model;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Enuo.Dotnet.Model
{
  [Table("admin")]
  public class Admin : BaseEntity
  {
    [MaxLength(10)]
    [Display(Name = "用户名")]
    [Required(ErrorMessage = "用户名不能为空")]
    [Column("name")]
    public string name { get; set; }

    [MaxLength(16)]
    [Display(Name = "密码")]
    [Required(ErrorMessage = "密码不能为空")]
    public string password { get; set; }
  }
}
