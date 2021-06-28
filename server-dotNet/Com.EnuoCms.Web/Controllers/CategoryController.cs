using System;
using System.Collections.Generic;
using System.Data.Entity.Validation;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Com.EnuoCms.Core.Common;
using Com.EnuoCms.IService;
using Com.EnuoCms.Model.Entities;
using System.Linq.Expressions;

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
namespace Com.EnuoCms.Web.Controllers
{
  [RoutePrefix("category")]
  public class CategoryController : Controller
  {
    private readonly ICommandBus commandBus;
    private readonly ICategoryRepository categoryRepository;

    public CategoryController(ICommandBus commandBus,
      ICategoryRepository categoryRepository

      )
    {
      this.commandBus = commandBus;
      this.categoryRepository = categoryRepository;
    }


    [Route("")]
    public ActionResult Index(Category ent)
    {
      Expression<Func<Category, bool>> query = s => s.ID > 0;
      if (!string.IsNullOrEmpty(ent.Title))
      {
        Expression<Func<Category, bool>> query2 = s => s.Title.Contains(ent.Title);
        query = query.AndAlso(query2);
      }

      var list = categoryRepository.GetMany(query);
      return View(list);
    }

    [Route("details/{id}")]
    public ActionResult Details(int id)
    {
      var ent = categoryRepository.GetById(id);
      return View(ent);
    }

    [Route("create")]
    public ActionResult Create()
    {
      var ent = new Category();
      return View(ent);
    }


    [HttpPost]
    [Route("create")]
    public ActionResult Create(FormCollection collection, Category ent)
    {
      try
      {
        CategoryCreateOrUpdateCommand cmd = new CategoryCreateOrUpdateCommand(ent);
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
      var ent = categoryRepository.GetById(id);
      return View(ent);
    }


    [HttpPost]
    [Route("edit/{id}")]
    public ActionResult Edit(int id, FormCollection collection, Category ent)
    {
      try
      {
        // TODO: Add update logic here
        var oldent = categoryRepository.GetById(id);
        oldent.getPropertiesFrom(ent);
        oldent.ID = id;
        CategoryCreateOrUpdateCommand cmd = new CategoryCreateOrUpdateCommand(oldent);
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

    [Route("delete/{id}")]
    public ActionResult Delete(int id)
    {
      return View();
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
