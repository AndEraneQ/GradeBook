import React, { useState } from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import './RegisterPage.css';

function RegisterPage(){

    const [role,setRole] = useState("STUDENT");

    return(
        <div className="register-container">
            <GoBackButton path='/users'/>
            <div className="register-page">
                <div className="header-container">
                    <h1>Type user data</h1>
                </div>
                <input type="text" placeholder="First Name:"/>
                <input type="text" placeholder="Last Name:"/>
                <input type="text" placeholder="Email:"/>
                <select id="options" value={role} onChange={(e) => setRole(e.target.value)}>
                    <option value="STUDENT">STUDENT</option>
                    <option value="TEACHER">TEACHER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
                {role==="STUDENT" && (
                    <input></input>
                )}
                <input type="text" placeholder="City:"/>
                <input type="text" placeholder="Street:"/>
                <input type="text" placeholder="Apartment Number:"/>
                <input type="text" placeholder="Building Number:"/>
            </div>
        </div>
    );
}

export default RegisterPage;