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


    render() {
        return (
            <div className="favorites">
                <FlatButton className="bar__btn"
                    eventKey={1}
                    icon={<StarBorder color="#fff" />}
                    onClick={this.handleToggle(this.isInFavorites)}
                />
            </div>
        );
    }

}