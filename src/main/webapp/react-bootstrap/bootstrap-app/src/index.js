import React from 'react';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import ReactDOM from 'react-dom';
import Dashboard from './Dashboard'

ReactDOM.render(<App />, document.getElementById('root'));
// ReactDOM.render(<Dashboard />, document.getElementById('dash'));
registerServiceWorker();
