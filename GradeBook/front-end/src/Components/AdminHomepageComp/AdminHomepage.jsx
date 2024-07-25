import React from "react";
import WelcomeHeader from "../WelcomeHeaderComp/WelcomeHeader";
import Card from "../CardComp/Card";
import { useNavigate } from 'react-router-dom';
import AuthService from "../../Services/AuthService";

function AdminHomepage() {
    const navigate = useNavigate();
    const userDataString = localStorage.getItem('user_data');
    const parsedUserData = userDataString ? JSON.parse(userDataString) : null;
    const name = parsedUserData
        ? `${parsedUserData.firstName || ''} ${parsedUserData.lastName || ''}`
        : 'User';

    function handleLogout(){
        AuthService.logout(navigate);
    }

    return (
        <div className="admin-homepage-container">
            <WelcomeHeader name={name} />
            <div className="cards">
                <Card imagePath="src/assets/manageClasses.png" text="Manage Class"/>
                <Card imagePath="src/assets/managePersons.png" text="Manage Persons"/>
                <Card imagePath="src/assets/manageSubjects.png" text="Manage Subjects"/>
                <Card imagePath="src/assets/checkYourData.png" text="Check Your Data"/>
                 <Card imagePath="src/assets/Logout.png" text="Logout" onClick={handleLogout}/>
            </div>

        </div>
    );
}

export default AdminHomepage;