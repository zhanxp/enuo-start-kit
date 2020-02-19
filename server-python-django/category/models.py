from django.db import models

# Create your models here.
class Category(models.Model):
    id = models.IntegerField(primary_key=True)
    title = models.CharField(max_length=45)
    ent_types = models.IntegerField()
    ent_status = models.IntegerField()
    creater = models.IntegerField()
    updater = models.IntegerField()
    create_date = models.DateTimeField()
    update_date = models.DateTimeField()