import React from 'react';
import { Link } from 'react-router-dom';
//import Cookies from 'universal-cookie';
//const cookie = new Cookies();

export default class Header extends React.Component {
    // constructor(arg) {
    //     super(arg);
    // }

    // componentWillMount = ()=> {

    // }

    // logout = ()=> {
    //     cookie.remove('ticket');
    // }

    render() {

        let userInfo =  <ul className="nav navbar-nav navbar-right">
                            <li>
                                <Link className="loginLink" to="/login">登录</Link>
                            </li>
                        </ul>;

        if(this.props.userName){
            
            userInfo =  <ul className="nav navbar-nav navbar-right">
                            <li>
                                <Link to="/admin"> {this.props.userName} 你好！</Link>
                            </li>
                            <li>
                                <a onClick={this.props.onLogout} >注销</a>
                            </li>
                        </ul>;
        }

        return (
            <div className="navbar navbar-inverse navbar-fixed-top">
                    <div className="container">
                        <div className="navbar-header">
                            <button type="button" className="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span className="icon-bar"></span>
                                    <span className="icon-bar"></span>
                                    <span className="icon-bar"></span>
                                </button>
                            <a className="navbar-brand" href="/">
                                {this.props.title}
                            </a>
                        </div>
                        <div className="navbar-collapse collapse">
                            <ul className="nav navbar-nav">
                                <li>
                                    <Link to="/">主页</Link>
                                </li>
                                <li>
                                    <Link to="/category">分类</Link>
                                </li>
                                <li>
                                    <Link to="/article">文章</Link>
                                </li>
                                <li>
                                    <Link to="/about">关于</Link>
                                </li>
                            </ul>

                           {userInfo} 
                            
                        </div>
                    </div>
                </div>
        )
    }
}