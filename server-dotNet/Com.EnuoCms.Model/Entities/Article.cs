using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Model.Entities
{
	[Table("article")]
    public class Article : BaseEntity
    {
		//[Key]
		//[Column("id")]
		//public int ID { get; set; }

        [Required]
        [MaxLength(200)]
        [Column("title")]
		[Display(Name = "名称")]
        public string Title { get; set; }

        [MaxLength(500)]
        [Column("intro")]
		[Display(Name = "简介")]
        public string Intro { get; set; }

        [MaxLength(500)]
		[Column("pic_url")]
		[Display(Name = "图片")]
        public string PicUrl { get; set; }

        [Required]
        [Column("category_id")]
		[Display(Name = "分类")]
        public int CategoryID { get; set; }

        [Column("content")]
		[Display(Name = "内容")]
        public string Content { get; set; }

    }
}
