import React, { Component } from 'react';
import { Router, Route, IndexRoute, hashHistory, Redirect } from 'react-router'
import Dashboard from '../DashboardComponent/Dashboard'
import RouterContainer from './RouterContainer';
import App from './App';
import Login from './Login';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

export default class RouteApp extends Component {
    constructor(props) {
        super(props);

        this.state =  {
            loggedIn: false
        }
    }

    loggedIn = (nextState, replace, callback) => {
        fetch('http://localhost:8090/permissions', {
            method: 'get',
            credentials: 'include',
        })
        .then(response =>  {
            if (response.status === 200) {
                this.setState({loggedIn: true}, callback);
            } else {
                if (nextState.location.pathname !== "/login") {
                    replace('/login');
                }
                this.setState({loggedIn: false}, callback);
            }
        })
    }

    isLoggedIn = (prevState, nextState, replace, callback) => {
        if (this.state.loggedIn && nextState.location.pathname.indexOf('login') >= 0) {
            replace('/');
        }
        callback();
    }

    render() {
        return (
            <MuiThemeProvider>
                <Router history={hashHistory}>
                    <Route path="/" component={RouterContainer} onChange={this.isLoggedIn} onEnter={this.loggedIn}>
                        <IndexRoute component={App}/>
                        <Route path="/login" component={Login}/>
                        <Route path="/dashboard" component={Dashboard} />
                    </Route>
                </Router>
            </MuiThemeProvider>
        );
    }

}