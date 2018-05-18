import React, { Component } from 'react';
import ReactStars from 'react-stars';

class RatingStars extends Component {

    constructor(props) {
        super(props);

        this.state = {
            changedRating: 0,
            currentRating:  this.props.rating,
            wasNotRated: true
        }

        this.ratingChanged = this.ratingChanged.bind(this);

    }

    ratingChanged = (newRating) => {
        if (this.state.wasNotRated) {
            console.log(newRating);
            this.setState({ wasNotRated: false });
            this.setState({ changedRating: newRating });
            fetch('http://localhost:8090/ratings', {
                method: 'post',

                body: JSON.stringify(this.state.changedRating)
            }).then(response => {
                this.setState({ currentRating: response });
            });
        }
    }

    render() {
        return (
            <div>
                <ReactStars
                    count={5}
                    size={24}
                    color2={'#ffd700'}
                    value={this.state.currentRating}
                    edit={this.state.wasNotRated}
                    onChange={e => this.ratingChanged(e)}
                />

            </div>
        );
    }
}

export default RatingStars;