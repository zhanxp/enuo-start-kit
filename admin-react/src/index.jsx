import React from 'react';
import ReactDOM from 'react-dom';
import registerServiceWorker from './registerServiceWorker';
// import { Router, Route, Link } from 'react-router'

import {
  BrowserRouter as Router,
  Route,
  Switch
} from 'react-router-dom';

import Layout from './components/shared/layout'
import Home from './components/home/index'
import About from './components/home/about'

import Article from './components/article/index'
import ArticleEdit from './components/article/edit'
import ArticleDetails from './components/article/details'
import ArticleAdd from './components/article/add'

import Category from './components/category/index'

import Login from './components/account/login'

// import Cookies from 'universal-cookie';
// const cookie = new Cookies();

// var ticket = cookie.get('ticket');
// if (!ticket) {
//   window.location = "/login?from=" + encodeURIComponent(window.location.hash);
// }

ReactDOM.render(
  <Router>
    <Switch>
      <Route path="/login" component={Login} />
      <Layout>
        <Switch>
          <Route exact path="/" component={Home}/>
          <Route path="/about" component={About}/>
          <Route path="/article" component={Article} />
          <Route path = "/article/details/:id" component={ ArticleDetails } />
          <Route path = "/article/edit/:id" component = { ArticleEdit } />
          <Route path="/article/add" component={ArticleAdd}/>
          <Route path="/category" component={Category}/>
        </Switch>
      </Layout>
    </Switch>
  </Router>,
  document.getElementById('root')
);

registerServiceWorker();
