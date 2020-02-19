import React from 'react';
import logo from './logo.svg';
import './index.css';

export default class Home extends React.Component {

  // constructor(props, context) {
  //   super(props, context);
  // }

  render() {
    return <p className = "App-intro" >
                <img src={ logo } className = "App-logo" alt = "logo" />
                To get started, edit <code> src/components/home/index.js </code> and save to reload. 
            </p>;
  }
}