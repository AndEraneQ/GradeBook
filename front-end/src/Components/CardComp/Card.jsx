import React from "react";
import './Card.css';

function Card({imagePath, text, onClick}){
    return (
        <div className="card-container" onClick={onClick}>
            <img src={imagePath} alt="Picture"/>
            <p>{text}</p>
        </div>
    );
}

export default Card;
