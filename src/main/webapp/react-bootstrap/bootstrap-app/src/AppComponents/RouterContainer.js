import React, {Component} from 'react';

export default class RouterContainer extends Component {

    
    render() {
        return (
            <div>
                {this.props.children}
            </div>
        );
    }
}