import React from "react";
import { useNavigate } from "react-router-dom";
import { FaCircleArrowLeft } from "react-icons/fa6";
import styles from './GoBackButton.module.css';

function GoBackButton() {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate(-1);
    }

    return (
        <div className={styles.buttonContainer}>
            <button className={styles.backButton} onClick={handleClick}> 
                <FaCircleArrowLeft size={30}/>
                <span><b>Go back</b></span>
            </button>
        </div>
    );
}

export default GoBackButton;
