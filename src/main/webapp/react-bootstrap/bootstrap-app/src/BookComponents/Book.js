import React, { Component } from 'react';
import RatingStars from './RatingStars';
import RentBook from './RentButton';
import { Card,  CardMedia} from 'material-ui/Card';
import FavoritesButton from './FavoritesButton';

class Book extends Component {

    render() {
        return (
            <div className="card">
                <div >
                    <Card 
                        style={{
                            width: "200px",
                            height: "300px"
                        }}
                    >
                        <CardMedia
                            // overlay={<FavoritesButton/>}
                            overlayContainerStyle={{height:"40px"}}
                        >
                            <img className="book__img" src={"https://s3.eu-central-1.amazonaws.com/com.db.bexlib.images/id" + this.props.id + ".jpeg"} alt="ceva" />
                        </CardMedia>
                    </Card>
                </div>
                <div>
                    <div>
                        <h2 className="book__title">{this.props.title}</h2>
                    </div>
                    <div>
                        <RatingStars rating={this.props.rating} />
                        <div className="rent__button">
                            <RentBook title={this.props.title} />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Book;
