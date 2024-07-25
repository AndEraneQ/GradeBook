import React from 'react';
import './WelcomeHeader.css';
function WelcomeHeader({name}){

    return(
        <div className="header-container">
            <h1>Welcome {name}</h1>
        </div>
    );
}

export default WelcomeHeader