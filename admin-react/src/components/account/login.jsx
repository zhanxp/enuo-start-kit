import React from 'react';
import { Redirect } from 'react-router-dom';
import axios from 'axios';
import { loginAction } from '../../service/accountService';
import Cookies from 'universal-cookie';
const cookie = new Cookies();

export default class Login extends React.Component {

  componentWillMount = () => {
        this.setState({
            ticket:cookie.get('ticket'),
            username:'',
            password:'',
            rememberMe:false
        });
    }

  login = (event) => {
        var self = this;
        loginAction(this.state.username,this.state.password)
            .then(function (response) {
                var res = response.data;
                if(res.success){
                    var ticket = res.data.ticket;
                    var expires = res.data.expires;
                    var name = res.data.name;
                    cookie.set('ticket',ticket);
                    cookie.set('name',name);
                    self.setState({
                        ticket:ticket
                    });
                }
            })
            .catch(function (error) {
                console.log(error);
            });

        event.preventDefault();
  }

  handleChange = (e) =>{
    var newState={};
    var name = e.target.name;
    var val = e.target.name==="rememberMe"?e.target.checked:e.target.value;
    newState[name] = val;
    this.setState(newState);
  }

  render() {
    const { ticket } = this.state;
    if (ticket) {
        return <Redirect to='/'/>;
    }

    var { username } = this.state;
    var { password } = this.state;
    var { rememberMe } = this.state;

    return (<div className="row">
                <div className="col-md-4 col-md-offset-4">
                    <div className="panel panel-default">
                        <div className="panel-heading">登录</div>
                        <div className="panel-body">
                            <form onSubmit={this.login} method="post" id="loginForm">
                                <div className="form-group">
                                    <label htmlFor="username">UserName</label>
                                    <input type="text" className="form-control" value={username} onChange={this.handleChange} name="username" placeholder="username" />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password">Password</label>
                                    <input type="password" className="form-control" value={password} onChange={this.handleChange} name="password" placeholder="password" />
                                </div>
                                <div className="form-group">
                                    <label>
                                       <input type="checkbox" value="true" checked={rememberMe} onChange={this.handleChange} name="rememberMe" /> 记住我
                                    </label>
                                </div>

                                <button type="submit"  className="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>);
  }
}