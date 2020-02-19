import React, { Component } from 'react';
import Header from './header'
import Footer from './footer'

import { Redirect } from 'react-router-dom';

import Cookies from 'universal-cookie';
const cookie = new Cookies();

export default class Layout extends Component {
    // constructor(arg) {
    //     super(arg);
    // }

    componentWillMount = () => {
        this.setState({
            ticket:cookie.get('ticket'),
            name:cookie.get('name')
        });
    }    

    logout  = () => {
        cookie.remove('ticket');
        cookie.remove('name');
        this.setState({
            ticket:'',
            name:''
        });
    }

    render() {
        const { ticket } = this.state;
        if (!ticket) {
            //window.location = "/login?from=" + encodeURIComponent(window.location.hash);
            return <Redirect to='/login'/>;
        }
          
        const { name } = this.state;
        return (<div >
                <Header title="Home" userName={name} onLogout={this.logout} />
                <div className="container body-content">
                    {this.props.children}
                    <Footer  />
                </div>  
            </div>
        );
    }
}