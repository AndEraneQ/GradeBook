import React from "react";
import './Card.css';

function Card({imagePath, text, onClick}){
    return (
        <div className="card-container" onClick={onClick}>
            <img className="card-pictue" src={imagePath} alt="Picture"/>
            <p className="card-text">{text}</p>
        </div>
    );
}

export default Card;
