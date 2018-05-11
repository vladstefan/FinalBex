import React, { Component } from 'react';
import AppBar from 'material-ui/AppBar';
import darkBaseTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import SearchButton from './SearchButton';
import Login from './Login';
import DrawMenu from './DrawMenu';

const customStyles = {
    background: 'rgba(0,0,0,0)',
    borderWidth: '0',
    borderColor: 'transparent'

}

class AppFloatingBar extends Component {

    render() {
        return (
            <MuiThemeProvider muiTheme={getMuiTheme(darkBaseTheme)}>
                <AppBar className="app__bar"
                    style={customStyles}
                    children={
                        <div className="app__searchAndLogin">  
                            
                            <SearchButton/>
                            <Login/>
                        </div>
                    }
                    iconElementLeft={<DrawMenu items={this.props.items}/>}
                    



                />
            </MuiThemeProvider>
        );
    }

}



export default AppFloatingBar;