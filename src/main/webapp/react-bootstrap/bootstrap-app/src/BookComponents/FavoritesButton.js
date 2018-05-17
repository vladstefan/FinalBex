import React, { Component } from 'react';
import FlatButton from 'material-ui/FlatButton';
import StarBorder from 'material-ui/svg-icons/toggle/star-border'

export default class FavoritesButton extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isInFavorites: false
        }

        this.changeIcon = this.changeIcon.bind(this);
        this.handleToggle = this.handleToggle.bind(this);
    }



    handleToggle(value) {
        this.setState({ isInFavorites: !value });
    }

    sendDataToBE(value){

    }


    changeIcon() {
        if (this.isInFavorites) {

            return (<FlatButton className="bar__btn"
                eventKey={1}
                icon={<StarBorder color="#fff" />}
                onClick={this.handleToggle(this.isInFavorites)}
            />);

        } else {
            return (<FlatButton className="bar__btn"
                eventKey={1}
                icon={<StarBorder color="#524" />}
                onClick={this.handleToggle(this.isInFavorites)}
            />);
        }
    }

    render() {
        return (
            <div className="favorites">
                {/* {this.changeIcon()} */}
            </div>
        );
    }

}