using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Enuo.Dotnet.Core.Model
{
  public class BaseEntity
  {
    [Key]
    [Column("id")]
    public virtual int id { get; set; }

    [Column("ent_types")]
    [Display(Name = "类型")]
    public int entTypes { get; set; }

    [Column("ent_status")]
    [Display(Name = "状态")]
    public int entStatus { get; set; }

    [MaxLength(50)]
    [Column("creater")]
    [Display(Name = "创建人")]
    public string creater { get; set; }

    [MaxLength(50)]
    [Column("updater")]
    [Display(Name = "更新人")]
    public string updater { get; set; }

    [Required]
    [Column("create_date")]
    [Display(Name = "创建时间")]
    [DisplayFormat(DataFormatString = "{0:yyyy年MM月dd日}")]
    public DateTime createDate { get; set; } = System.DateTime.Now;

    [Required]
    [Column("update_date")]
    [Display(Name = "更新时间")]
    [DisplayFormat(DataFormatString = "{0:yyyy年MM月dd日}")]
    public DateTime updateDate { get; set; } = System.DateTime.Now;
  }
}
