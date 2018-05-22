import React from 'react';
import './index.css';
import RouteApp from './AppComponents/RouteApp'
import registerServiceWorker from './registerServiceWorker';
import ReactDOM from 'react-dom';




ReactDOM.render((
  <RouteApp/>
), document.getElementById('root'))


// ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
