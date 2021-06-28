using Enuo.Dotnet.Core.Model;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Enuo.Dotnet.Model
{
  [Table("article")]
  public class Article : BaseEntity
  {
    [MaxLength(200)]
    [Display(Name = "标题")]
    public string title { get; set; }

    [MaxLength(500)]
    [Column("intro")]
    [Display(Name = "简介")]
    public string intro { get; set; }

    [MaxLength(500)]
    [Column("pic_url")]
    [Display(Name = "图片")]
    public string picUrl { get; set; }

    [Required]
    [Column("category_id")]
    [Display(Name = "分类")]
    public int categoryID { get; set; }

    [Display(Name = "内容")]
    public string content { get; set; }
  }
}
