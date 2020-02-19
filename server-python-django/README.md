virtualenv --python=python3.6 env

source env/bin/activate
deactivate

pip install django==1.8.3
django-admin.py startproject enuocms
python manage.py startapp article


python manage.py runserver 127.0.0.1:8000

python manage.py migrate

python manage.py makemigrations