from django.shortcuts import render
from django.shortcuts import HttpResponse
from category import models

# Create your views here.

def index(request):
    #return HttpResponse("Hello world!");
    category_list = models.Category.objects.all()
    return render(request,"category/index.html",{"list":category_list})