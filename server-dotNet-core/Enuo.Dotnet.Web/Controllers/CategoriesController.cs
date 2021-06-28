using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Enuo.Dotnet.Model;
using Enuo.Dotnet.Service;
using Microsoft.AspNetCore.Authorization;

namespace Enuo.Dotnet.Web.Controllers
{
  [Authorize]
  public class CategoriesController : Controller
  {
    private readonly CategoryService _service;

    public CategoriesController(CategoryService service)
    {
      _service = service;
    }

    // GET: Categories
    public async Task<IActionResult> Index()
    {
      return View(await _service.List());
    }

    // GET: Categories/Details/5
    public async Task<IActionResult> Details(int? id)
    {
      if (id == null)
      {
        return NotFound();
      }

      var category = await _service.FindById(id.Value);
      if (category == null)
      {
        return NotFound();
      }

      return View(category);
    }

    // GET: Categories/Create
    public IActionResult Create()
    {
      return View();
    }

    // POST: Categories/Create
    // To protect from overposting attacks, enable the specific properties you want to bind to.
    // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create([Bind("title,id,entTypes,entStatus,creater,updater,createDate,updateDate")] Category category)
    {
      if (ModelState.IsValid)
      {
        await _service.Insert(category);
        return RedirectToAction(nameof(Index));
      }
      return View(category);
    }

    // GET: Categories/Edit/5
    public async Task<IActionResult> Edit(int? id)
    {
      if (id == null)
      {
        return NotFound();
      }

      var category = await _service.FindById(id.Value);
      if (category == null)
      {
        return NotFound();
      }
      return View(category);
    }

    // POST: Categories/Edit/5
    // To protect from overposting attacks, enable the specific properties you want to bind to.
    // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Edit(int id, [Bind("title,id,entTypes,entStatus,creater,updater,createDate,updateDate")] Category category)
    {
      if (id != category.id)
      {
        return NotFound();
      }

      if (ModelState.IsValid)
      {
        try
        {
          await _service.Update(category);
        }
        catch (DbUpdateConcurrencyException)
        {
          if (!CategoryExists(category.id))
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
      return View(category);
    }

    // GET: Categories/Delete/5
    public async Task<IActionResult> Delete(int? id)
    {
      if (id == null)
      {
        return NotFound();
      }

      var category = await _service.FindById(id.Value);
      if (category == null)
      {
        return NotFound();
      }

      return View(category);
    }

    // POST: Categories/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
      await _service.Delete(id);
      return RedirectToAction(nameof(Index));
    }

    private bool CategoryExists(int id)
    {
      return _service.Exists(id);
    }
  }
}
