import React from 'react';
import './index.css';
import { Router, Route, IndexRoute, hashHistory } from 'react-router'
import Dashboard from './DashboardComponent/Dashboard'
import RouterContainer from './AppComponents/RouterContainer';
import App from './AppComponents/App';
import Login from './AppComponents/Login';
import registerServiceWorker from './registerServiceWorker';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import ReactDOM from 'react-dom';

ReactDOM.render((
  <MuiThemeProvider>
    <Router history={hashHistory}>
      <Route path="/" component={RouterContainer}>
        <IndexRoute component={Login} />
        <Route path="/home" component={App}/>
        <Route path="/dashboard" component={Dashboard} />
      </Route>
    </Router>
  </MuiThemeProvider>
), document.getElementById('root'))


// ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
