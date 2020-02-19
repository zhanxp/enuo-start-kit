# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
#from django.shortcuts import HttpResponse


# Create your views here.

def index(request):
    #return HttpResponse("Hello world!");
    return render(request,"home/index.html",)


def about(request):
    return render(request,"home/about.html",)
