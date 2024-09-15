import React from "react";
import './Header.css';

function Header({text}){
    return(
        <div className="header-container">
            <h1 className="header-text">{text}</h1>
        </div>
    );
}

export default Header;