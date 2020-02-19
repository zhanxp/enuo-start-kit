from django.conf.urls import include, url
from category import views

urlpatterns = [
    #url(r'^page/(?P<page>\d+)/(?P<number>\d+)', views.page),
    url(r'^$', views.index),
    #url(r'^about$', views.about),
]