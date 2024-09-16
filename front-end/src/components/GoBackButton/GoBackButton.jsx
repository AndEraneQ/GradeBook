import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { FaCircleArrowLeft } from "react-icons/fa6";
import './GoBackButton.css';

function GoBackButton() {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate(-1);
    }
    
    return(
        <div className="button-container">
            <button className="back-button" onClick={handleClick}>
                <FaCircleArrowLeft size={30}/>
            </button>  
        </div>
    );

}

export default GoBackButton;