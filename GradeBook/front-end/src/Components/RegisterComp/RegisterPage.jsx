import React from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import './RegisterPage.css';

function RegisterPage(){
    return(
        <div className="register-container">
            <GoBackButton path='/users'/>
            <div className="register-page">
                <input type="text" placeholder="First Name:"/>
                <input type="text" placeholder="Last Name:"/>
                <input type="text" placeholder="Email:"/>
                <input type="text" placeholder="City:"/>
                <input type="text" placeholder="Street:"/>
                <input type="text" placeholder="Apartment Number:"/>
                <input type="text" placeholder="Building Number:"/>
            </div>
        </div>
    );
}

export default RegisterPage;