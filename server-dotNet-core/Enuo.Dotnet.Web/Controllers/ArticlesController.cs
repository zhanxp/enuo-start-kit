using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Enuo.Dotnet.Model;
using Enuo.Dotnet.Service;
using Microsoft.Extensions.Logging;
using Microsoft.AspNetCore.Authorization;

namespace Enuo.Dotnet.Web.Controllers
{
  [Authorize]
  public class ArticlesController : Controller
  {
    private readonly ArticleService _service;
    private readonly ILogger<ArticlesController> _logger;

    public ArticlesController(ArticleService service, ILogger<ArticlesController> logger)
    {
      this._service = service;
      this._logger = logger;
    }

    // GET: Articles
    public async Task<IActionResult> Index()
    {
      return View(await _service.List());
    }

    // GET: Articles/Details/5
    public async Task<IActionResult> Details(int? id)
    {
      if (id == null)
      {
        return NotFound();
      }

      var article = await _service.FindById(id.Value);
      if (article == null)
      {
        return NotFound();
      }

      return View(article);
    }

    // GET: Articles/Create
    public IActionResult Create()
    {
      return View();
    }

    // POST: Articles/Create
    // To protect from overposting attacks, enable the specific properties you want to bind to.
    // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create([Bind("title,intro,picUrl,categoryID,content,id,entTypes,entStatus,creater,updater,createDate,updateDate")] Article article)
    {
      if (ModelState.IsValid)
      {
        await _service.Insert(article);
        return RedirectToAction(nameof(Index));
      }
      return View(article);
    }

    // GET: Articles/Edit/5
    public async Task<IActionResult> Edit(int? id)
    {
      if (id == null)
      {
        return NotFound();
      }

      var article = await _service.FindById(id.Value);
      if (article == null)
      {
        return NotFound();
      }
      return View(article);
    }

    // POST: Articles/Edit/5
    // To protect from overposting attacks, enable the specific properties you want to bind to.
    // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Edit(int id, [Bind("title,intro,picUrl,categoryID,content,id,entTypes,entStatus,creater,updater,createDate,updateDate")] Article article)
    {
      if (id != article.id)
      {
        return NotFound();
      }

      if (ModelState.IsValid)
      {
        try
        {
          await _service.Update(article);
        }
        catch (DbUpdateConcurrencyException)
        {
          if (!ArticleExists(article.id))
          {
            return NotFound();
          }
          else
          {
            throw;
          }
        }
        return RedirectToAction(nameof(Index));
      }
      return View(article);
    }

    // GET: Articles/Delete/5
    public async Task<IActionResult> Delete(int? id)
    {
      if (id == null)
      {
        return NotFound();
      }

      var article = await _service.FindById(id.Value);
      if (article == null)
      {
        return NotFound();
      }

      return View(article);
    }

    // POST: Articles/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
      await _service.Delete(id);
      return RedirectToAction(nameof(Index));
    }

    private bool ArticleExists(int id)
    {
      return _service.Exists(id);
    }
  }
}
