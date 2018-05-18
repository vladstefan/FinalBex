import React from 'react';
import './index.css';
import { Router, Route, IndexRoute, hashHistory} from 'react-router'
import Dashboard from './modules/Dashboard'
import RouterContainer from './RouterContainer';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import ReactDOM from 'react-dom';

ReactDOM.render((
  <Router history={hashHistory}>
    <Route path="/" component={RouterContainer}>
      <IndexRoute component={App}/>
      {/* <Route path="/" component={App}/> */}
      <Route path="/dashboard" component={Dashboard}/>
    </Route>
  </Router>
), document.getElementById('root'))


// ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
