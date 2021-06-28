using Com.EnuoCms.Core.Common;
using Com.EnuoCms.IService;


using Com.EnuoCms.Model.Entities;
using System;
using System.Collections.Generic;
using System.Data.Entity.Validation;
using System.Linq;
using System.Web;
using System.Web.Mvc;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Controllers
{
  [RoutePrefix("article")]
  public class ArticleController : Controller
  {
    private readonly ICommandBus commandBus;
    private readonly IArticleRepository articleRepository;
    private readonly ICategoryRepository categoryRepository;

    public ArticleController(ICommandBus commandBus,
        IArticleRepository articleRepository,
        ICategoryRepository categoryRepository

        )
    {
      this.commandBus = commandBus;
      this.categoryRepository = categoryRepository;
      this.articleRepository = articleRepository;
    }

    [Route("")]
    public ActionResult Index(int? pageIndex, int? pageSize)
    {
      var list = articleRepository.QueryPagedList(s => s.ID > 0,
                                                  pageIndex ?? 1,
                                                  pageSize ?? 10,
                                                  new OrderByExpression<Article, DateTime>(sim => sim.CreateDate, true));
      ViewData["pageIndex"] = list.pageIndex;
      ViewData["pageSize"] = list.pageSize;
      ViewData["total"] = list.total;
      return View(list.items);
    }

    [Route("details/{id}")]
    public ActionResult Details(int id)
    {
      var ent = articleRepository.GetById(id);
      return View(ent);
    }

    [Route("create")]
    public ActionResult Create()
    {
      var ent = new Article();
      return View(ent);
    }


    [HttpPost]
    [Route("create")]
    public ActionResult Create(FormCollection collection, Article ent)
    {
      try
      {
        ArticleCreateOrUpdateCommand cmd = new ArticleCreateOrUpdateCommand(ent);
        commandBus.Submit(cmd);

        return RedirectToAction("Index");
      }
      catch (DbEntityValidationException ex)
      {
        foreach (var item in ex.EntityValidationErrors)
        {
          foreach (var citem in item.ValidationErrors)
          {
            ModelState.AddModelError(citem.PropertyName, citem.ErrorMessage);
          }
        }
      }
      catch (Exception ex)
      {
        ModelState.AddModelError("", ex.Message);
      }
      return View(ent);
    }

    [Route("edit/{id}")]
    public ActionResult Edit(int id)
    {
      var oldent = articleRepository.GetById(id);
      return View(oldent);
    }


    [HttpPost]
    [Route("edit/{id}")]
    public ActionResult Edit(int id, FormCollection collection, Article ent)
    {
      try
      {
        var oldent = articleRepository.GetById(id);
        oldent.getPropertiesFrom(ent);
        oldent.ID = id;

        ArticleCreateOrUpdateCommand cmd = new ArticleCreateOrUpdateCommand(oldent);
        commandBus.Submit(cmd);
        return RedirectToAction("Index");
      }
      catch (DbEntityValidationException ex)
      {
        foreach (var item in ex.EntityValidationErrors)
        {
          foreach (var citem in item.ValidationErrors)
          {
            ModelState.AddModelError(citem.PropertyName, citem.ErrorMessage);
          }
        }
      }
      catch (Exception ex)
      {
        ModelState.AddModelError("", ex.Message);
      }
      return View(ent);
    }



    [HttpPost]
    [Route("delete/{id}")]
    public ActionResult Delete(int id, FormCollection collection)
    {
      try
      {
        // TODO: Add delete logic here
        return RedirectToAction("Index");
      }
      catch
      {
        return View();
      }
    }
  }
}
